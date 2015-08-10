package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.ActionType;
import com.apricode.omby.domain.OptVal;

@Repository(value="optValDao")
public class JpaOptValDao extends JpaDao<OptVal, Long> implements OptValDao
{

	
	public JpaOptValDao() {
		super(OptVal.class);
	}

	public JpaOptValDao(Class<OptVal> entityClass) {
		super(entityClass);
	}

}
