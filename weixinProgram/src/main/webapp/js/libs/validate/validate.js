/**
 * 系统校验js,校验input框,input框的格式如下：
 * <input type="text" name="name" id="name" maxlength="30" data-rel="notnull,int" value = ""/>
 * data-rel里面是需要校验的规则,多个规则用逗号隔开
 * 
 * 按钮加上data-action="submit"，点击按钮时会触发校验
 */
$(document).ready(function(){
	var _inps =$("input[data-rel]");
	var _vad = {};
	if (_inps) {
			_inps.each(function(){
				var $this=$(this);
				$this.blur(function(){
					_validateInput(this,_vad);
				}).focus(function(){
					$(this).removeClass("er-ipt");
					$("#tiptip_holder").remove();
				})
			});
			$('[data-action="submit"]').on("click",function(){
				_inps.each(function(){
					_validateInput(this,_vad);
					var _pass=_vad.pass;
					if(_pass!=true){
						return false;
					}
				});
				var _pass=_vad.pass;
				if(_pass!=true){
					return false;
				}
			});
	}
})

function _validateInput(_inputObj,_vad) {
	$(_inputObj).removeClass("er-ipt");
	$("#tiptip_holder").remove();
	_vad.pass = true;
	var _$this=$(_inputObj);
	var _type=_$this.data("rel");
	var _val=$.trim(_$this.val());
	var _types = _type.split(",");
	for (var i = 0 ; i < _types.length; i++) {
		if(!_validateData(_$this,_types[i],_val,_vad)){
			break;
		}
	}
}

function _validateData(_$this,_type,_val,_vad) {
	switch(_type){
		case 'notnull':
			if(_val==""){
				_$this.tipTip({content:"不能为空!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_vad.pass = false;
				return false;
			}
			break;
		case 'int':
			if(!isInt(_val)){
				_$this.tipTip({content:"请输入整数!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_vad.pass = false;
				return false;
			}
			break;
		case 'float':
			if(!isFloat(_val)){
				_$this.tipTip({content:"请输入小数!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_vad.pass = false;
				return false;
			}
			break;
		case 'email':
			if(!isEmail(_val)){
				_$this.tipTip({content:"请输入有效的邮箱!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_vad.pass = false;
				return false;
			}
			break;
		case 'password':
			if(!isPassword(_val)){
				_$this.tipTip({content:"请输入6-16位，包含数字和字母的密码!",keepAlive:true}).trigger("mouseenter").addClass("er-ipt");
				_vad.pass = false;
				return false;
			}
			break;
	}
	return true;
}

/**
 *"^\\d+$"　　//非负整数（正整数 + 0） 
 *"^[0-9]*[1-9][0-9]*$"　　//正整数 
 *"^((-\\d+)|(0+))$"　　//非正整数（负整数 + 0） 
 *"^-[0-9]*[1-9][0-9]*$"　　//负整数 
 *"^-?\\d+$"　　　　//整数 
 *"^\\d+(\\.\\d+)?$"　　//非负浮点数（正浮点数 + 0） 
 *"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"　　//正浮点数 
 *"^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"　　//非正浮点数（负浮点数 + 0） 
 *"^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"　　//负浮点数 
 *"^(-?\\d+)(\\.\\d+)?$"　　//浮点数
 */

/**
 * 在这里校验的时候，如果为空，则都通过
 */
function isInt(_val) {
	if(_val=="") return true;
	var re = new RegExp("^\\d+$");
	return re.test(_val);
}

function isFloat(_val) {
	if(_val=="") return true;
	var re = new RegExp("^\\d+(\\.\\d+)?$");
	 return re.test(_val);
}

function isEmail(_val) {
	if(_val=="") return true;
	var re=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return re.test(_val);
}

function isPassword(_val) {
	if(_val=="") return true;
	if (_val.length < 6 || _val.length>16 ) {
		return false;
	}
	var reg=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
	return reg.test(_val);
}
