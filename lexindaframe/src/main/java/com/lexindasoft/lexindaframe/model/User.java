package com.lexindasoft.lexindaframe.model;

import java.io.Serializable;


public class User implements Serializable{
	
	private static final long serialVersionUID = 7247714666080613254L;
	
	private int id;
	
	private String userName;
	
	private String userPassword;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
