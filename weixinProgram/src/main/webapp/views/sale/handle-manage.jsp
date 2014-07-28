<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>售后处理人员管理</title>
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
    <li class="active"><a href="javascript:void(0);" class="on"> <em>售后处理人员管理</em></a></li>
    <li><a id="addHandlePeople" href="/sale/add"> <em>添加售后处理人员</em></a></li>
  </ul>
  <div class="main-content">
    <div class="pad-10">
      <div class="toolbar explain-col form-horizontal">
        <form action="/sale/queryHandlePeople" method="post">
        <input type="hidden" name="page" value="${page}"/> 
          <span class="search-filter">
            <label>处理人员姓名:</label>
            <input class="span2" type="text" name="handlePeople" value="${handlePeople}"/>    
          </span>
         <span class="search-filter">
            <label>处理人员电话:</label>
            <input class="span2" type="text" name="handlePhone" value="${handlePhone}"/>    
          </span>
        <button class="query-btn" type="submit" id="search"> 
          查询
        </button>
      </form>
    </div>
    <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="90px">名称</th>
            <th width="120px">电话</th>
            <th width="150px">创建时间</th>
            <th width="200px">管理操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${handlePeopleList}" var="handlePeople">
            <tr>
              <td>${handlePeople.handlePeople}</td>
              <td>${handlePeople.handlePhone}</td>
              <td><fmt:formatDate value="${handlePeople.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td>
                <div class="btn-toolbar">
                  <div class="btn-group">
                    <a class="delete-action" href="javascript:void(0);" data="/sale/delete?id=${handlePeople.id}&page=${page}&"> 删除</a>
                  </div>
                </div>
             </td>
            </tr>
          </c:forEach>
        </tbody>
     </table>
    </div>
    <c:if test="${totalPage > 1}">
      <div class="pagination">
        <ul>
          <li <c:if test="${page <= 1}">class="disabled"</c:if>><a href="javascript:void(0);" data="${page-1}">Prev</a></li>
          <c:forEach begin="1" end="${totalPage}" varStatus="index">
          <li <c:if test="${page == index.index}">class="active"</c:if>><a href="javascript:void(0);" data="${index.index}">${index.index}</a></li>
          </c:forEach>
          <li <c:if test="${page >=  totalPage}">class="disabled"</c:if>><a href="javascript:void(0);" data="${page+1}">Next</a></li>
        </ul>
      </div>
    </c:if>
  </div>
  <div style="display:nonoe">
  <form id="repairMachine" action="/sale/queryHandlePeople" method="post">
    <input type="hidden" name="handlePhone" value="${handlePhone}">
    <input type="hidden" name="handlePeople" value="${handlePeople}">
    <input type="hidden" name="page" value="${page}">
  </form>
</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">

$(".pagination li a").live("click", function(e){
  if($(this).parent().hasClass("active") || $(this).parent().hasClass("disabled")){
    return ;
  }
  var page = parseInt($(this).attr("data"));
  $("#repairMachine input[name='page']").val(page);
  $("#repairMachine").submit();
});

$(".delete-action").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该记录？", 2, "删除记录", function(){window.location.href = href;});
});

</script>
</body>
</html>