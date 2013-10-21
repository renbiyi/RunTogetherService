package com.duapp.runtogether.account.service;

import com.duapp.runtogether.account.bo.SysUser;

public interface UserService {

	SysUser findUserByUsername(String username);

	Long save(SysUser user);

	void addUserFromPassport(Long uid, String username);
}
