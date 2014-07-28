function refreshData (freshUrl,paras) {
	this.url = freshUrl;
	this.paras = paras;
}

/***
 * 列表页更新操作后跳转刷新该列表页
 * 情况一：该操作不需要打开新的页面，操作完直接刷新列表页
 *     动态生成一个form,根据传递的data来生成hidden,然后提交from,刷新
 *  
 * 情况二：该操作需要打开新的页面，页面提交成功后跳转到列表页
 *     将参数存储到cookie中，然后在新页面中需要调用对应的方法生成form,提交
 */
function listUpdate(isNewPage,formId, toUrl, rData) {
	if (isNewPage) {
		$.cookie(formId+"_paras", JSON.stringify(rData.paras));
		$.cookie(formId+"_refreshUrl", rData.url);
		toNewPage(toUrl);
	}else {
		updateContentByURL(toUrl,formId,rData);
	}
}

function updateContentByURL(url,formId,rData){
	$.ajax({
		url:url,
		type:"GET",
		cache:false,
		success:function(rsp){
			freshForm(formId,rData.url,rData.paras,rsp);
		}			
	})
}

function toNewPage(url) {
	window.location.href=url;
}

function freshForm(formId,refreshUrl,paras,rsp) {
	if (refreshUrl) {
		if ($("#"+formId+"_refreshForm")) {
			$("body").remove("#"+formId+"_refreshForm");
		}
		var str = "<div style=\"display:none\"><form id=\""+formId+"_refreshForm\" action=\""+refreshUrl+"\" method=\"get\">";
		if (paras) {
			for ( var para in paras) {
				str = str + "<input type=\"hidden\" name=\""+para+"\" value=\""+paras[para]+"\"  />";
			}
		}
		str = str + "</form></div>";
		$("body").append(str);
		if (rsp) {
			_alertDialog(rsp.msg,"错误信息",_freshForm,[formId]);
		}else {
			_freshForm(formId);
		}
	}
}

function _alertDialog(msg,title,callBack,paras) {
	template='<div id="alertTmp" class="modal hide fade">'+
	 '<div class="modal-header">'+
	 '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
	 '<h3>'+title+'</h3>'+
	 '</div>'+
	 '<div class="modal-body">'+
	 '<p>'+msg+'</p>'+
	 '</div>'+
	 '<div class="modal-footer">'+
	 '<button class="btn red-btn" data-dismiss="modal" aria-hidden="true">关闭</button>'+
	 '</div></div>';
	$(document.body).prepend(template);
	$("#alertTmp").modal('show');
	$('#alertTmp').on('hidden', function () {
		$('#alertTmp').remove();
		callBack.apply(this,paras);
	})
}

function _freshForm(formId) {
	$("#"+formId+"_refreshForm").submit();
}

function freshFormByCookie(formId) {
	var datas = JSON.parse($.cookie(formId+"_paras"));
	var url = $.cookie(formId+"_refreshUrl"); 
	freshForm(formId,url,datas);
}

