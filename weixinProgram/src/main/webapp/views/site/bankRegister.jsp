<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" > 
    <title>上海融商金融支付</title> 
    <meta name="description" content="Camera a free jQuery slideshow with many effects, transitions, adaptive layout, easy to customize, using canvas and mobile ready"> 
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel='stylesheet' id='camera-css'  href='../css/jquery.mobile-1.4.2.min.css' type='text/css' media='all'> 
	<script type='text/javascript' src='../js/libs/jquery-1.7.2.min.js'></script>
    <script type='text/javascript' src='../js/jquery.mobile-1.4.2.min.js'></script>
    <script>
		jQuery(function(){
			
			$("#submitBut").click(function(){
				$("#userNameText").text("");
				$("#passwordText").text("");
				$("#passwordAgainText").text("");
				$("#bankNameText").text("");
				$("#telephoneText").text("");
				$("#provinceText").text("");
				if($("#userName").val()==null||$("#userName").val()==""){
					$("#userNameText").text("用户名不能为空！");
					_scroll_to($("#userNameText"));
				}else if(!userCheck($("#userName").val())){
					$("#userNameText").text("用户名只能是字母或数字！");
					_scroll_to($("#userNameText"));
				}else if($("#password").val()==null||$("#password").val()==""){
					$("#passwordText").text("密码不能为空！");
					_scroll_to($("#passwordText"));
				}else if($("#passwordAgain").val()==null||$("#passwordAgain").val()==""){
					$("#passwordAgainText").text("确认密码不能为空！");
					_scroll_to($("#passwordAgainText"));
				}else if($("#password").val()!=$("#passwordAgain").val()){
					$("#passwordAgainText").text("确认密码与密码不相同！");
					_scroll_to($("#passwordText"));
				}else if($("#bankName").val()==null||$("#bankName").val()==""){
					$("#bankNameText").text("联系人不能为空！");
					_scroll_to($("#bankNameText"));
				}else if($("#telephone").val()==null||$("#telephone").val()==""){
					$("#telephoneText").text("电话不能为空！");
					_scroll_to($("#telephoneText"));
				}else if(!telCheck($("#telephone").val())){
					$("#telephoneText").text("请输入有效电话！");
					_scroll_to($("#telephoneText"));
				}else if($("#province").val()==0||$("#province").val()==undefined){
					$("#provinceText").text("请选择所属银行！");
					_scroll_to($("#provinceText"));
				}else{
					$("#provinceName").val($("#province").val());
					$("#submitBut").button("disable");
					$("#formRepairMachine").submit();
				}
			});
			$("#resultBut").click(function(){
				$("#bankName").val("");
				$("#telephone").val("");
			});
		});
		function _scroll_to(jqueryObj) {
			$("html,body").animate({scrollTop: jqueryObj.offset().top}, 1000); 
		}
		function userCheck(user){
			var patten=/^[A-Za-z0-9]+$/;
			 if(patten.test(user))
            {            
               return true;
          	}
          	else 
          	{
              return false;
          	} 
		}
		 function telCheck(phone)
		    {
		       with(document.forms[0]) 
		       {
		        var patten =/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}?$/;
		        var pat = /^(\b13[0-9]{9}\b)|(\b14[7-7]\d{8}\b)|(\b15[0-9]\d{8}\b)|(\b17[0-9]\d{8}\b)|(\b18[0-9]\d{8}\b)|\b1[1-9]{2,4}\b$/ ;
		        var checkphone=phone.toString().split('-');
		        if( checkphone.length>2)
		          return false;
		         if (phone !="" || phone.length!=0) 
		      {         
		       if (phone.substring(0,3) == "+86") 
		       {
		                phone = phone.substring(3,phone.length);
		         }
		         if (phone.substring(0, 2) == "13"||phone.substring(0, 2) == "14" || phone.substring(0, 2) == "15" || phone.substring(0, 2) == "18" || phone.substring(0, 2) == "17") {
		                if(pat.test(phone))
		                {            
		                  return true;
		             }
		             else 
		             {
		                 return false;
		             } 
		         } 
		         else
		         {
		           if(patten.test(phone)) 
		           {
		               return true;
		           } 
		           else 
		           {
		                  return false;
		              }
		         }
		        } 
		        else
		        {
		            return false;
		     } 
		      }
		    }
		</script>
	
</head>
<body>
	<div data-role="page">
	
	  <div data-role="header">
	  
	  </div>
	  
	  <div data-role="content" style="color:green;">
		   	 <h3 style="text-align: center;margin-top: -3px;margin-bottom: -10px;text-shadow:none;">欢迎注册</h3>
	   <!-- <div data-role="collapsible" data-collapsed="false"> -->
		   <div class="explain-col" <c:if test="${empty isSuccess}">style="display:none"</c:if>>
		   <c:if test="${isSuccess == 1}">
				<font color="red">温馨提示: 用户名已存在，提交失败！</font>
		   </c:if>
		   <c:if test="${isSuccess == 2}">
				<font color="red">温馨提示: 提交失败！</font>
		   </c:if>
		   <c:if test="${isSuccess == 0}">
				<font color="green">温馨提示: 提交成功！</font>
		   </c:if>
		   
		   </div>
		    	<form id="formRepairMachine" action="/bankSave" method="post" data-ajax="false">
		    	<input type="text" id="openId" name="openId" value="${openId}">
				  <div data-role="fieldcontain">
				    <label for="userName" style="text-shadow:none;"><span style="color:red;">*</span>用户名:<span id="userNameText" style="color:red;"></span></label>
				    <input type="text" name="userName" id="userName" placeholder="请填写您的用户名..." value="">
				    <label for="userName" style="text-shadow:none;"><span style="color:red;">*</span>密码:<span id="passwordText" style="color:red;"></span></label>
				    <input type="password" name="password" id="password" placeholder="请填写您的密码..." value="">
				    <label for="userName" style="text-shadow:none;"><span style="color:red;">*</span>确认密码:<span id="passwordAgainText" style="color:red;"></span></label>
				    <input type="password" name="passwordAgain" id="passwordAgain" placeholder="请填写您的密码..." value="">
				    <label for="lname" style="text-shadow:none;"><span style="color:red;">*</span>姓名:<span id="bankNameText" style="color:red;"></span></label>
				    <input type="text" name="bankName" id="bankName" placeholder="请填写您的姓名..." value="">
				     <label for="lname" style="text-shadow:none;"><span style="color:red;">*</span>电话:<span id="telephoneText" style="color:red;"></span></label>
				    <input type="text" name="telephone" id="telephone" placeholder="请填写您的电话..." value="">
				    </div>
				     <div data-role="controlgroup" style="text-shadow:none;">  
			           <legend><span style="color:red;">*</span>性别：</legend>  
			          <input type="radio" name="sax" id="radio-choice-1" value="1" checked="checked" />
						<label for="radio-choice-1">男</label>
						<input type="radio" name="sax" id="radio-choice-2" value="0" />
						<label for="radio-choice-2">女</label>
			         </div>
			         <fieldset data-role="fieldcontain">
			         	<input type="hidden" name="provinceName" id="provinceName" value="" />
				        <label for="province" style="text-shadow:none;"><span style="color:red;">*</span>选择所属银行：<span id="provinceText" style="color:red;"></span></label>
				        <select name="province" id="province">
				         	<c:forEach  items="${bankList}" var="bank">
				         		<option value="${bank.bankId}">${bank.bankDesc}</option>
				         	</c:forEach>
				        </select>
				      </fieldset>
			          <div data-role="fieldcontain">
    				 	<div class="ui-grid-a">
	    				 	<div class="ui-block-a">
	    				 		<input type="button" id="submitBut" value="确定">
	    				 	</div>
	    				 	<div class="ui-block-b">
	    				 		<input type="button" id="resultBut" value="清空">
	    				 	</div>
    					 </div>
				  	</div>
				</form>
		 <!-- </div> -->
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>
</body>
</html>