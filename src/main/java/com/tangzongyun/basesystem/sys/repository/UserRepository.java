package com.tangzongyun.basesystem.sys.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tangzongyun.basesystem.sys.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByLoginName(@Param("loginName")String loginName);

}
