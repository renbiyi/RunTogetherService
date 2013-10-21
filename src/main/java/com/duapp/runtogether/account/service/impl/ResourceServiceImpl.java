package com.duapp.runtogether.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.duapp.runtogether.account.bo.SysResource;
import com.duapp.runtogether.account.dao.ResourceDao;
import com.duapp.runtogether.account.service.ResourceService;

@Component
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public List<SysResource> findAllResources() {
		return resourceDao.findAllResources();
	}

}
