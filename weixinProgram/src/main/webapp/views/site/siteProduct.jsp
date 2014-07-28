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
    <link rel='stylesheet'  href='../css/jquery.mobile-1.4.2.min.css' type='text/css'>
    <link rel='stylesheet'  href='../css/jquery.bxslider.css' type='text/css'>
    <link rel='stylesheet'  href='../css/menu.css' type='text/css'> 
	<script type='text/javascript' src='../js/libs/jquery-1.7.2.min.js'></script>
    <script type='text/javascript' src='../js/jquery.mobile-1.4.2.min.js'></script>
     <script type='text/javascript' src='../js/jquery.bxslider.min.js'></script>
    <script>
    $(document).ready(function(){
    	 $("#header").append(" <ul class='bxslider'> "+
		  		 " <li><img  src= '../images/slides/bridge.jpg' width='100%' height='200px' /></li> "+
				 " <li><img  src= '../images/slides/leaf.jpg'  width='100%' height='200px'/></li> "+
				 " <li><img  src= '../images/slides/road.jpg' width='100%' height='200px' /></li> "+
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
		</script>
	
</head>
<body>
	<div data-role="page">
	
	  <div data-role="header" id="header">
	   
	  </div>
	  
	  <div id="cate1">
	  
	  	<!-- <div class="ui-grid-a">
	    	<div class="ui-block-a">
	    		<a href="#" target="_blank">
	    			<img alt="" src="../images/chanpin.jpg" width="100%" height="60px">
	    			<span style="color:red;float: left;">无线POS</span>
	    		</a>
	    	</div>
	    	<div class="ui-block-b" align=center>
	    		<a data-role="button" href="#" target="_blank">
	    			<img alt="" src="../images/chanpin.jpg" width="100%" height="60px">
	    			<span style="color:red;float: left;">无线POS</span>
	    		</a>
	    	</div>
    	</div>
    	<br>
    	<div class="ui-grid-a">
	    	<div class="ui-block-a" align=center>
	    		<a data-role="button" href="#" target="_blank">
	    			<img alt="" src="../images/chanpin.jpg" width="100%" height="60px">
	    			<span style="color:red;float: left;">无线POS</span>
	    		</a>
	    	</div>
	    	<div class="ui-block-b" align=center>
	    		<a data-role="button" href="#" target="_blank">
	    			<img alt="" src="../images/chanpin.jpg" width="100%" height="60px">
	    			<span style="color:red;float: left;">无线POS</span>
	    		</a>
	    	</div>
    	</div> -->
	  		<ul class="mainmenu">
	  		<c:forEach items="${productInfoList}" var="productInfo">
	  			<li>
	  				<div class="menubtn">
	  					<a href="/siteDetail?id=${productInfo.id}" data-ajax="false">
	  						<div class="menumesg">
	  							<div class="menuimg">
	  								<img src="${productInfo.siteInfoImg}">
	  							</div>
	  							<div class="menutitle">${productInfo.siteInfoName}</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			</c:forEach>
	  			<!-- <li>
	  				<div class="menubtn">
	  					<a href="#">
	  						<div class="menumesg">
	  							<div class="menuimg">
	  								<img src="http://wx.hzkc.cn/data/upload/users/30/image/20140418/5350f7269e923.jpg">
	  							</div>
	  							<div class="menutitle">快刷手机POS机</div>
	  						</div>
	  					</a>
	  				</div>
	  			</li>
	  			<li>
	  				<div class="menubtn">
	  					<a href="#">
		  					<div class="menumesg">
		  						<div class="menuimg">
		  							<img src="http://wx.hzkc.cn/data/upload/users/30/image/20140408/5343d341971c5.jpg">
		  						</div>
		  						<div class="menutitle">移动（无线）POS机</div>
		  					</div>
		  				</a>
		  			</div>
		  		</li>
		  		<li>
		  			<div class="menubtn">
		  				<a href="#">
		  					<div class="menumesg">
		  						<div class="menuimg">
		  							<img src="http://wx.hzkc.cn/data/upload/users/30/image/20140418/5350f6baeaa2f.jpg">
		  						</div>
		  						<div class="menutitle">产品费率</div>
		  					</div>
		  				</a>
		  			</div>
		  		</li> -->
		  		<div class="clr"></div>
		  	</ul>
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>
</body>
</html>