package com.lexindasoft.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lexindasoft.lexindaframe.model.Admin;
import com.lexindasoft.lexindaframe.model.AdminRole;
import com.lexindasoft.lexindaframe.model.BankList;
import com.lexindasoft.lexindaframe.model.Menu;
import com.lexindasoft.lexindaframe.model.WeixinResponse;
import com.lexindasoft.lexindaframe.service.AdminService;
import com.lexindasoft.lexindaframe.service.BankService;
import com.lexindasoft.lexindaframe.util.AjaxUtils;
import com.lexindasoft.lexindaframe.util.AntiSpamUtils;
import com.lexindasoft.lexindaframe.util.HttpClientWeixin;
import com.lexindasoft.lexindaframe.util.RuntimeConfig;
import com.lexindasoft.lexindaframe.util.UserUtils;
import com.lexindasoft.lexindaframe.util.WeixinUtil;

@Controller
public class UserController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	BankService bankService;
	
	private final int PAGE_SIZE = 20;
	
	@RequestMapping(value="/user/admin/manage",method=RequestMethod.GET)
	public String adminManage(HttpServletRequest req,
            Model model){
		List<Admin> adminList = adminService.getUserList(1, 0, 0);
		model.addAttribute("adminList", adminList);
		return "user/admin-manage";
	}
	
	@RequestMapping(value="/user/admin/add",method=RequestMethod.GET)
	public String addAdmin(@RequestParam(value="errorMSG",required=false) String errorMSG,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		return "user/admin-add";
	}
	
	@RequestMapping(value="/user/admin/edit/{uid:[0-9]+}",method=RequestMethod.GET)
	public String editAdmin(@RequestParam(value="errorMSG",required=false) String errorMSG,@RequestParam("uid") int userid,HttpServletRequest req,
            Model model){
		Admin user = adminService.getUserById(userid);
		model.addAttribute("user", user);
		
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		return "user/admin-edit";
	}
	
	@RequestMapping(value="/user/admin/delete/{uid:[0-9]+}",method=RequestMethod.GET)
	public String delAdmin(@RequestParam("uid") int userid){
		//@TODO 查看用户权限
		adminService.delUser(userid);
		return "redirect:/user/admin/manage";
	}
	
	@RequestMapping(value="/user/admin/add",method=RequestMethod.POST)
	public String addAdminUser(@RequestParam("username") String username,
								@RequestParam("password") String password,
								@RequestParam("repassword") String repassword,
								@RequestParam("email") String email,
								@RequestParam("realname") String realname,
								@RequestParam("bankType") int bankType,
								@RequestParam("roleid") int roleid,HttpServletRequest req,
					            Model model){
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return "redirect:/user/admin/add?errorMSG=必须输入用户名和密码。";
		}
		
		if(adminService.checkUserName(username) > 0){
			return "redirect:/user/admin/add?errorMSG=用户名已存在。";
		}
		
		if(!password.equals(repassword)){
			return "redirect:/user/admin/add?errorMSG=两次输入的密码不正确。";
		}
		
		if(roleid <= 0){
			return "redirect:/user/admin/add?errorMSG=用户权限组选择不正确。";
		}
		
		username = AntiSpamUtils.safeText(username);
		email = AntiSpamUtils.safeText(email);
		realname = AntiSpamUtils.safeText(realname);
		adminService.addUser(username, password, roleid, email, realname, 1,null,bankType);
		
		return "redirect:/user/admin/manage";
	}
	
	@RequestMapping(value="/user/admin/edit",method=RequestMethod.POST)
	public String editAdminUser(@RequestParam("userid") int userid, 
								@RequestParam("username") String username, 
								@RequestParam("password") String password,
								@RequestParam("repassword") String repassword,
								@RequestParam("email") String email,
								@RequestParam("realname") String realname,
								@RequestParam("roleid") int roleid,
								HttpServletRequest req,
					            Model model){
		Admin user = adminService.getUserById(userid);
		
		if(StringUtils.isEmpty(username)){
			return "redirect:/user/admin/edit/"+userid+"?errorMSG=必须输入用户名。";
		}
		
		if(!user.getUserName().equals(username)){
			if(adminService.checkUserName(username) > 0){
				return "redirect:/user/admin/edit/"+userid+"?errorMSG=用户名已存在。";
			}
			user.setUserName(AntiSpamUtils.safeText(username));
		}
		
		if(!StringUtils.isEmpty(password)){
			if(!password.equals(repassword)){
				return "redirect:/user/admin/edit/"+userid+"?errorMSG=两次输入的密码不正确。";
			}
			user.setUserPassword(DigestUtils.md5Hex(password));
		}

		user.setUserEmail(AntiSpamUtils.safeText(email));
		user.setRealName(AntiSpamUtils.safeText(realname));
		adminService.updateUser(user);
		
		return "redirect:/user/admin/manage";
	}
	
	@RequestMapping(value="/user/manage",method=RequestMethod.GET)
	public String userList(@RequestParam(value="page",required=false) int page,
			HttpServletRequest req,
            Model model){
		page = (page <= 0 ? 1 : page);
		HttpSession hs = req.getSession();
		Admin admin = (Admin) hs.getAttribute("user");
		List<Admin> userList = adminService.getUserList(admin.getRoleId(), page, PAGE_SIZE);
		int userTotalNum = adminService.getUserTotalNum(0);
		int totalPage = (int)Math.ceil(userTotalNum *1.0 / PAGE_SIZE);
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		
		List<AdminRole> roleList = adminService.getRoleList(0);
		model.addAttribute("roleList", roleList);
		
		return "user/user-manage";
	}
	
	@RequestMapping(value="/user/searchlist",method=RequestMethod.POST)
	public String userSearchList(@RequestParam(value="userid",required=false) Integer userid,@RequestParam(value="username",required=false) String username,
			@RequestParam(value="roleid",required=false) Integer roleid,@RequestParam("page") int page,HttpServletRequest req,
            Model model){
		Map<String, Object> search = new HashMap<String, Object>();
		int userId =(userid !=null ? userid.intValue() : 0);
		search.put("userId", userId);
		search.put("userName", username);
		search.put("roleId", roleid);
		
		page = (page <= 0 ? 1 : page);
		
		if(userid == null && StringUtils.isEmpty(username) && roleid <= 0){
			return "redirect:/user/manage?page="+page+"&";
		}
		
		model.addAttribute("search", search);
		
		int totalNum = adminService.countUserSearchList(userId, username, roleid);
		int totalPage = (int)Math.ceil(totalNum *1.0 / PAGE_SIZE);
		List<Admin> userList = adminService.getUserSearchList(userId, username, roleid, page, PAGE_SIZE);
		
		model.addAttribute("userList", userList);
		model.addAttribute("roleId", roleid);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		
		List<AdminRole> roleList = adminService.getRoleList(0);
		model.addAttribute("roleList", roleList);
		
		return "user/user-manage";
	}
	
	@RequestMapping(value="/user/add",method=RequestMethod.GET)
	public String showUserAdd(@RequestParam(value="errorMSG",required=false) String errorMSG,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		List<BankList> bankList = bankService.getBankList();
		model.addAttribute("bankList", bankList);
		List<AdminRole> roleList = adminService.getRoleList(0);
		model.addAttribute("roleList", roleList);
		return "user/user-add";
	}
	
	@RequestMapping(value="/user/add",method=RequestMethod.POST)
	public String addUser(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			@RequestParam("repassword") String repassword,
			@RequestParam("email") String email,
			@RequestParam("realname") String realname,
			@RequestParam("bankType") int bankType,
			@RequestParam("roleid") int roleid,HttpServletRequest req,
            Model model){
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return "redirect:/user/add?errorMSG=必须输入用户名和密码。";
		}
		
		if(adminService.checkUserName(username) > 0){
			return "redirect:/user/add?errorMSG=用户名已存在。";
		}
		
		if(!password.equals(repassword)){
			return "redirect:/user/add?errorMSG=两次输入的密码不正确。";
		}
		
		if(roleid <= 0 ){
			return "redirect:/user/add?errorMSG=请选择该用户的权限组。";
		}
		List<BankList> bankList = bankService.getBankList();
		model.addAttribute("bankList", bankList);
		username = AntiSpamUtils.safeText(username);
		email = AntiSpamUtils.safeText(email);
		realname = AntiSpamUtils.safeText(realname);
		adminService.addUser(username, password, roleid, email, realname, 1,null,bankType);
		
		return "redirect:/user/manage?page=1&";
	}
	
	@RequestMapping(value="/user/edit",method=RequestMethod.GET)
	public String editUser(@RequestParam(value="errorMSG",required=false) String errorMSG,@RequestParam("uid") int userid,@RequestParam("page") int page,HttpServletRequest req,
            Model model) throws UnsupportedEncodingException{
		Admin user = adminService.getUserById(userid);
		model.addAttribute("user", user);
		List<AdminRole> roleList = adminService.getRoleList(0);
		model.addAttribute("roleList", roleList);
		model.addAttribute("page", page);
		List<BankList> bankList = bankService.getBankList();
		model.addAttribute("bankList", bankList);
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG",new String(errorMSG.getBytes("iso-8859-1"), "utf-8"));
		}
		
		return "user/user-edit";
	}
	
	@RequestMapping(value="/user/delete",method=RequestMethod.GET)
	public String delUser(@RequestParam("uid") int userid,@RequestParam("page") int page){
		//@TODO 查看用户权限
		adminService.delUser(userid);
		return "redirect:/user/manage?page="+page+"&";
	}
	
	@RequestMapping(value="/user/edit",method=RequestMethod.POST)
	public String editUserInfo(@RequestParam("userid") int userid,@RequestParam("username") String username, @RequestParam(value="password",required=false) String password,@RequestParam(value="repassword",required=false) String repassword,
			@RequestParam(value="email",required=false) String email,@RequestParam(value="realname",required=false) String realname,@RequestParam("roleid") int roleid,@RequestParam("page") String page,HttpServletRequest req,
            Model model){
		Admin user = adminService.getUserById(userid);
		
		if(StringUtils.isEmpty(username)){
			return "redirect:/user/edit?uid="+userid+"&errorMSG=必须输入用户名。&page="+page+"&";
		}
		
		if(!user.getUserName().equals(username)){
			if(adminService.checkUserName(username) > 0){
				return "redirect:/user/edit?uid="+userid+"&errorMSG=用户名已存在。&page="+page+"&";
			}
			user.setUserName(AntiSpamUtils.safeText(username));
		}
		
		if(!StringUtils.isEmpty(password)){
			if(!password.equals(repassword)){
				return "redirect:/user/edit?uid="+userid+"&errorMSG=两次输入的密码不正确。&page="+page+"&";
			}
			user.setUserPassword(DigestUtils.md5Hex(password));
		}
		
		user.setUserEmail(AntiSpamUtils.safeText(email));
		user.setRealName(AntiSpamUtils.safeText(realname));
		user.setRoleId(roleid);
		adminService.updateUser(user);
		
		return "redirect:/user/manage?page="+page+"&";
	}
	
	@RequestMapping(value="/user/adminrole/manage",method=RequestMethod.GET)
	public String roleManage(HttpServletRequest req,
            Model model){
		//TODO 获取登录用户信息
		Admin user = adminService.getUserById(UserUtils.getUserId(req));
		if (user == null){
			return "/";
		}
		List<AdminRole> roleList = adminService.getRoleList(user.getRoleId());
		model.addAttribute("roleList", roleList);
		return "user/role-manage";
	}
	
	@RequestMapping(value="user/access/setting",method=RequestMethod.GET)
	public String accessSetting(@RequestParam("roleid") int roleid,HttpServletRequest req,
            Model model){
		model.addAttribute("roleid", roleid);
		AdminRole role = adminService.getRole(roleid);
		if (role != null){
			String modules = role.getRoleModules();
			if (StringUtils.isNotEmpty(modules)){
				Gson gson = new Gson();
				Map<String, String> map = gson.fromJson(modules, new TypeToken<Map<String, String>>(){}.getType());
				model.addAttribute("moduleMap", map);
			}
		}
		
		return "user/access-setting";
	}
	
	@RequestMapping(value="/user/role/add",method=RequestMethod.GET)
	public String  showRoleAdd(@RequestParam("errorMSG") String errorMSG,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		return "user/role-add";
	}
	
	@RequestMapping(value="/user/role/edit/{id:[0-9]+}",method=RequestMethod.GET)
	public String editRole(@RequestParam("errorMSG") String errorMSG,@RequestParam("id") int roleid,HttpServletRequest req,
            Model model){
		if(!StringUtils.isEmpty(errorMSG)){
			model.addAttribute("errorMSG", errorMSG);
		}
		AdminRole role = adminService.getRole(roleid);
		model.addAttribute("role", role);
		return "user/role-edit";
	}
	
	@RequestMapping(value="/user/role/add",method=RequestMethod.POST)
	public String addRole(@RequestParam("rolename") String rolename, @RequestParam("roleDesc") String roleDesc,@RequestParam("isEnable") int isEnable,HttpServletRequest req,
            Model model){
		if(StringUtils.isEmpty(rolename)){
			return "redirect:/user/role/add?errorMSG=必须输入角色名称。";
		}
		if (StringUtils.isEmpty(roleDesc)){
			return "redirect:/user/role/add?errorMSG=必须输入角色描述";
		}
		if (adminService.checkRoleName(rolename) > 0){
			return "redirect:/user/role/add?errorMSG=角色名称已存在。";
		}
		adminService.addRole(rolename, roleDesc, isEnable);
		return "redirect:/user/adminrole/manage";
	}
	
	
	@RequestMapping(value="/user/role/edit",method=RequestMethod.POST)
	public String editRoleInfo(@RequestParam("rolename") String rolename, @RequestParam("roleDesc") String roleDesc,
			@RequestParam("isEnable") int isEnable, @RequestParam("roleid") int roleid,HttpServletRequest req,
            Model model){
		AdminRole role = adminService.getRole(roleid);
		if(StringUtils.isEmpty(rolename)){
			return "redirect:/user/role/edit/"+roleid+"?errorMSG=必须输入角色名称。";
		}
		if(StringUtils.isEmpty(roleDesc)){
			return "redirect:/user/role/edit/"+roleid+"?errorMSG=必须输入角色描述。";
		}
		if(!role.getRoleName().equals(rolename)){
			if(adminService.checkRoleName(rolename) > 0){
				return "redirect:/user/role/edit/"+roleid+"?errorMSG=角色名称已存在。";
			}
			role.setRoleName(AntiSpamUtils.safeText(rolename));
		}
		role.setRoleId(roleid);
		role.setUserDisabled(isEnable);
		adminService.updateRole(role);
		return "redirect:/user/adminrole/manage";
	}
	
	@RequestMapping(value="/user/role/editRoleModule",method=RequestMethod.POST)
	public void editRoleModule(@RequestParam("modules") String modules, @RequestParam("roleid") int roleid,
							HttpServletResponse resp){
		Map<String, String> map = new HashMap<String, String>();
		adminService.updateRoleModule(roleid, modules);
		map.put("result", "success");
		AjaxUtils.printJson(map, resp);
	}
	
	@RequestMapping(value="/user/role/remove/{id:[0-9]+}",method=RequestMethod.GET)
	public String remove(@RequestParam("id") int roleid){
		adminService.deleteAdminRole(roleid);
		return "redirect:/user/adminrole/manage";
	}
	
	@RequestMapping(value="/user/role/editRoleColumn",method=RequestMethod.POST)
	public void editRoleColumn(@RequestParam("categorys") String categorys, @RequestParam("roleid") int roleid,
								HttpServletResponse resp){
		Map<String, String> map = new HashMap<String, String>();
		adminService.updateRoleColumn(roleid, categorys);
		map.put("result", "success");
		AjaxUtils.printJson(map, resp);
	}
	
	@RequestMapping(value="/user/enable",method=RequestMethod.GET)
	public String enableUser(@RequestParam("uid") int userid, @RequestParam("page") int page){
		Admin user = adminService.getUserById(userid);
		if(user != null){
			user.setUserStatus(1);
			adminService.updateUser(user);
			bankService.updateBankInfo(user.getOpenId());
			String access_token = RuntimeConfig.getInstance().getAssessToken();
			System.out.println("token"+access_token);
			int i = WeixinUtil.createMessage(user.getOpenId(),access_token);
			System.out.println("发送主动回复消息状态"+i);
		}
		return "redirect:/user/manage?page="+page+"&";
	}
	
	@RequestMapping(value="/user/disable",method=RequestMethod.GET)
	public String disableUser(@RequestParam("uid") int userid, @RequestParam("page") int page){
		Admin user = adminService.getUserById(userid);
		if(user != null){
			user.setUserStatus(0);
			adminService.updateUser(user);
		}
		return "redirect:/user/manage?page="+page+"&";
	}
	
	public static void main(String[] args){
		int userInfoData = WeixinUtil.createMessage("o5TeKuKfOAjIZGaCgzn6-fKx9V-U", "4ENGM-vEqAdDOWR0k5cJ7aJ8qgq-f3rwNufaM3iEKNYKnRM8MUHfcaSRywpmnjPqCZ4AltxIgby591iFNhH_Dw");
		System.out.println(userInfoData);
	}
	
}
