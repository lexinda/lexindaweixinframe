package com.lexindasoft.lexindaframe.util;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.AdminRole;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.ui.Model;



public class UserUtils {

	public static int getUserId(HttpServletRequest req){
		HttpSession userSession = req.getSession();
		if(userSession.getAttribute("userId") == null){
			return 0;
		}
		else{
			return NumberUtils.toInt(userSession.getAttribute("userId").toString());
		}		
	}
	
	public static String getUserLastLoginIP(HttpServletRequest req){
		HttpSession userSession = req.getSession();
		if(userSession.getAttribute("lastloginip") == null){
			return null;
		}
		else{
			return userSession.getAttribute("lastloginip").toString();
		}		
	}
	
	public static Date getUserLastLoginTime(HttpServletRequest req){
		HttpSession userSession =req.getSession();
		if(userSession.getAttribute("lastlogintime") == null){
			return null;
		}
		else{
			return (Date)userSession.getAttribute("lastlogintime");
		}		
	}
	
	public static Admin getUser(Model model){
		return (Admin)model.addAttribute("user");		
	}
	
	public static AdminRole getRole(Model model){
		return (AdminRole)model.addAttribute("role");	
	}
}
