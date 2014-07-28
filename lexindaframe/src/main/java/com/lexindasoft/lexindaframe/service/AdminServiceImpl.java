package com.lexindasoft.lexindaframe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexindasoft.lexindaframe.dao.AdminDao;
import com.lexindasoft.lexindaframe.dao.AdminRoleDao;
import com.lexindasoft.lexindaframe.dao.AdminRolePrivDao;
import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.AdminRole;
import com.lexindasoft.lexindaframe.model.UserInfo;
import com.lexindasoft.lexindaframe.model.WeixinBusiness;




@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AdminRoleDao adminRoleDao;
	
	@Autowired
	AdminRolePrivDao adminRolePrivDao;
	
	public Admin getUser(String username, String password) {
		password = DigestUtils.md5Hex(password);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userName", username);
		paramMap.put("userPassword", password);
		return adminDao.getUser(paramMap);
	}

	
	public int addUser(String username, String password, int roleid,
			String email, String realname, int status,String openId,int bankType) {
		Admin admin = new Admin();
		admin.setUserName(username);
		admin.setUserPassword(DigestUtils.md5Hex(password));
		admin.setRoleId(roleid);
		admin.setUserEmail(email);
		admin.setRealName(realname);
		admin.setOpenId(openId);
		admin.setUserStatus(status);
		admin.setBankType(bankType);
		
		return adminDao.addUser(admin);
	}

	
	public int delUser(int userid) {
		return adminDao.delUser(userid);
	}

	
	public List<Admin> getUserList(int roleid, int page, int limit) {
		page = (page <= 0 ? 1 : page);
		int index = (page - 1) * limit;
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("roleId", roleid);
		paramMap.put("index", index);
		paramMap.put("limit", limit);
		List<Admin> userList = adminDao.getUserList(paramMap);
		for(Admin user : userList){
//			user.setAdminRole(adminRoleDao.getRole(user.getRoleId()));
		}
		return userList;
	}

	
	public int getUserTotalNum(int roleId) {
		
		int result = 0;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("roleKey", roleId);
		result = adminDao.countUserList(paramMap);
		
		return result;
	}

	
	public int checkUserName(String username) {
		return adminDao.checkUserName(username);
	}

	
	public List<AdminRole> getRoleList(int superAdminAvaiable) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("superAdminAvaiable", superAdminAvaiable);
		 List<AdminRole> list = adminRoleDao.getRoleList(paramMap);
		return list;
	}

	
	public Admin getUserById(int userid) {
		return adminDao.getUserById(userid);
	}

	
	public int updateUser(Admin user) {
		return adminDao.updateUser(user);
	}

	
	public int countUserSearchList(int userid, String username, int roleid) {
		if(StringUtils.isBlank(username)){
			username="";
		}else{
			username = "%" + username + "%";
		}		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userid);
		paramMap.put("userName", username);
		paramMap.put("roleId", roleid);
		return adminDao.countUserSearchList(paramMap);
	}

	
	public List<Admin> getUserSearchList(int userid, String username,
			int roleid, int page, int limit) {
		int index = (page - 1) * limit;
		if(StringUtils.isBlank(username)){
			username="";
		}else{
			username = "%" + username + "%";
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userid);
		paramMap.put("userName", username);
		paramMap.put("roleId", roleid);
		paramMap.put("index", index);
		paramMap.put("limit", limit);
		List<Admin> userList = adminDao.getUserSearchList(paramMap);
		return userList;
	}

	
	public int addRole(String rolename, String roledesc, int isEnable) {
		AdminRole role = new AdminRole();
		role.setRoleName(rolename);
		role.setUserDescription(roledesc);
		role.setUserDisabled(isEnable);
		return adminRoleDao.addRole(role);
	}

	
	public int checkRoleName(String rolename) {
		return adminRoleDao.checkRoleName(rolename);
	}

	
	public AdminRole getRole(int roleid) {
		return adminRoleDao.getRole(roleid);
	}

	
	public int updateRole(AdminRole role) {
		return adminRoleDao.updateRole(role);
	}

	
	public int updateRoleModule(int roleid, String modules) {
		AdminRole role = new AdminRole();
		role.setRoleId(roleid);
		role.setRoleModules(modules);
		return adminRoleDao.updateRoleModule(role);
	}

	
	public int deleteAdminRole(int roleid) {
		return adminRoleDao.deleteAdminRole(roleid);
	}

	
	public int updateRoleColumn(int roleid, String categorys) {
		AdminRole role = new AdminRole();
		role.setRoleId(roleid);
		role.setRoleColumns(categorys);
		return adminRoleDao.updateRoleColumn(role);
	}

	
	public int addUser(Admin user) {
		user.setRoleId(1);
		user.setUserStatus(1);
		return adminDao.addUser(user);
	}

	
	public Admin getUserByMerchantId(int merchantId) {
		if (merchantId<=0 ) {
			return null;
		}
		return adminDao.getUserByMerchantId(merchantId);
	}


	public int insertWeixinUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return adminDao.insertWeixinUser(userInfo);
	}


	public int updateWeixinUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return adminDao.updateWeixinUser(userInfo);
	}


	public UserInfo queryUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		return adminDao.queryUserByOpenId(openId);
	}


	public int insertWeixinBusiness(WeixinBusiness weixinBusiness) {
		// TODO Auto-generated method stub
		return adminDao.insertWeixinBusiness(weixinBusiness);
	}


	public int updateWeixinBusiness(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return adminDao.updateWeixinBusiness(map);
	}


	public WeixinBusiness queryWeixinBusiness(WeixinBusiness weixinBusiness) {
		// TODO Auto-generated method stub
		return adminDao.queryWeixinBusiness(weixinBusiness);
	}


	public List<WeixinBusiness> queryAllWeixinBusiness(WeixinBusiness weixinBusiness) {
		// TODO Auto-generated method stub
		return adminDao.queryAllWeixinBusiness(weixinBusiness);
	}
	
}
