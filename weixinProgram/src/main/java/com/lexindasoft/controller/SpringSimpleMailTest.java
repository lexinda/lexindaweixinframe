package com.lexindasoft.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * 简单的邮件程序
 * 
 * Aug 11, 2010 2:55:40 PM
 */
public class SpringSimpleMailTest {
	public static void main(String[] args) {
		ApplicationContext actx = new ClassPathXmlApplicationContext(
				"mailMessage.xml");
		MailSender ms = (MailSender) actx.getBean("mailSender");
		SimpleMailMessage smm = (SimpleMailMessage) actx.getBean("mailMessage");
		// 主题,内容
		smm.setSubject("你好，融商");
		smm.setText("上海融商网络科技有限公司给您发的测试邮件，可以忽略！");
		ms.send(smm);
	}
}
