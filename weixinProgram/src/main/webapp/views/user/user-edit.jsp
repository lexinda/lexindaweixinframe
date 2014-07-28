<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>编辑用户</title>
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
		<a href="javascript:void(0);" class="on"> <em>编辑用户</em>
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
					<form id="editUser" class="form-horizontal" action="/user/edit" method="post">
						<input type="hidden" name="userid" value="${user.userId}"/>
						<input type="hidden" name="page" value="${page}"/>
						<div class="control-group">
							<label class="control-label">用户名:</label><span id="userNameWarn" style="color:red"></span>
							<div class="controls">
								<input type="text" id="username" name="username" value="${user.userName}" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">密码:</label>
							<div class="controls">
								<input type="password" id="password" name="password" value="" maxlength="20" /><span id="passwordWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">确认密码:</label>
							<div class="controls">
								<input type="password" id="repassword" name="repassword" value="" maxlength="20" /><span id="repasswordWarn" style="color:red"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">E-mail:</label>
							<div class="controls">
								<input type="text" id="email" name="email" value="${user.userEmail}" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">真实姓名:</label>
							<div class="controls">
								<input type="text" id="realname" name="realname" value="${user.realName}" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所属银行:</label>
							<div class="controls">
								<select class="span2" id="bankType" name="bankType">
									<c:forEach items="${bankList}" var="bank">
										<option value="${bank.bankId}" <c:if test="${bank.bankId == user.bankType}">selected</c:if>>${bank.bankDesc}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所属角色:</label>
							<div class="controls">
								<select class="span2" id="roleid" name="roleid">
									<c:forEach items="${roleList}" var="role">
										<option value="${role.roleId}" <c:if test="${user.roleId == role.roleId}">selected</c:if>>${role.roleName}</option>
									</c:forEach>
								</select>
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
$("#submitBtn").live("click", function(){
	$("#editUser").submit();
});
</script>
</body>
	</html>