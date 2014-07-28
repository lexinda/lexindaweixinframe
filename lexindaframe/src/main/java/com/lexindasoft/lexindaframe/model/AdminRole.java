package com.lexindasoft.lexindaframe.model;

public class AdminRole{

	private int roleId;
	
	private String roleName;
	
	private String userDescription;
	
	private int listOrder;
	
	private int userDisabled;
	
	private String roleModules;
	
	private String roleColumns;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getUserDisabled() {
		return userDisabled;
	}

	public void setUserDisabled(int userDisabled) {
		this.userDisabled = userDisabled;
	}

	public String getRoleModules() {
		return roleModules;
	}

	public void setRoleModules(String roleModules) {
		this.roleModules = roleModules;
	}

	public String getRoleColumns() {
		return roleColumns;
	}

	public void setRoleColumns(String roleColumns) {
		this.roleColumns = roleColumns;
	}
	
}
