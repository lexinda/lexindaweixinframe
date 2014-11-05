package com.lexindasoft.lexindaframe.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



public class DashboardSessionFilter implements Filter {
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(DashboardSessionFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		logger.debug("Begin:...");
		
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String servletPath = req.getServletPath();
        if(servletPath.contains("login") ||servletPath.contains("site")||servletPath.contains("bank")||servletPath.contains("report") ||servletPath.contains(".jpg")  || servletPath.contains(".bmp") 
                || servletPath.contains("images") || servletPath.contains("css") || servletPath.contains("js") 
               ){
        	chain.doFilter(req, resp); 
        	logger.debug("end:....");
            }else{ 
            	if(req.getSession().getAttribute("user")==null){
            		req.getSession().invalidate();
            		request.setCharacterEncoding("UTF-8");
                    response.setContentType("UTF-8");
                    java.io.PrintWriter out = response.getWriter();
            		out.println("<script>");
            	    out.println("window.open('/login.jsp','_top');");
            	    out.println("</script>"); 
//            	    RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.jsp"); 
//            	    requestDispatcher.forward(request,response);
            	}else{
            		chain.doFilter(req, resp); 
            	}
            }
    }
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}










