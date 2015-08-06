package com.apricode.omby.test;
//  Repository https://github.com/ozangurler/apricode.git
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.transaction.annotation.Transactional;

import com.apricode.omby.dao.LawsuitDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;
import com.apricode.omby.domain.UserLawsuit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context.xml")
public class DaoTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private LawsuitDao lawsuitDao;

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

		List<Lawsuit> llist = this.lawsuitDao.findAll();
		for (Lawsuit aLawsuit : llist) {
			this.lawsuitDao.delete(aLawsuit.getId());
		}

		List<Role> rlist = this.roleDao.findAll();
		for (Role aRole : rlist) {
			this.roleDao.delete(aRole.getId());
		}

		List<User> ulist = this.userDao.findAll();
		for (User aUser : ulist) {
			this.userDao.delete(aUser.getId());
		}

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After each test");

		List<Lawsuit> llist = this.lawsuitDao.findAll();
		for (Lawsuit aLawsuit : llist) {
			this.lawsuitDao.delete(aLawsuit.getId());
		}

		List<Role> rlist = this.roleDao.findAll();
		for (Role aRole : rlist) {
			this.roleDao.delete(aRole.getId());
		}

		List<User> ulist = this.userDao.findAll();
		for (User aUser : ulist) {
			this.userDao.delete(aUser.getId());
		}

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

		Role suerRole = new Role("SUER");
		this.roleDao.save(suerRole);

		createdUser.addRole(suerRole.getName());

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

		Role defendantRole = new Role("DEFENDANT");
		this.roleDao.save(defendantRole);

		createdUser.addRole(defendantRole.getName());

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

		Role followerRole = new Role("FOLLOWER");
		this.roleDao.save(followerRole);

		createdUser.addRole(followerRole.getName());

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

		Role attorneyRole = new Role("ATTORNEY");
		this.roleDao.save(attorneyRole);

		createdUser.addRole(attorneyRole.getName());

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

		Role judgeRole = new Role("JUDGE");
		this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole.getName());

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

		Role juryRole = new Role("JURY");
		this.roleDao.save(juryRole);

		createdUser.addRole(juryRole.getName());

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

		Role prosecutorRole = new Role("PROSECUTOR");
		this.roleDao.save(prosecutorRole);

		createdUser.addRole(prosecutorRole.getName());

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

		Role witnessRole = new Role("WITNESS");
		this.roleDao.save(witnessRole);

		createdUser.addRole(witnessRole.getName());

		// Control mechanism
		User readUserFromDB = this.userDao.findByName(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Try to insert a user with more than one role in one lawsuit
	@Test
	public void testCreateUserWithMoreThanOneRoleInOneLawsuit() {

		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithWitnessRole starterted");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
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

		Role suerRole = new Role("SUER");
		createdUser.addRole(suerRole.getName());
		this.roleDao.save(suerRole);

		createdUser = this.userDao.save(createdUser);

		System.out.println("DAO1 dryCleanPaymentLawsuit started");

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		// dryCleanLawsuit.addUser(createdUser);
		createdUser.addLawsuit(dryCleanLawsuit);

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
	// Insert a lawsuit
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
		Role suerRole = new Role("SUER");
		this.roleDao.save(suerRole);

		Role defendantRole = new Role("DEFENDANT");
		this.roleDao.save(defendantRole);

		Role prosecutorRole = new Role("PROSECUTOR");
		this.roleDao.save(prosecutorRole);

		Role attorneyRole = new Role("ATTORNEY");
		this.roleDao.save(attorneyRole);

		Role judgeRole = new Role("JUDGE");
		this.roleDao.save(judgeRole);

		Role juryRole = new Role("JURY");
		this.roleDao.save(juryRole);

		Role followerRole = new Role("FOLLOWER");
		this.roleDao.save(followerRole);

		Role witnessRole = new Role("WITNESS");
		this.roleDao.save(witnessRole);

		// bind role to the user
		createdUser1.addRole(suerRole.getName());
		createdUser1.addRole(prosecutorRole.getName());

		createdUser2.addRole(witnessRole.getName());

		// save user
		createdUser1 = this.userDao.save(createdUser1);
		createdUser2 = this.userDao.save(createdUser2);

		// create law suit
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		// join user to lawsuit
		createdUser1.addLawsuit(dryCleanLawsuit);

		// persist user with lawsuit
		createdUser1 = this.userDao.save(createdUser1);

		dryCleanLawsuit.addUser(createdUser2);
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
