package com.lexindasoft.lexindaframe.service;

import java.util.List;
import java.util.Map;

import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.AdminRole;
import com.lexindasoft.lexindaframe.model.UserInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;



public interface AdminService {
	
	public Admin getUser(String username, String password);
	
	public Admin getUserByMerchantId(int merchantId);
	
	public Admin getUserById(int userid);
	
	public int addUser(String username, String password, int roleid, String email, String realname, int status,String openId,int bankType);
	
	public int addUser(Admin user);
	
	public int delUser(int userid);
	
	public List<Admin> getUserList(int roleid, int page, int limit);
	
	public int getUserTotalNum(int roleId);
	
	public int checkUserName(String username);
	
	public List<AdminRole> getRoleList(int superAdminAvaiable);
	
	public int updateUser(Admin user);
	
	public int countUserSearchList(int userid, String username, int roleid);
	
	public List<Admin> getUserSearchList(int userid, String username, int roleid, int page, int limit);
	
	public int addRole(String rolename, String roledesc, int isEnable);
	
	public int checkRoleName(String rolename);
	
	public AdminRole getRole(int roleid);
	
	public int updateRole(AdminRole role);
	
	public int updateRoleModule(int roleid, String modules);
	
	public int deleteAdminRole(int roleid);
	
	public int updateRoleColumn(int roleid, String categorys);
	
	public int insertWeixinUser(UserInfo userInfo);
	
	public int updateWeixinUser(UserInfo userInfo);
	
	public UserInfo queryUserByOpenId(String openId);
	
	public int insertWeixinBusiness(WeixinBusiness weixinBusiness);
	
	public int updateWeixinBusiness(Map<String,Object> map);
	
	public WeixinBusiness queryWeixinBusiness(WeixinBusiness weixinBusiness);
	
	public List<WeixinBusiness> queryAllWeixinBusiness(WeixinBusiness weixinBusiness);
}
