<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<h3 class="f14">
	<span class="switchs cu on" title="展开与收缩"></span>权限管理
</h3>
<ul>
	<c:if test="${roleMap.user_manager == 1}">
		<li class="sub_menu"><a href="/cmsRstManage/sale/bankReport?page=1&status=0&roleId=${roleMap.user_manager}&userId=${user.userId}&bankType=0&" hidefocus="true" style="outline: none;">报件管理</a></li>
	</c:if>
	<c:if test="${roleMap.user_manager == 3}">
		<li class="sub_menu"><a href="/cmsRstManage/sale/bankReport?page=1&status=0&roleId=${roleMap.user_manager}&userId=${user.userId}&bankType=0&" hidefocus="true" style="outline: none;">报件管理</a></li>
	</c:if>
	<c:if test="${roleMap.user_manager == 4}">
		<li class="sub_menu"><a href="/cmsRstManage/sale/bankReport?page=1&status=0&roleId=${roleMap.user_manager}&userId=${user.userId}&bankType=0&" hidefocus="true" style="outline: none;">报件管理</a></li>
	</c:if>
</ul>