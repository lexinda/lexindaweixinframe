<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>权限管理</title>
  <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
  <link href="/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="/css/g-system.css" rel="stylesheet" type="text/css" />
  <link href="/css/table_form.css" rel="stylesheet" type="text/css" />
  <link href="/css/customer.css" rel="stylesheet" type="text/css" />
  <style type="text/css">
  .nextLevel{padding-left:15px;}
  .table-list td{padding-left: 50px;}
  .bottomBtn{text-align: center;padding-bottom: 5px;}
  .bottomBtn button{margin-left: 30px;}
  </style>
</head>
<body>
  <div class="table-list" id="load_priv">
    <table width="100%" cellspacing="0">
      <thead>
        <tr>
          <th class="text-l cu-span" style="padding-left:50px;">
            <span class="all">全选</span>
            /
            <span class="cancel">取消</span>
          </th>
        </tr>
      </thead>
    </table>
    <form name="myform" action="" method="post">
      <table width="100%" class="table table-condensed table-hover">
        <tbody>
          <tr><td><input type="checkbox" name="" value="" class="module_setting" <c:if test="${moduleMap.module == 1}">checked</c:if>/> 模块设置</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="module_child menu" <c:if test="${moduleMap.module_menu == 1}">checked</c:if>/> 管理菜单</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="module_child template" <c:if test="${moduleMap.module_template == 1}">checked</c:if>/> 东盟模板</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="module_child template_th" <c:if test="${moduleMap.module_template_th == 1}">checked</c:if>/> 泰国模板</td></tr>
          <tr><td><span class="nextLevel">└─</span> <input type="checkbox" name="" value="" class="module_child template_vn" <c:if test="${moduleMap.module_template_vn == 1}">checked</c:if>/> 越南模板</td></tr>
          <tr><td><input type="checkbox" name="" value="" class="content_manager" <c:if test="${moduleMap.module == 1}">checked</c:if>/> 内容管理</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="content_child news" <c:if test="${moduleMap.content_news == 1}">checked</c:if>/> 新闻管理</td></tr>
          <tr><td><span class="nextLevel">└─</span> <input type="checkbox" name="" value="" class="content_child column" <c:if test="${moduleMap.content_column == 1}">checked</c:if>/> 管理栏目</td></tr>
          <tr><td><input type="checkbox" name="" value="" class="publish_manager" <c:if test="${moduleMap.publish == 1}">checked</c:if>/> 发布管理</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="publish_child check" <c:if test="${moduleMap.publish_check == 1}">checked</c:if>/> 审核管理</td></tr>
          <tr><td><span class="nextLevel">└─</span> <input type="checkbox" name="" value="" class="publish_child manager" <c:if test="${moduleMap.publish_manager == 1}">checked</c:if>/> 发布管理</td></tr>
          <tr><td><input type="checkbox" name="" value="" class="user_manager" <c:if test="${moduleMap.user == 1}">checked</c:if>/> 用户管理</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="user_child user" <c:if test="${moduleMap.user_manager == 1}">checked</c:if>/> 用户管理</td></tr>
          <tr><td><span class="nextLevel">├─</span> <input type="checkbox" name="" value="" class="user_child role" <c:if test="${moduleMap.user_role == 1}">checked</c:if>/> 角色管理</td></tr>
          <tr><td><span class="nextLevel">└─</span> <input type="checkbox" name="" value="" class="user_child admin" <c:if test="${moduleMap.user_admin == 1}">checked</c:if>/> 管理员管理</td></tr>
        </tbody>
      </table>
      <div class="bottomBtn">
      	<input type="hidden" id="roleid" value="${roleid}"/>
        <button class="red-btn action-submit" type="button" id="saveBtn">保存</button>
        <button class="red-btn action-close" type="button" id="closeBtn">关闭</button>
      </div>
    </form>
  </div>
  <%@include file="../inc/footer.jsp"%>
  <script type="text/javascript">
  $(".action-close").live("click", function(){
    window.top.closeDialog('accessSetting');
  });
  
  $(document).on("click","#saveBtn",function(){
	  var module = $(".module_setting").attr("checked") == 'checked' ? 1 : 0;
	  var content = $(".content_manager").attr("checked") == 'checked' ? 1 : 0;
	  var publish = $(".publish_manager").attr("checked") == 'checked' ? 1 : 0;
	  var user = $(".user_manager").attr("checked") == 'checked' ? 1 : 0;
	  var module_menu = $(".module_child.menu").attr("checked") == 'checked' ? 1 : 0;
	  var module_template = $(".module_child.template").attr("checked") == 'checked' ? 1 : 0;
	  var module_template_th = $(".module_child.template_th").attr("checked") == 'checked' ? 1 : 0;
	  var module_template_vn = $(".module_child.template_vn").attr("checked") == 'checked' ? 1 : 0;
	  var content_news = $(".content_child.news").attr("checked") == 'checked' ? 1 : 0;
	  var content_column = $(".content_child.column").attr("checked") == 'checked' ? 1 : 0;
	  var publish_check = $(".publish_child.check").attr("checked") == 'checked' ? 1 : 0;
	  var publish_manager = $(".publish_child.manager").attr("checked") == 'checked' ? 1 : 0;
	  var user_manager = $(".user_child.user").attr("checked") == 'checked' ? 1 : 0;
	  var user_role = $(".user_child.role").attr("checked") == 'checked' ? 1 : 0;
	  var user_admin = $(".user_child.admin").attr("checked") == 'checked' ? 1 : 0;
	  
	  var str = "";
	  str += '{"module":"'+module+'",';
	  str += '"content":"'+content+'",';
	  str += '"publish":"'+publish+'",';
	  str += '"user":"'+user+'",';
	  str += '"module_menu":"'+module_menu+'",';
	  str += '"module_template":"'+module_template+'",';
	  str += '"module_template_th":"'+module_template_th+'",';
	  str += '"module_template_vn":"'+module_template_vn+'",';
	  str += '"content_news":"'+content_news+'",';
	  str += '"content_column":"'+content_column+'",';
	  str += '"publish_check":"'+publish_check+'",';
	  str += '"publish_manager":"'+publish_manager+'",';
	  str += '"user_manager":"'+user_manager+'",';
	  str += '"user_role":"'+user_role+'",';
	  str += '"user_admin":"'+user_admin+'"}';
	 	
	  $.ajax({
		  url:'/user/role/editRoleModule',
		  type:'POST',
		  data:{roleid:$("#roleid").val(), modules: str},
	  	  dataType:'json',
	  	  success: function(data){
	  		  if (typeof data.result != 'undefined' && data.result == 'success'){
	  		  	alert("权限设置成功");
	  			window.top.closeDialog('accessSetting');
	  		  }
	  	  }
	  });
	  
  }).on("click",".all",function(){
	  $("input[type=checkbox]").attr("checked","checked")
  }).on("click",".cancel",function(){
	  $("input[type=checkbox]:checked").removeAttr("checked")
  }).on("click",".module_setting",function(){
	  var flag = $(this).attr("checked");
	  if (flag){
		  $(".module_child").attr("checked","checked");
	  }else{
		  $(".module_child").removeAttr("checked");
	  }
  }).on("click",".module_child",function(){
	  var flag = false;
	  $(".module_child").each(function(){
		  if ($(this).attr("checked") == 'checked'){
			  flag = true;
		  }
	  });
	  if (flag){
		  $(".module_setting").attr("checked","checked");
	  }else{
		  $(".module_setting").removeAttr("checked");
	  }
	  
  }).on("click",".content_manager",function(){
	  var flag = $(this).attr("checked");
	  if (flag){
		  $(".content_child").attr("checked","checked");
	  }else{
		  $(".content_child").removeAttr("checked");
	  }
  }).on("click",".content_child",function(){
	  var flag = false;
	  $(".content_child").each(function(){
		  if ($(this).attr("checked") == 'checked'){
			  flag = true;
		  }
	  });
	  if (flag){
		  $(".content_manager").attr("checked","checked");
	  }else{
		  $(".content_manager").removeAttr("checked");
	  }
  }).on("click",".publish_manager",function(){
	  var flag = $(this).attr("checked");
	  if (flag){
		  $(".publish_child").attr("checked","checked");
	  }else{
		  $(".publish_child").removeAttr("checked");
	  }
  }).on("click",".publish_child",function(){
	  var flag = false;
	  $(".publish_child").each(function(){
		  if ($(this).attr("checked") == 'checked'){
			  flag = true;
		  }
	  });
	  if (flag){
		  $(".publish_manager").attr("checked","checked");
	  }else{
		  $(".publish_manager").removeAttr("checked");
	  }
  }).on("click",".user_manager",function(){
	  var flag = $(this).attr("checked");
	  if (flag){
		  $(".user_child").attr("checked","checked");
	  }else{
		  $(".user_child").removeAttr("checked");
	  }
  }).on("click",".user_child",function(){
	  var flag = false;
	  $(".user_child").each(function(){
		  if ($(this).attr("checked") == 'checked'){
			  flag = true;
		  }
	  });
	  if (flag){
		  $(".user_manager").attr("checked","checked");
	  }else{
		  $(".user_manager").removeAttr("checked");
	  }
  });
  </script>
</body>
</html>