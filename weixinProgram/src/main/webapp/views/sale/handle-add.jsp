<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加售后处理人员</title>
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
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>
				<c:if test="${errorMSG ==1}">
					<span style="color:red;">温馨提示：必须输入姓名和电话号码。</span>
				</c:if>
				<c:if test="${errorMSG ==2}">
					<span style="color:red;">温馨提示：名称已存在。</span>
				</c:if>
				<c:if test="${errorMSG ==3}">
					<span style="color:green;">温馨提示：添加成功。</span>
				</c:if>
				<c:if test="${errorMSG ==4}">
					<span style="color:red;">温馨提示：添加失败。</span>
				</c:if>
</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="addAdminUser" class="form-horizontal" action="/cmsRstManage/sale/add" method="post">
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>名称:</label>
							<div class="controls">
								<input type="text" id="handlePeople" name="handlePeople" value="" maxlength="20" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>电话:</label>
							<div class="controls">
								<input type="text" id="handlePhone" name="handlePhone" value="" maxlength="20" />
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
	if($("#handlePeople").val() <= 0) return;
	if($("#handlePhone").val() <= 0) return;
	$("#addAdminUser").submit();
}
$("#submitBtn").live("click", function(){
	//$("#submitFlag").val(1);
	CheckAndSubmit();
});
</script>
</body>
	</html>