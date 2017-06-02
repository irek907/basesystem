package com.tangzongyun.basesystem.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tangzongyun.basesystem.sys.domain.UserRoleMap;

public interface UserRoleRepository extends JpaRepository<UserRoleMap,Long>{
	List<UserRoleMap> findURMapByUserId(@Param("userId") Long userId);
}
