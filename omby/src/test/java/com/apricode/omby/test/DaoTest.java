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
			

	// Insert a user with a defendant role
	public void testCreateUserWithDefendantRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithDefendantRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role defendantRole = new Role("DEFENDANT");
		this.roleDao.save(defendantRole);
		
		createdUser.addRole(defendantRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}
				
	
	
	
	
	// Insert a user with a follower role
	public void testCreateUserWithFollowerRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithFollowerRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role followerRole = new Role("FOLLOWER");
		this.roleDao.save(followerRole);
		
		createdUser.addRole(followerRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}	
	
	
	
	
	// Insert a user with a attorney role
	public void testCreateUserWithAttorneyRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithAttorneyRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role attorneyRole = new Role("ATTORNEY");
		this.roleDao.save(attorneyRole);
		
		createdUser.addRole(attorneyRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}	
		
	
	
	
	// Insert a user with a judge role
	public void testCreateUserWithJudgeRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithJudgeRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role judgeRole = new Role("JUDGE");
		this.roleDao.save(judgeRole);
		
		createdUser.addRole(judgeRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}		
	
	
	// Insert a user with a jury role
	public void testCreateUserWithJuryRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithJuryRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role juryRole = new Role("JURY");
		this.roleDao.save(juryRole);
		
		createdUser.addRole(juryRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}		
	
	
	
	// Insert a user with a prosecutor role
	public void testCreateUserWithProsecutorRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithProsecutorRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role prosecutorRole = new Role("PROSECUTOR");
		this.roleDao.save(prosecutorRole);
		
		createdUser.addRole(prosecutorRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}		
	
	
	// Insert a user with a witness role
	public void testCreateUserWithWitnessRole() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithWitnessRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role witnessRole = new Role("WITNESS");
		this.roleDao.save(witnessRole);
		
		createdUser.addRole(witnessRole.getName());
		
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}			
	
	
	
	
	
	// Try to insert a user with more than one role in one lawsuit
	public void testCreateUserWithMoreThanOneRoleInOneLawsuit() {
		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithWitnessRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);	
		
		Role witnessRole = new Role("WITNESS");
		this.roleDao.save(witnessRole);
		
		Role attorneyRole = new Role("ATTORNEY");
		this.roleDao.save(attorneyRole);
		
		
		createdUser.addRole(witnessRole.getName());
		createdUser.addRole(attorneyRole.getName());
		
		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
	}			
		
	
	
	
	
	
	
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
