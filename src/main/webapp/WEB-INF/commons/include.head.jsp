<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<link rel="stylesheet" href="<%=basePath%>resources/admin/js/bootstrap/css/bootstrap.css"  type="text/css">
<link rel="stylesheet" href="<%=basePath%>resources/admin/css/main.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>resources/admin/js/ui/css/ui.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>resources/admin/js/ui/css/icon.css" type="text/css"/>
<link href="<%=basePath%>resources/admin/images/favicon.png" rel="SHORTCUT ICON" />
<!-- jquery插件 -->
<script type="text/javascript" src="<%=basePath%>resources/admin/js/jquery/jquery-1.10.2.js"></script>

<!-- easyui插件  -->
<link href="<%=basePath%>resources/easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" media="screen"/>
<link id="easyuiTheme"
      href="<%=basePath%>resources/admin/js/easyui-1.3.5/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css"
      rel="stylesheet" type="text/css" media="screen"/>
<!-- zTree插件 -->
<link rel="stylesheet" href="<%=basePath%>resources/admin/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css"/>
<link href="<%=basePath%>resources/css/global.css" rel="stylesheet" type="text/css"/>
