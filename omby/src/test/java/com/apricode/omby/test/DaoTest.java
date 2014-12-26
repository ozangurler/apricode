package com.apricode.omby.test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apricode.omby.dao.UserDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;

public class DaoTest {
	private UserDao userDao;
	private RoleDao roleDao;


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




	
	
	// Insert a user without a role 
	@Test
	public void testCreateUserWithoutRoles() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithoutRoles starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		this.userDao.save(createdUser);	
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
	}
		
	
	
	
	
	// Insert a user with a suer role
	@Test
	public void testCreateUserWithSuerRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithoutRoles starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role suerRole = new Role("SUER");
		this.roleDao.save(suerRole);
		
		createdUser.addRole(suerRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}
			
	
	
	
	
	
	// Insert a user with a defendant role
	// Insert a user with a follower role
	// Insert a user with a attorney role
	// Insert a user with a judge role
	// Insert a user with a jury role
	// Insert a user with a prosecutor role
	// Insert a user with a witness role
	// Try to insert a user with more than one role in one lawsuit
	// Insert a lawsuit
	// Insert many users to one lawsuit
	// Try to insert more than one judge to one lawsuit
	// Insert one user to more than one lawsuit
	// Create a user with more than one role
	// Make Judge decide
	// Try to make an attorney to decide
	// Make a jury vote
	// Try to make a defendant vote
	// Calculate jury role points for a user after successful decision
	// Calculate jury role points for a user after failed decision
	// Make a defendant comment
	// Make a prosecutor ask question
	// Check if user names are opaque to lawsuit
	// Check points of a user for each role after lawsuit calculation
	// Create a public lawsuit
	// Create a private lawsuit
	// Try join a private lawsuit
	// Join to a public lawsuit
	// Create a lawsuit with more than one jury 
	// Create a lawsuit with many followers
	// Create a lawsuit with many attorneys
	// Create all of the roles 
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
