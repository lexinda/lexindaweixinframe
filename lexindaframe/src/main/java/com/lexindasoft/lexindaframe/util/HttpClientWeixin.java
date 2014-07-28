package com.lexindasoft.lexindaframe.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.google.gson.Gson;

public class HttpClientWeixin implements java.io.Serializable {

	private static final long serialVersionUID = -176092625883595547L;
	
	private final Log logger = LogFactory.getLog(getClass());
	private final boolean isDebugEnabled = logger.isDebugEnabled();

	private static final int OK = 200;
	private static MultiThreadedHttpConnectionManager connectionManager = getConnectionManager();
	private org.apache.commons.httpclient.HttpClient client = getClient();

	public HttpClientWeixin() {
	}

	public String get(String url, List<HttpParam> params) {
		if (null != params && params.size() > 0) {
			String encodedParams = encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		logger.info("Get " + url);
		GetMethod getmethod = new GetMethod(url);
		return httpRequest(getmethod);
	}

	public String post(String url, List<HttpParam> params) {
		logger.info("Post " + url + ", params=" + new Gson().toJson(params));
		PostMethod postMethod = new PostMethod(url);
		for (int i = 0; null != params&&i < params.size(); i++) {
			postMethod.addParameter(params.get(i).getName(), params.get(i)
					.getValue());
		}
		HttpMethodParams param = postMethod.getParams();
		param.setContentCharset("UTF-8");
		return httpRequest(postMethod);
	}

	public String httpRequest(HttpMethod method) {
		int responseCode = -1;
		try {
			client.executeMethod(method);

			responseCode = method.getStatusCode();
			logger.info("http response status code=" + String.valueOf(responseCode));

			String response = method.getResponseBodyAsString();
			logger.info("http response body=" + response);

			if (responseCode != OK) {
				throw new RuntimeException();
			}
			return response;
		} catch (IOException ioe) {
			throw new RuntimeException();
		} finally {
			method.releaseConnection();
		}
	}

	private String encodeParameters(List<HttpParam> postParams) {
		StringBuilder buf = new StringBuilder();
		for (int j = 0; j < postParams.size(); j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				buf.append(
						URLEncoder.encode(postParams.get(j).getName(), "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(postParams.get(j).getValue(),
								"UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();
	}
	
	private static MultiThreadedHttpConnectionManager getConnectionManager(){
		MultiThreadedHttpConnectionManager ret = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = ret.getParams();
		params.setDefaultMaxConnectionsPerHost(150);
		params.setConnectionTimeout(30000);
		params.setSoTimeout(30000);
		
		Protocol.registerProtocol("https", new Protocol("https", new SimpleSSLSocketFactory(), 443));
		
		return ret;
	}
	
	private org.apache.commons.httpclient.HttpClient getClient(){
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		org.apache.commons.httpclient.HttpClient ret = new org.apache.commons.httpclient.HttpClient(clientParams, connectionManager);
		return ret;
	}

}
