<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>上海融商金融支付</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/table_form.css"/>
	 <link href="${adminUrl}/css/customer.css" rel="stylesheet" type="text/css" />
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

input[type="text"] { height: 18px; font-size: 12px;}
.required{color: #d80000;padding: 0 5px;}
.stockNum{display: none;}
.item-img {width: 311px;height: 185px;vertical-align: middle; padding-bottom: 5px;}
.red{color: red;}
.green{color:green;}
input.span2{width: 135px;}
</style>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
  <li class="active"><a href="/cmsRstManage/infoList" data-toggle="tab">内容详情</a></li>
  <li class=""><a href="/cmsRstManage/infoList?siteInfoType=${siteInfoType}">返回</a></li>
</ul>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col red " <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="explain-col green" <c:if test="${empty sucessMSG}">style="display:none"</c:if>>温馨提示: ${sucessMSG}</div>
			
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="addTechnician" class="form-horizontal" action="/cmsRstManage/addNew" method="post" enctype="multipart/form-data">
					<input type="hidden" name="siteInfoType" id="siteInfoType" value="${siteInfoType}"/>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>内容标题:</label>
							<div class="controls">
								<input type="text" id="tName" name="tName" value="" maxlength="50" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required"></span>内容简介:</label>
							<div class="controls">
								<input type="text" id="tSax" name="tSax" value="" maxlength="100" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>上传图片:</label>
							<div class="controls">
								<input class="span2" type="button" id="tImg" name="tImg" value="选择图片"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>内容详情:</label>
							<div class="controls">
								<script type="text/plain" id="tContentEditor"></script>
								<textarea name="tContent" id="tContent" class="textItem" style="display:none;width:900px;height:246px;"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="red-btn"  type="button">保存</button></div>
				<input type="hidden" id="submitFlag" value="0" />
			</div>
		</div>
	</div>
<div style="display:none">
<form id="ImgForm" action="/imgUpload" method="post" enctype="multipart/form-data">
<input id="selectImg" type="file" name="selectImg" />
</form>
</div>
<%@include file="../inc/footer.jsp"%>
<script src="${adminUrl}/js/libs/ueditor/editor_config.js"></script>
<script src="${adminUrl}/js/libs/ueditor/editor_all.js"></script>
<script src="${adminUrl}/js/libs/DatePicker/WdatePicker.js"></script>
<script src="${adminUrl}/js/libs/jquery.form.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//init Editor 
	window.UEDITOR_HOME_URL = "${adminUrl}/js/libs/ueditor/";
	//some config
	$.extend(window.UEDITOR_CONFIG,{
		UEDITOR_HOME_URL:"${adminUrl}/js/libs/ueditor/"
		//image upload url
		,focus:false
		,initialContent:$(".textItem").val()
		,imageUrl:"${adminUrl}/Editer/imgUpload"
		,imagePath:""
		,imageManager:false
		,initialFrameWidth:914
		,initialFrameHeight:380
		,catcherUrl:""
	});
	var tContentEditor = UE.getEditor('tContentEditor');
	tContentEditor.addListener("contentChange",function(){});
	
	$('#ImgForm').ajaxForm({ 
        dataType:  'json', 
        success:   function(rsp){
        	if(rsp.status == 1){
        		var imgUrl = rsp.imgUrl;
        		$("#tImg").parent().html('<img class="item-img" src="${baseUrlImg}'+imgUrl+'">'+
        								'<input type="hidden" id="uploadImg" name="uploadImg" value="'+imgUrl+'"/>'+
        								'<input class="span2" type="button" id="tImg" name="tImg" value="选择图片"/>');
        	}
        	$('#ImgForm').resetForm();
        } 
    }); 

}).on("change", "#selectImg", function(){
	$('#ImgForm').submit();
}).on('click', '#tImg', function() {
	$("#selectImg").click();
});

$("#submitBtn").live("click", function(){
	var editer = UE.getEditor('tContentEditor');
	$("#tContent").val(editer.getContent());
	var ischeck =checkForm();

	
});

function checkForm(){
	var tName = $.trim($("#tName").val());
	if(tName == "" || tName.length <= 0){
		alert("名称不能为空！",2);
		return false;
	}

	var tImg = $("#uploadImg").val();
	if(tImg == null){
		alert("请选择需要上传的图片。",2);
		return false;
	}

	var tContent = $("#tContent").val();
	if(tContent == "" || tContent.length <= 0){
		alert("详情内容不能为空。",2);
		return false;
	}

	var  $this = $("#submitBtn");
	$("#addTechnician").submit();
	
	

}
</script>
</body>
	</html>