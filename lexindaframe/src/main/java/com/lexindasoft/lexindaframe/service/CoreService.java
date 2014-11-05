package com.lexindasoft.lexindaframe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lexindasoft.lexindaframe.model.Article;
import com.lexindasoft.lexindaframe.model.BaseRespMessage;
import com.lexindasoft.lexindaframe.model.CustomerRespMessage;
import com.lexindasoft.lexindaframe.model.NewsRespMessage;
import com.lexindasoft.lexindaframe.model.TextRespMessage;
import com.lexindasoft.lexindaframe.util.MessageUtil;

@Service
public class CoreService {
	/** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
        	 String respContent = "请求处理异常，请稍候尝试！";  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 默认回复此文本消息  
            TextRespMessage textRespMessage = new TextRespMessage();  
            textRespMessage.setToUserName(fromUserName);  
            textRespMessage.setFromUserName(toUserName);  
            textRespMessage.setCreateTime(new Date().getTime());  
            textRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textRespMessage.setFuncFlag(0);  
            // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义  
            textRespMessage.setContent("欢迎访问<a href=\"http://www.rstpay.com/\">融商微信公众平台</a>!");  
            // 将文本消息对象转换成xml字符串  
            respMessage = MessageUtil.textMessageToXml(textRespMessage);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                // 接收用户发送的文本消息内容  
                String content = requestMap.get("Content");  
  
                // 创建图文消息  
                NewsRespMessage newsRespMessage = new NewsRespMessage();  
                newsRespMessage.setToUserName(fromUserName);  
                newsRespMessage.setFromUserName(toUserName);  
                newsRespMessage.setCreateTime(new Date().getTime());  
                newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                newsRespMessage.setFuncFlag(0);  
  
                List<Article> articleList = new ArrayList<Article>();  
                // 单图文消息  
                if ("11".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("上海融商网络科技有限公司");  
                    article.setDescription("上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系...");  
                    article.setPicUrl("http://www.rstpay.com/images/rongshang.jpg");  
                    article.setUrl("http://www.rstpay.com/siteHome");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsRespMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsRespMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsRespMessage);  
                }  
                // 单图文消息---不含图片  
                else if ("12".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("上海融商网络科技有限公司");  
                    // 图文消息中可以使用QQ表情、符号表情  
                    article.setDescription("上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系...");  
                    // 将图片置为空  
                    article.setPicUrl("");  
                    article.setUrl("http://www.rstpay.com/siteHome");  
                    articleList.add(article);  
                    newsRespMessage.setArticleCount(articleList.size());  
                    newsRespMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsRespMessage);  
                }  
                // 多图文消息  
                else if ("s3".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("微信公众帐号开发教程\n引言");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第2篇\n微信公众帐号的类型");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第3篇\n开发模式启用及接口配置");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsRespMessage.setArticleCount(articleList.size());  
                    newsRespMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsRespMessage);  
                }  
                // 多图文消息---首条消息不含图片  
                else if ("s4".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("微信公众帐号开发教程Java版");  
                    article1.setDescription("");  
                    // 将图片置为空  
                    article1.setPicUrl("");  
                    article1.setUrl("http://blog.csdn.net/lyq8479");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第4篇\n消息及消息处理工具的封装");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第5篇\n各种消息的接收与响应");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
  
                    Article article4 = new Article();  
                    article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");  
                    article4.setDescription("");  
                    article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    articleList.add(article4);  
                    newsRespMessage.setArticleCount(articleList.size());  
                    newsRespMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsRespMessage);  
                }  
                // 多图文消息---最后一条消息不含图片  
                else if ("s5".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("第7篇\n文本消息中换行符的使用");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第8篇\n文本消息中使用网页超链接");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
  
                    Article article3 = new Article();  
                    article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");  
                    article3.setDescription("");  
                    // 将图片置为空  
                    article3.setPicUrl("");  
                    article3.setUrl("http://blog.csdn.net/lyq8479");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsRespMessage.setArticleCount(articleList.size());  
                    newsRespMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsRespMessage);  
                }// 客服消息  
                else if ("你好".equals(content)||"在".equals(content)||"客服".equals(content)) {  
                	CustomerRespMessage customerMessage = new CustomerRespMessage();
                	customerMessage.setToUserName(fromUserName);  
                	customerMessage.setFromUserName(toUserName);  
                	customerMessage.setCreateTime(new Date().getTime());  
                	customerMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER); 
                	respMessage = MessageUtil.customerMessageToXml(customerMessage); 
                }    
            }// 多客服消息  
            else if (msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER)) {  
            	BaseRespMessage customerMessage = new BaseRespMessage();
            	customerMessage.setToUserName(fromUserName);  
            	customerMessage.setFromUserName(toUserName);  
            	customerMessage.setCreateTime(new Date().getTime());  
            	customerMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER);  
            	customerMessage.setFuncFlag(0);
            } // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("11")) {  
                    	 // 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("上海融商网络科技有限公司");  
                        article.setDescription("上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系...【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/rongshang.jpg");  
                        article.setUrl("http://www.rstpay.com/siteHome");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                        
                    }else if (eventKey.equals("12")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
                       
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("公司介绍");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/upload/2014/0614/pimg_PqZXSOjS1ini.jpg");  
                        article.setUrl("http://www.rstpay.com/siteDetail?id=19&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    } else if (eventKey.equals("13")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
                       
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("招贤纳士");  
                        article.setDescription("上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系...【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/zhaoxiannashi.jpg");  
                        article.setUrl("http://www.rstpay.com/siteList?siteInfoType=3&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    } else if (eventKey.equals("14")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("我的商户");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/merchant.jpg");  
                        article.setUrl("http://www.rstpay.com/siteMerchant");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    } else if (eventKey.equals("15")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("联系我们");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/upload/2014/0616/pimg_schabw8Tas75.jpg");  
                        article.setUrl("http://www.rstpay.com/siteDetail?id=21&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    } else if (eventKey.equals("21")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("产品介绍");  
                        article.setDescription("上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系...【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/chanpinjieshao.jpg");  
                        article.setUrl("http://www.rstpay.com/siteProduct");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    }else if (eventKey.equals("22")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("办理须知");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/banlixuzhi.jpg");  
                        article.setUrl("http://www.rstpay.com/siteDetail?id=28&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    }else if (eventKey.equals("23")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("机具申请");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/jiju.jpg");  
                        article.setUrl("http://www.rstpay.com/siteTestOauth?type=5&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    }else if (eventKey.equals("24")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("及时到账");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/top.jpg");  
                        article.setUrl("http://www.rstpay.com/siteTestOauth?type=4&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    } else if (eventKey.equals("25")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("加盟融商");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/jiameng.jpg");  
                        article.setUrl("http://www.rstpay.com/siteDetail?id=29&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage);
                    } else if (eventKey.equals("31")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("机具报修");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/jijubaoxiu.jpg");  
                        article.setUrl("http://www.rstpay.com/siteTestOauth?type=1&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    }else if (eventKey.equals("32")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("纸张申请");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/zhizhang.jpg");  
                        article.setUrl("http://www.rstpay.com/siteTestOauth?type=2&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    }else if (eventKey.equals("33")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("我要增机");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/weitu/jiju.jpg");  
                        article.setUrl("http://www.rstpay.com/siteTestOauth?type=3&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    }else if (eventKey.equals("34")) {  
                    	CustomerRespMessage customerMessage = new CustomerRespMessage();
                    	customerMessage.setToUserName(fromUserName);  
                    	customerMessage.setFromUserName(toUserName);  
                    	customerMessage.setCreateTime(new Date().getTime());  
                    	customerMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER); 
                    	respMessage = MessageUtil.customerMessageToXml(customerMessage);
                    }else if (eventKey.equals("35")) {  
                    	// 创建图文消息  
                        NewsRespMessage newsRespMessage = new NewsRespMessage();  
                        newsRespMessage.setToUserName(fromUserName);  
                        newsRespMessage.setFromUserName(toUserName);  
                        newsRespMessage.setCreateTime(new Date().getTime());  
                        newsRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                        newsRespMessage.setFuncFlag(0);  
          
                        List<Article> articleList = new ArrayList<Article>();  
                        // 单图文消息  
                        Article article = new Article();  
                        article.setTitle("常见问题");  
                        article.setDescription("【点击查看详情】");  
                        article.setPicUrl("http://www.rstpay.com/images/upload/2014/0630/pimg_iNelVNj7z44J.jpg");  
                        article.setUrl("http://www.rstpay.com/siteDetail?id=30&");  
                        articleList.add(article);  
                            // 设置图文消息个数  
                        newsRespMessage.setArticleCount(articleList.size());  
                            // 设置图文消息包含的图文集合  
                        newsRespMessage.setArticles(articleList);  
                            // 将图文消息对象转换成xml字符串  
                        respMessage = MessageUtil.newsMessageToXml(newsRespMessage); 
                    }
                }  
                
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }  
  
    /** 
     * emoji表情转换(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }  
}
