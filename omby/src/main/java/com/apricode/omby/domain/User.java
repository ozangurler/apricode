package com.apricode.omby.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.apricode.omby.transfer.LawsuitTransfer;


@Entity
@Table
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class User implements com.apricode.omby.domain.Entity, UserDetails{

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(User.class);
	private Set <UserLawsuit> lawsuitUsers = new HashSet<UserLawsuit>(0);
	private Long id;
	private Integer version;
	
	private Set<Role> roles = new HashSet<Role>();
    private String firstName;   
    private String userName;
    private String userNameOpaque;    

    private String email;    
    private String password;
    private Date createdOn;	
		
    
	protected User()
	{
		setCreatedOn(new Date());
		/* Reflection instantiation */
	}


    @Size(min = 1, max = 60)
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Size(min = 1, max = 60)
	@Column(unique = true, length = 60, nullable = false)
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		logger.debug("username set");
		this.userName = userName;
	}

	@Size(min = 1, max = 60)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}




	public User(String email, String passwordHash)
	{
		this.userName = email;
		this.userNameOpaque = generateRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",20);
		
		this.email = email;
		this.password = passwordHash;
		setCreatedOn(new Date());
	}


	public static String generateRandomString(String fromCharacters, int length)
	{
		Random rng = new Random();
		int lengthOfFromCharacters = fromCharacters.length();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = fromCharacters.charAt(rng.nextInt(  lengthOfFromCharacters  ));
	    }
	    return new String(text);
	}




//	@ElementCollection(fetch = FetchType.EAGER)
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.MERGE,  CascadeType.REFRESH}
			
			)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})		
	public Set<Role> getRoles()
	{
		return this.roles;
	}


	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}


	public void addRole(Role role)
	{
		this.roles.add(role);
	}


	@Override
	@Size(min = 1, max = 60)
	public String getPassword()
	{
		return this.password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		Set<Role> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}
    


	
	


	@Override
	@Transient
	public String getUsername()
	{
		return this.userName;
	}


	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}


	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return true;
	}


	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}


	@Override
	@Transient
	public boolean isEnabled()
	{
		return true;
	}    
    
    
    /**
     * 
     * @param lawsuit
     * @param role
     * @throws OmbyRuleException
     * 
     * 1 lawsuit can not have one user with different roles
     * 1 lawsuit can not have more than one judge
     * 
     */
	public void addLawsuit(Lawsuit lawsuit, Role role) throws OmbyRuleException{
		// private lawsuits do not except follower
		if (!lawsuit.getPublicLawsuit() && role.getName().equals(Role.FOLLOWER)){
			throw new OmbyRuleException();
		}
		
		UserLawsuit lawsuitUser = new UserLawsuit();
		lawsuitUser.setLawsuit(lawsuit);
		lawsuitUser.setUser(this);
		lawsuitUser.setStatus(new Integer(0));
		lawsuitUser.setRole(role);
		
		int countOfJudges = 0 ;
		Set<UserLawsuit> readLawsuitsFromDB = getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();			
			
			if ( readLawsuitFromDB.getLawsuit().getName().equals(lawsuit.getName()) 					
												&& 
					readLawsuitFromDB.getRole().getName().equals(Role.JUDGE)
					)
				{
					countOfJudges++;					
				}
			
			
			if ( readLawsuitFromDB.getLawsuit().getName().equals(lawsuit.getName()) 
				)
			{
				throw new OmbyRuleException();
				
			}
		}
		// FIXME baska kullanici ayni lawsuiti judge olarak eklerse readLawsuitFromDB icinde bu lawsuit 
		// gelmeyecegi icin ayni davada 1 den fazla juge izin veriyor.
		// lawsuit tarafi degil de user tarafinden addlawsuit user icin control her lawsuit icin ayri yapilmali
		if (countOfJudges > 0 && role.getName().equals(Role.JUDGE))
		{
			throw new OmbyRuleException();
		}
			
			
		lawsuitUsers.add(lawsuitUser);
	}
    
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public void setLawsuitUsers(Set <UserLawsuit> lawsuitUsers){this.lawsuitUsers = lawsuitUsers;}
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.user", orphanRemoval=true, 
			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//	@JsonManagedReference
	public Set <UserLawsuit> getLawsuitUsers(){
		return lawsuitUsers;
		}


	
	@Size(min = 1, max = 60)
	@Column(unique = true, length = 60, nullable = false)
	public String getUserNameOpaque() {
		return userNameOpaque;
	}


	public void setUserNameOpaque(String userNameOpaque) {
		this.userNameOpaque = userNameOpaque;
	}

	@Transient
	public List<LawsuitTransfer> getMyLawsuitTransfers() {
		List<LawsuitTransfer> allEntries =  new ArrayList<LawsuitTransfer>();
		
		for (UserLawsuit ul:  this.getLawsuitUsers() ){			
			Lawsuit ls = ul.getLawsuit();
			LawsuitTransfer lst = ls.getLawSuitTransfer();

			logger.info ("Lawsuit Name: " + lst.getName() + " Id: " + lst.getId() );
			allEntries.add (lst );			
		}
		
		return allEntries;
	}

}