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
</head>
<body>
	<div data-role="page" style="background-color: #F4F4F4;">
	  <div data-role="header">
	  </div>
	  <div data-role="content">
	   <c:if test="${reportInfoListSize > 0}">  
            <ul id="list" data-role="listview" data-ajax="false">  
                <c:forEach items="${reportInfoList}" var="list">
                     <li id="li${list.id}">
							<h2>商户名称：${list.receiptName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机型：${list.machineDesc}</h2>
                     		<h3>状态：${list.statusDesc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理时间：<fmt:formatDate pattern="yyyy-MM-dd  hh:mm:ss" value="${list.updateTime}" type="both"/>
		    				</h3>
		    				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    				<c:if test="${content!=null ||content !=''}">
    						备注：${content}
    						</c:if>
		    				</p>
    						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    						<h3>联系人：${list.userName}</h3>
    						<h3>联系电话：<a href="tel:${list.phone}">${list.phone}</a></h3>
		    				</p>
    				</li>  
                </c:forEach>
            </ul>  
        </c:if>  
        <c:if test="${reportInfoListSize == 0}">  
            	不存在任何数据.  
        </c:if>  
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>

	
    
    <div style="clear:both; display:block; height:100px"></div>
</body>
</html>