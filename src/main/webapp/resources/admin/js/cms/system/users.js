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
	$("#userSearchBtn").on("click",function(){
		loadUserTable();
	})
	$("#userCancel").on("click",function(){
		$("input[name='username']").val('');
		$("input[name='name']").val('');
		$("input[name='phone']").val('');
		$("#orgSystemId").val('');
		loadUserTable();
	})
	/**事务组织机构单击事件**/
	$("#orgSystemSearchBtn").on("click",function(){
		var url = rootPath+"user/list.do";
		var name = $("#orgSystemName").val();
		location.href = url+"?name="+name;
	})
})

function querySysOrgUser(event, treeId, treeNode){
	$("#orgSystemId").val(treeNode.id);
	loadUserTable();
}

function loadUserTable(){
	var url = rootPath+"/user/queryList.do"
	var params = {};
	params.username = $("#tools input[name='username']").val();
	params.name = $("#tools input[name='name']").val();
	params.phone = $("#tools input[name='phone']").val();
	params.orgSystemId = $("#orgSystemId").val();
	$("#user-datagrid").mydatagrid({
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