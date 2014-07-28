<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<h3 class="f14">
	<span class="switchs cu on" title="展开与收缩"></span>权限管理
</h3>
<ul>
	<c:if test="${roleMap.user_manager == 1}">
		<li class="sub_menu"><a href="/user/manage?&page=1&" hidefocus="true" style="outline: none;">用户管理</a></li>
		<li class="sub_menu"><a href="/sale/queryHandlePeople?&page=1&" hidefocus="true" style="outline: none;">售后处理人员管理</a></li>
	</c:if>
	<%--<li class="sub_menu"><a href="/user/manage?&page=1&" hidefocus="true" style="outline: none;">用户管理</a></li>
	 <c:if test="${roleMap.user_admin == 1}">
		<li class="sub_menu"><a href="/user/admin/manage" hidefocus="true" style="outline: none;">管理员管理</a></li>
	</c:if>
	<li class="sub_menu"><a href="/user/admin/manage" hidefocus="true" style="outline: none;">管理员管理</a></li> --%>
</ul>