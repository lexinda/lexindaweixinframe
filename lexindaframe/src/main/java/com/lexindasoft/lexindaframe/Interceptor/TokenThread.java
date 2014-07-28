package com.lexindasoft.lexindaframe.Interceptor;

import org.apache.log4j.Logger;

import com.lexindasoft.lexindaframe.model.AccessToken;
import com.lexindasoft.lexindaframe.util.RuntimeConfig;
import com.lexindasoft.lexindaframe.util.WeixinUtil;

public class TokenThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(WeixinUtil.class);
	// 第三方用户唯一凭证
	public static String appid = RuntimeConfig.getInstance().getAppId();
	// 第三方用户唯一凭证密钥
	public static String appsecret = RuntimeConfig.getInstance().getAppSecret();
	public static AccessToken accessToken = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeixinUtil.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					logger.info("获取access_token成功，有效时长{}秒 token:{}"+ accessToken.getExpiresIn()+ accessToken.getToken());
					RuntimeConfig.getInstance().setAssessToken(accessToken.getToken());
					
					System.out.println(RuntimeConfig.getInstance().getAssessToken());
					// 休眠7000秒
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					logger.error("{}", e1);
				}
				logger.error("{}", e);
			}
		}
	}

}
