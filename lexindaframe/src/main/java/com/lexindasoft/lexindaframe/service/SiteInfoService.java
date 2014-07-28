package com.lexindasoft.lexindaframe.service;

import java.util.List;

import com.lexindasoft.lexindaframe.model.HandlePeople;
import com.lexindasoft.lexindaframe.model.SiteInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;

public interface SiteInfoService {
	
	public int addSiteInfo(SiteInfo siteInfo);
	
	public List<SiteInfo> findAllSiteInfo(SiteInfo siteInfo);
	
	public List<SiteInfo> findSiteInfoByName(SiteInfo siteInfo);
	
	public int deleteSiteInfo(int siteInfoId);
	
	public SiteInfo showSiteInfoDetail(int siteInfoId);
	
	public int updateSiteInfo(SiteInfo siteInfo);
	
	public List<WeixinBusiness> findAllWeixinBusiness(WeixinBusiness weixinBusiness);
	
	public List<WeixinBusiness> findWeixinBusinessByReceiptName(WeixinBusiness weixinBusiness);
	
	public WeixinBusiness findWeixinBusinessById(int id);
	
	public int deleteWeixinBusiness(int id);
	
	public HandlePeople queryHandlePeopleById(int id);
	
	public int insertHandlePeople(HandlePeople handlePeople);
	
	public int deleteHandlePeople(int id);
	
	public List<HandlePeople> findHandleByPeopleName(HandlePeople handlePeople);
	
	public List<HandlePeople> findAllHandleByPeopleName(HandlePeople handlePeople);
}
