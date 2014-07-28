package com.lexindasoft.lexindaframe.model;

import java.util.Date;

public class Admin {

	private int userId;

	private String userName;

	private String userPassword;

	private int roleId;
	
	private String roleName;

	private String lastloginIp;

	private Date lastloginTime;

	private String userEmail;

	private String realName;

	private int userStatus;

	private int storeId;
	
	private String openId;
	
	private int bankType;
	
	private String bankName;
	
	private Date createTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getLastloginIp() {
		return lastloginIp;
	}

	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	public Date getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getBankType() {
		return bankType;
	}

	public void setBankType(int bankType) {
		this.bankType = bankType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", lastloginIp=" + lastloginIp
				+ ", lastloginTime=" + lastloginTime + ", userEmail="
				+ userEmail + ", realName=" + realName + ", userStatus="
				+ userStatus + ", storeId=" + storeId + ", openId=" + openId
				+ ", bankType=" + bankType + ", bankName=" + bankName
				+ ", createTime=" + createTime + "]";
	}

}
