package com.lexindasoft.lexindaframe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexindasoft.lexindaframe.dao.SiteInfoDao;
import com.lexindasoft.lexindaframe.model.HandlePeople;
import com.lexindasoft.lexindaframe.model.SiteInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;

@Service
public class SiteInfoServiceImpl implements SiteInfoService {

	@Autowired
	SiteInfoDao siteInfoDao;
	
	public int addSiteInfo(SiteInfo siteInfo) {
		// TODO Auto-generated method stub
		int siteInfoId = siteInfoDao.insertSiteInfo(siteInfo);
		return siteInfoId;
	}

	public List<SiteInfo> findAllSiteInfo(SiteInfo siteInfo) {
		// TODO Auto-generated method stub
		if(siteInfo.getSiteInfoName() != null){
			siteInfo.setSiteInfoName("%"+siteInfo.getSiteInfoName()+"%");
		}
		
		return siteInfoDao.findAllSiteInfo(siteInfo);
	}

	public List<SiteInfo> findSiteInfoByName(SiteInfo siteInfo) {
		// TODO Auto-generated method stub
		if(siteInfo.getSiteInfoName() != null){
			siteInfo.setSiteInfoName("%"+siteInfo.getSiteInfoName()+"%");
		}
		
		return siteInfoDao.findSiteInfoByName(siteInfo);
	}

	public int deleteSiteInfo(int siteInfoId) {
		// TODO Auto-generated method stub
		return siteInfoDao.deleteSiteInfo(siteInfoId);
	}

	public SiteInfo showSiteInfoDetail(int siteInfoId) {
		// TODO Auto-generated method stub
		return siteInfoDao.showSiteInfoDetail(siteInfoId);
	}

	public int updateSiteInfo(SiteInfo siteInfo) {
		// TODO Auto-generated method stub
		return siteInfoDao.updateSiteInfo(siteInfo);
	}

	public List<WeixinBusiness> findAllWeixinBusiness(
			WeixinBusiness weixinBusiness) {
		// TODO Auto-generated method stub
		return siteInfoDao.findAllWeixinBusiness(weixinBusiness);
	}

	public List<WeixinBusiness> findWeixinBusinessByReceiptName(
			WeixinBusiness weixinBusiness) {
		// TODO Auto-generated method stub
		return siteInfoDao.findWeixinBusinessByReceiptName(weixinBusiness);
	}

	public WeixinBusiness findWeixinBusinessById(int id) {
		// TODO Auto-generated method stub
		return siteInfoDao.findWeixinBusinessById(id);
	}

	public int deleteWeixinBusiness(int id) {
		// TODO Auto-generated method stub
		return siteInfoDao.deleteWeixinBusiness(id);
	}

	public HandlePeople queryHandlePeopleById(int id) {
		// TODO Auto-generated method stub
		return siteInfoDao.queryHandlePeopleById(id);
	}

	public int insertHandlePeople(HandlePeople handlePeople) {
		// TODO Auto-generated method stub
		return siteInfoDao.insertHandlePeople(handlePeople);
	}

	public int deleteHandlePeople(int id) {
		// TODO Auto-generated method stub
		return siteInfoDao.deleteHandlePeople(id);
	}

	public List<HandlePeople> findHandleByPeopleName(HandlePeople handlePeople) {
		// TODO Auto-generated method stub
		
		if(handlePeople.getHandlePeople()!=null){
			handlePeople.setHandlePeople("%"+handlePeople.getHandlePeople()+"%");
		}
		
		return siteInfoDao.findHandleByPeopleName(handlePeople);
	}

	public List<HandlePeople> findAllHandleByPeopleName(
			HandlePeople handlePeople) {
		// TODO Auto-generated method stub
		if(handlePeople.getHandlePeople()!=null){
			handlePeople.setHandlePeople("%"+handlePeople.getHandlePeople()+"%");
		}
		return siteInfoDao.findAllHandleByPeopleName(handlePeople);
	}

}
