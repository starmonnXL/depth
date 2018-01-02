function saveOrgBi () {
	$('#orgBiAddForm').form("submit", {
		url:rootPath+"/orgbi/add.do",
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			var data = eval('('+data+')');
			if(data.status){
				 Msg.alert("提示信息", data.info, "ok");
				 $('#orgBiAddForm').form('reset');
				 var zTree = $.fn.zTree.getZTreeObj("orgBiTree");
				 var node = $.parseJSON(data.data);
				 var nodes = zTree.getSelectedNodes()[0];
				 if(!nodes.isParent){
					 var newNode = {};
					 newNode.id = node.id;
					 newNode.name = node.name;
					 if(node.parentid){
						 newNode.sid = node.sid;
						 newNode.pId = node.parentid;
					 } else {
					 newNode.pId = node.sid;
					 }
					 zTree.addNodes(nodes, newNode); 
				 } else {
					 if(nodes.open){
					 var newNode = {};
					 newNode.id = node.id;
					 newNode.name = node.name;
					 if(node.parentid){
						 newNode.sid = node.sid;
						 newNode.pId = node.parentid;
					 } else {
							 newNode.pId = node.sid;
					 }
					 zTree.addNodes(nodes, newNode);
					 } else {
						 zTree.expandNode(nodes, true, true, true);
					 } 
				 }
			} else {
				Msg.alert("提示信息", data.info, "error");
			}    			
		},
		error: function () {
			Msg.alert("提示信息", "添加失败请重试!",'info');
	    }
	})	
    				
}
    
function resetOrgBi(){
	$('#orgBiAddForm').form('reset');
}