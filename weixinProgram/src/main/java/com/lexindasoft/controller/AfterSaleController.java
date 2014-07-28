package com.lexindasoft.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.lexindasoft.lexindaframe.model.HandlePeople;
import com.lexindasoft.lexindaframe.model.ReportInfo;
import com.lexindasoft.lexindaframe.model.ReportStatus;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.service.BankService;
import com.lexindasoft.lexindaframe.service.SiteInfoService;
import com.lexindasoft.lexindaframe.util.Inputs;

@Controller
public class AfterSaleController {

	private final Log logger = LogFactory.getLog(getClass());
	
	private final int PAGE_SIZE = 20;
	
	@Autowired
	SiteInfoService siteInfoService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	BankService bankService;
	
	@RequestMapping(value="/sale/repairMachine",method=RequestMethod.GET)
    public String repairMachine(@RequestParam("type") int type,
					    		@RequestParam(value="receiptName",required=false) String receiptName,
					    		@RequestParam("page") int page,
					    		@RequestParam("status") int status, Model model){
		 
		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		receiptName=Inputs.trimToNull(receiptName);
		WeixinBusiness weixinBusiness = getWeixinBusiness(-1,null,receiptName,null,null,null,type,status,-1,pageIndex,PAGE_SIZE);
		
		List<WeixinBusiness> dataList = siteInfoService.findAllWeixinBusiness(weixinBusiness);
		int totalPage = (int)Math.ceil(dataList.size() *1.0 / PAGE_SIZE);
		List<WeixinBusiness> weixinBusinessList = siteInfoService.findWeixinBusinessByReceiptName(weixinBusiness);
		HandlePeople handlePeopleO = getHandlePeople(-1,null,null,null,pageIndex,PAGE_SIZE);
		List<HandlePeople> peopleList = siteInfoService.findAllHandleByPeopleName(handlePeopleO);
		model.addAttribute("peopleList", peopleList);
		model.addAttribute("weixinBusinessList", weixinBusinessList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("receiptName", receiptName);
		model.addAttribute("type", type);
		model.addAttribute("status", status);
		
    	return "sale/sale-repairMachine";
    }
	
	@RequestMapping(value="/sale/repairMachine",method=RequestMethod.POST)
    public String repairMachineQuery(@RequestParam("type") int type,@RequestParam(value="receiptName",required=false) String receiptName,@RequestParam("page") int page,@RequestParam("status") int status, Model model){
		 
		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		receiptName=Inputs.trimToNull(receiptName);
		WeixinBusiness weixinBusiness = getWeixinBusiness(-1,null,receiptName,null,null,null,type,status,-1,pageIndex,PAGE_SIZE);
		
		List<WeixinBusiness> dataList = siteInfoService.findAllWeixinBusiness(weixinBusiness);
		int totalPage = (int)Math.ceil(dataList.size() *1.0 / PAGE_SIZE);
		List<WeixinBusiness> weixinBusinessList = siteInfoService.findWeixinBusinessByReceiptName(weixinBusiness);
		HandlePeople handlePeopleO = getHandlePeople(-1,null,null,null,pageIndex,PAGE_SIZE);
		List<HandlePeople> peopleList = siteInfoService.findAllHandleByPeopleName(handlePeopleO);
		model.addAttribute("peopleList", peopleList);
		model.addAttribute("weixinBusinessList", weixinBusinessList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("receiptName", receiptName);
		model.addAttribute("type", type);
		model.addAttribute("status", status);
		
    	return "sale/sale-repairMachine";
    }
	
	@RequestMapping(value="/sale/bankReport",method=RequestMethod.GET)
    public String bankReport(@RequestParam(value="bankUser",required=false) String bankUser,
					    		@RequestParam(value="bankType",required=false) Integer bankType,
					    		@RequestParam(value="merchantName",required=false) String merchantName,
					    		@RequestParam("page") int page,
					    		@RequestParam("status") int status, 
					    		@RequestParam("roleId") int roleId,
					    		@RequestParam("userId") int userId,Model model){
		 
		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		Admin admin = adminService.getUserById(userId);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> paramMapPage = new HashMap<String,Object>();
		List<ReportInfo> reportList = new ArrayList<ReportInfo>();
		List<ReportInfo> reportListPage = new ArrayList<ReportInfo>();
		if(roleId==3){
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			int bankTypes = admin.getBankType();
			
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("bankType", bankTypes);
			paramMap.put("status", status);
			
			if(bankUser!=null){
				paramMapPage.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMapPage.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("bankType", bankTypes);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListManager(paramMap);
			reportListPage = bankService.getReportInfoListManagerP(paramMapPage);
		}else if(roleId==4){
			
			String openId = admin.getOpenId();
			
			merchantName = Inputs.trimToNull(merchantName);
			
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("merchantName", merchantName);
			paramMap.put("openId", openId);
			paramMap.put("status", status);
			
			
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("merchantName", merchantName);
			paramMapPage.put("openId", openId);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListNomal(paramMap);
			reportListPage = bankService.getReportInfoListNomalP(paramMapPage);
		}else{
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			/*String openId = null;
			if(bankUser != null){
				BankModel bankInfo = bankService.getOpenid(bankUser);
				openId = bankInfo.getOpenid();
			}*/
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			
			paramMap.put("bankType", bankType);
			paramMap.put("status", status);
			
			
			if(bankUser!=null){
				paramMapPage.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMapPage.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("bankType", bankType);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListByOpenIds(paramMap);
			reportListPage = bankService.getReportInfoListByOpenIdP(paramMapPage);
		}
		for(ReportInfo reportInfo : reportList){
			BankList bankList = bankService.getBankListById(Integer.parseInt(reportInfo.getBankType()));
			String bankName = bankList.getBankDesc();
			reportInfo.setBankType(bankName);
		}
		List<BankList> bankList = bankService.getBankList();
		List<ReportStatus> statusList = bankService.getStatusList();
		int totalPage = (int)Math.ceil(reportList.size() *1.0 / PAGE_SIZE);
		model.addAttribute("reportInfoList", reportList);
		model.addAttribute("bankList", bankList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("bankUser", bankUser);
		model.addAttribute("bankType", bankType);
		model.addAttribute("merchantName", merchantName);
		model.addAttribute("statusId", status);
		model.addAttribute("roleId", roleId);
		model.addAttribute("userId", userId);
		
    	return "sale/sale-bankReport";
    }
	
	@RequestMapping(value="/sale/bankReport",method=RequestMethod.POST)
    public String bankReportQuery(@RequestParam(value="bankUser",required=false) String bankUser,
						    		@RequestParam(value="bankType",required=false) Integer bankType,
						    		@RequestParam(value="merchantName",required=false) String merchantName,
    								@RequestParam("page") int page,
    								@RequestParam("status") int status, 
    								@RequestParam("roleId") int roleId,
    								@RequestParam("userId") int userId,Model model){
		 
		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		Admin admin = adminService.getUserById(userId);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> paramMapPage = new HashMap<String,Object>();
		List<ReportInfo> reportList = new ArrayList<ReportInfo>();
		List<ReportInfo> reportListPage = new ArrayList<ReportInfo>();
		if(roleId==3){
			int bankTypes = admin.getBankType();
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("bankType", bankTypes);
			paramMap.put("status", status);
			
			
			if(bankUser!=null){
				paramMapPage.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMapPage.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("bankType", bankTypes);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListManager(paramMap);
			reportListPage = bankService.getReportInfoListManagerP(paramMapPage);
		}else if(roleId==4){
			String openId = admin.getOpenId();
			merchantName = Inputs.trimToNull(merchantName);
			
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("openId", openId);
			paramMap.put("merchantName", merchantName);
			paramMap.put("status", status);
			
			
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("merchantName", merchantName);
			paramMapPage.put("openId", openId);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListNomal(paramMap);
			reportListPage = bankService.getReportInfoListNomalP(paramMapPage);
		}else{
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			/*receiptName=Inputs.trimToNull(receiptName);
			String openId = null;
			if(receiptName != null){
				BankModel bankInfo = bankService.getOpenid(receiptName);
				openId = bankInfo.getOpenid();
			}*/
			
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			
			paramMap.put("bankType", bankType);
			paramMap.put("status", status);
			
			if(bankUser!=null){
				paramMapPage.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMapPage.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMapPage.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMapPage.put("merchantName", merchantName);
			}
			paramMapPage.put("bankType", bankType);
			paramMapPage.put("status", status);
			paramMapPage.put("pageIndex", pageIndex);
			paramMapPage.put("pageSize", PAGE_SIZE);
			
			reportList = bankService.getReportInfoListByOpenIds(paramMap);
			reportListPage = bankService.getReportInfoListByOpenIdP(paramMapPage);
		}
		for(ReportInfo reportInfo : reportList){
			BankList bankList = bankService.getBankListById(Integer.parseInt(reportInfo.getBankType()));
			String bankName = bankList.getBankDesc();
			reportInfo.setBankType(bankName);
		}
		List<BankList> bankList = bankService.getBankList();
		List<ReportStatus> statusList = bankService.getStatusList();
		int totalPage = (int)Math.ceil(reportList.size() *1.0 / PAGE_SIZE);
		model.addAttribute("reportInfoList", reportList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("bankList", bankList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("bankUser", bankUser);
		model.addAttribute("bankType", bankType);
		model.addAttribute("merchantName", merchantName);
		model.addAttribute("statusId", status);
		model.addAttribute("roleId", roleId);
		model.addAttribute("userId", userId);
		
    	return "sale/sale-bankReport";
    }
	
	@RequestMapping(value="/sale/showInfoDetail",method=RequestMethod.GET)
    public String showInfoDetail(@RequestParam("type") int type,
    								@RequestParam("id") int id,
    								@RequestParam(value="receiptName",required=false) String receiptName,
    								@RequestParam("page") int page,
    								@RequestParam("status") int status, 
    								Model model){
		WeixinBusiness weixinBusinessData = siteInfoService.findWeixinBusinessById(id);
		model.addAttribute("weixinBusinessData", weixinBusinessData);
		model.addAttribute("type", type);
		model.addAttribute("status", status);
		model.addAttribute("receiptName", receiptName);
		model.addAttribute("page", page);
		
    	return "sale/sale-showInfoDetail";
    }
	
	@RequestMapping(value="/sale/updateStatus",method=RequestMethod.POST)
    public String updateStatus(@RequestParam("type") int type,
    							@RequestParam("id") int id,
    							@RequestParam(value="receiptName",required=false) String receiptName,
    							@RequestParam("page") int page,
    							@RequestParam("status") int status,
    							@RequestParam("handleStatus") int handleStatus,
    							@RequestParam(value="handlePeople",required=false) String handlePeople, 
    							@RequestParam(value="handleTelephone",required=false) String handleTelephone, 
    							@RequestParam(value="expressPeople",required=false) String expressPeople, 
    							@RequestParam(value="expressTelephone",required=false) String expressTelephone, 
    							Model model) throws UnsupportedEncodingException{
		if(StringUtils.isNotBlank(handlePeople)){
			handlePeople=new String(handlePeople.getBytes("iso8859-1"),"UTF-8");
		}
		if(StringUtils.isNotBlank(handleTelephone)){
			handleTelephone=new String(handleTelephone.getBytes("iso8859-1"),"UTF-8");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		if(handleStatus == 0){
			map.put("handlePeople", handlePeople);
			map.put("handleTelephone", handleTelephone);
		}else if(handleStatus == 1){
			map.put("handlePeople", expressPeople);
			map.put("handleTelephone", expressTelephone);
		}
		
		int errorMSG=0;
		int data = adminService.updateWeixinBusiness(map);
		if(data>0){
			errorMSG=1;
		}
		WeixinBusiness weixinBusinessData = siteInfoService.findWeixinBusinessById(id);
		model.addAttribute("weixinBusinessData", weixinBusinessData);
		model.addAttribute("type", type);
		model.addAttribute("errorMSG", errorMSG);
		model.addAttribute("status", status);
		model.addAttribute("receiptName", receiptName);
		model.addAttribute("page", page);
		
    	return "sale/sale-showInfoDetail";
    }
	
	@RequestMapping(value="/sale/updateBusinessStatus",method=RequestMethod.POST)
    public void updateBusinessStatus(@RequestParam("id") int id,
    							@RequestParam("handlePeopleId") int handlePeopleId, 
    							HttpServletRequest req,
                				HttpServletResponse response,
    			                Model model) throws UnsupportedEncodingException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HandlePeople handlePeople = siteInfoService.queryHandlePeopleById(handlePeopleId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("handlePeople", handlePeople.getHandlePeople());
		map.put("handleTelephone", handlePeople.getHandlePhone());
		int data = adminService.updateWeixinBusiness(map);
		Gson gson = new Gson();
		if(data>0){
			 jsonMap.put("isSuccess", 1);
		}else{
			jsonMap.put("isSuccess", 0);
		}
        String list1 = gson.toJson(jsonMap);
        response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(list1);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
    }
	
	@RequestMapping(value="/sale/deleteWeixinBusiness",method=RequestMethod.GET)
    public String deleteWeixinBusiness(@RequestParam("type") int type,
    									@RequestParam("id") int id,
    									@RequestParam(value="receiptName",required=false) String receiptName,
    									@RequestParam("page") int page,
    									@RequestParam("status") int status, Model model){
		int delete = siteInfoService.deleteWeixinBusiness(id);
		
    	return "redirect:/sale/repairMachine?type="+type+"&receiptName="+receiptName+"&page="+page+"&status="+status;
    }
	
	@RequestMapping(value="/sale/queryHandlePeople",method=RequestMethod.GET)
    public String queryHandlePeopleAll(@RequestParam(value="handlePeople",required=false) String handlePeople,
    								@RequestParam(value="handlePhone",required=false) String handlePhone,
    								@RequestParam("page") int page,
					    			Model model){
		 
		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		handlePeople = Inputs.trimToNull(handlePeople);
		handlePhone = Inputs.trimToNull(handlePhone);
		HandlePeople handlePeopleO = getHandlePeople(-1,handlePeople,handlePhone,null,pageIndex,PAGE_SIZE);
		List<HandlePeople> dataList = siteInfoService.findAllHandleByPeopleName(handlePeopleO);
		int totalPage = (int)Math.ceil(dataList.size() *1.0 / PAGE_SIZE);
		List<HandlePeople> handlePeopleList = siteInfoService.findHandleByPeopleName(handlePeopleO);
		model.addAttribute("handlePeopleList", handlePeopleList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("handlePeople", handlePeople);
		model.addAttribute("handlePhone", handlePhone);
		
    	return "sale/handle-manage";
    }
	
	@RequestMapping(value="/sale/queryHandlePeople",method=RequestMethod.POST)
	public String queryHandlePeople(@RequestParam(value="handlePeople",required=false) String handlePeople,
			@RequestParam(value="handlePhone",required=false) String handlePhone,
			@RequestParam("page") int page,
			Model model){

		page = (page <= 0 ? 1 : page);
		int pageIndex = (page-1)*PAGE_SIZE;
		handlePeople = Inputs.trimToNull(handlePeople);
		handlePhone = Inputs.trimToNull(handlePhone);
		HandlePeople handlePeopleO = getHandlePeople(-1,handlePeople,handlePhone,null,pageIndex,PAGE_SIZE);
		List<HandlePeople> dataList = siteInfoService.findAllHandleByPeopleName(handlePeopleO);
		int totalPage = (int)Math.ceil(dataList.size() *1.0 / PAGE_SIZE);
		List<HandlePeople> handlePeopleList = siteInfoService.findHandleByPeopleName(handlePeopleO);
		model.addAttribute("handlePeopleList", handlePeopleList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("handlePeople", handlePeople);
		model.addAttribute("handlePhone", handlePhone);
		
		return "sale/handle-manage";
	}
	
	@RequestMapping(value="/sale/add",method=RequestMethod.GET)
	public String addAdmin(@RequestParam(value="errorMSG",required=false) String errorMSG,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		return "sale/handle-add";
	}
	
	@RequestMapping(value="/sale/add",method=RequestMethod.POST)
	public String addAdminUser(@RequestParam("handlePeople") String handlePeople,
								@RequestParam("handlePhone") String handlePhone,
								HttpServletRequest req,
					            Model model) throws UnsupportedEncodingException{
		if(StringUtils.isEmpty(handlePeople) || StringUtils.isEmpty(handlePeople)){
			return "redirect:/sale/add?errorMSG=1";
		}
		handlePeople = Inputs.trimToNull(handlePeople);
		handlePhone = Inputs.trimToNull(handlePhone);
		if(StringUtils.isNotBlank(handlePeople)){
			handlePeople=new String(handlePeople.getBytes("iso8859-1"),"UTF-8");
		}
		if(StringUtils.isNotBlank(handlePhone)){
			handlePhone=new String(handlePhone.getBytes("iso8859-1"),"UTF-8");
		}
		HandlePeople handlePeopleO = getHandlePeople(-1,handlePeople,null,null,-1,PAGE_SIZE);
		List<HandlePeople> handlePeopleList = siteInfoService.findAllHandleByPeopleName(handlePeopleO);
		
		if(handlePeopleList.size() > 0){
			return "redirect:/sale/add?errorMSG=2";
		}
		
		HandlePeople handlePeopleI = getHandlePeople(-1,handlePeople,handlePhone,null,-1,-1);
		int result = siteInfoService.insertHandlePeople(handlePeopleI);
		if(result>0){
			return "redirect:/sale/add?errorMSG=3";
		}else{
			return "redirect:/sale/add?errorMSG=4";
		}
		
	}
	
	@RequestMapping(value="/sale/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") int id,@RequestParam("page") int page,
								HttpServletRequest req,
					            Model model){
		int result = siteInfoService.deleteHandlePeople(id);
		return "redirect:/sale/queryHandlePeople?page="+page;
		
	}
	
	private HandlePeople getHandlePeople(int id,String handlePeople,String handlePhone,Date createTime,int pageSize,int pageNum ){
		HandlePeople handlePeopleO = new HandlePeople();
		
		if(id>0){
			handlePeopleO.setId(id);
		}
		
		handlePeopleO.setHandlePeople(handlePeople);
		
		handlePeopleO.setHandlePhone(handlePhone);
		
		if(pageSize != -1){
			handlePeopleO.setPageSize(pageSize);
		}
		
		if(pageNum != -1){
			handlePeopleO.setPageNum(pageNum);
		}
		
		return handlePeopleO;
	}
	
	private WeixinBusiness getWeixinBusiness(int id,String openid,String receiptName,String businessName,String telephone,String address,int type,int status,int sax,int pageSize,int pageNum){
		
		WeixinBusiness weixinBusiness = new WeixinBusiness();
		if(id>0){
			weixinBusiness.setId(id);
		}
		
		weixinBusiness.setOpenid(openid);
		
		weixinBusiness.setReceiptName(receiptName);
		
		weixinBusiness.setBusinessName(businessName);
		
		weixinBusiness.setTelephone(telephone);
		
		weixinBusiness.setAddress(address);
		
		weixinBusiness.setType(type);
		
		if(sax!=-1){
			weixinBusiness.setSax(sax);
		}
		
		weixinBusiness.setStatus(status);
		
		if(pageSize != -1){
			weixinBusiness.setPageSize(pageSize);
		}
		
		if(pageNum != -1){
			weixinBusiness.setPageNum(pageNum);
		}
		
		return weixinBusiness;
	}
	
	public SXSSFWorkbook write_Excel(List<ReportInfo> ls) throws IOException  {  
        SXSSFWorkbook wb = new SXSSFWorkbook(10000);//内存中保留 10000 条数据，以免内存溢出，其余写入 硬盘          
            Sheet sheet = wb.createSheet(String.valueOf(0));  
            wb.setSheetName(0, "sheet");  
            Row row = sheet.createRow(0); 
            Cell cellH = row.createCell(0);                     
            cellH.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0,"序号".length()*384); //设置单元格宽度  
            cellH.setCellValue("序号");//写入内容  
            
            Cell cellH1 = row.createCell(1);                     
            cellH1.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "客户经理".length()*384); //设置单元格宽度  
            cellH1.setCellValue("客户经理");//写入内容  
            
            Cell cellH2 = row.createCell(2);                     
            cellH2.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "所属支行".length()*384); //设置单元格宽度  
            cellH2.setCellValue("所属支行");//写入内容 
            
            Cell cellH3 = row.createCell(3);                     
            cellH3.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "商户名称".length()*384); //设置单元格宽度  
            cellH3.setCellValue("商户名称");//写入内容 
            
            Cell cellH4 = row.createCell(4);                     
            cellH4.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "商户地址".length()*384); //设置单元格宽度  
            cellH4.setCellValue("商户地址");//写入内容 
            
            Cell cellH5 = row.createCell(5);                     
            cellH5.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "联系人".length()*384); //设置单元格宽度  
            cellH5.setCellValue("联系人");//写入内容 
            
            Cell cellH6 = row.createCell(6);                     
            cellH6.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "联系电话".length()*384); //设置单元格宽度  
            cellH6.setCellValue("联系电话");//写入内容 
            
            Cell cellH7 = row.createCell(7);                     
            cellH7.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0,"机具类型".length()*384); //设置单元格宽度  
            cellH7.setCellValue("机具类型");//写入内容 
            
            Cell cellH8 = row.createCell(8);                     
            cellH8.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "手续费率".length()*384); //设置单元格宽度  
            cellH8.setCellValue("手续费率");//写入内容 
            
            Cell cellH9 = row.createCell(9);                     
            cellH9.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
            sheet.setColumnWidth(0, "装机状态".length()*384); //设置单元格宽度  
            cellH9.setCellValue("装机状态");//写入内容 
            
            int n=1;
            for(int i=0;i<ls.size();i++){  
                Row row1 = sheet.createRow(n); 
                n++;
                ReportInfo s = ls.get(i); 
                
                Cell cell = row1.createCell(0);                     
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell.setCellValue(i);//写入内容  
                
                Cell cell1 = row1.createCell(1);                     
                cell1.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell1.setCellValue(s.getBankUser());//写入内容  
                
                Cell cell2 = row1.createCell(2);                     
                cell2.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell2.setCellValue(s.getBankType());//写入内容 
                
                Cell cell3 = row1.createCell(3);                     
                cell3.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell3.setCellValue(s.getMerchantName());//写入内容 
                
                Cell cell4 = row1.createCell(4);                     
                cell4.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell4.setCellValue(s.getProvince()+s.getCity()+s.getArea()+s.getStreet());//写入内容 
                
                Cell cell5 = row1.createCell(5);                     
                cell5.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell5.setCellValue(s.getUserName());//写入内容 
                
                Cell cell6 = row1.createCell(6);                     
                cell6.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell6.setCellValue(s.getPhone());//写入内容 
                
                Cell cell7 = row1.createCell(7);                     
                cell7.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell7.setCellValue(s.getMachineDesc());//写入内容 
                
                Cell cell8 = row1.createCell(8);                     
                cell8.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell8.setCellValue(s.getRate());//写入内容 
                
                Cell cell9 = row1.createCell(9);                     
                cell9.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式                    
                sheet.setColumnWidth(0, s.getOpenid().length()*384); //设置单元格宽度  
                cell9.setCellValue(s.getStatusDesc());//写入内容 
                
         }    
        return wb;
    }  
	
	 public  void WriteExcel(List<ReportInfo> ls,OutputStream out)throws Exception{
         try{
        	 SXSSFWorkbook workbook =write_Excel(ls);
             workbook.write(out);          
             out.close();
            // System.out.println("Done!");
         }catch(Exception e){
             //System.out.println("Failed!");
             throw new Exception(e.getMessage());
         }
         
         
     }
	 
	@RequestMapping(value="/site/download",method=RequestMethod.GET) 
	public void downLoad(@RequestParam(value="bankUser",required=false) String bankUser,
			    		@RequestParam(value="bankType",required=false) Integer bankType,
			    		@RequestParam(value="merchantName",required=false) String merchantName,
						@RequestParam("page") int page,
						@RequestParam("status") int status, 
						@RequestParam("roleId") int roleId,
						@RequestParam("userId") int userId,
			            HttpServletResponse response) throws Exception {
		
		Admin admin = adminService.getUserById(userId);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> paramMapPage = new HashMap<String,Object>();
		List<ReportInfo> reportList = new ArrayList<ReportInfo>();
		if(roleId==3){
			int bankTypes = admin.getBankType();
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("bankType", bankTypes);
			paramMap.put("status", status);
			reportList = bankService.getReportInfoListManager(paramMap);
		}else if(roleId==4){
			String openId = admin.getOpenId();
			merchantName = Inputs.trimToNull(merchantName);
			
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			paramMap.put("openId", openId);
			paramMap.put("merchantName", merchantName);
			paramMap.put("status", status);
			
			
			
			reportList = bankService.getReportInfoListNomal(paramMap);
		}else{
			bankUser=Inputs.trimToNull(bankUser);
			merchantName = Inputs.trimToNull(merchantName);
			if(bankUser!=null){
				paramMap.put("bankUser", "%"+bankUser+"%");
			}else{
				paramMap.put("bankUser", bankUser);
			}
			if(merchantName!=null){
				paramMap.put("merchantName", "%"+merchantName+"%");
			}else{
				paramMap.put("merchantName", merchantName);
			}
			
			paramMap.put("bankType", bankType);
			paramMap.put("status", status);
			
			
			reportList = bankService.getReportInfoListByOpenIds(paramMap);
		}
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
			BankList bankList = bankService.getBankListById(Integer.parseInt(reportInfo.getBankType()));
			String bankName = bankList.getBankDesc();
			reportInfo.setBankType(bankName);
			String statusDesc = statusMap.get(""+reportInfo.getStatus()+"");
			reportInfo.setStatusDesc(statusDesc);
			String mechineDesc = mechineMap.get(""+reportInfo.getMachineName()+"");
			reportInfo.setMachineDesc(mechineDesc);
		}
        //取得输出流
        OutputStream out = response.getOutputStream();
        //清空输出流
        response.reset();
        response.setCharacterEncoding("UTF-8");
        //设置响应头和下载保存的文件名              
        response.setHeader("content-disposition","attachment;filename=rongshang.xlsx");
        //定义输出类型
        response.setContentType("APPLICATION/msexcel");     
        WriteExcel(reportList,out);
        out.close();
        
        //这一行非常关键，否则在实际中有可能出现莫名其妙的问题！！！
       response.flushBuffer();//强行将响应缓存中的内容发送到目的地                              
    
   
     }
	
	@RequestMapping(value="/sale/reportContent",method=RequestMethod.GET)
	public String reportContent(@RequestParam("openId") int openId,@RequestParam("status") int status,
								HttpServletRequest req,
					            Model model){
		model.addAttribute("openId", openId);
		model.addAttribute("status", status);
		return "/sale/content-add";
		
	}
	
	@RequestMapping(value="/sale/reportContent",method=RequestMethod.POST)
	public String reportContentP(@RequestParam("openId") int openId,@RequestParam("status") int status,
								@RequestParam("content") String content,HttpServletRequest req,
					            Model model){
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("openId", openId);
		dataMap.put("status", status);
		dataMap.put("content", content);
		int result = bankService.updateReportStatus(dataMap);
		int errorMSG = 1;
		if(result>0){
			errorMSG=0;
			model.addAttribute("errorMSG", errorMSG);
		}else{
			model.addAttribute("errorMSG", errorMSG);
		}
		return "/sale/content-add";
		
	}
	
	@RequestMapping(value="/sale/reportOtherStatus",method=RequestMethod.POST)
    public void reportOtherStatus(@RequestParam("openId") String openId,
    							@RequestParam("status") int status, 
    							HttpServletRequest req,
                				HttpServletResponse response,
    			                Model model) throws UnsupportedEncodingException{
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		dataMap.put("openId", openId);
		dataMap.put("status", status);
		dataMap.put("content", "");
		int data = bankService.updateReportStatus(dataMap);
		Gson gson = new Gson();
		if(data>0){
			 jsonMap.put("isSuccess", 1);
		}else{
			jsonMap.put("isSuccess", 0);
		}
        String list1 = gson.toJson(jsonMap);
        response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(list1);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
    }

}
