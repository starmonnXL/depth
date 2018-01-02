var settingSysOrg = {
	data: {
		simpleData: {
			enable: true,
			dKey: "id",
			pIdKey: "parentid",
		}
	},
	callback: {
		onClick: querySysOrgUser
	}
};
$(function(){
	var treeData = $.parseJSON($("#sysorg").val());
	$.fn.zTree.init($("#orgSysTree"), settingSysOrg,treeData);
	
	loadUserTable();
	$("#roleUserSearchBtn").on("click",function(){
		loadUserTable();
	})
	$("#roleUserCancel").on("click",function(){
		$("input[name='roleusername']").val('');
		$("input[name='rolename']").val('');
		$("input[name='rolephone']").val('');
		$("#orgSystemId").val('');
		loadUserTable();
	})
	
	$("#cancelUserRole").on("click",function(){
		$("#userRoleDialog").dialog("close");
	})
	/**添加用户信息到业务组织机构**/
	$("#addUserRole").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("userRoleList");
		var nodes = treeObj.getChangeCheckedNodes();
		if(nodes != null && nodes.length > 0){
			var node = [];
			for(var i=0;i<nodes.length;i++){
				var param = {};
				param.uid = $("#userId").val();
				param.sid = nodes[i].pId;
				param.rid = nodes[i].id;
				node.push(param);
			}
			var url = rootPath+"/user/saveUserRoles.do";
			
			$.post(url,{nodes:JSON.stringify(node)},function(data){
				if(data.status){
					Msg.alert("提示信息", data.info,'info');
					$("#userRoleDialog").dialog("close");
				} else {
					Msg.alert("提示信息", data.info,'info');
				}
			},'json');
		} else {
			Msg.alert("提示信息", "请选择需要授权给用户的角色",'info');
		}
	})
})

function querySysOrgUser(event, treeId, treeNode){
	$("#orgSystemId").val(treeNode.id);
	loadUserTable();
}
function loadUserTable(){
	var url = rootPath+"/user/queryList.do"
	var params = {};
	params.username = $("input[name='roleusername']").val();
	params.name = $("input[name='rolename']").val();
	params.phone = $("input[name='rolephone']").val();
	params.orgSystemId = $("#orgSystemId").val();
	$("#user-role-datagrid").mydatagrid({
		url:url,
		queryParam:params
	});
}

function sexFormat(val,row){
	if(row.sex == 1){
		return "男";
	} else {
		return "女";
	}
}

function roleUserFormat(val,row){
	if(row.id){
		return '<a class="userAuthorBtns data-author" data-code="ROLES" onclick="addRole(\''+row.id+'\')"></a>';
	} else {
		return "-";
	}
}

function addRole(id){
	$("#userId").val(id);
	var url = rootPath+"/user/userRoles.do";
	MUI.openDialog("&nbsp;用户角色管理",url,'userRoleDialog',{width:400,height:600,modal:true,buttons:'#userRoleDiaBtn',iconCls:'icon-user'});
}