<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>管理员管理</title>
  <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
  <link href="/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="/css/g-system.css" rel="stylesheet" type="text/css" />
   <link href="/css/customer.css" rel="stylesheet" type="text/css" />
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
    <li class="active"><a href="javascript:void(0);" class="on"> <em>管理员管理</em></a></li>
    <li><a id="addSuperAdmin" href="/user/admin/add"> <em>添加管理员</em></a></li>
  </ul>
  <div class="main-content">
    <div class="pad-10">
      <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="90px">用户ID</th>
            <th width="120px">用户名</th>
            <th width="120px">所属角色</th>
            <th width="150px">最后登录IP</th>
            <th width="150px">最后登录时间</th>
            <th width="150px">E-mail</th>
            <th width="120px">真实姓名</th>
            <th width="200px">管理操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${adminList}" var="user">
            <tr>
              <td>${user.userId}</td>
              <td>${user.userName}</td>
              <td>${user.roleName}</td>
              <td>${user.lastloginIp}</td>
              <td><fmt:formatDate value="${user.lastloginTime}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td>${user.userEmail}</td>
              <td>${user.realName}</td>
              <td>
                <div class="btn-toolbar">
                  <div class="btn-group">
                    <a class="modify-action" href="/user/admin/edit/${user.userId}">修改</a>
                    <a class="delete-action" href="javascript:void(0);" data="/user/admin/delete/${user.userId}">删除</a>
                  </div>
                </div>
             </td>
            </tr>
          </c:forEach>
        </tbody>
     </table>
    </div>
  </div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">

$(".pagination li a").live("click", function(e){
  if($(this).parent().hasClass("active") || $(this).parent().hasClass("disabled")){
    return ;
  }
  var page = parseInt($(this).attr("data"));
  $("#approveList input[name='page']").val(page);
  $("#approveList").submit();
});
$(".delete-action").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该管理员？", 2, "删除管理员", function(){window.location.href = href;});
});
</script>
</body>
</html>