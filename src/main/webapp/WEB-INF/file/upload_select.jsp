<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件件选择列表</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>

</head>
<body>
	<div class="easyui-tabs" data-options="fit:true" id="file-select-tab">
		<div title="选择文件" style="overflow:hidden">
			<iframe scrolling="yes" frameborder="0" id="fileListFrame" name="fileListFrame" src="<%=basePath%>file/${method}/uploadList.do" style="width:100%;height:100%;"></iframe>
		</div>
		<div title="添加新文件"  style="overflow:hidden">
			<iframe scrolling="yes" frameborder="0" id="addFileFrame" src="<%=basePath%>file/${method}/uploadAdd.do" style="width:100%;height:100%;"></iframe>
		</div>
	</div>
	<%@ include file="/WEB-INF/commons/include.footer.jsp" %>
</body>
</html>