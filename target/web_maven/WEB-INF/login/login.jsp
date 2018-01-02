<%@ page language="java"  pageEncoding="UTF-8"%>
<%
    response.setHeader("Access-Control-Allow-Origin","*");
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"	+ request.getServerName() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="/depth/images/admin/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/depth/images/admin/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/depth/css/admin/amazeui.min.css" />
    <link rel="stylesheet" href="/depth/css/admin/admin.css">
    <link rel="stylesheet" href="/depth/css/admin/app.css">
</head>

<body data-type="login">

<div class="am-g myapp-login">
    <div class="myapp-login-logo-block  tpl-login-max">
        <div class="myapp-login-logo-text">
            <div class="myapp-login-logo-text">
                灵感<span> 登录</span> <i class="am-icon-skyatlas"></i>

            </div>
        </div>

        <div class="login-font">
             <span> 没有账号？</span>去 <a href="register.to"><i>注册 </i></a>
        </div>
        <div class="am-u-sm-10 login-am-center">
            <form class="am-form">
                <fieldset>
                    <div class="am-form-group">
                        <input type="text" class="" id="doc-ipt-phone-1" placeholder="请输入管理员账号" name="phone">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入管理员密码" name="passWord">
                    </div>
                    <p><button type="button" class="am-btn am-btn-default" onclick="login()">登录</button></p>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script src="/depth/js/admin/jquery.min.js"></script>
<script src="/depth/plug/layer/layer.js"></script>
<script src="/depth/js/admin/amazeui.min.js"></script>
<script src="/depth/js/admin/app.js"></script>
<script type="text/javascript">
    var rootPath = "<%=basePath%>";
</script>
<script>
$(function(){

});
function login(){
    var phone = $("input[name=phone]").val();
    if(null == phone.trim() || phone.trim() == ""){
        layer.msg("请输入手机号!");
        return;
    }else if(!(/^1[34578]\d{9}$/.test(phone.trim()))){
        layer.msg("手机号格式不正确！请从新输入");
        return;
    }else{
        var passWord = $("input[name=passWord]").val();
        if(null == passWord.trim() || passWord.trim() == ""){
            layer.msg("请输入登录密码!");
            return;
        }else if(passWord.trim().length < 6 || passWord.trim().length > 20 ){
            layer.msg("登录密码长度不符!请确认并重新输入");
            return;
        }else{
            var parms = {
                phone: phone,
                passWord: passWord
            };
            $.ajax({
                type: 'post',
                url: rootPath + "admin/validateLogin.to",
                data:parms,
                success: function (data) {
                    if (data.status) {
                        window.location.href = rootPath+"admin/index.do";
                    } else {
                        layer.msg(data.info);
                        $("input[name=passWord]").val('');
                        return;
                    }
                },
                error: function (data){
                    layer.open({
                        type: 0,
                        content: '登录失败，请稍后再试！',
                        title: false
                    });
                }
            });
        }
    }
}
</script>
</body>

</html>
