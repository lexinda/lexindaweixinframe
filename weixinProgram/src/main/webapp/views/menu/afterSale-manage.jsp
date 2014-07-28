<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<h3 class="f14">
	<span class="switchs cu on" title="展开与收缩"></span>权限管理
</h3>
<ul>
	<c:if test="${roleMap.user_manager == 1 || roleMap.user_manager == 2}">
		<li class="sub_menu"><a href="/sale/repairMachine?page=1&type=1&status=0&" hidefocus="true" style="outline: none;">机具报修</a></li>
		<li class="sub_menu"><a href="/sale/repairMachine?page=1&type=2&status=0&" hidefocus="true" style="outline: none;">纸张申请</a></li>
		<li class="sub_menu"><a href="/sale/repairMachine?page=1&type=3&status=0&" hidefocus="true" style="outline: none;">我要增机</a></li>
		<li class="sub_menu"><a href="/sale/repairMachine?page=1&type=4&status=0&" hidefocus="true" style="outline: none;">即时到帐</a></li>
		<li class="sub_menu"><a href="/sale/repairMachine?page=1&type=5&status=0&" hidefocus="true" style="outline: none;">机具申请</a></li>
	</c:if>
</ul>