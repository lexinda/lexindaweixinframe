<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<link rel="stylesheet" href="${urlAdmin}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${urlAdmin}/css/customer.css">
<style>
.pagination {margin:5px 0;text-align:center;}
</style>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
  <li class="active"><a href="infoList" data-toggle="tab">内容详情</a></li>
  <c:if test="${siteInfoType ==3}">
   <li class=""><a href="/addNew?&siteInfoType=${siteInfoType}&">添加内容</a></li>
  </c:if>
   <c:if test="${siteInfoType ==2}">
    <li class=""><a href="/addNew?&siteInfoType=${siteInfoType}&">添加内容</a></li>
  </c:if>
</ul>
<div class="toolbar">
  <div class="explain-col form-horizontal">
  	<form action="/infoList" method="get">
	    <span class="search-filter">
	      <label>内容标题:</label><input type="text" id="tName" name="tName" value="" maxlength="100" />
	    </span>
	    <button class="query-btn" type="submit" id="search">查询</button>
	</form>
  </div>
</div>
<div id="infoList"  class="order-list">
	<table class="table table-condensed table-hover">
	<thead>
		<tr>
			<th>内容标题</th>
			<th>内容简介</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="site" items="${siteInfoList}">
		<tr>
			<td>${site.siteInfoName}</td>
			<td>${site.siteInfoSax}</td>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${site.createTime}" type="both"/>
			</td>
			<td>
				<div class="btn-group">
					<a class="modify-action" href="/showInfoDetail?tId=${site.id}">编辑</a>
					<a class="delete-action" href="javascript:void(0);" data="/deleteInfo?tId=${site.id}&siteInfoType=${siteInfoType}">删除</a>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	<c:if test="${pageNum > 1}">
	<div class="pagination pagination-small">
		<ul class="page-operation">
			<li><a href="javascript:void(0);" data="${prePage}">Prev</a></li>
			<c:forEach begin="1" end="${pageNum}" varStatus="index">
	          <li <c:if test="${currentPage == index.index}">class="active"</c:if>><a href="javascript:void(0);" data="${index.index}">${index.index}</a></li>
	        </c:forEach>
			<li><a href="javascript:void(0);" data="${nextPage}">Next</a></li>
		</ul>
	</div>
	</c:if>
</div>
<div style="display:none">
	<form id="updateContent" action="/infoList" method="get">
		<input type="hidden" name="page" value="${currentPage}">
	</form>
</div>
<%@include file="../inc/footer.jsp" %>
<script src="${adminUrl}/js/libs/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on("click",".page-operation li a",function(e){
	e.preventDefault();

	var pageVal = $(this).attr("data");
	$("#updateContent input[name='page']").val(pageVal);
	$("#updateContent").submit();

});
$(".delete-action").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该记录？", 2, "删除记录", function(){window.location.href = href;});
});
</script>
</body>
</html>
