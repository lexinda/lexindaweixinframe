<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<h3 class="f14">
	<span class="switchs cu on" title="展开与收缩"></span>权限管理
</h3>
<ul>
	<c:if test="${roleMap.user_manager == 1}">
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=4&" hidefocus="true" style="outline: none;">公司介绍</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=3&" hidefocus="true" style="outline: none;">招贤纳士</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=5&" hidefocus="true" style="outline: none;">联系我们</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=1&" hidefocus="true" style="outline: none;">新闻管理</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=2&" hidefocus="true" style="outline: none;">产品管理</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=6&" hidefocus="true" style="outline: none;">办理须知</a></li>
		<!-- <li class="sub_menu"><a href="/infoList?page=1&siteInfoType=7&" hidefocus="true" style="outline: none;">机具申请</a></li> -->
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=8&" hidefocus="true" style="outline: none;">加盟融商</a></li>
		<li class="sub_menu"><a href="/cmsRstManage/infoList?page=1&siteInfoType=9&" hidefocus="true" style="outline: none;">常见故障</a></li>
	</c:if>
</ul>