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
    <script type='text/javascript' src='../js/AreaData_min.js'></script>
    <script>
		jQuery(function(){
			
			$("#submitBut").click(function(){
				$("#receiptNameText").text("");
				$("#businessNameText").text("");
				$("#telephoneText").text("");
				$("#provinceText").text("");
				$("#cityText").text("");
				$("#areaText").text("");
				var provinceName=$("#province").find("option:selected").text();
				var cityName=$("#city").find("option:selected").text();
				var areaName=$("#area").find("option:selected").text();
				if($("#receiptName").val()==null||$("#receiptName").val()==""){
					$("#receiptNameText").text("小票名称不能为空！");
					_scroll_to($("#receiptNameText"));
				}else if($("#businessName").val()==null||$("#businessName").val()==""){
					$("#businessNameText").text("联系人不能为空！");
					_scroll_to($("#businessNameText"));
				}else if($("#telephone").val()==null||$("#telephone").val()==""){
					$("#telephoneText").text("电话不能为空！");
					_scroll_to($("#telephoneText"));
				}else if(!telCheck($("#telephone").val())){
					$("#telephoneText").text("请输入有效电话！");
					_scroll_to($("#telephoneText"));
				}else if($("#province").val()==0||$("#province").val()==undefined){
					$("#provinceText").text("请选择省！");
					_scroll_to($("#province"));
				}else if($("#city").val()==0||$("#city").val()==undefined){
					$("#cityText").text("请选择市！");
					_scroll_to($("#city"));
				}else if($("#area").val()==0){
					if($("#province").val() == 11 || $("#province").val() == 12 || $("#province").val() == 31 || $("#province").val() == 71 || $("#province").val() == 50 || $("#province").val() == 81 || $("#province").val() == 82){
						if($("#street").val()==null||$("#street").val()==""){
							$("#streetText").text("街道门牌号不能为空！");
							_scroll_to($("#streetText"));
						}else{
							$("#provinceName").val(provinceName);
							$("#cityName").val(cityName);
							$("#areaName").val(areaName);
							$("#submitBut").button("disable");
							$("#formRepairMachine").submit();
						}
						
					}else{
						$("#areaText").text("请选择区！");
						_scroll_to($("#area"));
					}
				}else if($("#street").val()==null||$("#street").val()==""){
					$("#streetText").text("街道门牌号不能为空！");
					_scroll_to($("#streetText"));
				}else{
					$("#provinceName").val(provinceName);
					$("#cityName").val(cityName);
					$("#areaName").val(areaName);
					$("#submitBut").button("disable");
					$("#formRepairMachine").submit();
				}
			});
			$("#resultBut").click(function(){
				$("#receiptName").val("");
				$("#businessName").val("");
				$("#telephone").val("");
				$("#address").val("");
			});
			for(var i=1;i<area_array.length;i++){
				if(area_array[i]!=undefined){
					$("#province").append("<option value='"+i+"'>"+area_array[i]+"</option>");
				}
			}
			
		});
		function changeComplexProvince(f, k, e, d) {
			var c = changeComplexProvince.arguments; var h = document.getElementById(e);
		    var g = document.getElementById(d); 
		    var b = 0; var a = 0; removeOptions(h); f = parseInt(f);
		    if (k[f] != undefined) {
		        for (b = 0; b < k[f].length; b++) {
		            if (k[f][b] == undefined) { continue }
		            if (c[3]) { if ((c[3] == true) && (f != 71) && (f != 81) && (f != 82)) { if ((b % 100) == 0) { continue } } }
		            h[a] = new Option(k[f][b], b); a++
		        }
		    }
		    removeOptions(g); g[0] = new Option("请选择 ", 0);
		   if (f == 11 || f == 12 || f == 31 || f == 71 || f == 50 || f == 81 || f == 82) {
		        if ($("#" + d))
		        { $("#" + d).hide(); }
		    }
		    else {
		        if ($("#" + d)) { $("#" + d).show(); }
		    }
		}
		function changeCity(c, a, t) {
		    c = parseInt(c); 
		    var _d = sub_arr[c];
		    var str = "";     
		    for (var i = c * 100; i < sub_arr[c].length; i++) {
		        if (_d[i] == undefined) continue; 
		        str += "<option value='" + i + "' >" + _d[i] + "</option>";
		    }
		    $("#" + a).append(str);
		    
		}
		function removeOptions(c) {
		    if ((c != undefined) && (c.options != undefined)) {
		        var a = c.options.length;
		        for (var b = 0; b < a; b++) {
		            c.options[0] = null;
		        }
		    }
		}
		
		function _scroll_to(jqueryObj) {
			$("html,body").animate({scrollTop: jqueryObj.offset().top}, 1000); 
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
	<div data-role="page" style="background-image: url('/images/bg6.jpg');background-repeat: no-repeat;">
	
	  <div data-role="header">
	  
	  </div>
	  
	  <div data-role="content" style="color:green;">
	  		<c:if test="${type == 1}">
		   	 <h3 style="text-align: center;margin-top: -3px;margin-bottom: -30px;text-shadow:none;">机具报修</h3>
		    </c:if>
		    <c:if test="${type == 2}">
		   	 <h3 style="text-align: center;margin-top: -3px;margin-bottom: -30px;text-shadow:none;">纸张申请</h3>
		    </c:if>
		    <c:if test="${type == 3}">
		   	 <h3 style="text-align: center;margin-top: -3px;margin-bottom: -30px;text-shadow:none;">我要增机</h3>
		    </c:if>
		    <c:if test="${type == 5}">
		   	 <h3 style="text-align: center;margin-top: -3px;margin-bottom: -30px;text-shadow:none;">机具申请</h3>
		    </c:if>
	  <div style="color:green;text-shadow:none;">
		  &nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${resultUser.headimgurl ==''}">
		  <img alt="" src="/images/touxiang.jpg" width="60px" height="60px">
		  </c:if>
		  <c:if test="${resultUser.headimgurl !=''}">
		  <img alt="" src="${resultUser.headimgurl}" width="60px" height="60px">
		  </c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 你好，${resultUser.nickname}
	   </div>
	   <!-- <div data-role="collapsible" data-collapsed="false"> -->
		   <div class="explain-col" <c:if test="${empty isSuccess}">style="display:none"</c:if>><font color="green">温馨提示: ${isSuccess}</font></div>
		    	<form id="formRepairMachine" action="/siteRepairMachine" method="post" data-ajax="false">
		    	<input type="hidden" id="openId" name="openId" value="${resultUser.openid}">
		    	<input type="hidden" id="type" name="type" value="${type}">
				  <div data-role="fieldcontain">
				    
				     <c:if test="${type != 5}">
				   		 <label for="fname" style="text-shadow:none;"><span style="color:red;">*</span>小票名称:<span id="receiptNameText" style="color:red;"></span></label>
				    	<input type="text" name="receiptName" id="receiptName" placeholder="请填写您小票名称..." value="${weixinBusinessData.receiptName}">
				    </c:if>
				     <c:if test="${type == 5}">
				    	<input type="hidden" name="receiptName" id="receiptName" placeholder="请填写您小票名称..." value="机具申请">
				    </c:if>
				    <label for="lname" style="text-shadow:none;"><span style="color:red;">*</span>联系人:<span id="businessNameText" style="color:red;"></span></label>
				    <input type="text" name="businessName" id="businessName" placeholder="请填写您的姓名..." value="${weixinBusinessData.businessName}">
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
				        <label for="province" style="text-shadow:none;"><span style="color:red;">*</span>选择省：<span id="provinceText" style="color:red;"></span></label>
				        <select name="province" id="province" onChange="changeComplexProvince(this.value, sub_array, 'city', 'area');">
				        <c:if test="${weixinBusinessData.province !=null}">
				         	<option value="999" >${weixinBusinessData.province}</option>
				         </c:if>
				         <c:if test="${weixinBusinessData.province ==null}">
				         	<option value="0" >请选择</option>
				         </c:if>
				        </select>
				        <input type="hidden" name="cityName" id="cityName" value="" />
				         <label for="city" style="text-shadow:none;"><span style="color:red;">*</span>选择市：<span id="cityText" style="color:red;"></span></label>
				        <select name="city" id="city" onChange="changeCity(this.value,'area','area');">
				        <c:if test="${weixinBusinessData.city !=null}">
				         	<option value="999" >${weixinBusinessData.city}</option>
				         </c:if>
				         <c:if test="${weixinBusinessData.city ==null}">
				         	<option value="0" >请选择</option>
				         </c:if>
				        </select>
				        <input type="hidden" name="areaName" id="areaName" value="" />
				         <label for="area" style="text-shadow:none;">选择区<span id="areaText" style="color:red;"></span></label>
				        <select name="area" id="area">
				       	 <c:if test="${weixinBusinessData.area !=null}">
				         	<option value="999" >${weixinBusinessData.area}</option>
				         </c:if>
				         <c:if test="${weixinBusinessData.area ==null}">
				         	<option value="0" >请选择</option>
				         </c:if>
				        </select>
				         <label for="street" style="text-shadow:none;"><span style="color:red;">*</span>街道门牌号:<span id="streetText" style="color:red;"></span></label>
				    		<input type="text" name="street" id="street" placeholder="请填写您的街道门牌号..." value="${weixinBusinessData.street}">
				      </fieldset>
			          <div data-role="fieldcontain">
				    <c:if test="${type == 1}">
				   	 	<label for="info" style="text-shadow:none;">描述：</label>
    					<textarea name="address" id="address" placeholder="填写您的故障简称，有助于我们尽快帮您解决问题..."> ${weixinBusinessData.address}</textarea>
				    </c:if>
				    <c:if test="${type == 2}">
					    <label for="info" style="text-shadow:none;">描述：</label>
	    				<textarea name="address" id="address" placeholder="填写您的实际地址..."> ${weixinBusinessData.address}</textarea>
    				</c:if>
    				<c:if test="${type == 3}">
					    <label for="info" style="text-shadow:none;">描述：</label>
	    				<textarea name="address" id="address" placeholder="填写您的实际地址..."> ${weixinBusinessData.address}</textarea>
    				</c:if>
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
		   <c:if test="${type == 1}">
		   	 <a data-role="button" href="/siteBusinessRecord?openId=${resultUser.openid}&type=${type}&" data-icon="search" data-transition="slide" style="color:green;">机具报修记录</a>
		    </c:if>
		    <c:if test="${type == 2}">
		   	 <a data-role="button" href="/siteBusinessRecord?openId=${resultUser.openid}&type=${type}&" data-icon="search" data-transition="slide" style="color:green;">纸张申请记录</a>
		    </c:if>
		    <c:if test="${type == 3}">
		   	 <a data-role="button" href="/siteBusinessRecord?openId=${resultUser.openid}&type=${type}&" data-icon="search" data-transition="slide" style="color:green;">我要增机记录</a>
		    </c:if>
		    <c:if test="${type == 5}">
		   	 <a data-role="button" href="/siteBusinessRecord?openId=${resultUser.openid}&type=${type}&" data-icon="search" data-transition="slide" style="color:green;">机具申请记录</a>
		    </c:if>
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>
</body>
</html>