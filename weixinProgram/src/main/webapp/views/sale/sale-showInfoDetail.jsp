<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>处理售后业务</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
	<style type="text/css">

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
.page-title{ margin: 15px 0 10px 25px; }

blockquote { border-left: 8px solid #006dcc; }
blockquote { padding: 0 0 0 15px; margin: 0 0 20px; }
h3 { font-size: 22px; }
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"] { height: 26px; line-height: 30px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }

input[type="text"] { height: 18px; font-size: 12px; }
.required{color: #d80000;padding: 0 5px;}
</style>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="/cmsRstManage/sale/repairMachine?page=${page}&type=${type}&status=${status}&receiptName=${receiptName}&"> <em>返回</em></a></li>
    <li class="active"><a href="javascript:void(0);" class="on"> <em>详情</em></a></li>
  </ul>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if> style="color:green;">温馨提示:
				<c:if test="${errorMSG == 1}">添加成功!</c:if>
				<c:if test="${errorMSG == 0}">添加失败!</c:if>
			</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">处理详情</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="updateStatusUser" class="form-horizontal" action="/cmsRstManage/sale/updateStatus" method="post">
						<input type="hidden" id="id" name="id" value="${weixinBusinessData.id}" />
						<input type="hidden" id="type" name="type" value="${type}" />
						<input type="hidden" id="receiptName" name="receiptName" value="${receiptName}" />
						<input type="hidden" id="page" name="page" value="${page}" />
						<input type="hidden" id="status" name="status" value="${status}" />
						<input type="hidden" id="handleStatus" name="handleStatus" value="0" />
						<div class="control-group">
							<c:if test="${type == 5 }">
								<span class="required">${weixinBusinessData.receiptName}</span>
							</c:if>
							<c:if test="${type != 5 }">
								<label class="control-label">小票名称：<span class="required">${weixinBusinessData.receiptName}</span></label>
							</c:if>
							
						</div>
						<c:if test="${type == 3 }">
						<div class="control-group" id="handleName" style="display: block;">
							<label class="control-label"><span class="required">*</span>处理人员姓名:</label>
							<div class="controls">
								<input type="text" id="handlePeople" name="handlePeople" value="${weixinBusinessData.handlePeople}" maxlength="20" /><span id="handlePeopleText" style="color:red;"></span>
							</div>
						</div>
						<div class="control-group" id="handlePhone" style="display: block;">
							<label class="control-label"><span class="required">*</span>处理人员电话:</label>
							<div class="controls">
								<input type="text" id="handleTelephone" name="handleTelephone" value="${weixinBusinessData.handleTelephone}" maxlength="20" /><span id="handleTelephoneText" style="color:red;"></span>
							</div>
						</div>
						</c:if>
						
						<c:if test="${type != 3 }">
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>处理方式:</label>
								<div class="controls">
									<label class="radio inline">
	        							<input type="radio" name="isEnable" value="0"  onchange = "change();" checked/>人员配送
	     					 		</label>
	     					 		<label class="radio inline">
	        							<input type="radio" name="isEnable" value="1"  onchange = "change();"/>快递配送
	     					 		</label>
								</div>
							</div>
							<div class="control-group" id="handleName" style="display: block;">
								<label class="control-label"><span class="required">*</span>处理人员姓名:</label>
								<div class="controls">
									<input type="text" id="handlePeople" name="handlePeople" value="${weixinBusinessData.handlePeople}" maxlength="20" /><span id="handlePeopleText" style="color:red;"></span>
								</div>
							</div>
							<div class="control-group" id="handlePhone" style="display: block;">
								<label class="control-label"><span class="required">*</span>处理人员电话:</label>
								<div class="controls">
									<input type="text" id="handleTelephone" name="handleTelephone" value="${weixinBusinessData.handleTelephone}" maxlength="20" /><span id="handleTelephoneText" style="color:red;"></span>
								</div>
							</div>
							<div class="control-group" id="expressName" style="display: none;">
								<label class="control-label"><span class="required">*</span>快递公司:</label>
								<div class="controls">
									<input type="text" id="expressPeople" name="expressPeople" value="${weixinBusinessData.handlePeople}" maxlength="20" /><span id="expressPeopleText" style="color:red;"></span>
								</div>
							</div>
							<div class="control-group" id="expressNumber" style="display: none;">
								<label class="control-label"><span class="required">*</span>快递单号:</label>
								<div class="controls">
									<input type="text" id="expressTelephone" name="expressTelephone" value="${weixinBusinessData.handleTelephone}" maxlength="20" /><span id="expressTelephoneText" style="color:red;"></span>
								</div>
							</div>
						</c:if>
					</form>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="red-btn" type="button">保存</button></div>
				
			</div> 
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
var CheckAndSubmit = function(){
	if($("#handleStatus").val() == 0){
		if($("#handlePeople").val() <= 0){
			$("#handlePeopleText").text("处理人员姓名不能为空！");
			return;
		} 
		if($("#handleTelephone").val() <= 0){
			$("#handleTelephoneText").text("处理人员电话不能为空！");
			return;
		}else if(!telCheck($("#handleTelephone").val())){
			$("#handleTelephoneText").text("处理人员电话无效！");
			return;
		} 
	}else if($("#handleStatus").val() ==1){
		if($("#expressPeople").val() <= 0){
			$("#expressPeopleText").text("快递公司不能为空！");
			return;
		} 
		if($("#expressTelephone").val() <= 0){
			$("#expressTelephoneText").text("快递单号不能为空！");
			return;
		}
	}
	
	 $("#updateStatusUser").submit(); 
}
$("#submitBtn").live("click", function(){
	CheckAndSubmit(); 
});
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

function change()
{
 var radio = document.getElementsByName("isEnable");
 //var radio = document.getElementByIdx_x("form1");
  // 用ById就不能取得全部的radio值,而是每次返回都为1
 var radioLength = radio.length;
 for(var i = 0;i < radioLength;i++)
 {
  if(radio[i].checked)
  {
   var radioValue = radio[i].value;
   $("#handleStatus").val(radioValue);
   if(radioValue == 1){
	   $("#handleName").css('display','none');
	   $("#handlePhone").css('display','none');
	   $("#expressName").css('display','block');
	   $("#expressNumber").css('display','block');
   }else{
	   $("#expressName").css('display','none');
	   $("#expressNumber").css('display','none');
	   $("#handleName").css('display','block');
	   $("#handlePhone").css('display','block');
   }
	
  }
 }
}
</script>
</body>
	</html>