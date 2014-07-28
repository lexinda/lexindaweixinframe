<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>售后服务</title>
  <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
  <link href="/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="/css/g-system.css" rel="stylesheet" type="text/css" />
   <link href="/css/customer.css" rel="stylesheet" type="text/css" />
   <link href="/lib/artDialog/skins/dialog.css" rel="stylesheet" type="text/css" />
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
    <li class="active"><a href="javascript:void(0);" class="on"> <em>售后服务管理</em></a></li>
  </ul>
  <div class="main-content">
    <div class="pad-10">
      <div class="toolbar explain-col form-horizontal">
        <form action="/sale/repairMachine" method="post">
        <input type="hidden" name="page" value="${page}"/> 
         <input type="hidden" name="type" value="${type}"/>   
          <span class="search-filter">
            <label>小票名称:</label>
            <input class="span2" type="text" name="receiptName" value="${weixinBusiness.receiptName}"/>    
          </span>
          <span class="search-filter">
            <label>是否处理:</label>
            <select class="span2" name="status">
            <c:if test="${type == 3}">
	            <option value="0" <c:if test="${status == 0}">selected</c:if>>未处理</option>
	            <option value="1" <c:if test="${status == 1}">selected</c:if>>审核中</option>
	            <option value="2" <c:if test="${status == 2}">selected</c:if>>审核通过</option>
	            <option value="3" <c:if test="${status == 3}">selected</c:if>>已完成</option>
             </c:if>
              <c:if test="${type != 3}">
              <c:if test="${status==0}">
	                <option value="0">未处理</option>
	                <option value="1">已处理</option>
                </c:if>
                <c:if test="${status==1}">
                	<option value="1">已处理</option>
	                <option value="0">未处理</option>
                </c:if>
              </c:if>
            	
          </select>
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
            <th width="90px">小票名称</th>
            <th width="120px">联系人</th>
             <th width="120px">所属城市</th>
             <th width="120px">性别</th>
            <th width="120px">电话</th>
            <th width="120px">详情</th>
            <th width="150px">创建时间</th>
            <c:if test="${status == 1}">
            	<th width="150px">处理人</th>
           	 	<th width="150px">处理人电话</th>
             </c:if>
            <th width="200px">管理操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${weixinBusinessList}" var="weixinBusiness">
            <tr>
              <td>${weixinBusiness.receiptName}</td>
              <td>${weixinBusiness.businessName}</td>
              <td>${weixinBusiness.province}${weixinBusiness.city}${weixinBusiness.area}${weixinBusiness.street}</td>
              <c:if test="${weixinBusiness.sax==1}">
              	<td>男</td>
              </c:if>
              <c:if test="${weixinBusiness.sax==0}">
              	<td>女</td>
              </c:if>
              <td>${weixinBusiness.telephone}</td>
              <td>${weixinBusiness.address}</td>
              <td><fmt:formatDate value="${weixinBusiness.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
               <c:if test="${weixinBusiness.status == 1}">
            	<td>${weixinBusiness.handlePeople}</td>
           	 	<td>${weixinBusiness.handleTelephone}</td>
             </c:if>
              <td>
                <div class="btn-toolbar">
                  <div class="btn-group">
                  <c:if test="${type == 2}">
                  	<c:if test="${weixinBusiness.status == 0}">
                  		<a class="modify-action" onclick="showInfoDetail('${weixinBusiness.id}','${type}','${status}','${page}','${receiptName}');" href="javascript:void(0);">未处理</a>
                  	</c:if>
                  	<c:if test="${weixinBusiness.status == 1}">
                  	</c:if>
                  </c:if>
                   <c:if test="${type == 3}">
                  	<c:if test="${weixinBusiness.status == 0}">
                  		<select class="span2" name="status" onchange="updateBusinessOther(this.value,'${weixinBusiness.id}','${type}','${status}','${page}','${receiptName}');">
	                 	<option value="0" <c:if test="${status == 0}">selected</c:if>>未处理</option>
	                 	<option value="1" <c:if test="${status == 1}">selected</c:if>>审核中</option>
	                 	<option value="2" <c:if test="${status == 2}">selected</c:if>>审核通过</option>
	                 	<option value="3" <c:if test="${status == 3}">selected</c:if>>已完成</option>
	          		  </select>
                  	</c:if>
                  	<c:if test="${weixinBusiness.status == 1}">
                  	</c:if>
                  </c:if>
                   <c:if test="${type == 1 || type == 4 || type == 5}">
	                  <c:if test="${weixinBusiness.status == 0}">
	                  <select class="span2" name="status" onchange="updateBusiness(this.value,${weixinBusiness.id});">
	                 	<option value="0">=处理人员=</option>
	                 	<c:forEach items="${peopleList}" var="people">
	                 		<option value="${people.id}">${people.handlePeople}</option>
	                 	</c:forEach>
	                	
	          		  </select>
	          		  </c:if>
          		  </c:if>
                  	<%-- <c:if test="${weixinBusiness.status == 0}">
                  	<a class="modify-action" href="/sale/showInfoDetail?id=${weixinBusiness.id}&type=${type}&status=${status}&page=${page}&receiptName=${receiptName}&">未处理</a>
                  	</c:if>
                  	<c:if test="${weixinBusiness.status == 1}">
                  	</c:if> --%>
                    <a class="delete-action" href="javascript:void(0);" data="/sale/deleteWeixinBusiness?id=${weixinBusiness.id}&type=${type}&status=${status}&page=${page}&receiptName=${receiptName}&"> 删除</a>
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
  <form id="repairMachine" action="/sale/repairMachine" method="post">
    <input type="hidden" name="type" value="${type}">
    <input type="hidden" name="status" value="${status}">
    <input type="hidden" name="receiptName" value="${weixinBusiness.receiptName}">
    <input type="hidden" name="page" value="${page}">
  </form>
</div>
<%@include file="../inc/footer.jsp"%>
<script src="/lib/artDialog/dialog.js"></script>
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

function updateBusiness(value,id){
	$.ajax({
		url:"/sale/updateBusinessStatus",
		type:"POST",
		data:{id:id,handlePeopleId:value},
		success:function(rsp){
			alert("成功处理！");
		}			
	});
}

function updateBusinessOther(value,id,type,status,page,receiptName){
	if(value==1){
		window.location.href='/sale/showInfoDetail?id='+id+'&type='+type+'&status='+status+'&page='+page+'&receiptName='+receiptName;
		}else{
			$.ajax({
				url:"/sale/updateBusinessStatus",
				type:"POST",
				data:{id:id,handlePeopleId:value},
				success:function(rsp){
					alert("成功处理！");
				}			
			});
		}
}

function showInfoDetail(id,type,status,page,receiptName){
	window.location.href='/sale/showInfoDetail?id='+id+'&type='+type+'&status='+status+'&page='+page+'&receiptName='+receiptName;
}
</script>
</body>
</html>