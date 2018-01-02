/**添加或修改提交表单事件**/
function submitForm(){
	$('#sysSaveOrUpdate').form('submit',{
		url:rootPath+"/sysconf/saveOrUpdate.do",
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			var data = eval('('+data+')');
			if(data.status){
				 $.messager.alert("提示信息", data.info, "ok");
				 $('#sysSaveOrUpdate').form('reset');
				 $("#saveOrUpdate").dialog('close');
				 $("#system-datagrid").datagrid("reload");
			} else {
				Msg.alert("提示信息", data.info, "error");
			}
		},
		error:function(){
			Msg.alert("提示信息", "添加失败请重试!",'info');
		}
	});
}
/**清空表单事件**/
function clearForm(){
	$('#sysSaveOrUpdate').form('reset');
}