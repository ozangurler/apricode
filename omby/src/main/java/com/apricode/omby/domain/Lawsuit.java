package com.apricode.omby.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

public class Lawsuit {
	  /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserLawsuit> users = new HashSet<UserLawsuit>();

    /**
     */
    @Size(min = 1, max = 60)
    private String name;

    /**
     */
    private Boolean isPublic;

	public Set<UserLawsuit> getUsers() {
		return users;
	}

	public void setUsers(Set<UserLawsuit> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
}
