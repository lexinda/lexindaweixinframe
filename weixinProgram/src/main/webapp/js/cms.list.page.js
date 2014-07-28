/**
*列表分页
*/
var _mallGummyPage = function(url,page,pageCount,paras) {
	this.url = url;
	this.paras = paras;
	this.page = page;
	this.pageCount = pageCount;
	this.drawPage();
}

_mallGummyPage.prototype.drawPage = function() {
	var str = "";
	var parastr = "";
	if (this.paras) {
		for (var p in paras ) {
			parastr = parastr + p+"="+paras[p]+"&";
		}
	}
	if (this.pageCount > 1) {
		var prePageIndex = this.page-1;
		if (prePageIndex < 1 ) {
			prePageIndex = 1;
		}
		var nextPageIndex = this.page+1;
		if (nextPageIndex > this.pageCount) {
			nextPageIndex = this.pageCount;
		}
		str = str + "<div class=\"pagination pagination-small\">" +
		                    "<ul class=\"page-operation\">"  +
		                       "<li><a href=\""+this.url+"?page="+prePageIndex+"&"+parastr+"\" >Prev</a></li>" ;
								for (var i = 1 ; i <= this.pageCount; i ++  ) {
									if (i == this.page) {
										str = str +"<li class=\"active\">";
									}else {
										str = str +"<li>";
									}
									str = str + "<a href=\""+this.url+"?page="+i+"&"+parastr+"\">"+i+"</a></li>";
								}
				str = str +"<li><a href=\""+this.url+"?page="+nextPageIndex+"&"+parastr+"\">Next</a></li></ul></div>";
		document.write(str);
	}
}

