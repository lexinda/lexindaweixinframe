$(document).ready(function(){
	var _form =$("input");
	var _vad = {};
	$('[data-action="submit"]').on("click",function(){
		var _ipts=_form.find("[data-rel]");
		_ipts.each(function(){
			$(this).trigger("blur");
			var _pass=_vad.data("pass");
			if(_pass!=true){
				return false
			}
		})
		var _pass=_form.data("pass");
		if(_pass==true){
			_form.submit();
		}
	})
	_form.each(function(){
		var _ipts=$(this).find("[data-rel]");
		_ipts.each(function(){
			var $this=$(this);
			$this.blur(function(){
				$(this).removeClass("er-ipt");
				$("#tiptip_holder").remove();
				_form.data("pass",true);
				var _$this=$(this);
				var _type=_$this.data("rel");
				var _val=$.trim(_$this.val());
				//dovalidate
			}).focus(function(){
				$(this).removeClass("er-ipt");
				$("#tiptip_holder").remove();
			})
		})
	})
}).on("click",function(e){
	if($(e.target).parents("form").size()<=0){
		$("[data-rel]").removeClass("er-ipt");
		$("#tiptip_holder").remove();
	}
})

function _validateData(_type,_val) {
	switch(_type){
		case 'notnull':
			if(_val==""){
				_$this.tipTip({content:"不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'frname':
			if(_val==""){
				_$this.tipTip({content:"Friend Name不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'email':
			if(_val==""){
				_$this.tipTip({content:"邮箱不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'email':
			if(_val==""){
				_$this.tipTip({content:"邮箱不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'password':
			if(_val==""){
				_$this.tipTip({content:"密码不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'repeatpwd':
			if(_val==""){
				_$this.tipTip({content:"确认密码不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'phone':
			if(_val==""){
				_$this.tipTip({content:"电话不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'vcode':
			if(_val==""){
				_$this.tipTip({content:"验证码不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
		case 'answer':
			if(_val==""){
				_$this.tipTip({content:"答案不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_form.data("pass",false);
				return false;
			}
			break;
	}
}