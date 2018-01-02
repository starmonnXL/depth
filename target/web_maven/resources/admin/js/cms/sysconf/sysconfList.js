$(function(){
	/**顶部添加操作**/
	$("#add").on("click",function(){
		var url = rootPath+"/sysconf/add.do"
		MUI.openSimpleDialog("添加系统", url, 'saveOrUpdate', 800, 300);
	});
	/**顶部更新操作**/
	$("#update").on("click",function(){
		var row = $("#system-datagrid").datagrid('getSelected');
		if(row){
			var id = row.id;
			var url = rootPath+"/sysconf/"+id+"/update.do";
			MUI.openSimpleDialog("修改系统", url, 'saveOrUpdate', 800, 300);
		} else {
			Msg.alert("提示信息", "请选择一条数据!",'info');
		}
	});
	/**加载数据列表**/
	var initUrl = rootPath+"/sysconf/list.do"
	$("#system-datagrid").mydatagrid({
		url:initUrl
	});
	
	$('#handle-bar').defineAuthor({
    	authority:hrightsCodes
    });
});

/**格式化数据按钮**/
function format(val,row){
	if(row.id){
		var btns = '<div class="handle-td"><a class="updateBtns data-author" data-code="UPDATE" onclick="updateSys(\''+row.id+'\')"></a>&nbsp;&nbsp;';
		if(row.state == "1"){
			btns += '<a class="disenableBtns data-author" data-code="ENABLE" onclick="enableSys(\''+row.id+'\')"></a>&nbsp;&nbsp';
		} else {
			btns += '<a class="enableBtns data-author" data-code="ENABLE" onclick="enableSys(\''+row.id+'\')"></a>&nbsp;&nbsp';
		}
		btns+='</div>';
		return btns;
	} else {
		return "-";
	}
}
/**行更新按钮事件**/
function updateSys(id){
	var url = rootPath+"/sysconf/"+id+"/update.do";
	MUI.openSimpleDialog("修改系统", url, 'saveOrUpdate', 800, 300);
}
/**停用或启用操作**/
function enableSys(id){
	var ids = [];
	ids.push(id);
	var url = rootPath+"/sysconf/"+ids+"/batchEnable.do";
	$.post(url,{},function(data){
		if(data.status){
			Msg.alert("提示信息", data.info,'info');
			$("#system-datagrid").datagrid("reload");
		} else {
			Msg.alert("提示信息", data.info,'info');
		}
	},'json')
}