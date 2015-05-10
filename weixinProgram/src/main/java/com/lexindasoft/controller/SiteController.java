package com.lexindasoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.lexindasoft.lexindaframe.model.Button;
import com.lexindasoft.lexindaframe.model.CommonButton;
import com.lexindasoft.lexindaframe.model.ComplexButton;
import com.lexindasoft.lexindaframe.model.Menu;
import com.lexindasoft.lexindaframe.model.ResultOauth;
import com.lexindasoft.lexindaframe.model.SiteInfo;
import com.lexindasoft.lexindaframe.model.UserInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;
import com.lexindasoft.lexindaframe.model.WeixinResponse;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.service.CoreService;
import com.lexindasoft.lexindaframe.service.SiteInfoService;
import com.lexindasoft.lexindaframe.util.AjaxUtils;
import com.lexindasoft.lexindaframe.util.HttpClientWeixin;
import com.lexindasoft.lexindaframe.util.HttpParam;
import com.lexindasoft.lexindaframe.util.ImageUploadUtils;
import com.lexindasoft.lexindaframe.util.Inputs;
import com.lexindasoft.lexindaframe.util.JsonUtil;
import com.lexindasoft.lexindaframe.util.RuntimeConfig;
import com.lexindasoft.lexindaframe.util.WeixinUtil;

@Controller
public class SiteController {

	private final Log logger = LogFactory.getLog(getClass());
	
	//private final String imagePath="/Volumes/E/windows/workspace/weixinProgram/src/main/webapp/images/upload";
	private final String imagePath= RuntimeConfig.getInstance().getImagePath();
	private final int PAGE_SIZE = 10;
	
	private static final String TOKENSERVICE =  RuntimeConfig.getInstance().getTokenService();
	
	@Autowired
	SiteInfoService siteInfoService;
	
	@Autowired
	CoreService coreService;
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/siteHome",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteHome() {
    	return "site/siteHome";
    }
	
	@RequestMapping(value="/siteMerchant",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteMerchant() {
    	return "site/siteMerchant1";
    }
	
	@RequestMapping(value="/siteProduct",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteProduct(Model model) {
		SiteInfo siteInfo = getSiteInfo(-1,2,null,null,null,null,PAGE_SIZE,1);
		
		List<SiteInfo> productInfoList= siteInfoService.findAllSiteInfo(siteInfo);
		
		model.addAttribute("productInfoList", productInfoList);
		
    	return "site/siteProduct";
    }
	
	@RequestMapping(value="/siteBusiness",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteBusiness(@RequestParam("businessType") int businessType,Model model) {
		model.addAttribute("businessType", businessType);
    	return "site/siteBusiness";
    }
	
	@RequestMapping(value="/siteList",method=RequestMethod.GET)
    public String siteList(@RequestParam("siteInfoType") int siteInfoType,@RequestParam(value="pageSize",required=false)Integer pageSize, @RequestParam(value="pageNum",required=false)Integer pageNum, Model model){
		 
		pageNum = (pageNum == null ? 1 : pageNum);
		
		int pageIndex = 0;
		
		if(pageNum!=1){
			pageIndex = (pageNum-1)*PAGE_SIZE;
		}
		
		SiteInfo siteInfo = getSiteInfo(-1,siteInfoType,null,null,null,null,PAGE_SIZE,pageIndex);
		
		List<SiteInfo> dataList = siteInfoService.findSiteInfoByName(siteInfo);
		
		model.addAttribute("dataList", dataList);
		
		model.addAttribute("dataListSize", dataList.size());
		
		model.addAttribute("siteInfoType", siteInfoType);
		
		model.addAttribute("pageSize",dataList.size());
		
    	return "site/siteList";
    }
	
	@RequestMapping(value = "/siteText", method = RequestMethod.GET)
	public void siteText( @RequestParam("siteInfoType") int siteInfoType,@RequestParam(value="pageSize",required=false)Integer pageSize, 
						@RequestParam(value="pageNum",required=false)Integer pageNum,
			                 HttpServletRequest request,
			                 HttpServletResponse response,
			                 Model model) {
		
		//for(int i=pageSize*(pageNum-1)+1;i<=pageSize*pageNum;i++){
			pageNum = (pageNum == null ? 1 : pageNum);
			
			int pageIndex = 0;
			
			if(pageNum!=1){
				pageIndex = (pageNum-1)*PAGE_SIZE;
			}
			
			SiteInfo siteInfo = getSiteInfo(-1,siteInfoType,null,null,null,null,PAGE_SIZE,pageIndex);
			
			List<SiteInfo> dataList = siteInfoService.findSiteInfoByName(siteInfo);
			Gson gson = new Gson();
			Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("dataList", dataList);
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
	@RequestMapping(value="/siteDetail",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String siteDetail(@RequestParam("id") int id,Model model) {
		
		SiteInfo siteInfo = siteInfoService.showSiteInfoDetail(id);
		
		model.addAttribute("siteInfo", siteInfo);
		
    	return "site/siteDetail";
    }
		@RequestMapping(value = "/cmsRstManage/infoList", method = RequestMethod.GET)
		public String getSiteInfoList(@RequestParam("siteInfoType") int siteInfoType,@RequestParam(value="page",required=false) Integer page,
									@RequestParam(value="tName",required=false) String tName,
									Model model){
			
			page = (page == null ? 1 : page);
			
			int pageIndex = 0;
			
			if(page!=1){
				pageIndex = (page-1)*PAGE_SIZE;
			}
			
			tName = Inputs.trimToNull(tName);
			
			SiteInfo siteInfo = getSiteInfo(-1,siteInfoType,tName,null,null,null,PAGE_SIZE,pageIndex);
			
			List<SiteInfo> siteInfoList= siteInfoService.findAllSiteInfo(siteInfo);
			
			List<SiteInfo> siteInfoListByName= siteInfoService.findSiteInfoByName(siteInfo);
			
			int totalNum=siteInfoList.size();
			
			int pageNum = (int)Math.ceil(totalNum * 1.0 / PAGE_SIZE);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("prePage", (page - 1 < 1 ? 1 : page - 1));
			model.addAttribute("nextPage", (page + 1 > pageNum ? pageNum : page + 1));
			model.addAttribute("currentPage", page);
			model.addAttribute("currentPage", page);
			model.addAttribute("siteInfoType", siteInfoType);
			model.addAttribute("siteInfoList", siteInfoListByName);
			
			return "site/siteInfo-list";
		}
		
		@RequestMapping(value = "/cmsRstManage/deleteInfo", method = RequestMethod.GET)
		public String deleteSiteInfo(@RequestParam("tId") int tId,@RequestParam("siteInfoType") int siteInfoType,Model model){
			int result = siteInfoService.deleteSiteInfo(tId);
			if(result>0){
				model.addAttribute("sucessMSG", "删除成功!");
			}else{
				model.addAttribute("errorMSG", "删除失败!");
			}
			return "redirect:/infoList?siteInfoType="+siteInfoType;
		}
		
		@RequestMapping(value = "/cmsRstManage/showInfoDetail", method = RequestMethod.GET)
		public String showTechnicianDetail(@RequestParam("tId") int tId,Model model){
			SiteInfo siteInfo= siteInfoService.showSiteInfoDetail(tId);
			model.addAttribute("siteInfo", siteInfo);
			return "site/siteInfo-detail";
		}
		
		
		@RequestMapping(value = "/cmsRstManage/addNew", method = RequestMethod.GET)
		public String addNewTechnician(@RequestParam("siteInfoType")int siteInfoType,Model model){
			model.addAttribute("siteInfoType", siteInfoType);
			return "site/siteInfo-add";
		}
		
		@RequestMapping(value = "/cmsRstManage/addNew", method = RequestMethod.POST)
		public String addNewTechnician(@RequestParam("siteInfoType") int siteInfoType,
				@RequestParam("tName") String tName,
				@RequestParam("tSax") String tSax,
				@RequestParam("uploadImg") String tImg,
				@RequestParam("tContent") String tContent,Model model) throws UnsupportedEncodingException{
			
			tName = Inputs.trimToNull(tName);
			
			tImg = Inputs.trimToNull(tImg);
			
			tContent = Inputs.trimToNull(tContent);
			
			if(tContent!=null){
				tContent = new String(tContent.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tName!=null){
				tName = new String(tName.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tSax!=null){
				tSax = new String(tSax.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tName != null){
				
				SiteInfo siteInfo = getSiteInfo(-1,siteInfoType,tName,tSax,tImg,tContent,-1,-1);
				
				int siteInfoId = siteInfoService.addSiteInfo(siteInfo);
				
				if(siteInfoId > 0){
					model.addAttribute("sucessMSG", "添加成功!");
				}else{
					model.addAttribute("errorMSG", "添加失败!");
				}
			}
			
			return "redirect:/infoList?siteInfoType="+siteInfoType;
		}
		
		@RequestMapping(value = "/cmsRstManage/updateSiteInfo", method = RequestMethod.POST)
		public String addNewTechnician(@RequestParam("id") int id,@RequestParam("siteInfoType") int siteInfoType,
				@RequestParam("tName") String tName,
				@RequestParam("tSax") String tSax,
				@RequestParam("uploadImg") String tImg,
				@RequestParam("tContent") String tContent,Model model) throws UnsupportedEncodingException{
			
			tName = Inputs.trimToNull(tName);
			
			tImg = Inputs.trimToNull(tImg);
			
			tContent = Inputs.trimToNull(tContent);
			
			if(tContent!=null){
				tContent = new String(tContent.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tName!=null){
				tName = new String(tName.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tSax!=null){
				tSax = new String(tSax.getBytes("iso8859-1"),"UTF-8");
			}
			
			if(tName != null){
				
				SiteInfo siteInfo = getSiteInfo(id,siteInfoType,tName,tSax,tImg,tContent,-1,-1);
				
				int technicianId = siteInfoService.updateSiteInfo(siteInfo);
				
				if(technicianId > 0){
					model.addAttribute("sucessMSG", "更新成功!");
				}else{
					model.addAttribute("errorMSG", "更新失败!");
				}
			}
			
			return "redirect:/infoList?siteInfoType="+siteInfoType;
		}
		
		private SiteInfo getSiteInfo(int siteInfoId,int siteInfoType,String tName,String tSax,String tImg,String tContent,int pageSize,int pageNum){
			
			SiteInfo site = new SiteInfo();
			if(siteInfoId>0){
				site.setId(siteInfoId);
			}
			
			site.setSiteInfoType(siteInfoType);
			
			site.setSiteInfoName(tName);
			
			site.setSiteInfoSax(tSax);
			
			site.setSiteInfoImg(tImg);
			
			site.setSiteInfoContent(tContent);
			
			if(pageSize != -1){
				site.setPageSize(pageSize);
			}
			
			if(pageNum != -1){
				site.setPageNum(pageNum);
			}
			
			return site;
		}
		
		@RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
		 public void uploadImg(@RequestParam("selectImg") CommonsMultipartFile selectImg,HttpServletResponse response){
		  Map<String, Object> rtn = new HashMap<String, Object>();
		 
		  if(ImageUploadUtils.isValidFormats(selectImg)){
		   rtn.put("status", 0);
		   AjaxUtils.printJson(rtn, response);
		  }
		 
		  String filePath = ImageUploadUtils.uploadImg(selectImg, imagePath,"pimg_", 0);
		  filePath = StringUtils.substringAfter(filePath, imagePath);
		  rtn.put("status", 1);
		  rtn.put("imgUrl", "images/upload"+filePath);
		 
		  AjaxUtils.printJson(rtn, response);
		 }
		//访问微信
		@RequestMapping(value="/siteWeixin",method=RequestMethod.POST,produces = "text/html; charset=UTF-8")
	    public @ResponseBody String siteWeixin(HttpServletRequest req,HttpServletResponse resp) throws IOException {
			String httpParamStr = "";
			Enumeration<String> pNames = req.getParameterNames();
			while(pNames.hasMoreElements()){
				String pName = pNames.nextElement();
				String pValue = req.getParameter(pName);
				httpParamStr = httpParamStr + pName  + ":" + pValue + "\n";
			}
			//LOGGER.debug("http参数:" + httpParamStr);

			if(isValid(req)){
				 // 调用核心业务类接收消息、处理消息  
		        String respMessage = CoreService.processRequest(req);
		        return respMessage;
			}else{
				return "error";
			}
		}
		//验证微信
		@RequestMapping(value="/siteWeixin",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	    public @ResponseBody String siteWeixinAuth(HttpServletRequest req,HttpServletResponse resp){
	        if(isValid(req)){
	            String echoStr = Inputs.trimToNull(req.getParameter("echostr"));
	            if(echoStr!=null){
	                return echoStr;
	            }else{
	                return "Nice to see you!";
	            }
	        }else{
	            return "error";
	        }
	    }
		@RequestMapping(value="/siteWeixinMenu",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
		public  @ResponseBody String siteWeixinMenu() {  
	        // 调用接口获取access_token  
	        String at = RuntimeConfig.getInstance().getAssessToken();  
	        String resultData="";
	        if (null != at) {  
	            // 调用接口创建菜单  
	            int result = WeixinUtil.createMenu(getMenu(), at);  
	  
	            // 判断菜单创建结果  
	            if (0 == result){
	                logger.info("菜单创建成功！"); 
	            	resultData = "菜单创建成功！";
	            }
	            else{
	                logger.info("菜单创建失败，错误码：" + result);  
	            	resultData = "菜单创建失败，错误码：" + result;
	            }
	        }  
	        return resultData;
	    }  
		/** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static Menu getMenu() {  
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("微官网");  
	        btn11.setType("click");  
	        btn11.setKey("11");  
	  
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("公司介绍");  
	        btn12.setType("click");  
	        btn12.setKey("12");  
	  
	        CommonButton btn13 = new CommonButton();  
	        btn13.setName("招贤纳士");  
	        btn13.setType("click");  
	        btn13.setKey("13");  
	  
	        CommonButton btn14 = new CommonButton();  
	        btn14.setName("我的商户");  
	        btn14.setType("click");  
	        btn14.setKey("14");
	        
	        CommonButton btn15 = new CommonButton();  
	        btn15.setName("联系我们");  
	        btn15.setType("click");  
	        btn15.setKey("15");
	  
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("产品介绍");  
	        btn21.setType("click");  
	        btn21.setKey("21");  
	  
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("办理须知");  
	        btn22.setType("click");  
	        btn22.setKey("22");  
	  
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("机具申请");  
	        btn23.setType("click");  
	        btn23.setKey("23");  
	  
	        /*ViewButton btn24 = new ViewButton();  
	        btn24.setName("即时到帐");  
	        btn24.setType("view");  
	        btn24.setUrl("http://law02.gotoip55.com//siteTestOauth?type=4&");*/ 
	        
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("即时到帐");  
	        btn24.setType("click");  
	        btn24.setKey("24"); 
	  
	        CommonButton btn25 = new CommonButton();  
	        btn25.setName("加盟融商");  
	        btn25.setType("click");  
	        btn25.setKey("25");  
	  
	        /*ViewButton btn31 = new ViewButton();  
	        btn31.setName("机具报修");  
	        btn31.setType("view");  
	        btn31.setUrl("http://law02.gotoip55.com//siteTestOauth?type=1&");*/    
	  
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("机具报修");  
	        btn31.setType("click");  
	        btn31.setKey("31"); 
	        
	        /*ViewButton btn32 = new ViewButton();  
	        btn32.setName("纸张申请");  
	        btn32.setType("view");  
	        btn32.setUrl("http://law02.gotoip55.com//siteTestOauth?type=2&");*/  
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("纸张申请");  
	        btn32.setType("click");  
	        btn32.setKey("32"); 
	  
	        /*ViewButton btn33 = new ViewButton();  
	        btn33.setName("我要增机");  
	        btn33.setType("view");  
	        btn33.setUrl("http://law02.gotoip55.com//siteTestOauth?type=3&");*/
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("我要增机");  
	        btn33.setType("click");  
	        btn33.setKey("33");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("在线客服");  
	        btn34.setType("click");  
	        btn34.setKey("34"); 
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("常见故障");  
	        btn35.setType("click");  
	        btn35.setKey("35"); 
	  
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("关于融商");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 ,btn15 });  
	  
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("业务合作");  
	        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });  
	  
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("售后服务");  
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34, btn35 });  
	  
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }  
		public boolean isValid(HttpServletRequest req){
			String signature = Inputs.trimToNull(req.getParameter("signature"));
			String timestamp = Inputs.trimToNull(req.getParameter("timestamp"));
			String nonce = Inputs.trimToNull(req.getParameter("nonce"));
			
			if(Inputs.notNull(signature, timestamp, nonce)){
				
				List<String> params = new ArrayList<String>();
				params.add(timestamp);
				params.add(nonce);
				params.add(TOKENSERVICE);
				Collections.sort(params);
				
				String orig = "";
				for(String param : params){
					orig = orig + param;
				}
				
				String sign = DigestUtils.shaHex(orig);
				
				return signature.equals(sign);
			}
			
			return false;
		}
		@RequestMapping(value="/siteSendEmail",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
	    public @ResponseBody String siteSendEmail() { 
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			boolean b=false;
			ApplicationContext actx = new ClassPathXmlApplicationContext(
					"mailMessage.xml");
			MailSender ms = (MailSender) actx.getBean("mailSender");
			SimpleMailMessage smm = (SimpleMailMessage) actx.getBean("mailMessage");
			// 主题,内容
			smm.setSubject("你好，融商");
			smm.setText("上海融商网络科技有限公司给您发的测试邮件，可以忽略！");
			try{
				ms.send(smm);
				b=true;
			}catch(RuntimeException e){
				throw e;
			}
			jsonMap.put("isSuccess", b);
			return JsonUtil.getSuccessJsonFromMap(jsonMap, "success");
	    }  
		
	    public void siteSendEmailDetail(String title,String content) { 
			ApplicationContext actx = new ClassPathXmlApplicationContext(
					"mailMessage.xml");
			MailSender ms = (MailSender) actx.getBean("mailSender");
			SimpleMailMessage smm = (SimpleMailMessage) actx.getBean("mailMessage");
			// 主题,内容
			smm.setSubject(title);
			smm.setText(content);
			try{
				ms.send(smm);
			}catch(RuntimeException e){
				throw e;
			}
	    }  
		
		@RequestMapping(value="/siteOauth",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	    public String siteOauth(HttpServletRequest req,HttpServletResponse resp,@RequestParam("type") int type,Model model) throws UnsupportedEncodingException { 
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
					}
					logger.debug(userInfo.getNickname());
				return "redirect:/siteRepairMachine?openId="+userInfo.getOpenid()+"&type="+type;
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
		@RequestMapping(value="/siteTestOauth",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
	    public String siteTestOauth(HttpServletRequest req,HttpServletResponse resp,@RequestParam("type") int type,Model model) { 
			return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+RuntimeConfig.getInstance().getAppId()+ "&redirect_uri=http://www.rstpay.com/siteOauth?type="+type+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	    }
		//oUZH8t65--vvBoQVHchyTRbktBnw
		@RequestMapping(value="/siteRepairMachine",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
		public String siteRepairMachine(@RequestParam("openId")String openId,@RequestParam(value="isSuccess",required=false)Integer isSuccess,@RequestParam(value="type",required=false)int type,Model model){
			UserInfo resultUser = adminService.queryUserByOpenId(openId);
			WeixinBusiness weixinBusiness = new WeixinBusiness();
			weixinBusiness.setOpenid(openId);
			weixinBusiness.setType(type);
			WeixinBusiness weixinBusinessData = adminService.queryWeixinBusiness(weixinBusiness);
			if(weixinBusinessData!=null){
				model.addAttribute("weixinBusinessData", weixinBusinessData);
			}
			model.addAttribute("type", type);
			model.addAttribute("resultUser", resultUser);
			String successInfo=null;
			if(isSuccess!=null){
				if(isSuccess==1){
					successInfo="提交成功！";
				}else if(isSuccess==0){
					successInfo="提交失败！";
				}
				model.addAttribute("isSuccess",successInfo);
			}
			if(type==4){
				return "site/siteInstantAccount";
			}else{
				return "site/siteRepairMachine";
			}
			
		}
		
		@RequestMapping(value="/siteRepairMachine",method=RequestMethod.POST,produces = "application/json; charset=UTF-8")
		public String siteDoRepairMachine(@RequestParam("openId") String openId,
										@RequestParam("receiptName") String receiptName,
										@RequestParam("businessName") String businessName,
										@RequestParam("telephone") String telephone,
										@RequestParam(value="address",required=false) String address,
										@RequestParam("type") int type,
										@RequestParam("sax") int sax,
										@RequestParam("provinceName") String provinceName,
										@RequestParam("cityName") String cityName,
										@RequestParam("areaName") String areaName,
										@RequestParam("street") String street,
										HttpServletResponse response,
										Model model) throws UnsupportedEncodingException{
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			Integer isSuccess=0;
			WeixinBusiness weixinBusiness = new WeixinBusiness();
			weixinBusiness.setOpenid(openId);
			if(StringUtils.isNotBlank(receiptName)){
				receiptName=new String(receiptName.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(businessName)){
				businessName=new String(businessName.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(telephone)){
				telephone=new String(telephone.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(address)){
				address=new String(address.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(provinceName)){
				provinceName=new String(provinceName.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(cityName)){
				cityName=new String(cityName.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(areaName)){
				areaName=new String(areaName.getBytes("iso8859-1"),"UTF-8");
			}
			if(StringUtils.isNotBlank(street)){
				street=new String(street.getBytes("iso8859-1"),"UTF-8");
			}
			weixinBusiness.setReceiptName(receiptName);
			weixinBusiness.setBusinessName(businessName);
			weixinBusiness.setTelephone(telephone);
			weixinBusiness.setAddress(address);
			weixinBusiness.setType(type);
			weixinBusiness.setSax(sax);
			weixinBusiness.setProvince(provinceName);
			weixinBusiness.setCity(cityName);
			weixinBusiness.setArea(areaName);
			weixinBusiness.setStreet(street);
			int resultUser = adminService.insertWeixinBusiness(weixinBusiness);
			String title="";
			if(type==1){
				title="机具报修";
			}else if(type==2){
				title="纸张申请";
			}else if(type==3){
				title="我要增机";
			}else if(type==4){
				title="即时到帐";
			}else if(type==5){
				title="机具申请";
			}
			String content="";
			String peopleSax="";
			if(sax==1){
				peopleSax="男";
			}else if(sax==0){
				peopleSax="女";
			}
			if(type==5){
				content=receiptName+"；联系人："+businessName+"；性别："+peopleSax+"；电话："+telephone+"；地址："+provinceName+cityName+areaName+street;
			}else{
				content="小票名称："+receiptName+"；联系人："+businessName+"；性别："+peopleSax+"；电话："+telephone+"；地址："+provinceName+cityName+areaName+street+"；描述："+address;
			}
			siteSendEmailDetail(title,content);
			if(resultUser>0){
				isSuccess=1;
			}
			return "redirect:/siteRepairMachine?openId="+openId+"&isSuccess="+isSuccess+"&type="+type;
		}
		
		@RequestMapping(value="/siteBusinessRecord",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
		public String siteBusinessRecord(@RequestParam("openId") String openId,
										@RequestParam("type") int type,
										HttpServletResponse response,
										Model model) throws UnsupportedEncodingException{
			WeixinBusiness weixinBusiness = new WeixinBusiness();
			weixinBusiness.setOpenid(openId);
			weixinBusiness.setType(type);
			List<WeixinBusiness> resultWeixinBusiness = adminService.queryAllWeixinBusiness(weixinBusiness);
			if(resultWeixinBusiness!=null){
				model.addAttribute("weixinBusinessData", resultWeixinBusiness);
			}
			model.addAttribute("weixinBusinessDataSize", resultWeixinBusiness.size());
			model.addAttribute("type", type);
			return "site/siteBusinessRecord";
		}
}
