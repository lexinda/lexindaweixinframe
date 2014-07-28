<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>乐信达运营后台管理系统</title>
<link rel="stylesheet" href="/css/login.css"/>
</head>
<body class="site com_login view-login layout-default task- itemid- ">
<c:if test="${!empty topWindowLogin}">
<script type="text/javascript">
window.top.location.href='/login';
</script>
</c:if>
<!-- Container -->
<div class="container">
  <div id="content" class="container-inner">
    <!-- Begin Content -->
    <div id="element-box" class="login well"> 
      <div class="login-title"><img src="../images/admin/login_t.png" alt="meiliwan.com" /></div>
      <div id="system-message-container"> </div>
      <div class="login-box">
	      <form id="loginForm" action="/login" method="post" name="myform">
	        <div class="control-group">
	          <div class="controls">
	            <div class="input-prepend input-append"> 
	              <input name="username" id="username" tabindex="1" type="text" class="input-medium uname-ipt" placeholder="User Name" size="15"/>
	            </div>
	          </div>
	        </div>
	        <div class="control-group">
	          <div class="controls">
	            <div class="input-prepend input-append"> 
	              <input name="password" type="password" id="password" tabindex="2"class="input-medium upwd-ipt" placeholder="Password" size="15"/>
				 </div>
	          </div>
	        </div>
	        <div class="control-group">
	          <div class="controls">
	            <div class="btn-group pull-left">
	              <button id="login-submit" tabindex="3" class="login-btn"> <i class="icon-lock icon-white"></i> Log in</button>
	            </div>
	          </div>
	        </div>
	      </form>
      </div>
    </div>
    <noscript>
    Warning! JavaScript must be enabled for proper operation of the Administrator backend.
    </noscript>
    <!-- End Content -->
    <div class="navbar navbar-fixed-bottom hidden-phone">
	  <div >Copyright&copy;2014-2017，版权所有 www.lexindasoft.com</div>
	  <!-- <a href="${urlM}" class="pull-left" target="_blank"><i class="icon-share icon-white"></i>乐信达首页</a> -->
	</div>
  </div>
</div>
<%@include file="views/inc/footer.jsp" %>
<script>
$(document).on("click","#login-submit",function(){
	$("#loginForm").submit();
}).on("focus",".input-medium",function(){
	$(this).addClass("focus")
}).on("blur",".input-medium",function(){
	$(this).removeClass("focus")
});
</script>
</body>
</html>
