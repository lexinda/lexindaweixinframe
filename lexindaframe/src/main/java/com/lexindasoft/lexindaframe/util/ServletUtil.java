package com.lexindasoft.lexindaframe.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class ServletUtil {

	public static String getClientRealIp(HttpServletRequest req){
		String ret = req.getHeader("X-Real-IP");
		if("".equals(StringUtils.trimToEmpty(ret))){
			ret = req.getRemoteAddr();
		}
		return ret;
	}
	
}
