<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>角色管理</title>
  <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
  <link href="/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="/css/g-system.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" href="/css/customer.css"/>
  <style type="text/css">

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
input[type="text"] { height: 16px; }

.search-filter { margin: 10px 20px 10px 20px; }

.search-filter label { display: inline; font-size: 12px; margin-right: 10px; }
select, input[type="file"] { height: 28px; line-height: 30px; font-size: 12px; }
.emptyBtn{display:inline-block;width:64px;height:26px;}
.news_title{width:260px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;display: block;}
.pagination{text-align:center;margin-top:0;}
.pagination a{color:#08c;}
</style>
</head>
<body>
  <ul class="nav nav-tabs">
    <li class="active"><a href="javascript:void(0);" class="on"> <em>角色管理</em></a></li>
    <li><a href="/user/role/add"> <em>添加角色</em></a></li>
  </ul>
  <div class="main-content">
    <div class="pad-10">
      <div class="bk10"></div>
      <table class="table table-hover">
        <thead>
          <tr>
            <th width="90px">序号</th>
            <th width="120px">角色名称</th>
            <th width="200px">角色描述</th>
            <th width="100px">状态</th>
            <th width="260px">管理操作</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${roleList}" var="role">
        		<tr>
	        		<td>${role.roleid}</td>
	              	<td>${role.rolename}</td>
	              	<td>${role.description}</td>
	              	<td>
              			<c:choose>
              				<c:when test="${role.disabled==0}"><span class="label label-success">启用</span></c:when>
              				<c:otherwise><span class="label" href="javascript:;">未启用</span></c:otherwise>
              			</c:choose>      
	              	</td>
	              	<td>
	              		<input type="hidden" name="roleid" value="${role.roleid}"/>
	              		<input type="hidden" name="rolename" value="${role.rolename}"/>
						<a href="javascript:;" class="action-access ps-action">权限设置</a><a href="#" class="action-category news-action">新闻栏目权限</a><a href="javascript:;" class="user_manager setting-action" data="${role.roleid}">成员管理</a> 	<a class="modify-action" href="/user/role/edit/${role.roleid}">修改</a>  <a class="delete-action" href="javascript:void(0);" data="/user/role/remove/${role.roleid}">删除</a>
	             	</td>
             	</tr>
        	</c:forEach>
        </tbody>
     </table>
    </div>
  </div>
 <div class="modal hide fade" id="accessSetting">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <input type="hidden" id="roleid"/>
    <h3>权限设置 - 角色:<span id="title"></span></h3>
  </div>
  <div class="modal-body">
    <table class="table table-hover">
        <thead>
          <tr>
            <th width="90px">全选 / 取消</th>
          </tr>
        </thead>
        <tbody>
            <tr>
              <td>设置</td>
            </tr>
        </tbody>
     </table>
  </div>
  <div class="modal-footer">
  	<a href="#" class="btn btn-primary">保存</a>
    <a href="#" class="btn">关闭</a>
  </div>
</div>

<div style="display:none">
<form action="/user/searchlist" method="post" id="search">
	<input name="roleid" type="hidden"/>
</form>
</div>

<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
$(".action-access").live("click", function(){
	var parent = $(this).parents("td");
	var roleid = $(parent).find("input[name=roleid]").val();
	var rolename = $(parent).find("input[name=rolename]").val();
	$("#title").text(rolename);
	window.top.art.dialog({title: rolename +' - 权限设置',id:'accessSetting',iframe:'/user/access/setting?roleid='+roleid,width:'700',height:'500'});
});
$(".action-category").live("click",function(){
	var parent = $(this).parents("td");
	var roleid = $(parent).find("input[name=roleid]").val();
	var rolename = $(parent).find("input[name=rolename]").val();
	$("#title").text(rolename);
	window.top.art.dialog({title: rolename + '- 栏目访问权限设置',id:'categorySetting',iframe:'/user/category/setting?roleid='+roleid,width:'700',height:'500'});
});

$(document).on("click",".user_manager",function(){
	$("#search").find("input").val($(this).attr("data"));
	$("#search").submit();
});
$(".delete-action").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该角色？", 2, "删除角色", function(){window.location.href = href;});
});
</script>
</body>
</html>