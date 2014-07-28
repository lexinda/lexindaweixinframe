package com.lexindasoft.lexindaframe.Interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.AdminRole;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.util.UserUtils;

public class BasedInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private AdminService adminService;
	//预处理  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
    	MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
    	System.out.println("methodName="+methodNameResolver.getHandlerMethodName(request)); 
    	int userId = UserUtils.getUserId(request);
    	System.out.println("userId---================================="+userId);
    	HttpSession hs = request.getSession();
    	
    	if(userId>0){
    		System.out.println("user---================================="+hs.getAttribute("roleMap"));
    		if(hs.getAttribute("roleMap")==null){
    			Admin user = adminService.getUserById(UserUtils.getUserId(request));
    			System.out.println("user---================================="+user);
        		AdminRole role = adminService.getRole(user.getRoleId());
        		System.out.println("role---================================="+role);
        		if (role != null){
        			Gson gson = new Gson();
        			Map<String, String> map = gson.fromJson(role.getRoleModules(), new TypeToken<Map<String, String>>(){}.getType());
        			System.out.println(map.toString());
        			hs.setAttribute("roleMap", map);
        		} else{
        			System.out.println("role is null");
        			hs.setAttribute("roleMap", new HashMap<String, String>());
        		}
        		hs.setAttribute("role", role);
        		hs.setAttribute("user", user);
    		}
    	}
		return true;
    }  
  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
    	System.out.println("Post-handle");  
    }  
  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    	System.out.println("After completion handle");  
    }  
}
