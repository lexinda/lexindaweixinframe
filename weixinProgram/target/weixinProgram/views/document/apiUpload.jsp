<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>个人信息</title>
	<link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="/css/customer.css"/>
	<link rel="stylesheet" type="text/css" href="/css/uploadify.css">
	</script>

	<style type="text/css">
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.info{display:block; padding-top: 6px; font-size: 12px;}
.col-tab {width: 60%;}
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"]{ height: 26px; line-height: 30px; }
input[type="text"]{ height:16px; font-size:12px; }
.main-content{padding-top:20px;}
</style>
</head>
<body>
	<div class="subc-title">
		Api文档上传
	</div>
	
	<div class="main-content">
	   <div class="control-group">
			<label class="control-label">文档说明:</label>
			<div class="controls">
			</div>
	   </div>
	   <div class="control-group">
		<label class="control-label">文档上传:</label>
		 <input name="imageUrlValue" id="imageUrlValue" type="hidden" 
		     value ="" readonly /> 
		<input type="file" name="file_upload" id="file_upload" />
		<div class="controls">
		</div>
	   </div>
	       
	</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript" src="/lib/jquery.min.js"></script>
<script type="text/javascript" src="/js/libs/uploadify/jquery.uploadify.js"></script>
<script>
$(document).ready(function() {
    $("#file_upload").uploadify({
    	'method'    : 'POST',
        'swf'       : '/js/libs/uploadify/uploadify.swf',
        'height'    :  30,  
        'uploader'  : '/document/Upload?type=0',
        'cancelImg' : '/images/uploadify-cancel.png',
        'folder'    : 'uploads',
        'fileTypeExts' : '*.doc;*.docx;',
        'queueID'   : 'uploader_queue',
        'auto'      : true,
        'multi'     : true,
        'simUploadLimit' : 1,
        'queueSizeLimit' : 1,
        'uploadLimit'    : 2,
        'buttonText'     : '上传',
        'onUploadStart' : function(file) {
        	var $imageholders= $(".upload-image");
       		var _size=1;
       		var __size=$imageholders.find("img").size()
       		 if(__size<_size){
       			 }else{
       				 alert("超出上传个数,先删除!"); $("#uploadify").uploadify('cancel');return false;
       			 }
         },  
         'onUploadSuccess':function(file, data, response){
        	 var $imageholders= $(".upload-image");
        	 var _size=$imageholders.size();
        	 $imageholders.each(function(){
        		 var $this=$(this);
        		$imageholders.addClass("has-pic");
       			 if(!$this.find("img").size()){
       				 var _str='<a class="del">X</a> <img src="${urlImg}/temp'+data+'"/>'
       				 var _fieldName=''
       				 var __index=$this.index();
       				 switch(__index){
       				   case 0:
       						_fieldName='primary'
       					   break;
       				   default:
       						_fieldName='alt'+__index;
       				   	   break;
       				 }
       				 $("#imageUrlValue").val(data) ; 
           			 $this.html(_str);
           			 return false;
           		 }
        	 })
         },  
         'onUploadComplete':function(){  
         }  
    });
});
</script>


</body>

</html>