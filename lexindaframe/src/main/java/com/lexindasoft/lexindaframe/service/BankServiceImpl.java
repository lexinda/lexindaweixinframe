package com.lexindasoft.lexindaframe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexindasoft.lexindaframe.dao.BankDao;
import com.lexindasoft.lexindaframe.model.BankList;
import com.lexindasoft.lexindaframe.model.BankModel;
import com.lexindasoft.lexindaframe.model.ReportInfo;
import com.lexindasoft.lexindaframe.model.ReportStatus;
@Service
public class BankServiceImpl implements BankService {
	@Autowired
	BankDao bankDao;

	public BankModel getBankInfoByOpenid(String openId) {
		// TODO Auto-generated method stub
		return bankDao.getBankInfoByOpenid(openId);
	}

	public List<BankList> getBankList() {
		// TODO Auto-generated method stub
		return bankDao.getBankList();
	}

	public int addBankInfo(BankModel bankModel) {
		// TODO Auto-generated method stub
		return bankDao.addBankInfo(bankModel);
	}

	public int updateBankInfo(String openId) {
		// TODO Auto-generated method stub
		return bankDao.updateBankInfo(openId);
	}

	public int addReportInfo(ReportInfo reportInfo) {
		// TODO Auto-generated method stub
		return bankDao.addReportInfo(reportInfo);
	}

	public List<ReportInfo> getReportInfoListByOpenId(String openId) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListByOpenId(openId);
	}

	public List<ReportInfo> getReportInfoListByOpenIds(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListByOpenIds(paramMap);
	}

	public List<ReportInfo> getReportInfoListByOpenIdP(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListByOpenIdP(paramMap);
	}

	public BankModel getOpenid(String bankUser) {
		// TODO Auto-generated method stub
		return bankDao.getOpenid(bankUser);
	}

	public List<ReportInfo> getReportInfoListNomal(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListNomal(paramMap);
	}

	public List<ReportInfo> getReportInfoListNomalP(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListNomalP(paramMap);
	}

	public List<ReportInfo> getReportInfoListManager(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListManager(paramMap);
	}

	public List<ReportInfo> getReportInfoListManagerP(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bankDao.getReportInfoListManagerP(paramMap);
	}

	public List<ReportStatus> getStatusList() {
		// TODO Auto-generated method stub
		return bankDao.getStatusList();
	}

	public BankList getBankListById(int bankId) {
		// TODO Auto-generated method stub
		return bankDao.getBankListById(bankId);
	}

	public ReportStatus getStatusListById(int statusId) {
		// TODO Auto-generated method stub
		return bankDao.getStatusListById(statusId);
	}

	public int updateReportStatus(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return bankDao.updateReportStatus(dataMap);
	}
	
}
