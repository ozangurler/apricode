package com.apricode.omby.dao;

import com.apricode.omby.domain.Role;


public interface RoleDao extends Dao<Role, Long>{
	
	Role findByName(String name);

}
