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
   <!--  <link rel='stylesheet' id='camera-css'  href='../css/camera.css' type='text/css' media='all'>  -->
    <link rel='stylesheet'  href='../css/jquery.mobile-1.4.2.min.css' type='text/css'> 
     <link rel='stylesheet'  href='../css/jquery.bxslider.css' type='text/css'> 
     <link rel='stylesheet'  href='../css/menu.css' type='text/css'> 
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
			width: 100%;
		}
	</style>
	<script type='text/javascript' src='../js/libs/jquery-1.7.2.min.js'></script>
    <!-- <script type='text/javascript' src='../js/jquery.mobile.customized.min.js'></script>
    <script type='text/javascript' src='../js/jquery.easing.1.3.js'></script> 
    <script type='text/javascript' src='../js/camera.min.js'></script>  -->
    <script type='text/javascript' src='../js/jquery.mobile-1.4.2.min.js'></script>
    <script type='text/javascript' src='../js/jquery.bxslider.min.js'></script>
    <script>
		/* jQuery(function(){
			jQuery('#camera_wrap_1').camera({
				height: '200px',
				width:'100%',
				time:300,
				loader:'bar',
				mobileNavHover: true,
				playPause: false
			});
			$("#camera_wrap_1").css("margin-bottom","-15px");
		}); */
		 $(document).ready(function(){
			 $("#header").append(" <ul class='bxslider'> "+
			  		 " <li><img  src= '../images/slides/home.jpg' width='100%' height='200px' /></li> "+
					 " <li><img  src= '../images/slides/home1.jpg'  width='100%' height='200px'/></li> "+
					 " <li><img  src= '../images/slides/home2.jpg' width='100%' height='200px' /></li> "+
					"</ul>");
		  	  var slider = $('.bxslider').bxSlider({
		  		  mode:'horizontal',
		  		  infiniteLoop: true,
		  		  speed:500,
		  		  hideControlOnEnd:true,
		  		  controls:false,
		  		  onSlideAfter: function(){
		  		  	slider.startAuto();
		  		  }
		  	  });
		  	  slider.startAuto();
		 });
		function showMe(value){
			alert(value);
		}
		</script>
	
</head>
<body>
	<div data-role="page">
	
	  <div data-role="header" id="header">
	    <!-- <div class="fluid_container">
	        <div class="camera_wrap camera_azure_skin" id="camera_wrap_1">
	            <div data-thumb="../images/slides/thumbs/bridge.jpg" data-src="../images/slides/bridge.jpg">
	                <div class="camera_caption fadeFromBottom">
	                    title1 <em>slides1</em>
	                </div>
	            </div>
	            <div data-thumb="../images/slides/thumbs/leaf.jpg" data-src="../images/slides/leaf.jpg">
	            </div>
	            <div data-thumb="../images/slides/thumbs/road.jpg" data-src="../images/slides/road.jpg">
	            </div>
	        </div>#camera_wrap_1
    	</div>.fluid_container -->
    	
	  </div>
	  
	  <div data-role="content">
	    <!-- <div class="container_12">

			<div class="grid_4"><a data-role="button" href="/siteList" data-icon="home" data-iconpos="top" style="white-space:normal;font-size: 15px;" data-ajax="false" >微官网</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="home" data-iconpos="top" style="white-space:normal;font-size: 15px;">产品介绍</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="home" data-iconpos="top" style="white-space:normal;font-size: 15px;">机具报修</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="grid" data-iconpos="top" style="white-space:normal;font-size: 15px;">公司介绍</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="grid" data-iconpos="top" style="white-space:normal;font-size: 15px;">办理须知</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="grid" data-iconpos="top" style="white-space:normal;font-size: 15px;">耗材申请</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">公司新闻</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">机具申请</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">我要增机</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">特约商户</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">即时到帐</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">在线客服</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">联系我们</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">加盟融商</a></div>
			
			<div class="grid_4"><a data-role="button" data-icon="info" data-iconpos="top" style="white-space:normal;font-size: 15px;">常见故障</a></div>
		</div> -->
		<!-- <div class="ui-grid-b">
	     <div class="ui-block-a">
	     	<a data-role="button" href="/siteDetail?id=19&" style="white-space:normal;font-size: 14px;background-color: black;" data-ajax="false" >公司介绍</a>
	     	<a data-role="button" href="/siteList?siteInfoType=3&" style="white-space:normal;font-size: 14px;" data-ajax="false">招贤纳士</a>
	     	<a data-role="button" href="/siteMerchant" style="white-space:normal;font-size: 14px;" data-ajax="false">我的商户</a>
	     	<a data-role="button" href="/siteDetail?id=21&" style="white-space:normal;font-size: 14px;" data-ajax="false">联系我们</a>
	     </div>
	     <div class="ui-block-b">
	       <a data-role="button" href="/siteProduct" style="white-space:normal;font-size: 14px;" data-ajax="false">产品介绍</a>
	       <a data-role="button" href="/siteDetail?id=28&" style="white-space:normal;font-size: 14px;" data-ajax="false" >办理须知</a>
	       <a data-role="button" href="/siteTestOauth?type=5&" style="white-space:normal;font-size: 14px;" data-ajax="false" >机具申请</a>
	       <a data-role="button" href="/siteTestOauth?type=4&" style="white-space:normal;font-size: 14px;" data-ajax="false">即时到帐</a>
	       <a data-role="button" href="/siteDetail?id=29&" style="white-space:normal;font-size: 14px;" data-ajax="false" >加盟融商</a>
	     </div>
	     <div class="ui-block-c">
	       <a data-role="button" href="/siteTestOauth?type=1&" style="white-space:normal;font-size: 14px;" data-ajax="false">机具报修</a>
	       <a data-role="button" href="/siteTestOauth?type=2&" style="white-space:normal;font-size: 14px;" data-ajax="false">纸张申请</a>
	       <a data-role="button" href="/siteTestOauth?type=3&" style="white-space:normal;font-size: 14px;" data-ajax="false">我要增机</a>
	       <a data-role="button" href="/siteDetail?id=21&" style="white-space:normal;font-size: 14px;" data-ajax="false" >在线客服</a>
	       <a data-role="button" href="/siteDetail?id=30&" style="white-space:normal;font-size: 14px;" data-ajax="false" >常见故障</a>
	     </div>
	   </div> -->
	  <!--  <div class="ui-grid-b">
	     <div class="ui-block-a">
	     	<a data-role="button" href="/siteList?siteInfoType=3&" style="white-space:normal;font-size: 14px;" data-ajax="false">招贤纳士</a>
	     	<a data-role="button" href="/siteProduct" style="white-space:normal;font-size: 14px;" data-ajax="false">产品介绍</a>
	     	<a data-role="button" href="/siteTestOauth?type=5&" style="white-space:normal;font-size: 14px;" data-ajax="false" >机具申请</a>
	       	<a data-role="button" href="/siteTestOauth?type=4&" style="white-space:normal;font-size: 14px;" data-ajax="false">即时到帐</a>
	     </div>
	     <div class="ui-block-b">
	       <a data-role="button" href="/siteTestOauth?type=1&" style="white-space:normal;font-size: 14px;" data-ajax="false">机具报修</a>
	       <a data-role="button" href="/siteTestOauth?type=2&" style="white-space:normal;font-size: 14px;" data-ajax="false">纸张申请</a>
	       <a data-role="button" href="/siteTestOauth?type=3&" style="white-space:normal;font-size: 14px;" data-ajax="false">我要增机</a>
	       <a data-role="button" href="/siteDetail?id=21&" style="white-space:normal;font-size: 14px;" data-ajax="false" >在线客服</a>
	     </div>
	   </div> -->
	  <!--  <div class="ui-grid-a">
	     <div class="ui-block-a">
	       	<a data-role="button" href="/siteList?siteInfoType=3&" style="white-space:normal;font-size: 21px;background-color: rgb(5,117,193);color: white;font-weight: 100;" data-ajax="false">招贤纳士</a>
	     	<a data-role="button" href="/siteProduct" style="white-space:normal;font-size: 21px;background-color: rgb(95,191,241);color: white;" data-ajax="false">产品介绍</a>
	     	<a data-role="button" href="/siteTestOauth?type=5&" style="white-space:normal;font-size: 21px;background-color: rgb(5,117,193);color: white;" data-ajax="false" >机具申请</a>
	       	<a data-role="button" href="/siteTestOauth?type=4&" style="white-space:normal;font-size: 21px;background-color: rgb(221,12,37);color: white;" data-ajax="false">即时到帐</a>
	     </div>
	     <div class="ui-block-b">
	       <a data-role="button" href="/siteTestOauth?type=1&" style="white-space:normal;font-size: 21px;background-color: rgb(95,191,241);color: white;" data-ajax="false">机具报修</a>
	       <a data-role="button" href="/siteTestOauth?type=2&" style="white-space:normal;font-size: 21px;background-color: rgb(5,117,193);color: white;" data-ajax="false">纸张申请</a>
	       <a data-role="button" href="/siteTestOauth?type=3&" style="white-space:normal;font-size: 21px;background-color: rgb(95,191,241);color: white;" data-ajax="false">我要增机</a>
	       <a data-role="button" href="/siteDetail?id=21&" style="white-space:normal;font-size: 21px;background-color: rgb(221,12,37);color: white;" data-ajax="false" >在线客服</a>
	     </div>
	   </div> -->
	  </div>
	 <div id="cate1" style="margin-top: -30px;">
	  		<ul class="mainmenu">
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(5,117,193);height:70px;">
	  					<a href="/siteList?siteInfoType=3&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">招贤纳士</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(95,191,241);color: white;height:70px;">
	  					<a href="/siteProduct" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">产品介绍</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(95,191,241);color: white;height:70px;">
	  					<a href="/siteTestOauth?type=5&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">机具申请</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(5,117,193);color: white;height:70px;">
	  					<a href="/siteTestOauth?type=4&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">即时到帐</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(5,117,193);color: white;height:70px;">
	  					<a href="/siteTestOauth?type=1&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">机具报修</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color: rgb(95,191,241);color: white;height:70px;">
	  					<a href="/siteTestOauth?type=2&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">纸张申请</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn" style="background-color:  rgb(221,12,37);color: white;height:70px;">
	  					<a href="/siteTestOauth?type=3&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">我要增机</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<!-- <div class="menubtn" style="background-color:  rgb(221,12,37);color: white;height:70px;">
	  					<a href="/siteDetail?id=21&" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">联系我们</div>
	  						</div>
	  					</a>
	  				</div> -->
	  				<div class="menubtn" style="background-color:  rgb(221,12,37);color: white;height:70px;">
	  					<a href="/bankForwardOauth" style="margin-top:20px;" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menutitle" style="font-size: 17px;color:white;">报件申请</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
		  		<div class="clr"></div>
		  	</ul>
	  </div>
	  <div data-role="footer">
	  </div>
	
	</div>

	
    
    <div style="clear:both; display:block; height:100px"></div>
</body>
</html>