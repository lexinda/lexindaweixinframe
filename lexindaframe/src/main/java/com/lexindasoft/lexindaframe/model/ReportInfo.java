package com.lexindasoft.lexindaframe.model;

import java.util.Date;

public class ReportInfo {
	private int id;
	private String openid;
	private String bankUser;
	private String bankType;
	private String merchantName;
	private String userName;
	private String phone;
	private int gender;
	private String province;
	private String city;
	private String area;
	private String street;
	private int machineName;
	private String machineDesc;
	private String rate;
	private String deposit;
	private int status;
	private String statusDesc;
	private String content;
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
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getMachineName() {
		return machineName;
	}
	public void setMachineName(int machineName) {
		this.machineName = machineName;
	}
	
	public String getMachineDesc() {
		return machineDesc;
	}
	public void setMachineDesc(String machineDesc) {
		this.machineDesc = machineDesc;
	}
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	@Override
	public String toString() {
		return "ReportInfo [id=" + id + ", openid=" + openid + ", bankUser="
				+ bankUser + ", bankType=" + bankType + ", merchantName="
				+ merchantName + ", userName=" + userName + ", phone=" + phone
				+ ", gender=" + gender + ", province=" + province + ", city="
				+ city + ", area=" + area + ", street=" + street
				+ ", machineName=" + machineName + ", machineDesc="
				+ machineDesc + ", rate=" + rate + ", deposit=" + deposit
				+ ", status=" + status + ", statusDesc=" + statusDesc
				+ ", content=" + content + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", pageNum=" + pageNum
				+ ", pageSize=" + pageSize + "]";
	}
	
}
