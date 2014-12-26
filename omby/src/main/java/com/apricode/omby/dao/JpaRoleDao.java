package com.apricode.omby.dao;

import com.apricode.omby.domain.Role;

public class JpaRoleDao extends JpaDao<Role, Long> implements RoleDao
{

	public JpaRoleDao(Class<Role> entityClass) {
		super(entityClass);
	}

}
