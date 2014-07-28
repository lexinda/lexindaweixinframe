package com.lexindasoft.lexindaframe.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lexindasoft.lexindaframe.model.BankList;
import com.lexindasoft.lexindaframe.model.BankModel;
import com.lexindasoft.lexindaframe.model.ReportInfo;
import com.lexindasoft.lexindaframe.model.ReportStatus;


@Component
public interface BankDao {

	public int addBankInfo(BankModel bankModel);
	
	public int updateBankInfo(String openId);
	
	public BankModel getBankInfoByOpenid(String openId);
		
	public List<BankList> getBankList();
	
	public int addReportInfo(ReportInfo reportInfo);
	
	public List<ReportInfo> getReportInfoListByOpenId(String openId);
	
	public List<ReportInfo> getReportInfoListByOpenIds(
			Map<String, Object> paramMap);
	
	public List<ReportInfo> getReportInfoListByOpenIdP(
			Map<String, Object> paramMap);
	
	public List<ReportInfo> getReportInfoListNomal(
			Map<String, Object> paramMap);
	
	public List<ReportInfo> getReportInfoListNomalP(
			Map<String, Object> paramMap);
	
	public List<ReportInfo> getReportInfoListManager(
			Map<String, Object> paramMap);
	
	public List<ReportInfo> getReportInfoListManagerP(
			Map<String, Object> paramMap);
	
	public BankModel getOpenid(String bankUser);
	
	public List<ReportStatus> getStatusList();
	
	public BankList getBankListById(int bankId);
	
	public ReportStatus getStatusListById(int statusId);
	
	public int updateReportStatus(Map<String,Object> dataMap);
	
}
