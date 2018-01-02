var rootpath = $("#path").val();
document.onkeydown = function(e){
	if($(".bac").length==0)
	{
		if(!e) e = window.event;
		if((e.keyCode || e.which) == 13){
			var obtnLogin=document.getElementById("submit_btn")
			obtnLogin.focus();
		}
	}
}

$(function(){
	changeCode();
	$('#submit_btn').on('click',function(){
		show_loading();
		if($('#username').val() == ''){
			show_err_msg('用户名还没填呢！');
			$('#username').focus();
		}else if($('#password').val() == ''){
			show_err_msg('密码还没填呢！');
			$('#password').focus();
		} else if ($("#code").val() == ''){
			show_err_msg('验证码还没填写呢！');
			$('#code').focus();
		} else{
			var url = rootpath+"/sys/login.do";
			var params = $("#login_form").serialize();
			$.post(url,params,function(data){
				if(data.status){
					window.location.href=rootpath+"/"+data.url;
				} else {
					show_err_msg(data.info);
				}
			},'json');
		}
	});
});

function genTimestamp() {
	var time = new Date();
	return time.getTime();
}

function changeCode() {
	var path = rootpath+"/code/getcode.do";
	$("#codeImg").attr("src", rootpath+"/code/getcode.do?t="+genTimestamp());
}