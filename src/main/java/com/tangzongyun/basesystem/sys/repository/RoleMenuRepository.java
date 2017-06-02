package com.tangzongyun.basesystem.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tangzongyun.basesystem.sys.domain.RoleMenuMap;

public interface RoleMenuRepository extends JpaRepository<RoleMenuMap,Long>{
	
	public List<RoleMenuMap> findRoleMenuMapByRoleId(@Param("roleId") Long roleId);

}
