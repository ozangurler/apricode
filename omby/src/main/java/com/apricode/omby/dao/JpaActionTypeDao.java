package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.ActionType;

@Repository(value="actionTypeDao")
public class JpaActionTypeDao extends JpaDao<ActionType, Long> implements ActionTypeDao
{

	
	public JpaActionTypeDao() {
		super(ActionType.class);
	}

	public JpaActionTypeDao(Class<ActionType> entityClass) {
		super(entityClass);
	}

}
