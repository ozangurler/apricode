package com.apricode.omby.test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context.xml")
public class SetupTest {	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Test cases for omby dao operations
		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("@Before each test");

//     We do not clean before each test since this is setup
		List <User> ulist = this.userDao.findAll();		
		for (User aUser : ulist){
			this.userDao.delete(aUser.getId());
		}
		
		List <Role> rlist = this.roleDao.findAll();		
		for (Role aRole : rlist){
			this.roleDao.delete(aRole.getId());
		}
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After each test");
	}

	
	// We will have only one test to setup
	@Test
	public void testCreateUserWithSuerRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithSuerRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role suerRole = new Role("SUER");
		this.roleDao.save(suerRole);
		
		createdUser.addRole(suerRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}


}
