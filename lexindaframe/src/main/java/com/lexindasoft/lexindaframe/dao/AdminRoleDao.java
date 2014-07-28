package com.lexindasoft.lexindaframe.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lexindasoft.lexindaframe.model.AdminRole;


@Component
public interface AdminRoleDao {

	public AdminRole getRole(int roleid);
	
	public List<AdminRole> getRoleList(Map<String,Object> paramMap);
	
	public int addRole(AdminRole adminRole);
	
	public int checkRoleName(String rolename);
	
	public int updateRole(AdminRole role);
	
	public int updateRoleModule(AdminRole role);
	
	public int deleteAdminRole(int roleid);
	
	public int updateRoleColumn(AdminRole role);
	
}
