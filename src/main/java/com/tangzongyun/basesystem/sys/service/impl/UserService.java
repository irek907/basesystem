package com.tangzongyun.basesystem.sys.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tangzongyun.basesystem.sys.domain.Menu;
import com.tangzongyun.basesystem.sys.domain.Role;
import com.tangzongyun.basesystem.sys.domain.RoleMenuMap;
import com.tangzongyun.basesystem.sys.domain.User;
import com.tangzongyun.basesystem.sys.domain.UserRoleMap;
import com.tangzongyun.basesystem.sys.repository.MenuRepository;
import com.tangzongyun.basesystem.sys.repository.RoleMenuRepository;
import com.tangzongyun.basesystem.sys.repository.RoleRepository;
import com.tangzongyun.basesystem.sys.repository.UserRepository;
import com.tangzongyun.basesystem.sys.repository.UserRoleRepository;
import com.tangzongyun.basesystem.sys.service.IUserService;


@Service("userService")
public class UserService implements IUserService{
	@Autowired
//	@Resource
	MenuRepository menuRepository;
	@Autowired
//	@Resource
	RoleRepository roleRepository;
	@Autowired
//	@Resource
	UserRepository userRepository;
	@Autowired
//	@Resource(name="roleMenuRepository")
	RoleMenuRepository rmRepository;
	@Autowired
//	@Resource(name="userRoleRepository")
	UserRoleRepository urRepository;
	
	
	@Override
	//@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public void test() {
		// TODO Auto-generated method stub
		//   pr.updatePersonEmail(1L, "XX@QQ.COM");
	}

	@Override
	public Set<String> getRolesByLoginName(String loginName) {
		Set<String> roleSet = new HashSet<String>();
		User user = userRepository.findByLoginName(loginName);
		List<UserRoleMap> urList = urRepository.findURMapByUserId(user.getId());
		for(UserRoleMap urMap:urList){
			Role role = roleRepository.findOne(urMap.getRoleId());
			roleSet.add(role.getRoleName());
		}
		// TODO Auto-generated method stub
		return roleSet;
	}

	@Override
	public Set<String> getPermissionByLoginName(String loginName) {
		// TODO Auto-generated method stub
		Set<String> menuSet = new HashSet<String>();
		User user = userRepository.findByLoginName(loginName);
		List<UserRoleMap> urList = urRepository.findURMapByUserId(user.getId());
		for(UserRoleMap urMap:urList){
			Role role = roleRepository.findOne(urMap.getRoleId());
			//查找对应的菜单
			List<RoleMenuMap> rmMapList =  rmRepository.findRoleMenuMapByRoleId(role.getId());
			for(RoleMenuMap rmMap:rmMapList){
				Menu menu = menuRepository.findOne(rmMap.getMenuId());
				menuSet.add(menu.getMenuName());
			}
		}
		
		return menuSet;
	}

}
