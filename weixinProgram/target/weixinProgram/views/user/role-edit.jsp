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
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
	<style type="text/css">
body{ padding-left: 10px; }

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
			<a href="/user/adminrole/manage">角色管理</a>
		</li>
		<li class="active">
			<a href="javascript:void(0);" class="on">编辑角色</a>
		</li>
	</ul>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">角色信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="editRole" class="form-horizontal" action="/user/role/edit" method="post">
						<input type="hidden" name="roleid" value="${role.roleid}"/>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>角色名称:</label>
							<div class="controls">
								<input type="text" id="rolename" name="rolename" value="${role.rolename}" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>角色描述:</label>
							<div class="controls">
								<input type="text" id="roleDesc" name="roleDesc" value="${role.description}" maxlength="150" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>是否启用:</label>
							<div class="controls">
								<label class="radio inline">
								<input type="radio" name="isEnable" value="0" <c:if test="${role.disabled==0}">checked</c:if>/>是
								</label>
								<label class="radio inline">
								<input type="radio" name="isEnable" value="1" <c:if test="${role.disabled==1}">checked</c:if>/>否
								</label>
							</div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="red-btn" type="button"><i class="icon-check icon-white"></i> 保存</button></div>
				<input type="hidden" id="submitFlag" value="0" />
			</div>
		</div>
	</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
$(document).on("click","#submitBtn",function(){
	$("#editRole").submit();
});
</script>
</body>
</html>