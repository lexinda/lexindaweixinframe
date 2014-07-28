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
	  	<c:if test="${type == 1}">
		   	 <h3>机具报修记录</h3>
		    </c:if>
		    <c:if test="${type == 2}">
		   	 <h3>纸张申请记录</h3>
		    </c:if>
		    <c:if test="${type == 3}">
		   	 <h3>我要增机记录</h3>
		    </c:if>
	   <c:if test="${weixinBusinessDataSize > 0}">  
            <ul id="list" data-role="listview" data-ajax="false">  
                <c:forEach items="${weixinBusinessData}" var="list">
                     <li id="li${list.id}">
							<c:if test="${type != 5 }">
								<h2>小票名称：${list.receiptName}</h2>
							</c:if>
                     		<h3><c:if test="${list.status == 0}">
    							<font color="red">待处理</font>
    							<fmt:formatDate pattern="yyyy-MM-dd  hh:mm:ss" value="${list.createTime}" type="both"/>
		    				</c:if>
    						<c:if test="${list.status == 1}">
    							<font color="green">已处理</font>
    							<fmt:formatDate pattern="yyyy-MM-dd  hh:mm:ss" value="${list.updateTime}" type="both"/>
		    				</c:if>
		    				</h3>
    						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    						<c:if test="${list.status == 1}">
    						<h3>处理人员姓名：业务员${list.handlePeople}</h3>
    						<h3>联系电话：<a href="tel:${list.handleTelephone}">${list.handleTelephone}</a></h3>
    						</c:if>
		    				</p>
    				</li>  
                </c:forEach>
            </ul>  
        </c:if>  
        <c:if test="${weixinBusinessDataSize == 0}">  
            	不存在任何数据.  
        </c:if>  
	  </div>
	
	  <div data-role="footer">
	  </div>
	
	</div>

	
    
    <div style="clear:both; display:block; height:100px"></div>
</body>
</html>