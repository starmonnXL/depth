
//页面加载完成之后执行  
$(function() {
	if($.browser.msie<8){
		Msg.alert('提示','抱歉，您使用浏览器版本太低，本系统不支持IE8(内核)以下浏览器。','error');
	}
	new Clock().display(document.getElementById("clock"));//设置时钟
});
//退出系统
function goOutSystem(){
	$.messager.confirm('提示',"确定退出系统吗?", function(r){
           if (r){
				window.location.href=appWebRoot+"/sys/logout.do";
		   }
     });
}

