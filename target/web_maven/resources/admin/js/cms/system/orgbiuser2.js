/**初始化系统树信息**/
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: sysTreeOnClick
	}
};
var zNodes = $.parseJSON($("#menuTree").val());
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
/**加载第一个组织机构下的用户**/
function loadOrgZtreeUser(){
	setTimeout(function(){
		var firsOrgNode = getFirstNode('orgBiTree');
		if(firsOrgNode){
			 $("#systemSid").val(firsOrgNode.sid);
	    	 $("#systemOrgId").val(firsOrgNode.id);
	    	 $("#sysOrgLevel").val(firsOrgNode.orglevel);
			loadOrgUserTable(firsOrgNode.orglevel,firsOrgNode.sid);
		}
	},500)
}
/**加载第一个系统下的组织机构**/
function loadSysTreeOrg(){
	var firsSysNode = getFirstNode('sysconfTree');
	if(firsSysNode){
		loadOrgZtree(firsSysNode.id);
	}
}
$(function(){
	/**初始化系统树**/
	$.fn.zTree.init($("#sysconfTree"), setting, zNodes);
	/**默认加载第一个系统下的组织机构**/
	loadSysTreeOrg();
	/**默认加载第一个组织机构下的用户**/
	loadOrgZtreeUser();
	/**关闭用户列表框**/
	$("#cancelUserDia").on("click",function(){
		$("#userListDialog").dialog("close");
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
});
/**系统树单击事件**/
function sysTreeOnClick(event, treeId, treeNode) {
	loadOrgZtree(treeNode.id)
};
/**加载用户组织机构树**/
function loadOrgZtree(sid,parentId){
	var url = rootPath+"/orgbi/getNodeList.do";
	var params = {};
	params.sid = sid;
	/*if(parentId){
		params.parentId = parentId;
	} else {
		params.parentId = 0;
	}*/
	/**业务组织机构树**/
	var settingOrg = {
		 view: {
	        	addHoverDom: addHoverDom,
	            removeHoverDom: removeHoverDom
	    },	
		data: {
			simpleData: {
				enable: true,
				dKey: "id",
				pIdKey: "pId",
			}
		},
		callback: {
    		onClick: loadOrgBiUser,
    	}
	};
	$.post(url,params,function(data){
		if(data.status){
			if(data.data == '[]'){
				Msg.alert("提示信息", "该系统下没有业务组织机构数据!");
			} else {
				var treeData = $.parseJSON(data.data);
				$.fn.zTree.init($("#orgBiTree"), settingOrg,treeData);
				loadOrgZtreeUser();
			}
		} else {
			Msg.alert("提示信息", "加载业务组织机构失败!");
		}
	},'json')
}
/**业务组织机构单击事件**/
function loadOrgBiUser(event, treeId, treeNode){
	loadOrgUserTable(treeNode.orglevel,treeNode.sid);
}
/**业务组织机构树鼠标移动事件**/
function addHoverDom(treeId, treeNode) {
	if(treeNode.id != 0){
		var aObj = $("#" + treeNode.tId + "_a");
		if ($("#addBtn_"+treeNode.tId).length>0) return;
		var editStr = "&nbsp;<span class='button add mybtn-add' id='addBtn_" + treeNode.tId
	        + "' title='添加组织机构用户' onclick='addUser("+treeNode.id+",\""+treeNode.sid+"\",\""+treeNode.orglevel+"\")'></span>";
		aObj.after(editStr);
	}
 };
 /**业务组织机构树鼠标移除事件**/
 function removeHoverDom(treeId, treeNode) {
     $("#addBtn_"+treeNode.tId).unbind().remove();
 };
 /**业务组织机构树添加按钮事件**/
 function addUser(orgId,sid,level){
	 $("#systemSid").val(sid);
	 $("#systemOrgId").val(orgId);
	 $("#sysOrgLevel").val(level);
	 var url = rootPath+"/user/list.do";
	 //MUI.openDialog("&nbsp;用户列表",url,'userListDialog',{width:1500,height:660,modal:true,buttons:'#bb',iconCls:'icon-user'});
	 MUI.openSimpleDialog("&nbsp;用户列表", url, 'userListDialog', 1260, 700);
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
		var btns = '<a class="removeBtns data-author" data-code="DELETE" onclick="deleteOrgUser('+row.id+')"></a>&nbsp;&nbsp;';
		return btns;
	} else {
		return "-";
	}
}
/**删除业务组织机构用户**/
function deleteOrgUser(id){
	Msg.confirm('确认信息','确认删除该条记录吗？',function(r){
		if(r){
			var url = rootPath+"/orguser/"+id+"/delete.do";
			$.post(url,{},function(data){
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