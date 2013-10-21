package com.duapp.runtogether.account.dao;

import java.util.List;

import com.duapp.runtogether.account.bo.SysResource;

public interface ResourceDao {

	List<SysResource> findAllResources();

}
