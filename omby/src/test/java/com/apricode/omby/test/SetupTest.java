package com.apricode.omby.test;

import java.util.Date;
import java.util.HashSet;
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

import com.apricode.omby.dao.ActionTypeDao;
import com.apricode.omby.dao.LawsuitDao;
import com.apricode.omby.dao.NewsEntryDao;
import com.apricode.omby.dao.OptValDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.dao.UserActionDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.domain.ActionType;
import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.NewsEntry;
import com.apricode.omby.domain.OmbyRuleException;
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
	private NewsEntryDao newsEntryDao;
	
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

	
	public void initDataBaseNewsEntries()
	{
//		User userUser = new User("user", this.passwordEncoder.encode("user"));
//		userUser.addRole("user");
//		this.userDao.save(userUser);
//
//		User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
//		adminUser.addRole("user");
//		adminUser.addRole("admin");
//		this.userDao.save(adminUser);

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		for (int i = 0; i < 10; i++) {
			NewsEntry newsEntry = new NewsEntry();
			newsEntry.setContent("This is example content " + i);
			newsEntry.setDate(new Date(timestamp));
			this.newsEntryDao.save(newsEntry);
			timestamp += 1000 * 60 * 60;
		}
	}	
	
	
	
	// We will have only one test to setup
	@Test
	public void testInitializeTestDB() {
		initDataBaseNewsEntries(); // FIXME sample coding for angular will be deleted later
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
		//////////////////////////////////////////////////////////////////////////////// ADDED ---------------
		
		// create action types
		ActionType voteAction = new ActionType("OY_KULLAN");
		voteAction = this.actionTypeDao.save(voteAction);
		ActionType questionAction = new ActionType("SORU_SOR");
		questionAction = this.actionTypeDao.save(questionAction);
		ActionType commentAction = new ActionType("YORUM_YAP");
		commentAction = this.actionTypeDao.save(commentAction);
		ActionType decideAction = new ActionType("KARAR_VER");
		decideAction = this.actionTypeDao.save(decideAction);
		
		
		Set<Role> juryRoles = new HashSet<Role>();
		juryRoles.add(juryRole);
		
		Set<Role> judgeRoles= new HashSet<Role>();
		judgeRoles.add(judgeRole);

		// Option values for action types
		OptVal masum = new OptVal("Masum");
		masum = this.optValDao.save(masum);
		OptVal suclu = new OptVal("Suclu");
		suclu = this.optValDao.save(suclu);
		OptVal basarili = new OptVal("Basarili");
		basarili = this.optValDao.save(basarili);
		
		Set<OptVal> optVals = new HashSet<OptVal>();		

		// which roles can make which action types
		decideAction.setRoles(judgeRoles);
		optVals.add(masum);
		optVals.add(suclu);
		decideAction.setOptVals(optVals);
		decideAction = this.actionTypeDao.save(decideAction);

		
		voteAction.setRoles(juryRoles);
		voteAction.setOptVals(optVals);		
		voteAction = this.actionTypeDao.save(voteAction);


		// Create Users and  should decide masum other as suclu
		//---------------------------------------------------------
		// create user1 jury
		String userYilmaz = "yilmazgorali@hotmail.com";
		User createdUserYilmaz = new User(userYilmaz,this.passwordEncoder.encode(userYilmaz));
		createdUserYilmaz.setFirstName("Yilmaz");
		createdUserYilmaz.addRole(juryRole);
		createdUserYilmaz = this.userDao.save(createdUserYilmaz);		
		
		// create user2 jury
		String userNameEngin = "engincabar@hotmail.com";
		User createdUserJuryEngin = new User(userNameEngin,this.passwordEncoder.encode(userNameEngin));		
		createdUserJuryEngin.setFirstName("Engin");	
		createdUserJuryEngin.addRole(juryRole);
		createdUserJuryEngin = this.userDao.save(createdUserJuryEngin);

		// create user3 judge 	
		String userNameJudge = "judy@hotmail.com";
		User judgeJudy = new User(userNameJudge,this.passwordEncoder.encode(userNameJudge));
		judgeJudy.setFirstName("Judge");
		judgeJudy.addRole(judgeRole);
		judgeJudy = this.userDao.save(judgeJudy);
		
		//-----------------------Add users to lawsuites		
		try {
			dryCleanLawsuit.addUser(createdUserYilmaz, juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUserYilmaz = this.userDao.save(createdUserYilmaz);

			dryCleanLawsuit.addUser(createdUserJuryEngin, juryRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			createdUserJuryEngin = this.userDao.save(createdUserJuryEngin);
			
			dryCleanLawsuit.addUser(judgeJudy, judgeRole);
			dryCleanLawsuit = this.lawsuitDao.save(dryCleanLawsuit);
			judgeJudy = this.userDao.save(judgeJudy);				
			
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------
		// One Jury votes masum other as suclu
		// First Jury Yilmaz Voted masum
		UserAction juryVoted = new UserAction();
		juryVoted.setActionType(voteAction);
		juryVoted.setLawsuit(dryCleanLawsuit);
		juryVoted.setVal(masum.getValCode()); //---- masum decision made as string
		juryVoted.setOptVal(masum);  //    --- masum decision made
		juryVoted.setRole(juryRole);
		juryVoted.setUser(createdUserYilmaz);
		juryVoted = userActionDao.save(juryVoted);
		
		// Second Jury Engin voted suclu
		UserAction juryVotedSuclu = new UserAction();
		juryVotedSuclu.setActionType(voteAction);
		juryVotedSuclu.setLawsuit(dryCleanLawsuit);
		juryVotedSuclu.setVal(suclu.getValCode()); //---- suclu decision made as string
		juryVotedSuclu.setOptVal(suclu);  //    --- suclu decision made
		juryVotedSuclu.setRole(juryRole);
		juryVotedSuclu.setUser(createdUserJuryEngin);
		juryVotedSuclu = userActionDao.save(juryVotedSuclu);		
		

		//Until here it was a setup that specifies judge can decide as masum or suclu
		UserAction judgeDecided = new UserAction();
		judgeDecided.setActionType(decideAction);
		judgeDecided.setLawsuit(dryCleanLawsuit);
		judgeDecided.setVal(masum.getValCode());
		judgeDecided.setOptVal(masum);   // judge decides as masum
		judgeDecided.setRole(judgeRole);
		judgeDecided.setUser(judgeJudy);
		judgeDecided = userActionDao.save(judgeDecided);
		
		// calculate jury points after judge decides masum
		
		
		
		
		
		
		
		
	}
	private void createRoles(){
		// create roles
		suerRole = this.roleDao.save(suerRole);
		defendantRole = this.roleDao.save(defendantRole);
		prosecutorRole = this.roleDao.save(prosecutorRole);
		attorneyRole = this.roleDao.save(attorneyRole);
		judgeRole = this.roleDao.save(judgeRole);
		juryRole= this.roleDao.save(juryRole);
		followerRole = this.roleDao.save(followerRole);
		witnessRole = this.roleDao.save(witnessRole);
	}


}
