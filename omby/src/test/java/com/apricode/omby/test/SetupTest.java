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
		createdUser.setCreatedOn(new Date());
		createdUser.setEmail(userName);
		createdUser.setFirstName("Ozan");
		Role suerRole = new Role("SUER");
		suerRole = this.roleDao.save(suerRole);
		createdUser.addRole(suerRole.getName());		
		createdUser = this.userDao.save(createdUser);		
		// Control mechanism 
		User readUserFromDB = this.userDao.findByName(userName);	
		assert (readUserFromDB.getUsername().equals(createdUser));
		
		
		String defendantUserName = "altunhasan@hotmail.com";
		User createdDefendantUser = new User(defendantUserName, this.passwordEncoder.encode(defendantUserName));
		createdDefendantUser.setCreatedOn(new Date());
		createdDefendantUser.setEmail(defendantUserName);
		createdDefendantUser.setFirstName("Hasan");
		Role defendantRole = new Role("DEFENDANT");	
		defendantRole = this.roleDao.save(defendantRole);
		createdDefendantUser.addRole(defendantRole.getName());		
		createdDefendantUser = this.userDao.save(createdDefendantUser);		
		// Control mechanism 
		User readUserFromDB2 = this.userDao.findByName(defendantUserName);	
		assert (readUserFromDB2.getUsername().equals(createdDefendantUser));		
		
		
		String followerUserName = "sevdan@hotmail.com";
		User createdFollowerUser = new User(followerUserName, this.passwordEncoder.encode(followerUserName));
		createdFollowerUser.setCreatedOn(new Date());
		createdFollowerUser.setEmail(followerUserName);
		createdFollowerUser.setFirstName("Sevdan");
		Role followerRole = new Role("FOLLOWER");
		Role witnessRole = new Role("WITNESS");
		defendantRole = this.roleDao.save(followerRole);
		witnessRole = this.roleDao.save(witnessRole);
		
		createdFollowerUser.addRole(followerRole.getName());
		createdFollowerUser.addRole(witnessRole.getName());
		
		createdFollowerUser = this.userDao.save(createdFollowerUser);		
		// Control mechanism 
		User readUserFromDB3 = this.userDao.findByName(followerUserName);	
		assert (readUserFromDB3.getUsername().equals(createdFollowerUser));			
		
		

		String attorneyUserName = "engin@hotmail.com";
		User createdAttorneyUser = new User(attorneyUserName, this.passwordEncoder.encode(attorneyUserName));
		createdAttorneyUser.setCreatedOn(new Date());
		createdAttorneyUser.setEmail(attorneyUserName);
		createdAttorneyUser.setFirstName("Engin");
		Role attorneyRole = new Role("ATTORNEY");
		attorneyRole = this.roleDao.save(attorneyRole);
		createdAttorneyUser.addRole(attorneyRole.getName());		
		createdAttorneyUser = this.userDao.save(createdAttorneyUser);		
		// Control mechanism 
		User readUserFromDB4 = this.userDao.findByName(attorneyUserName);	
		assert (readUserFromDB4.getUsername().equals(createdAttorneyUser));	
		
		
		
		String judgeUserName = "onur@hotmail.com";
		User createdJudgeUser = new User(judgeUserName, this.passwordEncoder.encode(judgeUserName));
		createdJudgeUser.setCreatedOn(new Date());
		createdJudgeUser.setEmail(judgeUserName);
		createdJudgeUser.setFirstName("Onur");
		Role judgeRole = new Role("JUDGE");
		judgeRole = this.roleDao.save(judgeRole);
		createdJudgeUser.addRole(judgeRole.getName());		
		createdJudgeUser = this.userDao.save(createdJudgeUser);		
		// Control mechanism 
		User readUserFromDB5 = this.userDao.findByName(judgeUserName);	
		assert (readUserFromDB5.getUsername().equals(createdJudgeUser));	
		
		
		

		String juryUserName = "osman@hotmail.com";
		User createdJuryUser = new User(juryUserName, this.passwordEncoder.encode(juryUserName));
		createdJuryUser.setCreatedOn(new Date());
		createdJuryUser.setEmail(juryUserName);
		createdJuryUser.setFirstName("Osman");
		Role juryRole = new Role("JURY");
		juryRole = this.roleDao.save(juryRole);
		createdJuryUser.addRole(juryRole.getName());		
		createdJuryUser = this.userDao.save(createdJuryUser);		
		// Control mechanism 
		User readUserFromDB6 = this.userDao.findByName(juryUserName);	
		assert (readUserFromDB6.getUsername().equals(createdJuryUser));		
		
		
		
		String prosecutorUserName = "davud@hotmail.com";
		User createdProsecutorUser = new User(prosecutorUserName, this.passwordEncoder.encode(prosecutorUserName));
		createdProsecutorUser.setCreatedOn(new Date());
		createdProsecutorUser.setEmail(prosecutorUserName);
		createdProsecutorUser.setFirstName("Davud");
		Role prosecutorRole = new Role("PROSECUTOR");
		prosecutorRole = this.roleDao.save(prosecutorRole);
		createdProsecutorUser.addRole(prosecutorRole.getName());		
		createdProsecutorUser = this.userDao.save(createdProsecutorUser);		
		// Control mechanism 
		User readUserFromDB7 = this.userDao.findByName(prosecutorUserName);	
		assert (readUserFromDB7.getUsername().equals(createdProsecutorUser));	
		
		
		
		String witnessUserName = "tulay@hotmail.com";
		User createdWitnessUser = new User(witnessUserName, this.passwordEncoder.encode(witnessUserName));
		createdWitnessUser.setCreatedOn(new Date());
		createdWitnessUser.setEmail(witnessUserName);
		createdWitnessUser.setFirstName("Tulay");
//		Role witnessRole = new Role("WITNESS");
//		witnessRole = this.roleDao.save(witnessRole);
		createdWitnessUser.addRole(witnessRole.getName());		
		createdWitnessUser = this.userDao.save(createdWitnessUser);		
		// Control mechanism 
		User readUserFromDB8 = this.userDao.findByName(witnessUserName);	
		assert (readUserFromDB8.getUsername().equals(createdWitnessUser));			
		
	}


}
