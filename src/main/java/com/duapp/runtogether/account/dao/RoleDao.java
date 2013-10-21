package com.duapp.runtogether.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duapp.runtogether.account.bo.SysRole;

public interface RoleDao {
	
	public List<SysRole> findRolesByUserId(@Param("userId") Long userId);

	public SysRole findRoleByName(@Param("roleName") String roleName);

}
