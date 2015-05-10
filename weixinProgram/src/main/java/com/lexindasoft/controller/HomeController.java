package com.lexindasoft.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.SiteInfo;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.service.SiteInfoService;
import com.lexindasoft.lexindaframe.util.ServletUtil;
import com.lexindasoft.lexindaframe.util.UserUtils;

@Controller
public class HomeController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	SiteInfoService siteInfoService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView none(HttpServletRequest req,
            Model model){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");
		return mav;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest req,
            Model model){
		SiteInfo siteInfo = new SiteInfo();
		siteInfo.setSiteInfoType(1);
		siteInfo.setPageNum(0);
		siteInfo.setPageSize(9);
		siteInfo.setId(999999);
		List<SiteInfo> newList = siteInfoService.findSiteNews(siteInfo);
		model.addAttribute("newList", newList);
		return "index/index";
		
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
    
    @RequestMapping("/news")
	public ModelAndView news(@RequestParam(value="pageNumber",required=false)Integer pageNumber,Model model){
		int pageSize=5;
		ModelAndView mav = new ModelAndView();
		int pageIndex = pageNumber==null?1:pageNumber;
		int pageNum = (pageIndex-1)*pageSize;
		SiteInfo siteInfo = new SiteInfo();
		siteInfo.setPageNum(pageNum);
		siteInfo.setPageSize(pageSize);
		siteInfo.setId(999999);
		List<SiteInfo> newList = siteInfoService.findSiteNews(siteInfo);
		List<SiteInfo> newAllList = siteInfoService.findAllSiteNews();
		int totalPage = (int)Math.ceil(newAllList.size() *1.0 / pageSize);
		model.addAttribute("newList", newList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageIndex", pageIndex);
		mav.setViewName("index/index-new");
		
		return mav;
	}
	
	@RequestMapping("/newsDetail")
	public ModelAndView newsDetail(@RequestParam(value="newsId",required=false)Integer newsId,Model model){
		SiteInfo siteInfo = new SiteInfo();
		siteInfo.setPageNum(0);
		siteInfo.setPageSize(1);
		siteInfo.setId(newsId);
		List<SiteInfo> newList = siteInfoService.findSiteNews(siteInfo);
		List<SiteInfo> newAllList = siteInfoService.findAllSiteNews();
		SiteInfo preInfo = new SiteInfo();
		SiteInfo nextInfo = new SiteInfo();
		int index=0;
		for(int i=0;i<newAllList.size();i++){
			SiteInfo siteInfoOld = (SiteInfo)newList.get(0);
			SiteInfo siteInfoCurrent = (SiteInfo)newAllList.get(0);
			if(siteInfoOld.getId()==siteInfoCurrent.getId()){
				index=i;
			}
		}
		if(index-1>0&&index-1<newAllList.size()-1){
			preInfo = newAllList.get(index-1);
		}else{
			preInfo.setId(999999);
			preInfo.setSiteInfoName("没有了");
		}
		if(index+1<newAllList.size()){
			nextInfo = newAllList.get(index+1);
		}else{
			nextInfo.setId(999999);
			nextInfo.setSiteInfoName("没有了");
		}
		ModelAndView mav = new ModelAndView();
		model.addAttribute("news", (SiteInfo)newList.get(0));
		model.addAttribute("preInfo", preInfo);
		model.addAttribute("nextInfo",nextInfo);
		mav.setViewName("index/index-newdetail");
		return mav;
	}
	
	@RequestMapping("/rest")
	public ModelAndView rest(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_rest");
		
		return mav;
	}
	
	@RequestMapping("/restDetail")
	public ModelAndView restDetail(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_restDetail");
		
		return mav;
	}
	
	@RequestMapping("/restProduct")
	public ModelAndView restProduct(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_restProduct");
		
		return mav;
	}
	
	@RequestMapping("/restUse")
	public ModelAndView restUse(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_restUse");
		
		return mav;
	}
	
	@RequestMapping("/restApp")
	public ModelAndView restApp(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_restApp");
		
		return mav;
	}
	
	@RequestMapping("/soft")
	public ModelAndView soft(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_soft");
		
		return mav;
	}
	
	@RequestMapping("/product")
	public ModelAndView product(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index_product");
		
		return mav;
	}
	
	@RequestMapping("/about")
	public ModelAndView about(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index-about");
		
		return mav;
	}
	
	@RequestMapping("/hr")
	public ModelAndView hr(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index-hr");
		
		return mav;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contact(){
		
		ModelAndView mav = new ModelAndView();
		
//		List<User> userList= userService.getAllUser();
		
		mav.setViewName("index/index-contact");
		
		return mav;
	}
	
/*	
 * 去掉上传的功能 ，api文档不考虑上传 
    @Get("menu/upload-r")
    public String uploadM() {
    	return "menu/upload-manage";
    }*/
}
