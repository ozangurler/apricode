package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.UserAction;

@Repository(value="userActionDao")
public class JpaUserActionDao extends JpaDao<UserAction, Long> implements UserActionDao
{

	
	public JpaUserActionDao() {
		super(UserAction.class);
	}

	public JpaUserActionDao(Class<UserAction> entityClass) {
		super(entityClass);
	}

}
