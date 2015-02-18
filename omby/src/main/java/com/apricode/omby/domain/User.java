package com.apricode.omby.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table
public class User implements com.apricode.omby.domain.Entity, UserDetails{

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(User.class);
	private Set <UserLawsuit> lawsuitUsers = new HashSet<UserLawsuit>(0);
	private Long id;
	private Integer version;
	private Set<String> roles = new HashSet<String>();
    private String firstName;   
    private String userName;    
    private String email;    
    private String password;
    private Date createdOn;	
		
    
	protected User()
	{
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




	public User(String userName, String passwordHash)
	{
		this.userName = userName;
		this.password = passwordHash;
	}







	@ElementCollection(fetch = FetchType.EAGER)
	public Set<String> getRoles()
	{
		return this.roles;
	}


	public void setRoles(Set<String> roles)
	{
		this.roles = roles;
	}


	public void addRole(String role)
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
		Set<String> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", orphanRemoval=true, 
			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public Set <UserLawsuit> getLawsuitUsers(){return lawsuitUsers;}



}