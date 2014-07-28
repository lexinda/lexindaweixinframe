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
    <link rel='stylesheet' id='camera-css'  href='../css/camera.css' type='text/css' media='all'> 
    <link rel='stylesheet' id='camera-css'  href='../css/jquery-mobile-fluid960.css' type='text/css' media='all'> 
    <link rel='stylesheet' id='camera-css'  href='../css/jquery.mobile-1.4.2.min.css' type='text/css' media='all'> 
    <style>
		body {
			margin: 0;
			padding: 0;
			font-size:15px;
			
		}
		a {
			color: #09f;
		}
		a:hover {
			text-decoration: none;
		}
		.fluid_container {
			margin: 0 auto;
			max-width: 1000px;
			width: 100%;
		}
		
	</style>
	<script type='text/javascript' src='../js/libs/jquery-1.7.2.min.js'></script>
    <script type='text/javascript' src='../js/jquery.mobile.customized.min.js'></script>
    <script type='text/javascript' src='../js/jquery.easing.1.3.js'></script> 
    <script type='text/javascript' src='../js/camera.min.js'></script> 
    <script type='text/javascript' src='../js/jquery.mobile-1.4.2.min.js'></script>
    <script>
		jQuery(function(){
			var speed = 1000;//自定义滚动速度
            //回到顶部
            $( "#top").click( function () {
                $( "html,body").animate({ "scrollTop" : 0 }, speed);
                });
            //回到底部
            var windowHeight = parseInt($("body").css("height" ));//整个页面的高度
            $( "#toBottom").click(function () {
                $( "html,body").animate({ "scrollTop" : windowHeight }, speed);
            });
		});
		</script>
	
</head>
<body>
	<div data-role="page" style="background-color: #F4F4F4;">
	  <div data-role="header">
	  </div>
	  <div data-role="content">
		  <h3 style="text-align: center;height: 0px;">${siteInfo.siteInfoName }</h3>
		  <h5 style="height: 0px;"><fmt:formatDate pattern="yyyy-MM-dd" value="${siteInfo.createTime}" type="both"/>&nbsp;&nbsp;<img alt="" src="../images/rh.gif" width="40px" height="8px"/><br></h5>
		  <div align=center style="text-align: left;">
		 	 <img alt="" src="${siteInfo.siteInfoImg}" width="100%" height="150px"/><br>
		 	 ${siteInfo.siteInfoContent}
		  </div>
		 <!--  gh_c9e932641525 -->
		 	<hr style="BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted" color=#111111 size=1> 
		 	<p style="color: green;">如果这篇文章对您或您的朋友有所帮助，您可以点击右上角的更多按钮</p>
		 	<img alt="" src="../images/shareTop.jpg" width="100%" height="44px"/>
		 	<p style="color: green;">分享给您的朋友们^_^
		 	<img alt="" src="../images/shareTop1.jpg" width="100%" height="70px"/>
			<hr style="BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted" color=#111111 size=1> 
		  	<button id="top" data-icon="arrow-u" data-iconpos="top">回到顶部</button>
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>

	
    
    <div style="clear:both; display:block; height:100px"></div>
</body>
</html>