package com.lexindasoft.lexindaframe.Interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InitServlet.class);

	public void init() throws ServletException {
			new Thread(new TokenThread()).start();
	}
}
