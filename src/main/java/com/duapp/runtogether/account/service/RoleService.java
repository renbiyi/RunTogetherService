package com.duapp.runtogether.account.service;

import java.util.List;

import com.duapp.runtogether.account.bo.SysRole;
import com.duapp.runtogether.account.bo.SysUser;

public interface RoleService {

	List<SysRole> findRolesByUser(SysUser user);
	
	SysRole findRoleByName(String roleName);

	void addRole4User(String roleName, SysUser user);
}
