package com.lexindasoft.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.util.ServletUtil;
import com.lexindasoft.lexindaframe.util.UserUtils;

@Controller
public class HomeController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String none(HttpServletRequest req,
            Model model){
		
		return "maintain";
		
	}
	
	@RequestMapping(value="/erro_404",method=RequestMethod.GET)
	public String erro_404(HttpServletRequest req,
            Model model){
		
		return "mainerror";
		
	}
	
	@RequestMapping(value="/erro_404",method=RequestMethod.POST)
	public String erro_405(HttpServletRequest req,
            Model model){
		
		return "mainerror";
		
	}
	
	@RequestMapping(value="/cmsRstManage",method=RequestMethod.GET)
	public String cmsRstManage(HttpServletRequest req,
            Model model){
		int userId = UserUtils.getUserId(req);
		if(userId <= 0){
			return "login";
		}else{
			return "redirect:/cmsRstManage/index";
		}
	}
	
	@RequestMapping(value="/cmsRstManage/login",method=RequestMethod.GET)
	public String login(@RequestParam(value="error",required=false) String error,Model model){
		model.addAttribute("error", error);
		return "login";
	}
	
	@RequestMapping(value="/cmsRstManage/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, 
						@RequestParam("password") String password,
						HttpServletRequest req,
			            Model model){
        // json转为简单Bean  
        Admin admin = adminService.getUser(username, password);
		if (admin == null){
			return "redirect:/cmsRstManage/login?error=true";
		} 
		
		if(admin.getUserStatus() == 0){
			return "redirect:/cmsRstManage/login?error=true";
		}
		HttpSession userSession = req.getSession();
		userSession.setAttribute("userId", admin.getUserId());
		userSession.setAttribute("lastloginip", admin.getLastloginIp());
		userSession.setAttribute("lastlogintime", admin.getLastloginTime());
		userSession.setAttribute("user", admin);
		admin.setLastloginIp(ServletUtil.getClientRealIp(req));
		admin.setLastloginTime(new Date());
		adminService.updateUser(admin);
		return "redirect:/cmsRstManage/index";
	}
	
	@RequestMapping(value="/cmsRstManage/index",method=RequestMethod.GET)
    public String index() {
    	return "index";
    }
	
	@RequestMapping(value="/menu/cmsRstManage/mypanel",method=RequestMethod.GET)
    public String mypanel() {
    	return "menu/mypanel";
    }
//	
//	@Get("menu/order-m")
//    public String orderM() {
//    	return "menu/order-manage";
//    }
//	
//	@Get("menu/product-m")
//    public String productM() {
//    	return "menu/product-manage";
//    }
//	
	@RequestMapping(value="/menu/cmsRstManage/access-m",method=RequestMethod.GET)
    public String accessM() {
    	return "menu/access-manage";
    }
	@RequestMapping(value="/menu/cmsRstManage/siteInfo-m",method=RequestMethod.GET)
    public String siteInfoM() {
    	return "menu/siteInfo-manage";
    }
	@RequestMapping(value="/menu/cmsRstManage/afterSale-m",method=RequestMethod.GET)
    public String afterSaleManager() {
    	return "menu/afterSale-manage";
    }
	@RequestMapping(value="/menu/cmsRstManage/reportSale-m",method=RequestMethod.GET)
    public String reportSaleManager() {
    	return "menu/reportSale-manage";
    }
//	
//	@Get("menu/mall-r")
//    public String mallR() {
//    	return "menu/mall-manage";
//    }
//	
//	@Get("menu/reconciliation-m")
//    public String reconciliationM() {
//    	return "menu/reconciliation-manage";
//    }
//	
//	@Get("menu/help-m")
//    public String helpM() {
//    	return "menu/help-manage";
//    }
	
	
	@RequestMapping(value="/empty",method=RequestMethod.GET)
    public String empty() {
    	return "empty";
    }
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpServletRequest req,
            Model model) {
		req.getSession().invalidate();
    	return "redirect:/cmsRstManage/login";
    }
	
//	@Get("menu/merchant-r")
    public String merchantR() {
    	return "menu/merchant-manage";
    }
	
/*	
 * 去掉上传的功能 ，api文档不考虑上传 
    @Get("menu/upload-r")
    public String uploadM() {
    	return "menu/upload-manage";
    }*/
}
