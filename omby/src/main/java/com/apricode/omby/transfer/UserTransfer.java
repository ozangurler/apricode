package com.apricode.omby.transfer;

import java.util.Map;


public class UserTransfer
{

	private final String userName;

	private final Map<String, Boolean> roles;


	public UserTransfer(String userName, Map<String, Boolean> roles)
	{
		this.userName = userName;
		this.roles = roles;
	}


	public String getUserName()
	{
		return this.userName;
	}


	public Map<String, Boolean> getRoles()
	{
		return this.roles;
	}

}