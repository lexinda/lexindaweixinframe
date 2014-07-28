package com.lexindasoft.lexindaframe.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

import static com.lexindasoft.lexindaframe.util.ResourceUtils.loadResource;

/**
 * Created by yiya on 3/18/14.
 */
public class RuntimeConfig {

    private static Log LOGGER = LogFactory.getLog(RuntimeConfig.class);
    private static boolean DEBUGABLE = LOGGER.isDebugEnabled();

    private static RuntimeConfig instance;
    private static Object lock = new Object();

    private String imagePath;
    private String appId;
    private String appSecret;

    private String tokenService;
    
    private String assessToken;
    
    private RuntimeConfig(){
        Map<String, String> configs = loadResource("/runtime-config.properties");
        LOGGER.info("runtime-config.properties: " + configs);

        imagePath = configs.get("imagePath");
        appId = configs.get("appId");
        appSecret = configs.get("appSecret");

        tokenService = configs.get("tokenService");
        
        assessToken = configs.get("assessToken");
    }

    public static RuntimeConfig getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance = new RuntimeConfig();
                }
            }
        }
        return instance;
    }

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getTokenService() {
		return tokenService;
	}

	public void setTokenService(String tokenService) {
		this.tokenService = tokenService;
	}

	public String getAssessToken() {
		return assessToken;
	}

	public void setAssessToken(String assessToken) {
		this.assessToken = assessToken;
	}

}
