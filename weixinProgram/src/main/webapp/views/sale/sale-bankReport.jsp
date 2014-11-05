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
    <li class="active"><a href="javascript:void(0);" class="on"> <em>报件管理</em></a></li>
  </ul>
  <div class="main-content">
    <div class="pad-10">
      <div class="toolbar explain-col form-horizontal">
        <form action="/cmsRstManage/sale/bankReport" method="post">
        <input type="hidden" name="page" value="${page}"/> 
         	<input class="span2" type="hidden" name="roleId" id="roleId" value="${roleId}"/> 
         	<input class="span2" type="hidden" name="userId" id="userId" value="${userId}"/> 
         	<c:if test="${roleId ==1 ||roleId ==2 }">
         	<span class="search-filter" style="float: left;">
            <label>所属分行:</label>
            <select class="span2" name="bankType" id=bankType>
	                <c:forEach items="${bankList}" var="bank">
                		<option value="${bank.bankId}" <c:if test="${bank.bankId == bankType}">selected</c:if>>${bank.bankDesc}</option>
              		</c:forEach>
          	</select>
        	</span>
        	</c:if>
        	<c:if test="${roleId ==1 ||roleId ==2 || roleId ==3 }">
          <span class="search-filter" style="float: left;">
            <label>客户经理姓名:</label>
            <input class="span2" type="text" name="bankUser" id="bankUser" value="${bankUser}"/>    
          </span>
          </c:if>
          <span class="search-filter" style="float: left;">
            <label>商户名称:</label>
            <input class="span2" type="text" name="merchantName" id="merchantName" value="${merchantName}"/>    
          </span>
          <span class="search-filter" style="float: left;">
            <label>报件状态:</label>
            <select class="span2" name="status" id=status>
	                <c:forEach items="${statusList}" var="status">
                		<option value="${status.statusId}" <c:if test="${status.statusId == statusId}">selected</c:if>>${status.statusDesc}</option>
              		</c:forEach>
          </select>
        </span>
        
        <button class="query-btn" type="submit" id="search" style="float: left;margin-top: 10px;">
        </button>
        <div class="save-btn" style="width:80px;color: white;text-align: center;float: left;margin-top: 10px;margin-left: 20px;">
         	<a onclick="downloadExcel(${statusId},${page},${roleId},${bankType},${userId});" href="#" style="text-align: center;"><font color="white">下载Excel</font></a>
         </div>
      </form>
    </div>
    <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="90px">客户经理</th>
            <th width="120px">所属支行</th>
            <th width="120px">商户名称</th>
            <th width="120px">商户地址</th>
            <th width="120px">联系人</th>
            <th width="120px">联系电话</th>
            <th width="120px">性别</th>
            <th width="120px">机具类型</th>
            <th width="120px">手续费率</th>
            <th width="120px">机具押金</th>
            <th width="150px">创建时间</th>
            <c:if test="${roleId == 3 || roleId == 4}">
             <th width="120px">报件状态</th>
            </c:if>
           <c:if test="${roleId == 1 || roleId == 2}">
            <th width="200px">管理操作</th>
            </c:if>
            <th width="150px">备注</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${reportInfoList}" var="report">
            <tr>
              <td>${report.bankUser}</td>
              <td>${report.bankType}</td>
              <td>${report.merchantName}</td>
              <td>${report.province}${report.city}${report.area}${report.street}</td>
              <td>${report.userName}</td>
              <td>${report.phone}</td>
              <c:if test="${report.gender == 1}">
              <td>男</td>
              </c:if>
              <c:if test="${report.gender == 0}">
              <td>女</td>
              </c:if>
              <c:if test="${report.machineName==1}">
              <td>固定</td>
              </c:if>
               <c:if test="${report.machineName==2}">
              <td>移动</td>
              </c:if>
               <c:if test="${report.machineName==3}">
              <td>半移动</td>
              </c:if>
               <c:if test="${report.machineName==4}">
              <td>分体</td>
              </c:if>              
              <td>${report.rate}</td>
              <td>${report.deposit}</td>
              <td><fmt:formatDate value="${report.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td>
                <div class="btn-toolbar">
                  <div class="btn-group">
                  <c:if test="${roleId == 3 || roleId == 4}">
                  	<select class="span2" name="status" disabled="disabled">
                 	 <c:forEach items="${statusList}" var="status">
                		<option value="${status.statusId}" <c:if test="${status.statusId == report.status}">selected</c:if>>${status.statusDesc}</option>
              		</c:forEach>
          		  </select>
                  </c:if>
                  <c:if test="${roleId == 1 || roleId == 2}">
                  	<c:if test="${statusId ==0 }">
	                  <select class="span2" name="status" onchange="updateBusiness(this.value,'${report.openid}');">
	                 	 <c:forEach items="${statusList}" var="status">
	                		<option value="${status.statusId}" <c:if test="${status.statusId == report.status}">selected</c:if>>${status.statusDesc}</option>
	              		</c:forEach>
	          		  </select>
	          		 </c:if>
	          		 <c:if test="${statusId !=0 }">
	                  <select class="span2" name="status" onchange="updateBusiness(this.value,'${report.openid}');">
	                 	 <c:forEach items="${statusList}" var="status">
	                		<option value="${status.statusId}" <c:if test="${status.statusId == statusId}">selected</c:if>>${status.statusDesc}</option>
	              		</c:forEach>
	          		  </select>
	          		 </c:if>
          		  </c:if>
                  	<%-- <c:if test="${weixinBusiness.status == 0}">
                  	<a class="modify-action" href="/sale/showInfoDetail?id=${weixinBusiness.id}&type=${type}&status=${status}&page=${page}&receiptName=${receiptName}&">未处理</a>
                  	</c:if>
                  	<c:if test="${weixinBusiness.status == 1}">
                  	</c:if> --%>
                  </div>
                </div>
             </td>
              <td>${report.content}</td>
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
  <form id="repairMachine" action="/cmsRstManage/sale/repairMachine" method="post">
    <input type="hidden" name="type" value="${type}">
    <input type="hidden" name="status" value="${status}">
    <input type="hidden" name="receiptName" value="${weixinBusiness.receiptName}">
    <input type="hidden" name="page" value="${page}">
  </form>
</div>
<%@include file="../inc/footer.jsp"%>
<script src="/lib/artDialog/dialog.js"></script>
<script type="text/javascript">
	function downloadExcel(statusId,page,roleId,bankType,userId){
		var bankUser = $("#bankUser").val();
		var merchantName = $("#merchantName").val();
		var bankType = $("#bankType").val();
		var status = $("#status").val();
		if(roleId == 1){
			window.location.href='/site/download?status='+status+'&page='+page+'&roleId='+roleId+'&bankType='+bankType+'&userId='+userId+'&bankUser='+bankUser+'&merchantName='+merchantName+'&';
		}else if(roleId == 3){
			window.location.href='/site/download?status='+status+'&page='+page+'&roleId='+roleId+'&bankType=&userId='+userId+'&bankUser='+bankUser+'&merchantName='+merchantName+'&';
		}if(roleId == 4){
			window.location.href='/site/download?status='+status+'&page='+page+'&roleId='+roleId+'&bankType=&userId='+userId+'&bankUser=&merchantName='+merchantName+'&';
		}
		
	}

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
	if(value==6||value==9){
	window.top.art.dialog({title:'状态详情',id:'accessSetting',iframe:'/cmsRstManage/sale/reportContent?openId='+id+'&status='+value,width:'700',height:'500'});
	}else{
		$.ajax({
				url:"/sale/reportOtherStatus",
				type:"POST",
				data:{status:value,openId:id},
				success:function(rsp){
					alert("成功处理！");
				}			
			});
	}
}

$(".action-access").live("click", function(){
	var parent = $(this).parents("td");
	var roleid = $(parent).find("input[name=roleid]").val();
	var rolename = $(parent).find("input[name=rolename]").val();
	$("#title").text(rolename);
	
});
</script>
</body>
</html>