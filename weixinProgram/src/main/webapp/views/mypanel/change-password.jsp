<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>密码修改</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
	<link rel="stylesheet" type="text/css" href="/js/libs/validate/tipTip.css"/>
	<style type="text/css">
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.info{display:block; padding-top: 4px; font-size: 14px;}
.col-tab {width: 60%;}
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"]{ height: 26px; line-height: 30px; }
input[type="text"]{ height:16px; font-size:12px; }
.main-content{padding-top:20px;}
</style>
</head>
<body>
	<div class="subc-title">
		修改密码
	</div>
	<div class="main-content">
		<div class="">
			<c:if test="${!empty errorMSG}"><div class="explain-col">温馨提示: ${errorMSG}</div></c:if>
			<div class="bk10"></div>
			<div class="col-tab">
				<div id="div_setting_1" class=" pad-10">
					<form id="changePassword" class="form-horizontal" action="/cmsRstManage/mypanel/changePassword" method="post">
						<dl class="dl-horizontal">
							<dt class="dl-lbl">当前密码:</dt>
						    <dd >
						      <input type="password" id="old-password" name="old-password" data-rel="notnull,password" maxlength="30"/>
						    </dd>
						    <dt class="dl-lbl">新密码:</dt>
						    <dd >
						      <input type="password" name="password" data-rel="notnull,password" maxlength="30"/>
						    </dd>
						    <dt class="dl-lbl">确认密码:</dt>
						    <dd >
						      <input type="password" name="re-password" data-rel="notnull,password" maxlength="30"/>
						    </dd>
						</dl>
					</form>
				</div>
				<div class="saveInfo"><button id="saveBtn" class="red-btn" data-action="submit" type="button">保存</button></div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script src="/js/libs/validate/jquery.tipTip.minified.js"></script>
<script src="/js/libs/validate/validate.js"></script>
<script type="text/javascript">
$("#saveBtn").live("click", function(){
	$("#changePassword").submit();
});
</script>
</body>
	</html>