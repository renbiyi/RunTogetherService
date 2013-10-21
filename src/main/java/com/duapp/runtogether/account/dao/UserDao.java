package com.duapp.runtogether.account.dao;

import com.duapp.runtogether.account.bo.SysUser;

public interface UserDao {

	SysUser findUserByUsername(String username);

	void save(SysUser user);

	void update(SysUser user);

}
