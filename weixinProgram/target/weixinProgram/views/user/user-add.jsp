<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加用户</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	 <link href="/css/customer.css" rel="stylesheet" type="text/css" />
	<style type="text/css">

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
.page-title{ margin: 15px 0 10px 25px; }

blockquote { border-left: 8px solid #006dcc; }
blockquote { padding: 0 0 0 15px; margin: 0 0 20px; }
h3 { font-size: 22px; }
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"] { height: 26px; line-height: 30px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }

input[type="text"] { height: 18px; font-size: 12px; }
.required{color: #d80000;padding: 0 5px;}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
		<a href="/user/manage"> <em>用户管理</em>
		</a>
		</li>
		<li class="active">
		<a href="javascript:void(0);" class="on"> <em>添加用户</em>
		</a>
		</li>
	</ul>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="addUser" class="form-horizontal" action="/user/add" method="post">
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>用户名:</label>
							<div class="controls">
								<input type="text" id="username" name="username" value="" maxlength="20" /><span id="userNameWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>密码:</label>
							<div class="controls">
								<input type="password" id="password" name="password" value="" maxlength="20" /><span id="passwordWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>确认密码:</label>
							<div class="controls">
								<input type="password" id="repassword" name="repassword" value="" maxlength="20" /><span id="repasswordWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">E-mail:</label>
							<div class="controls">
								<input type="text" id="email" name="email" value="" maxlength="50" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">真实姓名:</label>
							<div class="controls">
								<input type="text" id="realname" name="realname" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所属银行:</label>
							<div class="controls">
								<select class="span2" id="bankType" name="bankType">
									<option value="0" selected>=选择银行=</option>
									<c:forEach items="${bankList}" var="bank">
										<option value="${bank.bankId}">${bank.bankDesc}</option>
									</c:forEach>
								</select><span id="roleidWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>所属角色:</label>
							<div class="controls">
								<select class="span2" id="roleid" name="roleid">
									<option value="0" selected>=选择权限组=</option>
									<c:forEach items="${roleList}" var="role">
										<option value="${role.roleId}">${role.roleName}</option>
									</c:forEach>
								</select><span id="roleidWarn" style="color:red"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="red-btn" type="button">保存</button></div>
				<input type="hidden" id="submitFlag" value="0" />
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
var CheckAndSubmit = function(){
	$("#userNameWarn").text("");
	$("#passwordWarn").text("");
	$("#repasswordWarn").text("");
	
	if($("#username").val() <= 0){
		$("#userNameWarn").text("用户名不能为空！");
	}else if($("#password").val() <= 0){
		$("#passwordWarn").text("密码不能为空！");
	}else if($("#repassword").val() <= 0){
		$("#repasswordWarn").text("确认密码不能为空！");
	}else if($("#roleid").val() == 0){
		$("#roleidWarn").text("请选择所属角色！");
	}else{
		$("#addUser").submit();
	}
}
$("#submitBtn").live("click", function(){
	CheckAndSubmit();
});
</script>
</body>
	</html>