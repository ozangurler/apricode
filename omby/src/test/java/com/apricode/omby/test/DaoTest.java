package com.apricode.omby.test;

//  Repository https://github.com/ozangurler/apricode.git
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

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

		// delete FROM omby_ozan.user_role;
		// delete FROM omby_ozan.user_role;
		// delete FROM omby_ozan.actiontype_optval;
		// delete from omby_ozan.useraction;
		// delete FROM omby_ozan.optval;
		// delete FROM omby_ozan.actiontype_role;
		// delete FROM omby_ozan.actiontype;
		// delete from omby_ozan.userlawsuit;
		// delete from omby_ozan.user;
		// delete from omby_ozan.role;
		// delete from omby_ozan.lawsuit;
		//
		// drop table omby_ozan.actiontype_optval;
		// drop table omby_ozan.useraction;
		// drop table omby_ozan.user_role;
		// drop table omby_ozan.optval;
		// drop table omby_ozan.actiontype_role;
		// drop table omby_ozan.userlawsuit;
		// drop table omby_ozan.userroleactiongrade;
		// drop table omby_ozan.userlawsuitroleactiongrade;
		// drop table omby_ozan.actiontype;
		// drop table omby_ozan.lawsuit;
		// drop table omby_ozan.user;
		// drop table omby_ozan.role;

		List<UserAction> ualist = this.userActionDao.findAll();
		for (UserAction aUserAction : ualist) {
			this.userActionDao.delete(aUserAction.getId());
		}

		List<ActionType> atlist = this.actionTypeDao.findAll();
		for (ActionType anActionType : atlist) {
			this.actionTypeDao.delete(anActionType.getId());
		}

		List<OptVal> ovlist = this.optValDao.findAll();
		for (OptVal anOptVal : ovlist) {
			this.optValDao.delete(anOptVal.getId());
		}

		List<User> ulist = this.userDao.findAll();
		for (User aUser : ulist) {
			this.userDao.delete(aUser.getId());
		}

		List<Role> rlist = this.roleDao.findAll();
		for (Role aRole : rlist) {
			this.roleDao.delete(aRole.getId());
		}

		List<Lawsuit> llist = this.lawsuitDao.findAll();
		for (Lawsuit aLawsuit : llist) {
			this.lawsuitDao.delete(aLawsuit.getId());
		}

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After each test");

		// List<Lawsuit> llist = this.lawsuitDao.findAll();
		// for (Lawsuit aLawsuit : llist) {
		// this.lawsuitDao.delete(aLawsuit.getId());
		// }
		//
		// List<Role> rlist = this.roleDao.findAll();
		// for (Role aRole : rlist) {
		// this.roleDao.delete(aRole.getId());
		// }
		//
		// List<User> ulist = this.userDao.findAll();
		// for (User aUser : ulist) {
		// this.userDao.delete(aUser.getId());
		// }

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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
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
		User readUserFromDB = this.userDao.findByEmail(userName);
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
		System.out
				.println("DAO1 testCreateUserWithMoreThanOneRoleInOneLawsuit started");
		User createdUser = new User(userName,
				this.passwordEncoder.encode(userName));
		createdUser = this.userDao.save(createdUser);

		Role witnessRole = new Role(Role.WITNESS);
		witnessRole = this.roleDao.save(witnessRole);

		Role attorneyRole = new Role(Role.ATTORNEY);
		attorneyRole = this.roleDao.save(attorneyRole);

		createdUser.addRole(witnessRole);
		createdUser.addRole(attorneyRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, attorneyRole);
			createdUser.addLawsuit(dryCleanLawsuit, witnessRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

		assert (readUserFromDB.getLawsuitUsers().size() == 1);

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
		judgeRole = this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		String lawsuitName2 = "radioTapeLawsuit";
		Lawsuit radioTapeLawsuit = new Lawsuit(lawsuitName2);
		radioTapeLawsuit = this.lawsuitDao.save(radioTapeLawsuit);

		try {
			dryCleanLawsuit.addUser(createdUser, judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);

			radioTapeLawsuit.addUser(createdUser, judgeRole);
			radioTapeLawsuit = this.lawsuitDao.save(radioTapeLawsuit);
			createdUser = this.userDao.save(createdUser);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

		assert (readUserFromDB.getLawsuitUsers().size() == 2);

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
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

	}

	// Create all of the roles
	@Test
	public void testCreateAllRoles() {
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
		judgeRole = this.roleDao.save(judgeRole);

		createdUser.addRole(judgeRole);
		createdUser2.addRole(judgeRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			dryCleanLawsuit.addUser(createdUser, judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);

			dryCleanLawsuit.addUser(createdUser2, judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert (readUserFromDB.getLawsuitUsers().size() == 1);
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
		juryRole = this.roleDao.save(juryRole);

		createdUser.addRole(juryRole);
		createdUser2.addRole(juryRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			dryCleanLawsuit.addUser(createdUser, juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);

			dryCleanLawsuit.addUser(createdUser2, juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert (readUserFromDB.getLawsuitUsers().size() == 1);
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
		followerRole = this.roleDao.save(followerRole);

		createdUser.addRole(followerRole);
		createdUser2.addRole(followerRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			dryCleanLawsuit.addUser(createdUser, followerRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);

			dryCleanLawsuit.addUser(createdUser2, followerRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert (readUserFromDB.getLawsuitUsers().size() == 2);
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
		attorneyRole = this.roleDao.save(attorneyRole);

		createdUser.addRole(attorneyRole);
		createdUser2.addRole(attorneyRole);

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			dryCleanLawsuit.addUser(createdUser, attorneyRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser = this.userDao.save(createdUser);

			dryCleanLawsuit.addUser(createdUser2, attorneyRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUser2 = this.userDao.save(createdUser2);
		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));
		assert (readUserFromDB.getLawsuitUsers().size() == 2);
	}

	// Create all of the action types
	@Test
	public void testCreateAllActionTypes() {
		System.out.println("DAO1 testCreateAllActionTypes started");
		// ID Code
		// 1 OyKullan
		// 2 SoruSor
		// 3 YorumYap
		// create action types
		ActionType voteAction = new ActionType("OY_KULLAN");
		voteAction = this.actionTypeDao.save(voteAction);

		Role juryRole = new Role(Role.JURY);
		juryRole = this.roleDao.save(juryRole);

		ActionType questionAction = new ActionType("SORU_SOR");
		questionAction = this.actionTypeDao.save(questionAction);

		ActionType commentAction = new ActionType("YORUM_YAP");
		commentAction = this.actionTypeDao.save(commentAction);

		// 1001 Masum
		// 1002 Suclu
		// 1003 Basarılı

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
	public void testJudgeDecide() {
		System.out.println("DAO1 testJudgeDecide started");

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		// create action types
		ActionType voteAction = new ActionType("OY_KULLAN");
		voteAction = this.actionTypeDao.save(voteAction);

		ActionType questionAction = new ActionType("SORU_SOR");
		questionAction = this.actionTypeDao.save(questionAction);

		ActionType commentAction = new ActionType("YORUM_YAP");
		commentAction = this.actionTypeDao.save(commentAction);

		ActionType decideAction = new ActionType("KARAR_VER");
		decideAction = this.actionTypeDao.save(decideAction);

		Role juryRole = new Role(Role.JURY);
		juryRole = this.roleDao.save(juryRole);

		Role judgeRole = new Role(Role.JUDGE);
		judgeRole = this.roleDao.save(judgeRole);

		OptVal masum = new OptVal("Masum");
		masum = this.optValDao.save(masum);

		OptVal suclu = new OptVal("Suclu");
		suclu = this.optValDao.save(suclu);

		OptVal basarili = new OptVal("Basarili");
		basarili = this.optValDao.save(basarili);

		Set<Role> roles = new HashSet<Role>();
		roles.add(judgeRole);
		decideAction.setRoles(roles);

		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(masum);
		optVals.add(suclu);
		decideAction.setOptVals(optVals);

		decideAction = this.actionTypeDao.save(decideAction);

		String userName = "ozangurler@hotmail.com";
		User judgeJudy = new User(userName,
				this.passwordEncoder.encode(userName));
		judgeJudy = this.userDao.save(judgeJudy);

		judgeJudy.addRole(judgeRole);

		try {
			dryCleanLawsuit.addUser(judgeJudy, judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			judgeJudy = this.userDao.save(judgeJudy);

		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		UserAction judgeDecided = new UserAction();
		judgeDecided.setActionType(decideAction);
		judgeDecided.setLawsuit(dryCleanLawsuit);
		judgeDecided.setVal(masum.getValCode());

		judgeDecided.setOptVal(masum);
		judgeDecided.setRole(judgeRole);
		judgeDecided.setUser(judgeJudy);
		judgeDecided = userActionDao.save(judgeDecided);

	}

	// Make a jury vote
	@Test
	public void testJuryVote() {
		System.out.println("DAO1 testJuryVote started");

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		ActionType voteAction = new ActionType("OY_KULLAN");
		voteAction = this.actionTypeDao.save(voteAction);

		Role juryRole = new Role(Role.JURY);
		juryRole = this.roleDao.save(juryRole);

		OptVal masum = new OptVal("Masum");
		masum = this.optValDao.save(masum);

		OptVal suclu = new OptVal("Suclu");
		suclu = this.optValDao.save(suclu);

		Set<Role> roles = new HashSet<Role>();
		roles.add(juryRole);
		voteAction.setRoles(roles);

		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(masum);
		optVals.add(suclu);
		voteAction.setOptVals(optVals);

		voteAction = this.actionTypeDao.save(voteAction);

		String userName = "ozangurler@hotmail.com";
		User juryDuty = new User(userName,
				this.passwordEncoder.encode(userName));
		juryDuty = this.userDao.save(juryDuty);

		juryDuty.addRole(juryRole);

		try {
			dryCleanLawsuit.addUser(juryDuty, juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			juryDuty = this.userDao.save(juryDuty);

		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		UserAction juryVoted = new UserAction();
		juryVoted.setActionType(voteAction);
		juryVoted.setLawsuit(dryCleanLawsuit);
		juryVoted.setVal(masum.getValCode());

		juryVoted.setOptVal(masum);
		juryVoted.setRole(juryRole);
		juryVoted.setUser(juryDuty);
		juryVoted = userActionDao.save(juryVoted);

	}

	// Make a defendant comment
	@Test
	public void testDefendantComment() {
		System.out.println("DAO1 testDefendantComment started");

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		ActionType commentAction = new ActionType("YORUM_YAP");
		commentAction = this.actionTypeDao.save(commentAction);

		Role defendantRole = new Role(Role.DEFENDANT);
		defendantRole = this.roleDao.save(defendantRole);

		OptVal basarili = new OptVal("Basarili");
		basarili = this.optValDao.save(basarili);

		Set<Role> roles = new HashSet<Role>();
		roles.add(defendantRole);
		commentAction.setRoles(roles);

		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(basarili);
		commentAction.setOptVals(optVals);

		commentAction = this.actionTypeDao.save(commentAction);

		String userName = "kemalsunal@hotmail.com";
		User kemalSunal = new User(userName,
				this.passwordEncoder.encode(userName));
		kemalSunal = this.userDao.save(kemalSunal);

		kemalSunal.addRole(defendantRole);

		try {
			dryCleanLawsuit.addUser(kemalSunal, defendantRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			kemalSunal = this.userDao.save(kemalSunal);

		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		UserAction defendantCommented = new UserAction();
		defendantCommented.setActionType(commentAction);
		defendantCommented.setLawsuit(dryCleanLawsuit);
		defendantCommented.setVal("SINIFI GECTI");

		defendantCommented.setOptVal(basarili);
		defendantCommented.setRole(defendantRole);
		defendantCommented.setUser(kemalSunal);
		defendantCommented = userActionDao.save(defendantCommented);

	}

	// Make a prosecutor ask question
	@Test
	public void testProsecutorAskQuestion() {
		System.out.println("DAO1 testDefendantComment started");

		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		ActionType questionAction = new ActionType("SORU_SOR");
		questionAction = this.actionTypeDao.save(questionAction);

		Role prosecutorRole = new Role(Role.PROSECUTOR);
		prosecutorRole = this.roleDao.save(prosecutorRole);

		OptVal neredeydin = new OptVal("Neredeydin");
		neredeydin = this.optValDao.save(neredeydin);

		Set<Role> roles = new HashSet<Role>();
		roles.add(prosecutorRole);
		questionAction.setRoles(roles);

		Set<OptVal> optVals = new HashSet<OptVal>();
		optVals.add(neredeydin);
		questionAction.setOptVals(optVals);

		questionAction = this.actionTypeDao.save(questionAction);

		String userName = "zekeriyaoz@hotmail.com";
		User savci = new User(userName, this.passwordEncoder.encode(userName));
		savci = this.userDao.save(savci);

		savci.addRole(prosecutorRole);

		try {
			dryCleanLawsuit.addUser(savci, prosecutorRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			savci = this.userDao.save(savci);

		} catch (OmbyRuleException e) {
			e.printStackTrace();

		}

		UserAction defendantCommented = new UserAction();
		defendantCommented.setActionType(questionAction);
		defendantCommented.setLawsuit(dryCleanLawsuit);
		defendantCommented.setVal("CINAYETIN OLDUGU SAAT");

		defendantCommented.setOptVal(neredeydin);
		defendantCommented.setRole(prosecutorRole);
		defendantCommented.setUser(savci);
		defendantCommented = userActionDao.save(defendantCommented);

	}

	// Create a public lawsuit
	@Test
	public void testCreatePublicLawsuit() {
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			assert (readLawsuitFromDB.getLawsuit().getName()
					.equals(lawsuitName));
		}

	}

	// Create a private lawsuit
	@Test
	public void testCreatePrivateLawsuit() {
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(false));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName);
		assert (readUserFromDB.getUsername().equals(createdUser));

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			assert (readLawsuitFromDB.getLawsuit().getName()
					.equals(lawsuitName));
		}

	}
	// Join to a public lawsuit
	@Test
	public void testJoinPublicLawsuit() {
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(true));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);
		
		
		// Follow a public lawsuit
		String userName2 = "ugurmumcu@hotmail.com";
		User takipci = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		
		Role followerRole = new Role(Role.FOLLOWER);
		takipci.addRole(followerRole);
		followerRole = this.roleDao.save(followerRole);		
		
		takipci = this.userDao.save(takipci);	
		try {
			takipci.addLawsuit(dryCleanLawsuit, followerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}		
		takipci = this.userDao.save(takipci);
		
		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName2);
		// assert (readUserFromDB.getUsername().equals(takipci));

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			assert (readLawsuitFromDB.getLawsuit().getName()
					.equals(lawsuitName));
		}

	}	
	
	
	// Try join a private lawsuit
	@Test
	public void testJoinPrivateLawsuit() {
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
		dryCleanLawsuit.setPublicLawsuit(new Boolean(false));
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		try {
			createdUser.addLawsuit(dryCleanLawsuit, suerRole);
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}

		createdUser = this.userDao.save(createdUser);
		
		
		// Follow a public lawsuit
		String userName2 = "ugurmumcu@hotmail.com";
		User takipci = new User(userName2,
				this.passwordEncoder.encode(userName2));		
		
		Role followerRole = new Role(Role.FOLLOWER);
		takipci.addRole(followerRole);
		followerRole = this.roleDao.save(followerRole);		
		
		takipci = this.userDao.save(takipci);	
		try {
			takipci.addLawsuit(dryCleanLawsuit, followerRole);
		} catch (OmbyRuleException e) {
			System.out.println("-------------PRIVATE LAWSUITS DOES NOT ACCEPT FOLLOWER-------------------ERRR ERR ERR");
			e.printStackTrace();
		}		
		takipci = this.userDao.save(takipci);
		

		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(userName2);

		Set<UserLawsuit> readLawsuitsFromDB = readUserFromDB.getLawsuitUsers();
		
		System.out.println("KATILINAN DAVA SAYISI " + readLawsuitsFromDB.size());
		
		
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();
			
			// private lawsuit should not be allowed to join 
			// code should not go in here
			System.out.println("PATLASIN------------- " + readLawsuitsFromDB.size());
			assertTrue (false );
		}
		assertTrue (true);

	}	
	
	// Check if user names are opaque
	@Test
	public void zzztestCheckIfUserNamesAreOpaque() {

		String email = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCheckIfUserNamesAreOpaque starterted");
		User createdUser = new User(email,
				this.passwordEncoder.encode(email));
		this.userDao.save(createdUser);
		// Control mechanism
		User readUserFromDB = this.userDao.findByEmail(email);
		System.out.println("RANDOM USERNAME------------- " + readUserFromDB.getUsername());
		assert (!readUserFromDB.getUsername().equals(createdUser.getEmail()));
	}

	// Try to make an attorney to decide-- I will do it later
	// Try to make a defendant vote-- I will do it later

	// Calculate jury role points for a user after successful decision
	// Calculate jury role points for a user after failed decision
	// Calculate points of a user for each role after lawsuit calculation	
	

}
