<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加管理员</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
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
		<a href="/user/admin/manage"> <em>管理员管理</em>
		</a></li>
		<li class="active">
		<a href="javascript:void(0);" class="on"> <em>添加管理员</em>
		</a></li>
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
					<form id="addAdminUser" class="form-horizontal" action="/user/admin/add" method="post">
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>用户名:</label>
							<div class="controls">
								<input type="text" id="username" name="username" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>密码:</label>
							<div class="controls">
								<input type="password" id="password" name="password" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>确认密码:</label>
							<div class="controls">
								<input type="password" id="repassword" name="repassword" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">E-mail:</label>
							<div class="controls">
								<input type="text" id="email" name="email" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">真实姓名:</label>
							<div class="controls">
								<input type="text" id="realname" name="realname" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所属角色:</label>
							<div class="controls">
								<select class="span2" disabled>
									<option value="1" selected>超级管理员</option>
								</select>
								<input type="hidden" name="roleid" value="1">
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
	if($("#catNameCheck").val() <= 0) return;
	if($("#catPathCheck").val() <= 0) return;
	if($("#submitFlag").val() <= 0) return;
	$("#addCategoryForm").submit();
}
$("#submitBtn").live("click", function(){
	$("#addAdminUser").submit();
	//$("#submitFlag").val(1);
	//CheckAndSubmit();
});
$("#catName").live("blur", function(){
	$("#catNameCheck").val(0);
	var catName = $(this).val();
	var cid = 0;
	$.ajax({
		url:"/content/category/checkName",
		type:"POST",
		data:{catName:catName,cid:cid},
		success:function(rsp){
			if(rsp.status > 0){
				$("#catNameTip").attr("class","onError");
				$("#catNameTip").html("该栏目名称已存在！");
			}
			else if(rsp.status == -1){
				$("#catNameTip").attr("class","onError");
				$("#catNameTip").html("栏目名称不能为空！");
			}
			else{
				$("#catNameTip").attr("class","onCorrect");
				$("#catNameTip").html("该栏目名称可用。");
				$("#catNameCheck").val(1);
				CheckAndSubmit();
			}
			
		}			
	})
});
$("#catPath").live("blur", function(){
	$("#catPathCheck").val(0);
	var catPathName = $(this).val();
	var cid = 0;
	$.ajax({
		url:"/content/category/checkPathName",
		type:"POST",
		data:{catPathName:catPathName,cid:cid},
		success:function(rsp){
			if(rsp.status > 0){
				$("#catPathTip").attr("class","onError");
				$("#catPathTip").html("该英文目录已存在！");
			}
			else if(rsp.status == -1){
				$("#catPathTip").attr("class","onError");
				$("#catPathTip").html("英文目录不能为空！");
			}
			else{
				$("#catPathTip").attr("class","onCorrect");
				$("#catPathTip").html("该英文目录可用。");
				$("#catPathCheck").val(1);
				CheckAndSubmit();
			}
		}			
	})
});
</script>
</body>
	</html>