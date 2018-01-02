<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>文件列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
</head>
<body>
		<!-- 搜索 strat -->
		<div class="serch-box" id="table-bar">
			<form id="access-form">
				<ul>
					<li>
			            <label>选择系统：</label>
			            <select class="easyui-combobox"  name="sid" style="width:200px; height: 30px">
			              <option value="">选择系统</option>
			              <c:forEach items="${systems }" var="s">
			                <option value="${s.id }">${s.name }</option>
			              </c:forEach>
			            </select>
			            <div></div>
			          </li>
			          <li>
			            <label>选择分类：</label>
			            <select class="easyui-combobox"  name="typeId" style="width:200px; height: 30px">
			              <option value="">选择分类</option>
			              <c:forEach items="${types }" var="t">
			                <option value="${t.id }">${t.name }</option>
			              </c:forEach>
			            </select>
			            <div></div>
			          </li>
			          <li>
			            <label>选择类型：</label>
			            <select class="easyui-combobox"  name="mimeType" style="width:200px; height: 30px">
			              <option value="">选择类型</option>
			              <c:forEach items="${mimeTypes }" var="mt">
			                <option value="${mt.code }">${mt.codedesc }</option>
			              </c:forEach>
			            </select>
			            <div></div>
		            </li>
					<li><label>操作时间：</label><input class="easyui-datetimebox" id="beginDate" name="beginDate" style="height:30px;line-height:30px" >&nbsp; - &nbsp;<input class="easyui-datetimebox" id="endDate" name="endDate" style="height:30px;line-height:30px"></li>
					<li><a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-search" id="file-search">搜索</a></li>
				</ul>
			</form>
			<!-- 操作栏 start -->
			<%--<div>--%>
				 <%--<a href="javascript:void(0)" class="easyui-linkbutton data-author" data-code="ADD" iconCls="icon-add" plain="true" onclick="upload_main()">上传文件</a>  --%>
			<%--</div>--%>
			<!-- 操作栏 end -->
		</div>
		<!-- 搜索 end -->
		<!-- 列表 start -->
		<input type="hidden" id="method" value="${method }">
		<table id="file-datagrid" >
			<thead>
				<tr>
					<th data-options="field:'id'" checkbox="true" width="30">文件ID</th>
					<th data-options="field:'sid'" width="50">系统编号</th>
					<th data-options="field:'typeId'" width="100">分类编号</th>
					<th data-options="field:'fileName'" width="80">文件名称</th>
					<th data-options="field:'mimeType'" width="100">文件类型</th>
					<th data-options="field:'fileSize'" width="100">文件大小</th>
					<th data-options="field:'aa02'" width="100">创建时间</th>
					<th data-options="field:'_operate'" formatter="fileformat" width="50">操作</th>
				</tr>
			</thead>
		</table>
		<!-- 列表 end -->
    <%@ include file="/WEB-INF/commons/include.footer.jsp" %>
    <script>
    $(function(){
    	var prarams = {};
    	initDataGrid(prarams);
    	$('#file-search').click(function(){
   			prarams.sid=$('[name="sid"]').val();
   			prarams.typeId=$('[name="typeId"]').val();
   			prarams.mimeType=$('[name="mimeType"]').val();
   			prarams.beginTime=$.trim($("#beginDate").datebox('getValue'));
   			prarams.endTime=$.trim($("#endDate").datebox('getValue'));
   			initDataGrid(prarams);
    	});
    });
    function fileformat(value,row,index) {
    	var btns = '<div class="handle-td"><a class="updateBtns data-author" data-code="EDITSTATE"></a>&nbsp;&nbsp</div>';
   		return btns;
    }
    function initDataGrid(params){
    	var select = true;
    	if($("#method").val() == 2){
    		select = false;
    	}
    	var path = rootPath+"file/querList.do";
    	$("#file-datagrid").mydatagrid({
    		url:path,
    		toolbar:'#table-bar',
    		singleSelect:select,//如果需要一次选择多个文件请将该属性设置成false即可
    		queryParam:params,
    	});
    }
    </script>
</body>
</html>
