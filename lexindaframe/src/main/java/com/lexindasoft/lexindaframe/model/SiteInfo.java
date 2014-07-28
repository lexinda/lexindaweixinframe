package com.lexindasoft.lexindaframe.model;

import java.util.Date;

public class SiteInfo {
	
	private int id;
	
	private int siteInfoType;
	
	private String siteInfoName;
	
	private String siteInfoSax;
	
	private String siteInfoImg;
	
	private String siteInfoContent;

	private Date createTime;
	
	private int pageNum;
	
	private int pageSize;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteInfoType() {
		return siteInfoType;
	}

	public void setSiteInfoType(int siteInfoType) {
		this.siteInfoType = siteInfoType;
	}

	public String getSiteInfoName() {
		return siteInfoName;
	}

	public void setSiteInfoName(String siteInfoName) {
		this.siteInfoName = siteInfoName;
	}

	public String getSiteInfoSax() {
		return siteInfoSax;
	}

	public void setSiteInfoSax(String siteInfoSax) {
		this.siteInfoSax = siteInfoSax;
	}

	public String getSiteInfoImg() {
		return siteInfoImg;
	}

	public void setSiteInfoImg(String siteInfoImg) {
		this.siteInfoImg = siteInfoImg;
	}

	public String getSiteInfoContent() {
		return siteInfoContent;
	}

	public void setSiteInfoContent(String siteInfoContent) {
		this.siteInfoContent = siteInfoContent;
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
