package com.lexindasoft.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
import com.lexindasoft.lexindaframe.util.Inputs;
import com.lexindasoft.lexindaframe.util.UserUtils;

@Controller
public class MyPanelController {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/mypanel/info",method=RequestMethod.GET)
	public String personalInfo(HttpServletRequest req,
            Model model){
		model.addAttribute("lastloginip", UserUtils.getUserLastLoginIP(req));
		model.addAttribute("lastlogintime", UserUtils.getUserLastLoginTime(req));
		//inv.addModel("adminRole", UserUtils.getRole(inv));
		return "mypanel/personal-info";
	}
	
	@RequestMapping(value="/mypanel/changePassword",method=RequestMethod.GET)
	public String changePassword(@RequestParam(value="errorMSG",required=false) String errorMSG,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		return "mypanel/change-password";
	}
	
	@RequestMapping(value="/mypanel/changePassword",method=RequestMethod.POST)
	public String updatePassword(@RequestParam("old-password") String oldPassword,@RequestParam("password") String password, 
									@RequestParam("re-password") String repassword,HttpServletRequest req,
						            Model model){
		
		int userid = UserUtils.getUserId(req);
		Admin admin = adminService.getUserById(userid);
		String oldPasswordPersist=admin.getUserPassword();
		
		oldPassword = Inputs.trimToNull(oldPassword);
		if(oldPassword==null){
			return "redirect:/mypanel/changePassword?errorMSG=当前密码不能为空！";
		}else if(!isValidate(oldPassword)){
			return "redirect:/mypanel/changePassword?errorMSG=当前密码长度需6-16位并且需包含字母数字及特殊字符！";
		}
		
		password = Inputs.trimToNull(password);
		if(password==null){
			return "redirect:/mypanel/changePassword?errorMSG=新密码不能为空!";
		}else if(!isValidate(password)){
			return "redirect:/mypanel/changePassword?errorMSG=新密码长度需6-16位并且需包含字母数字及特殊字符！";
		}
		
		repassword = Inputs.trimToNull(repassword);
		if(repassword==null){
			return "redirect:/mypanel/changePassword?errorMSG=新密码不能为空!";
		}else if(!isValidate(repassword)){
			return "redirect:/mypanel/changePassword?errorMSG=新密码长度需6-16位并且需包含字母数字及特殊字符！";
		}
		
		if(!DigestUtils.md5Hex(oldPassword).equals(oldPasswordPersist)){
			return "redirect:/mypanel/changePassword?errorMSG=当前密码输入不正确！";
		}else{
			if(!password.equals(repassword)){
				return "redirect:/mypanel/changePassword?errorMSG=两次输入的新密码不正确。";
			}
			admin.setUserPassword(DigestUtils.md5Hex(password));
			adminService.updateUser(admin);
			model.addAttribute("errorMSG", "密码修改成功!");
		}
	
		return "mypanel/change-password";
	}
	
	public boolean isValidate(String password){
		boolean  b = false;
		//密码长度6-16位包含字母数字及特殊字符
		if(password.length()<6 || password.length()>16){
			return b;
		}
		String reg="^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(password);
		 b =m.matches();
		return b;
	}
}
