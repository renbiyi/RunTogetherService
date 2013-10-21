package com.duapp.runtogether.account.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.duapp.runtogether.account.bo.SysUser;
import com.duapp.runtogether.account.service.UserService;
import com.duapp.runtogether.test.util.SpringTransactionalTestCase;

public class UserServiceTest extends SpringTransactionalTestCase {

	private static Logger log = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserService userService;
	
	@Test
	public void itShouldFindUser() {
		String username = "zhanghao_py";
		SysUser user = userService.findUserByUsername(username);
		log.info(user.getId());
	}
	
	@Test
	public void it() {
		int a = 32;
		a = a >> 32;
		log.info(a);
	}
}
