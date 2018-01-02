var orgbisettings = {
	async: {
		enable: true,
		url: getUrl,
		dataType: "json",
		dataFilter: ajaxDataFilter
	},	
	data: {
		simpleData: {
			enable: true
		}
	},
	view: {
		expandSpeed: "",
	},
	check: {
		enable: false
	},
	callback: {
		beforeExpand: beforeExpand,
		onAsyncSuccess: onAsyncSuccess,
		onAsyncError: onAsyncError,
		onRightClick: OnRightClick,
		onClick: loadOrgBiUser
	}	
}
var zTree, rMenu;
$(function(){
	$.fn.zTree.init($("#sysconfTree"), orgbisettings);
	zTree = $.fn.zTree.getZTreeObj("sysconfTree");
	rMenu = $("#rMenuOrgBiUser");
	
	loadFirstNodeUser();
	
	/**关闭用户列表框**/
	$("#cancelUserDia").on("click",function(){
		$("#userListDialog").dialog("close");
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
	/**添加用户信息到业务组织机构**/
	$("#addUserIntro").on("click",function(){
		var sid = $("#systemSid").val();
    	var orgId = $("#systemOrgId").val();
		var rows = $("#user-datagrid").datagrid('getChecked');
		var ids = '';
		if(rows != null && rows.length > 0){
			for(var i=0; i<rows.length; i++){  
			    if(i == rows.length-1){
					ids += rows[i].id;  
			    } else {
			    	ids += rows[i].id+",";
			    }
			}
			var params = {};
			params.sid = sid;
			params.biOrgId = orgId;
			params.userIds = ids;
			var url = rootPath+"/orguser/save.do";
			$.post(url,params,function(data){
				if(data.status){
					Msg.alert("提示信息", data.info);
					$("#orgUserSearchBtn").click();
					$("#userListDialog").dialog("close");
				} else {
					Msg.alert("提示信息", data.info);
				}
			},'json')
		} else {
			Msg.alert("提示信息", "请选择要添加的用户!");
		}
	})
	/**搜索业务组织机构用户**/
	$("#orgUserSearchBtn").on("click",function(){
		var level = $("#sysOrgLevel").val();
		var sid = $("#systemSid").val();
		loadOrgUserTable(level,sid);
	})
	/**重置业务组织机构信息**/
	$("#orgUserCancel").on("click",function(){
		$("input[name='username']").val('');
		$("input[name='name']").val('');
		$("input[name='phone']").val('');
		var level = $("#sysOrgLevel").val();
		var sid = $("#systemSid").val();
		loadOrgUserTable(level,sid);
	})
})
/**获取树形的第一个节点**/
function getFirstNode(treeId){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	if(treeObj){
		var nodes = treeObj.getNodes();
		if(nodes){
			return firstNode = nodes[0];
		} else {
			return false;
		}
	} else {
		return false;
	}
}
function loadFirstNodeUser(){
	setTimeout(function(){
		var firsOrgNode = getFirstNode('sysconfTree');
		zTree.selectNode(firsOrgNode);
		if(firsOrgNode){
			 $("#systemSid").val(firsOrgNode.id);
			loadOrgUserTable(null,firsOrgNode.id);
		}
	},500)
}
function ajaxDataFilter(treeId, parentNode, responseData) {
	if(responseData.status){
		var datas = $.parseJSON(responseData.data);
		return datas;
	} else {
		Msg.alert("提示信息", data.info,'info');
	}	
}
function getUrl(treeId, treeNode){
	var url = rootPath+"/sysconf/getOwnerSystemList.do";
	if(!treeNode){
		return url;
	} else {
		if(treeNode.sid){
			url = rootPath+"/orgbi/getNodeList.do?sid="+treeNode.sid+"&parentId="+treeNode.id;
		} else {
			url = rootPath+"/orgbi/getNodeList.do?sid="+treeNode.id+"&parentId=0";
		}
		return url;
	}
}
function beforeExpand(treeId, treeNode) {
	if (!treeNode.isAjaxing) {
		ajaxGetNodes(treeNode, "refresh");
		return true;
	} else {
		Msg.alert("提示信息",'正在加载数据中，请稍后展开节点...','info');
		return false;
	}
}
function ajaxGetNodes(treeNode, reloadType) {
	if (reloadType == "refresh") {
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (!msg || msg.length == 0) {
		return;
	}
	zTree.updateNode(treeNode);
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	Msg.alert("提示信息",'异步获取数据出现异常...','info');
	zTree.updateNode(treeNode);
}
function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		if(treeNode.sid){
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		} else {
			zTree.selectNode(treeNode);
			showRMenu("systemNode", event.clientX, event.clientY);
		}
	}
}
function showRMenu(type, x, y) {
	$("#rMenuOrgBiUser ul").show();
	if (type=="root") {
		$("#m_add_orguser").hide();
		//$("#m_update_orguser").hide();
		//$("#m_del_orguser").hide();
	} else if(type=="systemNode"){
		$("#m_add_orguser").hide();
		//$("#m_update_orguser").hide();
		//$("#m_del_orguser").hide();
	} else {
		$("#m_add_orguser").show();
		//$("#m_update_orguser").show();
		//$("#m_del_orguser").show();
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenuOrgBiUser").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}
/**添加用户操作**/
function addUser(){
	 var treeNode = zTree.getSelectedNodes()[0];
	 $("#systemSid").val(treeNode.sid);
	 $("#systemOrgId").val(treeNode.id);
	 $("#sysOrgLevel").val(treeNode.orglevel);
	 var url = rootPath+"/user/list.do";
	 MUI.openDialog("&nbsp;用户列表",url,'userListDialog',{width:1200,height:660,modal:true,buttons:'#bb',iconCls:'icon-user'});
	 hideRMenu();
}
/**加载组织机构用户信息**/
function loadOrgBiUser(event, treeId, treeNode){
	if(treeNode.sid){
		loadOrgUserTable(treeNode.orglevel,treeNode.sid);
	} else {
		loadOrgUserTable(null,treeNode.id);
	}
}
/**加载用户组织机构用户信息**/
function loadOrgUserTable(userLevel,sid){
	var url = rootPath+"/orguser/queryList.do"
	var params = {};
	params.username = $("input[name='username']").val();
	params.name = $("input[name='name']").val();
	params.phone = $("input[name='phone']").val();
	params.level = userLevel;
	params.sid = sid;
	$("#org-user-datagrid").mydatagrid({
		url:url,
		queryParam:params
	});
}
/**格式化业务组织机构用户信息操作按钮**/
function orgUserFormat(val,row){
	if(row.id){
		var btns = '<div class="handle-td"><a class="userAuthorBtns data-author" data-code="ROLES" onclick="addRole(\''+row.userId+'\')"></a>&nbsp;&nbsp;'
					+'<a class="removeBtns data-author" data-code="DELETE" onclick="deleteOrgUser('+row.id+',\''+row.userId+'\',\''+row.sid+'\')"></a></div>';
		return btns;
	} else {
		return "-";
	}
}
/**删除业务组织机构用户**/
function deleteOrgUser(id,userId,sid){
	Msg.confirm('确认信息','确认删除该条记录吗？',function(r){
		if(r){
			var url = rootPath+"/orguser/"+id+"/delete.do";
			$.post(url,{userId:userId,sid:sid},function(data){
				if(data.status){
					Msg.alert("提示信息", data.info,'info');
					$("#orgUserSearchBtn").click();
				} else {
					Msg.alert("提示信息", data.info,'info');
				}
			},'json')
		}
	});
}
function addRole(id){
	$("#userId").val(id);
	var url = rootPath+"/user/userRoles.do";
	MUI.openDialog("&nbsp;用户角色管理",url,'userRoleDialog',{width:400,height:600,modal:true,buttons:'#userRoleDiaBtn',iconCls:'icon-user'});
}
