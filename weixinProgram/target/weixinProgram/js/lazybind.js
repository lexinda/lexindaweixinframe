var curPageNumber = 2;  //加载第几屏  
var lazyData;           //延迟加载数据，数组格式，例：[{id:'1',topic:'第[1]期', startTime:'2011-07-21 11:40:30'}, {id:'2',topic:'第[2]期', startTime:'2011-07-21 12:00:00'}]  
var params;             //AJAX异步延迟加载的参数  
var isLoading=false;    //正在加载的标识  
var isFinalPage=false;  //已经是最后一屏  
var footer = "<div id=\"footer\" data-role=\"footer\"><center><img src='" + _CONTEXTPATH + "/images/loader.gif' width='18' height='18'><p5>正在加载，请稍候...<p5></center></div>";  
  
    
function handleFooter(isRemove){  
    if(isRemove){  
        document.getElementById("footer").parentNode.removeChild(document.getElementById("footer"));  
    }else{  
        $('#mainPage').append(footer);   
    }  
}  
   
   
  
/** 手指触点DOM元素的序号 */  
function getScrollPosition(event){  
    var target = $(event.target);  
    while(target.attr("id")==undefined){  
        target = target.parent();  
    }   
    /*var targetId = target.attr('id');   
    var position="0";   
    var targetTag = "li";  
    if(targetId!=undefined && targetId.indexOf(targetTag)==0){  
        position = targetId.substring(targetTag.length);  
    }  */
    //var position = $("ul li").length//页面只有一个ul
    var position = $("#list li").length
    return position;  
}  
  
function calPosition(order){  
    return (curPageNumber-1) * pageSize + order;  
}  
  
//参数检查  
function checkParam(args){  
    if(args==undefined || args.url==undefined || args.linkHrefFormat==undefined || args.linkTextFormat==undefined){  
        return false;  
    }  
    return true;  
}  
  
/** 是否为最后一个版面*/  
function isLastScreen(position){  
    //最后一屏序号的下限与上限  
    var minPos = (curPageNumber-2)*pageSize;  
    var maxPos = (curPageNumber-1)*pageSize;
    if(position>=minPos && position<=maxPos){  
        //alert("curPageNumber="+curPageNumber+";position="+position + ";minPos="+minPos + ";maxPos="+maxPos);  
        return true;  
    }  
    //calFooter();  
    return false;  
}  
  
  
/** 延迟加载列表版面 */  
function lazyBind(event,args){  
    //正在加载或已经是最后一屏，则退出  
    if(isLoading || isFinalPage){return;}  
    if(!checkParam(args)){return;}  
  
    //手指触点在最后一屏才触发异步加载  
    var position = getScrollPosition(event); 
    if(!isLastScreen(parseInt(position))){return;}  
    isLoading = true;  
  
    //显示正在加载的提示  
    handleFooter(false);  
    params = {pageSize:pageSize, pageNum:curPageNumber};  
  
    $.ajax({  
        url: args.url,  
        data: params,  
        async: true,  
        dataType: "json",  
        success: function(data){  
            lazyData=data.dataList;  
            if(lazyData.length>0){  
                //复制样式  
                var liClass     = $('li:first').attr('class');                     
                var liTheme     = $('li:first').attr('data-theme');                
                var divClass    = $('li:first div:first').attr('class');           
                var divClass2   = $('li:first div:last').attr('class');            
                var linkClass   = $('li:first a:first').attr('class');             
                var spanClass   = $('li:first span:first').attr('class');          
                  
                //处理列表的link和显示文本  
                var linkHref = args.linkHrefFormat;  
                var linkText = args.linkTextFormat;  
                var regExp = /\$\{\w+\}/g;  //正则表达式，匹配所有"${字段名}"的内容  
                var linkHrefArray = linkHref.match(regExp);  
                var linkTextArray = linkText.match(regExp);  
              
                  
                //数据绑定  
                for(var i=0; i<lazyData.length; i++){  
                    var item = lazyData[i];   
  
                    /*//超链接  
                    var href = linkHref;  
                    for(var j=0; j<linkHrefArray.length; j++){  
                        var field = linkHrefArray[j];   //field 类似:${id}  
                        href = href.replace(field,eval("item."+field.substr(2,field.length-3)));  
                    }  
                          
                    //文本  
                    var text = linkText;  
                    for(var j=0; j<linkTextArray.length; j++){  
                        var field = linkTextArray[j];  
                        text = text.replace(field,eval("item."+field.substr(2,field.length-3)));  
                    }  */
                    var href = "/siteDetail?id="+item.id;
                    var text = item.siteInfoName;
                      
                    //列表item  
                    var li = "<LI id=\""+calPosition(i)+"\" class=\""+liClass+"\" data-theme=\""+liTheme+"\">"+  
                             "      <A class=\""+linkClass+"\" href=\""+href+"\" data-ajax=\"false\">"+
                             "<img src="+item.siteInfoImg+" height=\"75px\" style=\"margin-top:2.5px\"> "+
                     		"<h2>"+item.siteInfoName+"</h2>"+
    						"<p>"+item.siteInfoSax+"</p>"+
                             "</A>"+  
                             "</LI>";  
                    $('#list').append(li);  
                }//end for  
                curPageNumber+=1;  
            }//end if  
  
            if(lazyData.length<pageSize){  
                isFinalPage = true;  
            }  
  
            isLoading = false;  
            handleFooter(true);  
        },  
        //执行ajax时出错  
        error: function(XMLHttpRequest, textStatus, errorThrown) {  
            alert(textStatus);  
        }  
    });  
}  