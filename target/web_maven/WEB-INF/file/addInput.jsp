<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上传文件</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/js/webuploader/webuploader.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/js/webuploader/style.css">
</head>
<body>
	<form id="form">
		<ul class="line-form">
			<li>
	            <label><em>*</em>选择系统：</label>
	            <select class="easyui-combobox"  name="sid" style="width:200px; height: 30px">
	              <option value="">选择系统</option>
	              <c:forEach items="${systems }" var="s">
	                <option value="${s.id }">${s.name }</option>
	              </c:forEach>
	            </select>
	            <div></div>
	          </li>
	          <li>
	            <label><em>*</em>选择分类：</label>
	            <select class="easyui-combobox"  name="typeId" style="width:200px; height: 30px">
	              <option value="">选择分类</option>
	              <c:forEach items="${types }" var="t">
	                <option value="${t.id }">${t.name }</option>
	              </c:forEach>
	            </select>
	            <div></div>
	          </li>
	          <li>
	            <label><em>*</em>选择类型：</label>
	            <select class="easyui-combobox"  name="mimeType" style="width:200px; height: 30px">
	              <option value="">选择类型</option>
	              <c:forEach items="${mimeTypes }" var="mt">
	                <option value="${mt.id }">${mt.name }</option>
	              </c:forEach>
	            </select>
	            <div></div>
          </li>
		</ul>
		<div class="popup-txt">
			<!-- 文件上传 -->
			<div id="wrapper">
				<div id="container">
					<div id="uploader">
						<div class="queueList">
							<div id="dndArea" class="placeholder">
								<div id="filePicker"></div>
								<p>或将文件拖到这里，单次最多可选20张</p>
							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
        <%@ include file="/WEB-INF/commons/include.footer.jsp" %>
		<script type="text/javascript" src="<%=basePath%>resources/js/webuploader/webuploader.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/js/webuploader/upload.js"></script>
</body>
</html>