<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META content="IE=edge" http-equiv="X-UA-Compatible">
<title>上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系，上海融商于2012年6月成立黑龙江分公司，吉林分公司，河南分公司和辽宁分公司。</title>
<meta name="description"
	content="上海融商网络科技有限公司（简称“上海融商”）成立于2012年02月，是一家综合性支付服务代理企业，总部设立于上海。得益于成熟的收单市场经验、在全国金融中心的地理位置、信息畅通并与全国的主要第三方机构有着成熟的渠道合作关系，上海融商于2012年6月成立黑龙江分公司，吉林分公司，河南分公司和辽宁分公司。" />
<meta name="keywords"
	content="上海融商,融商,融商支付,信用卡,微支付" />
<link rel="stylesheet" href="../css/uikit.min.css" />
<link href="../css/footer/Reset.css" rel="stylesheet"
	type="text/css" />
<link href="../css/footer/style.css" rel="stylesheet"
	type="text/css" />
<link type="text/css" rel="stylesheet"
	href="../css/templates/header.css" />
<link type="text/css" rel="stylesheet"
	href="../css/iphoneslide/base_style.css" />
<link type="text/css" rel="stylesheet"
	href="../css/iphoneslide/index.css" />
	<link href="../css/footer/rightbanner.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript"
	src="../js/footer/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="../js/footer/main.js"></script>
<script src="../js/uikit.min.js"></script>
<script src="../js/components/pagination.min.js"></script>
<script language="JavaScript">
	$(function(){
		showFocus(1);
	});
	function showFocus(num)
	{
		for(var id = 1;id<=4;id++)
		{
			var fpid="tab"+id;
			var fnid="focusnav"+id;
			if(id==num){
			   try{document.getElementById(fpid).style.display="block"}catch(e){};
			   try{document.getElementById(fnid).className="active"}catch(e){};
			}else{
			   try{document.getElementById(fpid).style.display="none"}catch(e){};
			   try{document.getElementById(fnid).className=""}catch(e){};
			}
		} 
	}
</script>

</head>
<body id="top">
	<div id="backTop">
		<a href="#top" title="返回顶部"></a>
	</div>
		<div class="head_wrap">
			<div class="head center">
				<div class="logo">
					<a href="http://www.rstpay.com/"><img
						src="../images/rh.png" style="width:200px;height:60px;" /></a>
				</div>
				<div class="nav_wrap">
					<div class="nav_bar center">
						<ul id="nav" class="nav clearfix">
							<li class="nLi">
								<h3>
									<a href="http://www.rstpay.com/">首页</a>
								</h3>
							</li>

							<li class="nLi on">
								<h3>
									<a href='http://www.rstpay.com/rest/'>融商通</a>
								</h3>
							</li>

							<li class="nLi">
								<h3>
									<a href='http://www.rstpay.com/product/'>POS产品</a>
								</h3>
							</li>

							<li class="nLi">
								<h3>
									<a href='http://www.rstpay.com/soft/'>查账登陆</a>
								</h3>
							</li>

							<li class="nLi">
								<h3>
									<a href='http://www.rstpay.com/news/'>新闻动态</a>
								</h3>
							</li>

							<li class="nLi">
								<h3>
									<a href='http://www.rstpay.com/about/'>关于我们</a>
								</h3>
							</li>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="fullSlide">
		<div class="bd">
			<ul>
				<li style="background: #e6e6e6;">
					<img class="bg"
							src="../images/rongshangtong.gif" />
					<div class="pic">
						<a href="/#"><img
							src="../images/rongshangtongimage1.png" /></a>
					</div>
					<img src="../images/iphonedownload.png" class="iphone" alt="" onclick="download(1)"/>
					<img src="../images/androiddownload.png" class="android" alt="" />
				</li>
			</ul>
		</div>
		<div class="hd">
			<ul>
			</ul>
		</div>
	</div>
	<!-- <script type="text/javascript">
		jQuery(".fullSlide").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			effect : "fold",
			autoPlay : true,
			autoPage : true,
			trigger : "click",
			interTime : 5000,
			switchLoad : "_src"
		});
	</script> -->
			<div class="container">
  <div class="telbgx">
  	<div class="m30 content clearfix">
				<div class="w790 f_L">
				<img src="../images/templates/pro3.png"/>
        </div>
        <div class="w160 f_R">
        	<div class="NewsNav mb50">
          <ul class="clearfix">
				
              <li><a href="http://www.rstpay.com/rest/">产品展示</a></li>
				
              <li><a href="http://www.rstpay.com/restDetail/">功能介绍</a></li>
				
              <li><a href="http://www.rstpay.com/restProduct/">产品特点</a></li>
              
              <li><a href="http://www.rstpay.com/restUse/">如何使用</a></li>
              
              <li><a href="http://www.rstpay.com/restApp/">下载软件</a></li>
				
          </ul>
        </div>
        </div>
	</div>
  </div>
</div>
	<div class="main_foot">
		<div class="foot">
			<div style="color:white;text-align: center;"><a href="http://www.rstpay.com/about" style="color:white;">关于我们</a><span>|</span><a href="http://www.rstpay.com/news" style="color:white;">新闻资讯</a><span>|</span><a href="http://www.rstpay.com/hr" style="color:white;">人才招聘</a><span>|</span><a href="http://www.rstpay.com/contact" style="color:white;">联系我们</a><span>|</span><a href="http://www.rstpay.com/soft" style="color:white;">查账登陆</a></div>
  			<div style="color:white;text-align: center;">&copy;2015 rstpay<span>|</span>上海融商 版权所有 </div>
  
  
			</div>
			<!--foot-->
			<div class="foot_bottom txt_C"></div>
		</div>

		<script language="JavaScript" type="text/JavaScript">
			function MM_openBrWindow(theURL, winName, features) {
				window.open(theURL, winName, features);
			}
		</script>
		<div class="service_box">
  <ul class="tab_nav">
    <li><a href="http://www.lexindasoft.com"><i class="icon icon_1"></i>在线客服</a></li>
    <li><a href="http://www.lexindasoft.com"><i class="icon icon_3"></i></a></li>
  </ul>
  <div class="tab_content">
    <div class="tab_plan">
      <p class="fs14">在线客服1:<br />
        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=734421679&amp;site=qq&amp;menu=yes" class="qq_link"><img border="0" src="http://wpa.qq.com/pa?p=2:2833026848:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
      <p class="fs14 mt10">在线客服2:<br />
        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=734421679&amp;site=qq&amp;menu=yes" class="qq_link"><img border="0" src="http://wpa.qq.com/pa?p=2:123456789:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
      <p class="fs14 mt10">商务客服:<br />
        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=734421679&amp;site=qq&amp;menu=yes" class="qq_link"><img border="0" src="http://wpa.qq.com/pa?p=2:2833026848:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
      <p class="mt10">服务时间:<br />
        9:00-18:00(工作日)<br />
      </p>
    </div>
    <div class="tab_plan" style="display: none;"><img src="../images/rongshangweixin.jpg" alt="官方微信自助客服" width="130" height="130"/>
      <p>扫一扫关注融商支付</p>
    </div>
  </div>
</div>
		<div class="dontshow"></div>

		<script>
			window._bd_share_config = {
				"common" : {
					"bdSnsKey" : {},
					"bdText" : "",
					"bdMini" : "1",
					"bdMiniList" : false,
					"bdPic" : "",
					"bdStyle" : "0",
					"bdSize" : "16"
				},
				"slide" : {
					"type" : "slide",
					"bdImg" : "7",
					"bdPos" : "left",
					"bdTop" : "100"
				}
			};
			with (document)
				0[(getElementsByTagName('head')[0] || body)
						.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
						+ ~(-new Date() / 36e5)];
		</script>
		<!-- <a key="52aebb5e3b05a3da0f5be2d1" logo_size="124x47"
			logo_type="realname" href="http://www.anquan.org"><script
				src="http://static.anquan.org/static/outer/js/aq_auth.js"></script></a> -->
				<script language="javascript" type="text/javascript"
			src="../js/iphoneslide/jquery.lazyload.js"></script>
		<script src="../js/iphoneslide/slider.js"
			type="text/javascript"></script>
		<script src="../js/iphoneslide/placeholder.js"
			type="text/javascript"></script>
		<script>
			$(function() {
				//图片延迟加载
				if ('undefined' == typeof (document.body.style.maxHeight)) {
					$("div.lazy").each(function(i) {
						var url = $(this).attr("data-original")
						$(this).css({
							"background-image" : "url(" + url + ")"
						});
					});
				} else {
					$("div.lazy").lazyload();
				}

				$(".icons_detail ul li").hover(function() {
					var _temp = $(this);
					var no = _temp.parent().find("li").index(_temp);
					$(".icon_tips").eq(no).toggleClass("hide");

				});
				$(".icons_detail ul").mouseenter(function() {
					$(".icon_show").addClass("hide");

				});
				$(".icons_detail ul").mouseleave(function() {
					$(".icon_show").removeClass("hide");

				});

				$("input,textarea").placeholder();

			});
		</script>
		<script type="text/javascript">
$(".service_box .tab_nav li").hover(function(){
	var i=$(this).index();
	$(this).addClass("active").siblings().removeClass("active");
	$(".service_box .tab_plan:eq("+i+")").show().siblings().hide();
});
$(function(){
	var t;
	$(".service_box").hover(function(){
		$(".service_box").animate({right:0},300)
		clearTimeout(t);
	},function(){
		t=setTimeout(function(e){
			$(".service_box").animate({right:"-146px"},300);
			$(".service_box").find(".tab_nav li").removeClass("active");
		},1000);
	});
});
var isTransition=true;
$(".m_logo").hover(function(){
	var r=0;
	if(isTransition){
		animateTime = setInterval(function(){
			if (r>=153){
				clearInterval(animateTime);
				isTransition=true;
			}else{
				isTransition=false;
				r++;
				$(".m_logo a").attr("style","-webkit-mask:-webkit-gradient(radial, 45 25, "+r+", 45 25, "+(r+15)+", from(rgb(0, 0, 0)), color-stop(0.5, rgba(0, 0, 0, 0.2)), to(rgb(0, 0, 0)));")
			};
		},5);
	}
},function(){
	return;
});

</script> 
</body>
</html>