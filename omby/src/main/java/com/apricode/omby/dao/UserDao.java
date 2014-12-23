package com.apricode.omby.dao;

import com.apricode.omby.dao.Dao;
import com.apricode.omby.domain.User;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDao extends Dao<User, Long>, UserDetailsService
{

	User findByName(String name);

}