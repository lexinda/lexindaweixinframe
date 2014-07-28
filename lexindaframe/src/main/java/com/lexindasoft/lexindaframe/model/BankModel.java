package com.lexindasoft.lexindaframe.model;

import java.util.Date;

public class BankModel {
	
	private int id;
	
	private String openid;
	
	private int bankType;
	
	private String bankUser;
	
	private String phone;
	
	private int gender;
	
	private int status;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int pageNum;
	
	private int pageSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getBankType() {
		return bankType;
	}

	public void setBankType(int bankType) {
		this.bankType = bankType;
	}

	public String getBankUser() {
		return bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "BankModel [id=" + id + ", openid=" + openid + ", bankType="
				+ bankType + ", bankUser=" + bankUser + ", phone=" + phone
				+ ", gender=" + gender + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", pageNum="
				+ pageNum + ", pageSize=" + pageSize + "]";
	}

}
