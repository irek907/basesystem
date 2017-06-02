package com.tangzongyun.basesystem.realm;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tangzongyun.basesystem.sys.domain.User;
import com.tangzongyun.basesystem.sys.repository.UserRepository;
import com.tangzongyun.basesystem.sys.service.IRoleService;
import com.tangzongyun.basesystem.sys.service.IUserService;


public class MyRealm extends AuthorizingRealm{
		@Autowired
		IRoleService roleService;
	    @Autowired
		//@Resource
		UserRepository userRepository;
	    @Autowired
		//@Resource
	  	IUserService userService;
	  /**
	     * 为当前登录的用户授予角色和权限
	     * @param principalCollection
	     * @return
	     */
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	        // 获取需要授权的用户名
	        String userName = (String) principalCollection.getPrimaryPrincipal();
	        // 创建授权对象
	        SimpleAuthorizationInfo authzInfo = new SimpleAuthorizationInfo();
	        // 查询当前用户名角色并授权 
	        authzInfo.setRoles(this.userService.getRolesByLoginName(userName));
	        // 查询当前用户名权限并授权
	        authzInfo.setStringPermissions(this.userService.getPermissionByLoginName(userName));
	        return authzInfo;
	    }

	    /**
	     * 验证当前登录的用户
	     * @param authenticationToken
	     * @return
	     * @throws AuthenticationException
	     */
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
	        // 获取需要验证的用户名
	        String userName = (String) authenticationToken.getPrincipal();
	        // 查询数据库中是否存在该用户名
	        User user = userRepository.findByLoginName(userName);
	        if (user != null) {
	            // 存在
	            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), "xx");
	            return authcInfo;
	        }
	        // 不存在
	        return null;
	    }
}
