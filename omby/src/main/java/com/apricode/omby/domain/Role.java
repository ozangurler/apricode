package com.apricode.omby.domain;

import javax.validation.constraints.Size;

public class Role {
    /**
     */
    @Size(min = 1, max = 60)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
