<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件上传DEMO</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
    <script type="text/javascript" src="<%=basePath%>resources/js/layer/layer.js"></script>
</head>
<body>
	<a href="javascript:void(0)" onclick="uploadDialog(1)">单选择文件</a>
	<a href="javascript:void(0)" onclick="uploadDialog(2)">多选择文件</a>
	<a href="javascript:void(0)" onclick="uploadDialogTest(1)">远程调用</a>
	<input id="testV" type="text">
	<div style="display: none;">
		<div id="fileOperatorBox">
		</div>
		<div id="fileUploadBtn">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="addFileIntro">添加选中文件</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="cancelAddFile">取消选择</a>
		</div>
	</div>
	<%@ include file="/WEB-INF/commons/include.footer.jsp" %>
	<script>
		$(function(){
			$("#addFileIntro").on("click",addFileIntro);
			//取消操作
			$("#cancelAddFile").on("click",cancelAddFile);
		})

		//如果是单个文件上传参数为 1
		//如果是多个文件上传参数为  2
		function uploadDialog(method){
			var url = rootPath+"file/"+method+"/uploadSelect.sec?mimeType=jpeg,gif,jpg,png,bmp,pic,tif";
			//MUI.openDialog("&nbsp;文件选择列表",url,'fileOperatorBox',{width:1000,height:600,modal:true,buttons:'#fileUploadBtn',iconCls:'icon-user'});
			layer.open({
				type:2,
				content:url,
				title:"文件选择列表",
				area:["60%","600px"],
				btn:["添加选中文件","取消选择"],
				yes:function(index){
					addFileIntro(index);
				},
				btn2: function(index){
					layer.close(index);
				}
			})
		}

		function uploadDialogTest(method){
			var url = "http://xiaodingwangplatform.com:8080/file/"+method+"/uploadSelect.sec?mimeType=jpeg,gif,jpg,png,bmp,pic,tif";
			layer.open({
				type:2,
				content:url,
				title:"文件选择列表",
				area:["60%","600px"],
				btn:["添加选中文件","取消选择"],
				yes:function(index,layero){
					//var rows = $("#fileListFrame").contents().$('#file-datagrid').datagrid('getChecked');
					//alert(rows);
					//addFileIntro(index);
				},
				btn2: function(index){
					layer.close(index);
				}
			})
		}

		function addFileIntro(index){
			//获取选中文件的所有行，固定写法
			//rows[i]中包含 id(文件ID)、sid（系统编号）、typeId（文件分类ID）、
			//typeName（分类名称）、fileName（文件名称）、mimeType（文件类型）、
			//mimeTypeName（文件类型名称）、filePath（文件路径）、fileSize（文件大小）属性
			var rows = $("#layui-layer-iframe"+index).contents().find("#fileListFrame")[0].contentWindow.$("#file-datagrid").bootstrapTable('getSelections');
			var ids = '';
			if(rows != null && rows.length > 0){
				for(var i=0; i<rows.length; i++){
				    if(i == rows.length-1){
						ids += rows[i].id;
				    } else {
				    	ids += rows[i].id+",";
				    }
				}
				alert(ids);
				/**保存文件信息到自己业务**/
			} else {
				layer.alert('请选择要添加的文件', {icon: 2,skin: 'layer-ext-moon'})
				//Msg.alert("提示信息", "请选择要添加的文件!");
			}
		}

		function cancelAddFile(){
			$("#fileOperatorBox").dialog("close");
		}

	</script>
</body>
</html>