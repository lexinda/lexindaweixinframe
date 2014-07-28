<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>个人信息</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
	<style type="text/css">
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.info{display:block; padding-top: 6px; font-size: 12px;}
.col-tab {width: 60%;}
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"]{ height: 26px; line-height: 30px; }
input[type="text"]{ height:16px; font-size:12px; }
.main-content{padding-top:20px;}
</style>
</head>
<body>
	<div class="subc-title">
		我的账户信息
	</div>
	<div class="main-content">
		<div class="">
			<div class="bk10"></div>
			<div class="col-tab">
				<div id="div_setting_1" class="form-horizontal">
						<dl class="dl-horizontal">
						    <dt class="dl-lbl">用户名称:</dt>
						    <dd>
						      <span class="info">${user.userName}</span>
						    </dd>
						     <c:if test="${user.lastloginIp}">
						    <dt class="dl-lbl">上次登陆IP:</dt>
						    <dd>
						      <span class="info">${user.lastloginIp}</span>
						    </dd>
						    </c:if>
						    <dt class="dl-lbl">上次登陆时间:</dt>
						    <dd>
						      <span class="info"><fmt:formatDate value="${user.lastloginTime}" pattern="yyyy-MM-dd HH:mm"/></span>
						    </dd>
						</div>
				</div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
</body>
	</html>