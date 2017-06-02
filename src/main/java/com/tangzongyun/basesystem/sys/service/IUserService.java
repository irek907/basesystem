package com.tangzongyun.basesystem.sys.service;

import java.util.Set;

public interface IUserService {
	
	public void test();
	
	public Set<String> getRolesByLoginName(String loginName);
	public Set<String> getPermissionByLoginName(String loginName);

}
