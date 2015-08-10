package com.apricode.omby.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@javax.persistence.Entity
public class ActionType implements com.apricode.omby.domain.Entity {
	
	
    public ActionType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
	/**
     */
    @Size(min = 1, max = 60)
    private String actionCode;
    
    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<Role>();
    
    
    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<OptVal> optVals = new HashSet<OptVal>();


    public ActionType(String actionCode) {
		super();
		this.actionCode = actionCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<OptVal> getOptVals() {
		return optVals;
	}

	public void setOptVals(Set<OptVal> optVals) {
		this.optVals = optVals;
	}
}
