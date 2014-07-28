package com.lexindasoft.lexindaframe.util;

import org.apache.commons.lang.StringUtils;

public final class Inputs {
	
	public static final void checkNull(Object... objs){
		if(!notNull(objs)){
			throw new NullPointerException();
		}
	}
	
	public static final boolean notNull(Object... objs){
		for(Object obj : objs){
			if(obj==null){
				return false;
			}
		}
		return true;
	}

	public static final String trimToNull(String str){
        String ts = StringUtils.trim(str);
        return StringUtils.isBlank(ts) ? null : ts;
	}

}
