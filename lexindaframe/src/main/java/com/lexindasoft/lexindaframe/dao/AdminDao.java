package com.lexindasoft.lexindaframe.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.UserInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;


@Component
public interface AdminDao {

		public Admin getUser(Map<String,Object> paramMap);
		
		public Admin getUserById(int userid);
		
		public int addUser(Admin admin);
		
		public int delUser(int userid);
		
		public List<Admin> getUserList(Map<String,Object> paramMap);
		
		public int countUserList(Map<String,Object> paramMap);
		
		public int checkUserName(String username);
		
		public int updateUser(Admin user);
		
		public int countUserSearchList(Map<String,Object> paramMap);
		
		public List<Admin> getUserSearchList(Map<String,Object> paramMap);
		
		public Admin getUserByMerchantId(int merchantId);
		
		public int insertWeixinUser(UserInfo userInfo);
		
		public int updateWeixinUser(UserInfo userInfo);
		
		public UserInfo queryUserByOpenId(String openId);
		
		public int insertWeixinBusiness(WeixinBusiness weixinBusiness);
		
		public int updateWeixinBusiness(Map<String,Object> map);
		
		public WeixinBusiness queryWeixinBusiness(WeixinBusiness weixinBusiness);
		
		public List<WeixinBusiness> queryAllWeixinBusiness(WeixinBusiness weixinBusiness);
	}
