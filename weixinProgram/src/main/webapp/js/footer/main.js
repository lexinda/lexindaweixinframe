
$(document).ready(function(){

//首先将#backTop隐藏
 $("#backTop").hide();
 
//当滚动条的位置处于距顶部400像素以下时，跳转链接出现，否则消失
$(function () {
$(window).scroll(function(){
if ($(window).scrollTop()>400){
$("#backTop").fadeIn(500);
}
else
{
$("#backTop").fadeOut(500);
}
});

//当点击跳转链接后，回到页面顶部位置

$("#backTop").click(function(){
$('body,html').animate({scrollTop:0},500);
return false;
});
});
});

$(document).ready(function(){
	 
	$('.Case .bd ul li').mouseover(function(){
		var b=$(this).find('.txtinfo').outerHeight(true);
		$(this).find('.txtinfo').stop().animate({"bottom": 0}, 100); 
	})
	$('.Case .bd ul li').mouseout(function(){
			var b=$(this).find('.txtinfo').outerHeight(true);
		$(this).find('.txtinfo').stop().animate({"bottom":-36}, 100); 
	})
		var b=$('.txtinfo').length;
		for(var i=0;i<b;i++){
			var b=$(".txtinfo").eq(i).outerHeight(true);
				$(".txtinfo").eq(i).stop().animate({"bottom":-36}, 100); 
			}
});

$(document).ready(function(){
	 
	$('.pageCase .bd ul li').mouseover(function(){
		var b=$(this).find('.nameInfo').outerHeight(true);
		$(this).find('.nameInfo').stop().animate({"bottom": 0}, 200); 
	})
	$('.pageCase .bd ul li').mouseout(function(){
			var b=$(this).find('.nameInfo').outerHeight(true);
		$(this).find('.nameInfo').stop().animate({"bottom": -30}, 200); 
	})
		 
});
$(document).ready(function(){
	$(".submit").click(function(){
	
		function isNull( str ){
			if ( str == "" ) return true;
			var regu = "^[ ]+$";
			var re = new RegExp(regu);
			return re.test(str);
			} 
		function checkPhone( strPhone ) {
		var phoneRegWithArea = /^[0][1-9]{2,3}-[0-9]{5,10}$/;
		var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/;
		var prompt = "您输入的电话号码不正确!"
		if( strPhone.length > 9 ) {
		if( phoneRegWithArea.test(strPhone) ){
		return true;
		}else{
		alert( prompt );
		return false;
		}
		}else{
		if( phoneRegNoArea.test( strPhone ) ){
		return true;
		}else{
		
		return false;
		}

		 
		}
		} 
		function checkEmail(strEmail) {
			//var emailReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
			var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
			if( emailReg.test(strEmail) ){
			return true;
			}else{
			
			return false;
			}
			} 
		var aa=$("#name").val();
		var bb=$("#name").attr('oldValue');
		var cc=$("#mailundis").val();
		var dd=$("#tel").val();
		
	if(aa==bb){
	alert('称呼不能为空');return false;
	}
	if(!checkEmail(cc)){
	alert('邮箱格式不正确');return false;
	}
	if(!checkPhone( dd )){
	alert('电话格式不正确');return false;
	}
		
		})
		
});	
$(document).ready(function(){
	 // 固定层
function buffer(a, b, c) {
    var d;
    return function() {
        if (d) return;
        d = setTimeout(function() {
            a.call(this),
            d = undefined
        },
        b)
    }
} (function() {
    function e() {
        var d = document.body.scrollTop || document.documentElement.scrollTop;
        d > b ? (a.className = "pageNav fixtop", c && (a.style.top = d - b + "px")) : a.className = "pageNav"
    }
    var a = document.getElementById("fixNav");
    if (a == undefined) return ! 1;
    var b = 0,
    c, d = a;
    while (d) b += d.offsetTop,
    d = d.offsetParent;
    c = window.ActiveXObject && !window.XMLHttpRequest;
    if (!c || !0) window.onscroll = buffer(e, 1, this)
})(); 
});

$(document).ready(function(){
	 // 固定层 tel400
function buffer(a, b, c) {
    var d;
    return function() {
        if (d) return;
        d = setTimeout(function() {
            a.call(this),
            d = undefined
        },
        b)
    }
} (function() {
    function e() {
        var d = document.body.scrollTop || document.documentElement.scrollTop;
        d > b ? (a.className = "tel400 fixtop400", c && (a.style.top = d - b + "px")) : a.className = "tel400"
    }
    var a = document.getElementById("fixNav400");
    if (a == undefined) return ! 1;
    var b = 0,
    c, d = a;
    while (d) b += d.offsetTop,
    d = d.offsetParent;
    c = window.ActiveXObject && !window.XMLHttpRequest;
    if (!c || !0) window.onscroll = buffer(e, 1, this)
})(); 
});
