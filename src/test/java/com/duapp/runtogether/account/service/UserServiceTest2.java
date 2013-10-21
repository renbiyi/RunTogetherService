package com.duapp.runtogether.account.service;

import org.apache.log4j.Logger;
import org.junit.Test;

public class UserServiceTest2 {

	private static Logger log = Logger.getLogger(UserServiceTest2.class);
	

	@Test
	public void it() {
		int a = 32;
		int a1 = a >> 16;
		int a2 = a / (2^32);
		log.info(a1);
		log.info(a2);
	}
}
