package com.lexindasoft.lexindaframe.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.lexindasoft.lexindaframe.model.AccessToken;
import com.lexindasoft.lexindaframe.model.Access_UserToken;
import com.lexindasoft.lexindaframe.model.Menu;
import com.lexindasoft.lexindaframe.model.MyX509TrustManager;
import com.lexindasoft.lexindaframe.model.WeixinResponse;

public class WeixinUtil {
	private static Logger logger = Logger.getLogger(WeixinUtil.class);
	  
    /** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        String jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = buffer.toString();  
        } catch (ConnectException ce) {  
        	logger.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
        	logger.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }  
    
 // 获取access_token的接口地址（GET） 限200（次/天）  
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
      
    /** 
     * 获取access_token 
     *  
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */  
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
        Access_UserToken accessUserToken = null;
        logger.info("获取access_token成功，有效时长{}秒 token:{}"+ appid+"......."+ appsecret);
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
        logger.info("获取access_token成功，有效时长{}秒 token:{}"+ requestUrl);
        String jsonObject = httpRequest(requestUrl, "GET", null);  
        // 如果请求成功  
        if (null != jsonObject) {  
            try {  
            	System.out.println(jsonObject);
            	accessUserToken = new Gson().fromJson(jsonObject, Access_UserToken.class);
                accessToken = new AccessToken();  
                accessToken.setToken(accessUserToken.getAccess_token());  
                accessToken.setExpiresIn(accessUserToken.getExpires_in());  
            } catch (Exception e) {  
                accessToken = null;  
                // 获取token失败  
                logger.error("获取token失败 errcode:{} errmsg:{}"+ accessUserToken.getErrcode()+ accessUserToken.getErrmsg());  
            }  
        }  
        return accessToken;  
    }  
    
 // 菜单创建（POST） 限100（次/天）  
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
      
    /** 
     * 创建菜单 
     *  
     * @param menu 菜单实例 
     * @param accessToken 有效的access_token 
     * @return 0表示成功，其他值表示失败 
     */  
    public static int createMenu(Menu menu, String accessToken) {  
        int result = 0;  
      
        // 拼装创建菜单的url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
        // 将菜单对象转换成json字符串  
        String jsonMenu = new Gson().toJson(menu);  
        // 调用接口创建菜单  
        String jsonObject = httpRequest(url, "POST", jsonMenu);  
      
        if (null != jsonObject) { 
        	WeixinResponse weixinResponse = new Gson().fromJson(jsonObject, WeixinResponse.class);
            if (0 != Integer.parseInt(weixinResponse.getErrcode())) {  
                result = Integer.parseInt(weixinResponse.getErrcode());  
                logger.error("创建菜单失败 errcode:{} errmsg:{}"+ weixinResponse.getErrcode()+weixinResponse.getErrmsg());  
            }  
        }  
      
        return result;  
    }  
    
    public static int createMessage(String openId, String accessToken) {  
        int result = 0;  
      
        // 拼装创建菜单的url  
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+ accessToken;  
        // 将菜单对象转换成json字符串  
        String jsonMenu = "{\"touser\": [\""+openId+"\" ], \"msgtype\": \"text\", \"text\": { \"content\": \"审核通过，你可以进行报件申请操作！\"}}";  
        // 调用接口创建菜单  
        String jsonObject = httpRequest(url, "POST", jsonMenu);  
      
        if (null != jsonObject) { 
        	WeixinResponse weixinResponse = new Gson().fromJson(jsonObject, WeixinResponse.class);
            if (0 != Integer.parseInt(weixinResponse.getErrcode())) {  
                result = Integer.parseInt(weixinResponse.getErrcode());  
                logger.error("发送消息失败 errcode:{} errmsg:{}"+ weixinResponse.getErrcode()+weixinResponse.getErrmsg());  
            }  
        }  
      
        return result;  
    }  
}
