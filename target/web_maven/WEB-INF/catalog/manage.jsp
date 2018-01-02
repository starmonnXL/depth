<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/24
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="icon" type="image/png" href="<%=basePath%>/images/admin/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="<%=basePath%>/images/admin/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="<%=basePath%>/css/admin/amazeui.min.css" />
    <link rel="stylesheet" href="<%=basePath%>/css/admin/admin.css">
    <link rel="stylesheet" href="<%=basePath%>/css/admin/app.css">
    <link href="<%=basePath%>/css/blog/font-awesome.min.css" rel="stylesheet">
    <!-- zTree插件 -->
    <link rel="stylesheet" href="<%=basePath%>resources/admin/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <link href="<%=basePath%>resources/css/global.css" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>/js/admin/echarts.min.js"></script>
</head>

<body data-type="generalComponents">
<%@ include file="/WEB-INF/commons/head.jsp" %>
<div class="tpl-page-container tpl-page-header-fixed">
    <%@ include file="/WEB-INF/commons/menu.jsp" %>
    <div class="tpl-content-wrapper">
        <div>
            <div class="tpl-content-page-title">
                Amaze UI 首页组件
            </div>
            <ol class="am-breadcrumb">
                <li><a href="#" class="am-icon-home">首页</a></li>
                <li><a href="#">分类</a></li>
                <li class="am-active">内容</li>
            </ol>
            <div class="tpl-content-scope">
                <div class="note note-info">
                    <h3>Amaze UI 为移动而生
                        <span class="close" data-close="note"></span>
                    </h3>
                    <p> Amaze UI 含近 20 个 CSS 组件、20 余 JS 组件，更有多个包含不同主题的 Web 组件，可快速构建界面出色、体验优秀的跨屏页面，大幅提升开发效率。</p>
                    <p><span class="label label-danger">提示:</span> Amaze UI 关注中文排版，根据用户代理调整字体，实现更好的中文排版效果。
                    </p>
                </div>
            </div>
        </div>
        <div>
            <div data-options="region:'west',title:'&nbsp;&nbsp;栏目管理',split:true,iconCls:'icon-home'" style="width:260px;" id="catalogRe">
                <div title="About" style="padding:10px;float: left;">
                    <ul id="handle_menu" class="ztree" style="padding: 27px;"></ul>
                </div>
            </div>

            <div class="tpl-portlet-components" style="background-color: #f5f8fd;padding: 10px;margin-top: 29px;border-radius: 6px;">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 新增栏目
                    </div>
                    <div class="tpl-portlet-input tpl-fz-ml">
                        <div class="portlet-input input-small input-inline">
                            <div class="input-icon right">
                                <i class="am-icon-search"></i>
                                <input type="text" class="form-control form-control-solid" placeholder="搜索..."> </div>
                        </div>
                    </div>


                </div>
                <div class="tpl-block ">

                    <div class="am-g tpl-amazeui-form">


                        <div class="am-u-sm-12 am-u-md-9">
                            <form class="am-form am-form-horizontal">
                                <div class="am-form-group">
                                    <label for="typeOne" class="am-u-sm-3 am-form-label">栏目类型</label>
                                    <div class="am-u-sm-9">
                                        <div class="am-form-group">
                                            <select data-am-selected="{btnSize: 'sm'}" id="typeOne">
                                                <option value="option2">网站栏目</option>
                                                <option value="option3">单页栏目</option>
                                            </select>
                                        </div>
                                        <%--<small>输入你的名字，让我们记住你。</small>--%>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="typeTwo" class="am-u-sm-3 am-form-label">栏目位置</label>
                                    <div class="am-u-sm-9">
                                        <div class="am-form-group">
                                            <select data-am-selected="{btnSize: 'sm'}" id="typeTwo">
                                                <option value="option2">顶部菜单</option>
                                                <option value="option3">滑动菜单</option>
                                                <option value="option3">右边静态菜单</option>
                                            </select>
                                        </div>
                                        <%--<small>输入你的名字，让我们记住你。</small>--%>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="name" class="am-u-sm-3 am-form-label">栏目名称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="name" placeholder="栏目名称">
                                        <%--<small>邮箱你懂得...</small>--%>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="abbreviation" class="am-u-sm-3 am-form-label">栏目简称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="abbreviation" placeholder="栏目简称">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="dir" class="am-u-sm-3 am-form-label">栏目目录</label>
                                    <div class="am-u-sm-9">
                                        <input type="text"  id="dir" placeholder="栏目目录，主要供前端url展示">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="urlName" class="am-u-sm-3 am-form-label">单页链接名称</label>
                                    <div class="am-u-sm-9">
                                        <input type="text"  id="urlName" placeholder="如果此目录是单页目录，则必填单页链接名称">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="sort" class="am-u-sm-3 am-form-label">排序</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" pattern="[0-9]*" id="sort" placeholder="排序">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="jumpUrl" class="am-u-sm-3 am-form-label">跳转链接</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="jumpUrl" placeholder="跳转外链接">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="pageTitle" class="am-u-sm-3 am-form-label">页面标题</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="pageTitle" placeholder="页面标题">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="pageKeywords" class="am-u-sm-3 am-form-label">页面关键字</label>
                                    <div class="am-u-sm-9">
                                        <textarea class="" rows="5" id="pageKeywords" placeholder="页面关键字" maxlength="100"
                                                  onchange="this.value=this.value.substring(0, 100)"
                                                  onkeydown="this.value=this.value.substring(0, 100)"
                                                  onkeyup="this.value=this.value.substring(0, 100)" ></textarea>
                                        <small>100字以内</small>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="pageDescription" class="am-u-sm-3 am-form-label">页面描述</label>
                                    <div class="am-u-sm-9">
                                        <textarea class="" rows="5" id="pageDescription" placeholder="页面描述" maxlength="230"
                                                  onchange="this.value=this.value.substring(0, 230)"
                                                  onkeydown="this.value=this.value.substring(0, 230)"
                                                  onkeyup="this.value=this.value.substring(0, 230)" ></textarea>
                                        <small>230字以内</small>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary">保存修改</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/admin/jquery.min.js"></script>
<!-- zTree插件 -->
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.exedit-3.5.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/js/admin/amazeui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/admin/iscroll.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/admin/app.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/admin/manage.js"></script>
<script>
    var rootPath = "<%=basePath%>";

</script>
</body>

</html>
