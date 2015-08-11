package com.apricode.omby.test;
//  Repository https://github.com/ozangurler/apricode.git
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apricode.omby.dao.ActionTypeDao;
import com.apricode.omby.dao.LawsuitDao;
import com.apricode.omby.dao.OptValDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.dao.UserActionDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.domain.ActionType;
import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.OmbyRuleException;
import com.apricode.omby.domain.OptVal;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;
import com.apricode.omby.domain.UserAction;
import com.apricode.omby.domain.UserLawsuit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ActionTypeDao actionTypeDao;
	@Autowired
	private OptValDao optValDao;
	@Autowired
	private LawsuitDao lawsuitDao;
	@Autowired
	private UserActionDao userActionDao;

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

		List<UserAction> ualist = this.userActionDao.findAll();
		for (UserAction aUserAction : ualist) {
			this.userActionDao.delete(aUserAction.getId());
		}
		
		List<User> ulist = this.userDao.findAll();
		for (User aUser : ulist) {
			this.userDao.delete(aUser.getId());
		}		
		
		List<Lawsuit> llist = this.lawsuitDao.findAll();
		for (Lawsuit aLawsuit : llist) {
			this.lawsuitDao.delete(aLawsuit.getId());
		}
	
		List<Role> rlist = this.roleDao.findAll();
		for (Role aRole : rlist) {
			this.roleDao.delete(aRole.getId());
		}
		
		List<OptVal> ovlist = this.optValDao.findAll();
		for (OptVal anOptVal : ovlist) {
			this.optValDao.delete(anOptVal.getId());
		}
		



		
		List<ActionType> atlist = this.actionTypeDao.findAll();
		for (ActionType anActionType : atlist) {
			this.actionTypeDao.delete(anActionType.getId());
		}
		

		

		

		

		



		

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After each test");

//		List<Lawsuit> llist = this.lawsuitDao.findAll();
//		for (Lawsuit aLawsuit : llist) {
//			this.lawsuitDao.delete(aLawsuit.getId());
//		}
//
//		List<Role> rlist = this.roleDao.findAll();
//		for (Role aRole : rlist) {
//			this.roleDao.delete(aRole.getId());
//		}
//
//		List<User> ulist = this.userDao.findAll();
//		for (User aUser : ulist) {
//			this.userDao.delete(aUser.getId());
//		}

	}

	// Insert a user without a role
	@Test
	public void testCreateUserWithoutRoles() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithoutRoles starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
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
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role suerRole = new Role(Role.SUER);
		this.roleDao.save(suerRole);

		createdUser.addRole(suerRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a defendant role
	@Test
	public void testCreateUserWithDefendantRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithDefendantRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role defendantRole = new Role(Role.DEFENDANT);
		this.roleDao.save(defendantRole);

		createdUser.addRole(defendantRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a follower role
	@Test
	public void testCreateUserWithFollowerRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithFollowerRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role followerRole = new Role(Role.FOLLOWER);
		this.roleDao.save(followerRole);

		createdUser.addRole(followerRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a attorney role
	@Test
	public void testCreateUserWithAttorneyRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithAttorneyRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role attorneyRole = new Role(Role.ATTORNEY);
		this.roleDao.save(attorneyRole);

		createdUser.addRole(attorneyRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a judge role
	@Test
	public void testCreateUserWithJudgeRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithJudgeRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role judgeRole = new Role(Role.JUDGE);
		this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a jury role
	@Test
	public void testCreateUserWithJuryRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithJuryRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role juryRole = new Role(Role.JURY);
		this.roleDao.save(juryRole);

		createdUser.addRole(juryRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a prosecutor role
	@Test
	public void testCreateUserWithProsecutorRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithProsecutorRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role prosecutorRole = new Role(Role.PROSECUTOR);
		this.roleDao.save(prosecutorRole);

		createdUser.addRole(prosecutorRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Insert a user with a witness role
	@Test
	public void testCreateUserWithWitnessRole() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithWitnessRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role witnessRole = new Role(Role.WITNESS);
		this.roleDao.save(witnessRole);

		createdUser.addRole(witnessRole);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}



	// Insert a lawsuit
	@Test
	public void testCreateLawsuit() {
		// fixed lazy initialization problem
		// http://stackoverflow.com/questions/26783852/keeping-the-session-open-in-junit-jpa-hibernate-struts-and-spring-integration-te
		// I used EAGER for users lawsuits and fixes the problem it takes all
		// lawsuits of user at once

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithWitnessRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));

		Role suerRole = new Role(Role.SUER);
		createdUser.addRole(suerRole);
		suerRole = this.roleDao.save(suerRole);

		createdUser = this.userDao.save(createdUser);


		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit,suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			assert (readLawsuitFromDB.getLawsuit().getName()
					.equals(lawsuitName));
		}

	}
	
	
	

	// Insert many users to one lawsuit
	@Test
	public void testCreateLawsuitWithManyUsers() {
		System.out.println("DAO testCreateLawsuitWithManyUsers starterted");

		// create user
		String userName = "ozangurler@hotmail.com";
		User createdUser1 = new User(userName,
				this.passwordEncoder.encode(userName));

		// create user2
		String userName2 = "yilmazgorali@hotmail.com";
		User createdUser2 = new User(userName2,
				this.passwordEncoder.encode(userName2));

		// create roles
		Role suerRole = new Role(Role.SUER);
		suerRole = this.roleDao.save(suerRole);

		Role defendantRole = new Role(Role.DEFENDANT);
		defendantRole = this.roleDao.save(defendantRole);

		Role prosecutorRole = new Role(Role.PROSECUTOR);
		prosecutorRole = this.roleDao.save(prosecutorRole);

		Role attorneyRole = new Role(Role.ATTORNEY);
		attorneyRole = this.roleDao.save(attorneyRole);

		Role judgeRole = new Role(Role.JUDGE);
		judgeRole = this.roleDao.save(judgeRole);

		Role juryRole = new Role(Role.JURY);
		juryRole = this.roleDao.save(juryRole);

		Role followerRole = new Role(Role.FOLLOWER);
		followerRole = this.roleDao.save(followerRole);

		Role witnessRole = new Role(Role.WITNESS);
		witnessRole = this.roleDao.save(witnessRole);

		// bind role to the user
		createdUser1.addRole(suerRole);
		createdUser1.addRole(prosecutorRole);

		createdUser2.addRole(witnessRole);

		// save user
		createdUser1 = this.userDao.save(createdUser1);
		createdUser2 = this.userDao.save(createdUser2);

		// create law suit
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		// join user to lawsuit as SUER
		try {
			createdUser1.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		// persist user with lawsuit
		createdUser1 = this.userDao.save(createdUser1);

		// law suit is added a user with defendant role
		try {
			dryCleanLawsuit.addUser(createdUser2, defendantRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser1));

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			assert (readLawsuitFromDB.getLawsuit().getName()
					.equals(lawsuitName));
		}
	}
	
	
	// Try to insert a user with more than one role in one lawsuit
	@Test
	public void testCreateUserWithMoreThanOneRoleInOneLawsuit() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithMoreThanOneRoleInOneLawsuit started");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role witnessRole = new Role(Role.WITNESS);
		witnessRole =this.roleDao.save(witnessRole);

		Role attorneyRole = new Role(Role.ATTORNEY);
		attorneyRole = this.roleDao.save(attorneyRole);

		createdUser.addRole(witnessRole);
		createdUser.addRole(attorneyRole);	
		
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);


		try {
			createdUser.addLawsuit(dryCleanLawsuit,attorneyRole);
			createdUser.addLawsuit(dryCleanLawsuit,witnessRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		assert ( readUserFromDB.getLawsuitUsers().size() ==1 );
		
		

	}

	
	// Insert one user to more than one lawsuit
	@Test
	public void testUserWithMoreThanOneLawsuites() {
		System.out.println("DAO1 testUserWithMoreThanOneLawsuites started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role judgeRole = new Role(Role.JUDGE);
		judgeRole =this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole);
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		String lawsuitName2 = "radioTapeLawsuit";
		Lawsuit radioTapeLawsuit = new Lawsuit(lawsuitName2);
		radioTapeLawsuit = this.lawsuitDao.save(radioTapeLawsuit);
		

		try {
			dryCleanLawsuit.addUser(createdUser,judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);
			
			radioTapeLawsuit.addUser(createdUser,judgeRole);
			radioTapeLawsuit = this.lawsuitDao.save(radioTapeLawsuit);
			createdUser = this.userDao.save(createdUser);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		assert ( readUserFromDB.getLawsuitUsers().size() == 2 );
		
		

	}	

	// Create a user with more than one role
	@Test
	public void testCreateUserWithMoreThanOneRole() {
		System.out.println("DAO1 testCreateUserWithMoreThanOneRole started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		

		Role witnessRole = new Role(Role.WITNESS);
		this.roleDao.save(witnessRole);
		
		Role judgeRole = new Role(Role.JUDGE);
		this.roleDao.save(judgeRole);
		
		createdUser.addRole(witnessRole);
		createdUser.addRole(judgeRole);
		
		createdUser = this.userDao.save(createdUser);
		
		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}	
	
	// Create all of the roles
	@Test
	public void testCreateAllRoles(){
		System.out.println("DAO1 testCreateAllRoles started");
		// create roles
		Role suerRole = new Role(Role.SUER);
		this.roleDao.save(suerRole);

		Role defendantRole = new Role(Role.DEFENDANT);
		this.roleDao.save(defendantRole);

		Role prosecutorRole = new Role(Role.PROSECUTOR);
		this.roleDao.save(prosecutorRole);

		Role attorneyRole = new Role(Role.ATTORNEY);
		this.roleDao.save(attorneyRole);

		Role judgeRole = new Role(Role.JUDGE);
		this.roleDao.save(judgeRole);

		Role juryRole = new Role(Role.JURY);
		this.roleDao.save(juryRole);

		Role followerRole = new Role(Role.FOLLOWER);
		this.roleDao.save(followerRole);

		Role witnessRole = new Role(Role.WITNESS);
		this.roleDao.save(witnessRole);

	}
	
	
	// Try to insert more than one judge to one lawsuit
	@Test
	public void testLawsuitWithMoreThanOneJudge() {
		System.out.println("DAO1 testLawsuitWithMoreThanOneJudge started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		// create user2
		String userName2 = "yilmazgorali@hotmail.com";
		User createdUser2 = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		createdUser2 = this.userDao.save(createdUser2);
		

		Role judgeRole = new Role(Role.JUDGE);
		judgeRole =this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole);
		createdUser2.addRole(judgeRole);
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);


		try {
			dryCleanLawsuit.addUser(createdUser,judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);
			
			dryCleanLawsuit.addUser(createdUser2,judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert ( readUserFromDB.getLawsuitUsers().size() == 1 );
	}
	
	// Create a lawsuit with more than one jury
	@Test
	public void testLawsuitWithMoreThanOneJury() {
		System.out.println("DAO1 testLawsuitWithMoreThanOneJury started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		// create user2
		String userName2 = "yilmazgorali@hotmail.com";
		User createdUser2 = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		createdUser2 = this.userDao.save(createdUser2);
		

		Role juryRole = new Role(Role.JURY);
		juryRole =this.roleDao.save(juryRole);

		createdUser.addRole(juryRole);
		createdUser2.addRole(juryRole);
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);


		try {
			dryCleanLawsuit.addUser(createdUser,juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);
			
			dryCleanLawsuit.addUser(createdUser2,juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert ( readUserFromDB.getLawsuitUsers().size() == 1 );
	}
	
	
	
	
	
	
	
	
	// Create a lawsuit with many followers
	@Test
	public void testLawsuitWithMoreThanOneFollower() {
		System.out.println("DAO1 testLawsuitWithMoreThanOneFollower started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		// create user2
		String userName2 = "yilmazgorali@hotmail.com";
		User createdUser2 = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		createdUser2 = this.userDao.save(createdUser2);
		

		Role followerRole = new Role(Role.FOLLOWER);
		followerRole =this.roleDao.save(followerRole);

		createdUser.addRole(followerRole);
		createdUser2.addRole(followerRole);
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);


		try {
			dryCleanLawsuit.addUser(createdUser,followerRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);
			
			dryCleanLawsuit.addUser(createdUser2,followerRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert ( readUserFromDB.getLawsuitUsers().size() == 2 );
	}
	
		
	
	
	// Create a lawsuit with many attorneys
	@Test
	public void testLawsuitWithMoreThanOneAttorney() {
		System.out.println("DAO1 testLawsuitWithMoreThanOneFollower started");

		String userName = "ozangurler@hotmail.com";
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		// create user2
		String userName2 = "yilmazgorali@hotmail.com";
		User createdUser2 = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		createdUser2 = this.userDao.save(createdUser2);
		

		Role attorneyRole = new Role(Role.ATTORNEY);
		attorneyRole =this.roleDao.save(attorneyRole);

		createdUser.addRole(attorneyRole);
		createdUser2.addRole(attorneyRole);
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);


		try {
			dryCleanLawsuit.addUser(createdUser,attorneyRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);
			
			dryCleanLawsuit.addUser(createdUser2,attorneyRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}

		
		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert ( readUserFromDB.getLawsuitUsers().size() == 2 );
	}
	
	
	
	// Create all of the action types
	@Test
	public void testCreateAllActionTypes(){
		System.out.println("DAO1 testCreateAllActionTypes started");
//	       ID     Code    
//	       1 OyKullan
//	       2 SoruSor
//	       3 YorumYap
		// create action types
		ActionType  voteAction = new ActionType("OY_KULLAN");		
		voteAction = this.actionTypeDao.save(voteAction);
		
		Role juryRole = new Role(Role.JURY);
		juryRole =this.roleDao.save(juryRole);		
		
		

		ActionType  questionAction = new ActionType("SORU_SOR");		
		questionAction = this.actionTypeDao.save(questionAction);
		
		ActionType  commentAction = new ActionType("YORUM_YAP");		
		commentAction =  this.actionTypeDao.save(commentAction);
		
		
//		1001   Masum                        
//		1002   Suclu  
//		1003   Basarılı 
		
		OptVal masum = new OptVal("Masum");
		masum = this.optValDao.save(masum);
		
		OptVal suclu = new OptVal("Suclu");
		suclu = this.optValDao.save(suclu);
		
		OptVal basarili = new OptVal("Basarili");
		basarili = this.optValDao.save(basarili);		
		
		
		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(masum);
		optVals.add(suclu);
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(juryRole);
		voteAction.setRoles(roles);
		
		voteAction.setOptVals(optVals);
		voteAction = this.actionTypeDao.save(voteAction);
		
		
	}	
	
	
	// Make Judge decide
	@Test
	public void zzztestJudgeDecide(){
		System.out.println("DAO1 testJudgeDecide started");

		// create action types
		ActionType  voteAction = new ActionType("OY_KULLAN");		
		voteAction = this.actionTypeDao.save(voteAction);
		
		Role juryRole = new Role(Role.JURY);
		juryRole =this.roleDao.save(juryRole);		
		
		

		ActionType  questionAction = new ActionType("SORU_SOR");		
		questionAction = this.actionTypeDao.save(questionAction);
		
		ActionType  commentAction = new ActionType("YORUM_YAP");		
		commentAction =  this.actionTypeDao.save(commentAction);
		
		ActionType  decideAction = new ActionType("KARAR_VER");		
		decideAction = this.actionTypeDao.save(decideAction);			
		Role judgeRole = new Role(Role.JUDGE);
		judgeRole =this.roleDao.save(judgeRole);	
		

		OptVal masum = new OptVal("Masum");
		masum = this.optValDao.save(masum);
		
		OptVal suclu = new OptVal("Suclu");
		suclu = this.optValDao.save(suclu);
		
		OptVal basarili = new OptVal("Basarili");
		basarili = this.optValDao.save(basarili);		
		
		
		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(masum);
		optVals.add(suclu);
		
		
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(judgeRole);
		decideAction.setRoles(roles);
		
		decideAction.setOptVals(optVals);
		decideAction = this.actionTypeDao.save(decideAction);
		
		
		
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
		
		
		String userName = "ozangurler@hotmail.com";
		User judgeJudy = new User(userName,
				this.passwordEncoder.encode(userName));
		judgeJudy = this.userDao.save(judgeJudy);

		judgeJudy.addRole(judgeRole);
		
		


		try {
			dryCleanLawsuit.addUser(judgeJudy,judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			judgeJudy = this.userDao.save(judgeJudy);

		} catch (OmbyRuleException e) {
			e.printStackTrace();
			
		}
		
		
		UserAction judgeDecided = new UserAction();
		judgeDecided.setActionType(decideAction);
		judgeDecided.setLawsuit(dryCleanLawsuit);
		judgeDecided.setOptVal(masum);
		judgeDecided.setRole(judgeRole);
		judgeDecided.setUser(judgeJudy);
		judgeDecided.setVal(masum.getValCode());
		
		judgeDecided = userActionDao.save(judgeDecided);
		
	}	
	
	
	
	
	
	
	// Make a jury vote
	// Try to make an attorney to decide
	// Try to make a defendant vote
	// Make a defendant comment
	// Make a prosecutor ask question	
	
	// Calculate jury role points for a user after successful decision
	// Calculate jury role points for a user after failed decision

	// Check if user names are opaque to lawsuit
	// Check points of a user for each role after lawsuit calculation
	// Create a public lawsuit
	// Create a private lawsuit
	// Try join a private lawsuit
	// Join to a public lawsuit

}
