package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.Role;

@Repository(value="roleDao")
public class JpaRoleDao extends JpaDao<Role, Long> implements RoleDao
{

	
	public JpaRoleDao() {
		super(Role.class);
	}

	public JpaRoleDao(Class<Role> entityClass) {
		super(entityClass);
	}

}
