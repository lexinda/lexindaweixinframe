<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <title>上海融商金融支付</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel='stylesheet'  href='../css/jquery.mobile-1.4.2.min.css' type='text/css'> 
        <link rel='stylesheet'  href='../css/jquery.bxslider.css' type='text/css'> 
		<script type='text/javascript' src='<%=request.getContextPath()%>/js/libs/jquery-1.7.2.min.js'></script>
	    <script type='text/javascript' src='../js/jquery.mobile-1.4.2.min.js'></script>
	    <script type='text/javascript' src='../js/jquery.bxslider.min.js'></script>
	    <script type="text/javascript">  
	        var _CONTEXTPATH = "<%=request.getContextPath()%>";  
	        var pageSize = "${pageSize}";           //根据屏幕大小计算出的每页显示数量  
	        //alert(pageSize);  
    	</script>  
	    <script type='text/javascript' src='../js/lazybind.js'></script> 
	    <script type="text/javascript">  
		    $(document).ready(function(){
		    	 $("#header").append(" <ul class='bxslider'> "+
				  		 " <li style='margin-top: -15px;'><img  src= '../images/slides/hr.jpg' width='100%' height='200px' /></li> "+
						 " <li style='margin-top: -15px;'><img  src= '../images/slides/hr1.jpg'  width='100%' height='200px'/></li> "+
						 " <li style='margin-top: -15px;'><img  src= '../images/slides/hr2.jpg' width='100%' height='200px' /></li> "+
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
	        //alert(pageSize);  
	        function toAudit(taskId) {  
	            location.href = "<c:url value='/mobileMiddlePay!toAudit.action' />?flowTask.id=" + taskId;  
	        }  
	  
	        function goDesktop() {  
	            location.href= "<c:url value='/mobileNav.action' />";  
	        }   
	    </script>
		<style type="text/css">  
			.show{display:block;}  
			.notshow{display:none;}  
		</style>  
    </head>
	<body>
    
<div id="mainPage" data-role="page">  

    <div data-role="header" id="header">  
    </div>  
      
    <div data-role="content">  
        <c:if test="${dataListSize > 0}">  
            <ul id="list" data-role="listview" data-ajax="false">  
                <c:forEach items="${dataList}" var="list">
                     <li id="li${list.id}">
                     	<a href="/siteDetail?id=${list.id}" data-ajax="false">
                     		<img src="${list.siteInfoImg}" height="75px" style="margin-top:2.5px"> 
                     		<h2>${list.siteInfoName}</h2>
    						<p>${list.siteInfoSax}</p>
    					</a>
    				</li>  
                </c:forEach>
            </ul>  
        </c:if>  
        <c:if test="${dataListSize == 0}">  
            	不存在任何数据.  
        </c:if>  
    </div>  
</div>  
</body>  
</html>  
<script>  
var args={  
    url:"/siteText?siteInfoType=${siteInfoType}",  
    linkHrefFormat:"javascript:toAudit('${id}')",  
    linkTextFormat:"${topic}(${startTime})"  
}   
  
$(document).ready(function(){  
    $('#mainPage').bind('scrollstart',function(){lazyBind(event,args);});  
});   
</script>  