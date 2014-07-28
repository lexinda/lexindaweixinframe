package com.lexindasoft.lexindaframe.model;

import java.util.Date;

public class HandlePeople {
	private int id;
	
	private String handlePeople;
	
	private String handlePhone;
	
	private Date createTime;
	
	private int pageNum;
	
	private int pageSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	public String getHandlePhone() {
		return handlePhone;
	}

	public void setHandlePhone(String handlePhone) {
		this.handlePhone = handlePhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
}
