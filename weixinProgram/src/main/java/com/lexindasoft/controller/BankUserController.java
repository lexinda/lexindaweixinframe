package com.lexindasoft.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.BankList;
import com.lexindasoft.lexindaframe.model.BankModel;
import com.lexindasoft.lexindaframe.model.ReportInfo;
import com.lexindasoft.lexindaframe.model.ReportStatus;
import com.lexindasoft.lexindaframe.model.ResultOauth;
import com.lexindasoft.lexindaframe.model.UserInfo;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.service.BankService;
import com.lexindasoft.lexindaframe.util.HttpClientWeixin;
import com.lexindasoft.lexindaframe.util.HttpParam;
import com.lexindasoft.lexindaframe.util.Inputs;
import com.lexindasoft.lexindaframe.util.RuntimeConfig;

@Controller
public class BankUserController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	BankService bankService;
	
	private final int PAGE_SIZE = 20;
	
	@RequestMapping(value="/bankOauth",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
    public String siteOauth(HttpServletRequest req,HttpServletResponse resp,Model model) throws UnsupportedEncodingException { 
		String code = Inputs.trimToNull(req.getParameter("code"));
		String state = Inputs.trimToNull(req.getParameter("state"));
		logger.debug("code"+code+":state"+state);
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("appid", RuntimeConfig.getInstance().getAppId()));
		params.add(new HttpParam("secret", RuntimeConfig.getInstance().getAppSecret()));
		params.add(new HttpParam("code", code));
		params.add(new HttpParam("grant_type", "authorization_code"));
		String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
		logger.debug(getOpenIdUrl);
		 String responseStr = new HttpClientWeixin().get(getOpenIdUrl,params);
		 responseStr=new String(responseStr.getBytes("iso8859-1"),"UTF-8");
		 logger.debug(responseStr);
		 ResultOauth accessTokenObject = new Gson().fromJson(responseStr,ResultOauth.class);
		 if(accessTokenObject == null){
			 int i=0;
			 while(accessTokenObject==null){
				 responseStr = new HttpClientWeixin().get(getOpenIdUrl,params);
				 responseStr=new String(responseStr.getBytes("iso8859-1"),"UTF-8");
				 logger.debug(responseStr);
				 accessTokenObject = new Gson().fromJson(responseStr,ResultOauth.class);
				 i++;
				 if(i>10){
					 break;
				 }
			 }
			 
		 }
		 if(accessTokenObject != null){
			String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
			List<HttpParam> params1 = new ArrayList<HttpParam>();
				params1.add(new HttpParam("access_token",RuntimeConfig.getInstance().getAssessToken()));
				params1.add(new HttpParam("openid",accessTokenObject.getOpenid()));
			String userInfoData = new HttpClientWeixin().get(getUserInfo,params1);
			userInfoData=new String(userInfoData.getBytes("iso8859-1"),"UTF-8");
			UserInfo userInfo = new Gson().fromJson(userInfoData, UserInfo.class);
			logger.debug(userInfo.toString());
			UserInfo resultUser = adminService.queryUserByOpenId(userInfo.getOpenid());
			String str=userInfo.getNickname();
			StringBuffer sb = new StringBuffer();
			if(str.length()>0){
		        for (int i = 0; i < str.length(); i++) {
		            char codePoint = str.charAt(i);
		 
		            if (isEmojiCharacter(codePoint)) {
		            	sb.append(codePoint);
		            }else{
		            	if(sb.toString().indexOf("^_^")>0){
		            		
		            	}else{
		            		sb.append("^_^");
		            	}
		            }
		        }
			}
			userInfo.setNickname(sb.toString());
			if(resultUser==null){
				adminService.insertWeixinUser(userInfo);
			}else if(!resultUser.getNickname().equals(userInfo.getNickname())||!resultUser.getHeadimgurl().equals(userInfo.getHeadimgurl())){
				adminService.updateWeixinUser(userInfo);
			}else{
				//@TODO 查看用户权限
				BankModel bankModel = new BankModel();
				bankModel = bankService.getBankInfoByOpenid(userInfo.getOpenid());
				List<BankList> bankList = bankService.getBankList();
				model.addAttribute("openId", userInfo.getOpenid());
				if(bankModel == null){
					model.addAttribute("bankList", bankList);
					return "site/bankRegister";
				}else{
					if(bankModel.getStatus()>0){
						model.addAttribute("resultUser", resultUser);
						return "site/bankReport";
					}else{
						return "site/bankReview";
					}
				}
			  }
				return null;
			}else{
				return null;
			}
    }  
	 private boolean isEmojiCharacter(char codePoint) {
	        return (codePoint == 0x0) ||
	                (codePoint == 0x9) ||
	                (codePoint == 0xA) ||
	                (codePoint == 0xD) ||
	                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
	                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
	                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	    }
	@RequestMapping(value="/bankForwardOauth",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteTestOauth(HttpServletRequest req,HttpServletResponse resp,Model model) { 
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+RuntimeConfig.getInstance().getAppId()+ "&redirect_uri=http://law02.gotoip55.com/bankOauth?response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    }
	
	@RequestMapping(value="/bankQuery",method=RequestMethod.GET)
	public String bankQuery(@RequestParam("openId") String openId,Model model){
		//@TODO 查看用户权限
		openId="o5TeKuKfOAjIZGaCgzn6-fKx9V-U";
		BankModel bankModel = new BankModel();
		bankModel = bankService.getBankInfoByOpenid(openId);
		List<BankList> bankList = bankService.getBankList();
		model.addAttribute("openId", openId);
		if(bankModel == null){
			model.addAttribute("bankList", bankList);
			return "site/bankRegister";
			
		}else{
			if(bankModel.getStatus()>0){
				return "site/bankReport";
			}else{
				return "site/bankReview";
			}
		}
	}
	
	@RequestMapping(value="/bankSave",method=RequestMethod.POST)
	public String bankSave(@RequestParam("openId") String openId,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("bankName") String bankName,
			@RequestParam("telephone") String telephone,
			@RequestParam("sax") int sax,
			@RequestParam("provinceName") int provinceName,
			Model model) throws UnsupportedEncodingException{
		bankName = Inputs.trimToNull(bankName);
		
		if(bankName!=null){
			bankName = new String(bankName.getBytes("iso8859-1"),"UTF-8");
		}
		int count = adminService.checkUserName(userName);
		if(count>0){
			List<BankList> bankList = bankService.getBankList();
			model.addAttribute("bankList", bankList);
			model.addAttribute("isSuccess", 1);
			model.addAttribute("openId", openId);
			return "site/bankRegister";
		}else{
			
			int rusult = adminService.addUser(userName, password, 3, null,bankName , 0,openId,provinceName);
			
			//@TODO 查看用户权限
			BankModel bankModel = new BankModel();
			bankModel.setOpenid(openId);
			bankModel.setBankUser(bankName);
			bankModel.setBankType(provinceName);
			bankModel.setPhone(telephone);
			bankModel.setGender(sax);
			
			int result1 = bankService.addBankInfo(bankModel);
			if(rusult>0&&result1>0){
				return "site/bankReview";
			}else{
				List<BankList> bankList = bankService.getBankList();
				model.addAttribute("bankList", bankList);
				model.addAttribute("isSuccess", 2);
				model.addAttribute("openId", openId);
				return "site/bankRegister";
			}
		}
	}
	
	@RequestMapping(value="/reportSave",method=RequestMethod.POST)
	public String reportSave(@RequestParam("openId") String openId,
			@RequestParam("receiptName") String receiptName,
			@RequestParam("businessName") String businessName,
			@RequestParam("telephone") String telephone,
			@RequestParam("sax") int sax,
			@RequestParam("provinceName") String provinceName,
			@RequestParam("cityName") String cityName,
			@RequestParam("areaName") String areaName,
			@RequestParam("street") String street,
			@RequestParam("machineName") int machineName,
			@RequestParam("rateName") String rateName,
			@RequestParam("deposit") String deposit,
			Model model) throws UnsupportedEncodingException{
		receiptName = Inputs.trimToNull(receiptName);
		
		if(receiptName!=null){
			receiptName = new String(receiptName.getBytes("iso8859-1"),"UTF-8");
		}
		
		businessName = Inputs.trimToNull(businessName);
		
		if(businessName!=null){
			businessName = new String(businessName.getBytes("iso8859-1"),"UTF-8");
		}
		
		provinceName = Inputs.trimToNull(provinceName);
				
		if(provinceName!=null){
			provinceName = new String(provinceName.getBytes("iso8859-1"),"UTF-8");
		}
				
		cityName = Inputs.trimToNull(cityName);
				
		if(cityName!=null){
			cityName = new String(cityName.getBytes("iso8859-1"),"UTF-8");
		}
				
		areaName = Inputs.trimToNull(areaName);
				
		if(areaName!=null){
			areaName = new String(areaName.getBytes("iso8859-1"),"UTF-8");
		}
				
		street = Inputs.trimToNull(street);
				
		if(street!=null){
			street = new String(street.getBytes("iso8859-1"),"UTF-8");
		}
		//@TODO 查看用户权限
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setOpenid(openId);
		reportInfo.setMerchantName(receiptName);
		reportInfo.setUserName(businessName);
		reportInfo.setPhone(telephone);
		reportInfo.setGender(sax);
		reportInfo.setProvince(provinceName);
		reportInfo.setCity(cityName);
		if(!areaName.contains("请选择")){
			reportInfo.setArea(areaName);
		}else{
			reportInfo.setArea("");
		}
		reportInfo.setStreet(street);
		reportInfo.setMachineName(machineName);
		if(rateName != null){
			reportInfo.setRate(rateName);
		}
		
		reportInfo.setDeposit(deposit);
		
		int result = bankService.addReportInfo(reportInfo);
		
		int isSuccess = 1;
		
		UserInfo resultUser = adminService.queryUserByOpenId(openId);
		model.addAttribute("resultUser", resultUser);
		model.addAttribute("openId", openId);
		if(result>0){
			isSuccess=0;
			model.addAttribute("isSuccess", isSuccess);
		}else{
			model.addAttribute("isSuccess", isSuccess);
		}
		
		return "site/bankReport";
	}
	
	@RequestMapping(value="/getReportInfoList",method=RequestMethod.GET)
	public String getReportInfoListByOpenId(@RequestParam(value="openId")String openId,Model model){
		List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
		reportInfoList = bankService.getReportInfoListByOpenId(openId);
		model.addAttribute("reportInfoList", reportInfoList);
		model.addAttribute("reportInfoListSize", reportInfoList.size());
		return "site/bankReportRecord";
	}
	
	@RequestMapping(value="/sale/bankReportRecord",method=RequestMethod.GET)
    public String bankReport(@RequestParam(value="openId",required=false) String openId,
					    	Model model){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<ReportInfo> reportList = new ArrayList<ReportInfo>();
		paramMap.put("merchantName", null);
		paramMap.put("openId", openId);
		paramMap.put("status", 0);
		reportList = bankService.getReportInfoListNomal(paramMap);
		Map<String,String> statusMap = new HashMap<String,String>();
		statusMap.put("0", "全部");
		statusMap.put("1", "未处理");
		statusMap.put("2", "受理报件");
		statusMap.put("3", "收集资料");
		statusMap.put("4", "入件审核");
		statusMap.put("5", "审核通过");
		statusMap.put("6", "审核不通过");
		statusMap.put("7", "准备装机");
		statusMap.put("8", "装机收款成功");
		statusMap.put("9", "装机收款不成功");
		statusMap.put("10", "结款成功");
		Map<String,String> mechineMap = new HashMap<String,String>();
		mechineMap.put("0", "全部");
		mechineMap.put("1", "固定");
		mechineMap.put("2", "移动");
		mechineMap.put("3", "半移动");
		mechineMap.put("4", "分体");
		for(ReportInfo reportInfo : reportList){
			String statusDesc = statusMap.get(""+reportInfo.getStatus()+"");
			reportInfo.setStatusDesc(statusDesc);
			String mechineDesc = mechineMap.get(""+reportInfo.getMachineName()+"");
			reportInfo.setMachineDesc(mechineDesc);
		}
		model.addAttribute("reportInfoListSize", reportList.size());
		model.addAttribute("reportInfoList", reportList);
		
    	return "site/bankReportRecord";
    }
}
