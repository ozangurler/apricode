package com.apricode.omby.test;

import java.util.Date;
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

import com.apricode.omby.dao.ActionTypeDao;
import com.apricode.omby.dao.LawsuitDao;
import com.apricode.omby.dao.OptValDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.dao.UserActionDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.domain.ActionType;
import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.OptVal;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;
import com.apricode.omby.domain.UserAction;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context.xml")
public class SetupTest {
	
	
	Role suerRole = new Role(Role.SUER);
	Role defendantRole = new Role(Role.DEFENDANT);
	Role prosecutorRole = new Role(Role.PROSECUTOR);
	Role attorneyRole = new Role(Role.ATTORNEY);
	Role judgeRole = new Role(Role.JUDGE);
	Role juryRole = new Role(Role.JURY);
	Role followerRole = new Role(Role.FOLLOWER);
	Role witnessRole = new Role(Role.WITNESS);
	
	
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

		// We do not clean before each test since this is setup
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
	}

	
	// We will have only one test to setup
	@Test
	public void testInitializeTestDB() {
		createRoles();
		// create law suit
		String lawsuitName = "dryCleanPaymentLawsuit";
		Lawsuit dryCleanLawsuit = new Lawsuit(lawsuitName);
		dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);

		
		String userName = "ozangurler@hotmail.com";
		System.out.println("DAO1 testCreateUserWithSuerRole starterted");		
		User createdUser = new User(userName, this.passwordEncoder.encode(userName));
		createdUser.setCreatedOn(new Date());
		createdUser.setEmail(userName);
		createdUser.setFirstName("Ozan");


		createdUser.addRole(suerRole);		
		createdUser = this.userDao.save(createdUser);		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
		String defendantUserName = "altunhasan@hotmail.com";
		User createdDefendantUser = new User(defendantUserName, this.passwordEncoder.encode(defendantUserName));
		createdDefendantUser.setCreatedOn(new Date());
		createdDefendantUser.setEmail(defendantUserName);
		createdDefendantUser.setFirstName("Hasan");


		createdDefendantUser.addRole(defendantRole);		
		createdDefendantUser = this.userDao.save(createdDefendantUser);		
		// Control mechanism 
		User readUserFromDB2 = this.userDao.findByName(defendantUserName);	
		assert (readUserFromDB2.getUsername().equals(createdDefendantUser));		
		
		
		String followerUserName = "sevdan@hotmail.com";
		User createdFollowerUser = new User(followerUserName, this.passwordEncoder.encode(followerUserName));
		createdFollowerUser.setCreatedOn(new Date());
		createdFollowerUser.setEmail(followerUserName);
		createdFollowerUser.setFirstName("Sevdan");
		
		createdFollowerUser.addRole(followerRole);
		createdFollowerUser.addRole(witnessRole);
		
		createdFollowerUser = this.userDao.save(createdFollowerUser);		
		// Control mechanism 
		User readUserFromDB3 = this.userDao.findByName(followerUserName);	
		assert (readUserFromDB3.getUsername().equals(createdFollowerUser));			
		
		

		String attorneyUserName = "engin@hotmail.com";
		User createdAttorneyUser = new User(attorneyUserName, this.passwordEncoder.encode(attorneyUserName));
		createdAttorneyUser.setCreatedOn(new Date());
		createdAttorneyUser.setEmail(attorneyUserName);
		createdAttorneyUser.setFirstName("Engin");


		createdAttorneyUser.addRole(attorneyRole);		
		createdAttorneyUser = this.userDao.save(createdAttorneyUser);		
		// Control mechanism 
		User readUserFromDB4 = this.userDao.findByName(attorneyUserName);	
		assert (readUserFromDB4.getUsername().equals(createdAttorneyUser));	
		
		
		
		String judgeUserName = "onur@hotmail.com";
		User createdJudgeUser = new User(judgeUserName, this.passwordEncoder.encode(judgeUserName));
		createdJudgeUser.setCreatedOn(new Date());
		createdJudgeUser.setEmail(judgeUserName);
		createdJudgeUser.setFirstName("Onur");


		createdJudgeUser.addRole(judgeRole);		
		createdJudgeUser = this.userDao.save(createdJudgeUser);		
		// Control mechanism 
		User readUserFromDB5 = this.userDao.findByName(judgeUserName);	
		assert (readUserFromDB5.getUsername().equals(createdJudgeUser));	
		
		
		

		String juryUserName = "osman@hotmail.com";
		User createdJuryUser = new User(juryUserName, this.passwordEncoder.encode(juryUserName));
		createdJuryUser.setCreatedOn(new Date());
		createdJuryUser.setEmail(juryUserName);
		createdJuryUser.setFirstName("Osman");


		createdJuryUser.addRole(juryRole);		
		createdJuryUser = this.userDao.save(createdJuryUser);		
		// Control mechanism 
		User readUserFromDB6 = this.userDao.findByName(juryUserName);	
		assert (readUserFromDB6.getUsername().equals(createdJuryUser));		
		
		
		
		String prosecutorUserName = "davud@hotmail.com";
		User createdProsecutorUser = new User(prosecutorUserName, this.passwordEncoder.encode(prosecutorUserName));
		createdProsecutorUser.setCreatedOn(new Date());
		createdProsecutorUser.setEmail(prosecutorUserName);
		createdProsecutorUser.setFirstName("Davud");


		createdProsecutorUser.addRole(prosecutorRole);		
		createdProsecutorUser = this.userDao.save(createdProsecutorUser);		
		// Control mechanism 
		User readUserFromDB7 = this.userDao.findByName(prosecutorUserName);	
		assert (readUserFromDB7.getUsername().equals(createdProsecutorUser));	
		
		  
		
		String witnessUserName = "tulay@hotmail.com";
		User createdWitnessUser = new User(witnessUserName, this.passwordEncoder.encode(witnessUserName));
		createdWitnessUser.setCreatedOn(new Date());
		createdWitnessUser.setEmail(witnessUserName);
		createdWitnessUser.setFirstName("Tulay");

		createdWitnessUser.addRole(witnessRole);		
		createdWitnessUser = this.userDao.save(createdWitnessUser);		
		// Control mechanism 
		User readUserFromDB8 = this.userDao.findByName(witnessUserName);	
		assert (readUserFromDB8.getUsername().equals(createdWitnessUser));			
		
		
		
		
		
	}
	private void createRoles(){
		// create roles
		this.roleDao.save(suerRole);
		this.roleDao.save(defendantRole);
		this.roleDao.save(prosecutorRole);
		this.roleDao.save(attorneyRole);
		this.roleDao.save(judgeRole);
		this.roleDao.save(juryRole);
		this.roleDao.save(followerRole);
		this.roleDao.save(witnessRole);
	}


}
