/*首页广告效果*/
$(function(){
     var len  = $(".adver_btn > li").length;
	 var index = 0;
	 var adTimer;
	 $(".adver_btn li").mouseover(function(){
		index  =   $(".adver_btn li").index(this);
		showImg(index);
	 }).eq(0).mouseover();	
	 
	 //滑入 停止动画，滑出开始动画.
	 $('.adver_img').hover(function(){
			 clearInterval(adTimer);
		 },function(){
			 adTimer = setInterval(function(){
			    showImg(index)
				index++;
				if(index==len){index=0;}
			  } , 3000);
	 }).trigger("mouseleave");
	 
	 $(".pic1").mouseenter(function(){
		 $(".arrow").animate({left: "2%"}, 200);	
		 $(".cont_1").css({display: "block"});
		 $(".cont_2").css({display: "none"});
		 $(".cont_3").css({display: "none"});
		 });
		 $(".pic2").mouseenter(function(){
		 $(".arrow").animate({left: "33%"}, 200);	
		 $(".cont_1").css({display: "none"});
		 $(".cont_2").css({display: "block"});
		 $(".cont_3").css({display: "none"});
		 });
		 $(".pic3").mouseenter(function(){
		 $(".arrow").animate({left: "66%"}, 200);	
		 $(".cont_1").css({display: "none"});
		 $(".cont_2").css({display: "none"});
		 $(".cont_3").css({display: "block"});
		 });
})

// 通过控制top ，来显示不同的幻灯片
/*
function showImg(index){
        var adHeight = $(".adver .adver_img").width();
		$(".slider").stop(true,false).animate({left : -adHeight*index},500);
		$(".adver_btn li").removeClass("active")
			.eq(index).addClass("active");
}
*/

function showImg(index){
	    var adWidth = $(".adver_img").width();
		$(".slider").stop(true,false).animate({left : -adWidth*index},1000);
		$(".adver_btn li").removeClass("active")
			.eq(index).addClass("active");
}
	
