package com.apricode.omby.domain;


import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;


public class ActionType {

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
