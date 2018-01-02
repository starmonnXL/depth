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
                灵感<span> 注册</span> <i class="am-icon-skyatlas"></i>

            </div>
        </div>

        <div class="login-font">
            <span> 我有账号！</span>去 <a href="login.to"><i>登录 </i></a>
        </div>
        <div class="am-u-sm-10 login-am-center">
            <form class="am-form">
                <fieldset>
                    <div class="am-form-group">
                        <input type="text" class="" id="doc-ipt-phone-1" placeholder="请输入手机号">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入密码">
                    </div>
                    <div class="am-form-group">
                        <input type="password" class="" id="doc-ipt-pwd-2" placeholder="请确认密码">
                    </div>
                    <div class="am-form-group">
                        <input type="text" class="" id="doc-ipt-name-1" placeholder="请输入真实姓名，方便管理员审核！">
                    </div>
                    <p><button type="submit" class="am-btn am-btn-default">登录</button></p>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script src="/depth/js/admin/jquery.min.js"></script>
<script src="/depth/js/admin/amazeui.min.js"></script>
<script src="/depth/js/admin/app.js"></script>
<script>

</script>
</body>

</html>
