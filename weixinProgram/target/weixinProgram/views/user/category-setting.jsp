<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>栏目权限设置</title>
  <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
  <link href="/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="/css/g-system.css" rel="stylesheet" type="text/css" />
  <link href="/css/table_form.css" rel="stylesheet" type="text/css" />
  <link href="/css/customer.css" rel="stylesheet" type="text/css" />
  <style type="text/css">
  .nextLevel{padding-left:15px;}
  .bottomBtn{text-align: center;padding-bottom: 5px;}
  .bottomBtn button{margin-left: 30px;}
  </style>
</head>
<body>
  <div class="table-list" id="load_priv">
    <table width="100%" cellspacing="0">
      <thead>
        <tr>
          <th class="text-l cu-span" style="padding-left:50px;width:120px;">
            <span class="all">全选</span>
            /
            <span class="cancel">取消</span>
          </th>
          <th class="text-l cu-span" style="padding-left:40px;">
            栏目名称
          </th>
        </tr>
      </thead>
    </table>
    <form name="myform" action="" method="post">
      <table width="100%" class="table table-condensed table-hover">
        <tbody>
         <!--  <tr>
              <td style="text-align:center;"><input type="checkbox" name="" value=""></td>
              <td>栏目一</td>
          </tr>
          <tr>
              <td style="text-align:center;"><input type="checkbox" name="" value=""></td>
              <td><span class="nextLevel">├─</span> 栏目二</td>
          </tr>
          <tr>
              <td style="text-align:center;"><input type="checkbox" name="" value=""></td>
              <td><span class="nextLevel">├─</span> 栏目三</td>
          </tr> -->
          <c:forEach items="${categoryList}" var="category">
          	<tr>
          	  <td style="text-align:center;"><input type="checkbox" name="" <c:if test="${roleColumnMap[category.catId]}">checked</c:if>    value="${category.catId}"/></td>
              <td>${category.catName}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="bottomBtn">
      	<input type="hidden" id="roleid" value="${roleid}"/>
        <button class="red-btn action-submit" type="button">保存</button>
        <button class="red-btn action-close" type="button"> 关闭</button>
      </div>
    </form>
  </div>
  <%@include file="../inc/footer.jsp"%>
  <script type="text/javascript">
  $(".action-close").live("click", function(){
    window.top.closeDialog('categorySetting');
  });
  $(document).on("click",".action-submit",function(){
	  var categoryIds = [];
	  $("input[type=checkbox]:checked").each(function(){
		  categoryIds.push($(this).val());
	  });
	  if (categoryIds.length == 0){
		  return;
	  }
	  var roleid = $("#roleid").val();
	  $.ajax({
		  url:'/user/role/editRoleColumn',
		  type:'POST',
		  data:{categorys:categoryIds.join(","), roleid:roleid},
	  	  dataType:'json',
	  	  success: function(data){
	  		  if (typeof data.result != 'undefined' && data.result == 'success'){
	  		  	alert("栏目权限设置成功");
	  			window.top.closeDialog('categorySetting');
	  		  }
	  	  }
	  });
  }).on("click",".all",function(){
	  $("input[type=checkbox]").attr("checked","checked");
  }).on("click",".cancel",function(){
	  $("input[type=checkbox]:checked").removeAttr("checked");
  });
  </script>
</body>
</html>