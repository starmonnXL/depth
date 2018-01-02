<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jquery插件 -->
<script type="text/javascript" src="<%=basePath%>resources/admin/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/admin/js/jquery/jquery-expand.js"></script>
<!-- easyui插件  -->
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/easyui-1.3.5/jquery.easyui.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>--%>
<!-- zTree插件 -->
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/admin/js/zTree/jquery.ztree.exedit-3.5.js" type="text/javascript"></script>
<!-- 自定义插件  主要是消息弹框处理 -->
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/ui/ui.taskBar.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/ui/ui.core.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/ui/ui.msg.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>resources/admin/js/ui/easyui.validate.js"></script>--%>
<script type="text/javascript">
	var rootPath = "<%=basePath%>";
	var hrightsCodes = eval('${hrightsCodes}');
</script>
<!-- 权限验证 -->
<%--<script src="<%=basePath%>resources/admin/js/cms/datagrid/jquery.datagrid.extend.js"></script>--%>
<%--<script src="<%=basePath%>resources/js/global/define_author.js"></script>--%>