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
		//addHoverDom: addHoverDom,
        //removeHoverDom: removeHoverDom
	},
	check: {
		enable: false
	},
	callback: {
		beforeExpand: beforeExpand,
		onAsyncSuccess: onAsyncSuccess,
		onAsyncError: onAsyncError,
		onRightClick: OnRightClick,
		onClick: zTreeOnClick
	}	
}
var zTree, rMenu;
$(function(){
	$.fn.zTree.init($("#orgBiTree"), orgbisettings);
	zTree = $.fn.zTree.getZTreeObj("orgBiTree");
	rMenu = $("#rMenuOrgBi");
})
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
		Msg.alert("提示信息",'正在下载数据中，请稍后展开节点!!!','info');
		return false;
	}
}
function ajaxGetNodes(treeNode, reloadType) {
	var zTree = $.fn.zTree.getZTreeObj("orgBiTree");
	if (reloadType == "refresh") {
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (!msg || msg.length == 0) {
		return;
	}
	var zTree = $.fn.zTree.getZTreeObj("orgBiTree");
	zTree.updateNode(treeNode);
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	var zTree = $.fn.zTree.getZTreeObj("orgBiTree");
	Msg.alert("提示信息",'异步获取数据出现异常!!!','info');
	zTree.updateNode(treeNode);
}
/*function addHoverDom(treeId, treeNode) {
	if(treeNode.sid && treeNode.pId){
		var aObj = $("#" + treeNode.tId + "_a");
		if ($("#addBtn_"+treeNode.tId).length>0) return;
		if ($("#updateBtn_"+treeNode.tId).length>0) return;
		if ($("#removeBtn_"+treeNode.tId).length>0) return;
		var editStr = "&nbsp;<span class='button mybtn-add' id='addBtn_"+treeNode.tId+"' title='添加业务组织机构'></span>"
				+ "&nbsp;<span class='button mybtn-update' id='updateBtn_"+treeNode.tId+"' title='修改业务组织机构'></span>"
				+ "&nbsp;<span class='button mybtn-remove' id='removeBtn_"+treeNode.tId+"' title='删除业务组织机构'></span>"
		aObj.after(editStr);
		var addbtn = $("#addBtn_"+treeNode.tId);
        if (addbtn) addbtn.bind("click", function(){
        	addOrgBi(treeNode);
        	return false;
        });
        var updatebtn = $("#updateBtn_"+treeNode.tId);
        if (updatebtn) updatebtn.bind("click", function(){
        	updateOrgBi(treeNode);
        	return false;
        });
        var removebtn = $("#removeBtn_"+treeNode.tId);
        if (removebtn) removebtn.bind("click", function(){
        	removeOrgBi(treeNode);
        	return false;
        });
	} else {
		var aObj = $("#" + treeNode.tId + "_a");
		if ($("#addBtn_"+treeNode.tId).length>0) return;
		var editStr = "&nbsp;<span class='button add mybtn-add' id='addBtn_"+ treeNode.tId+"' title='添加业务组织机构'></span>";
		aObj.after(editStr);
		var addbtns = $("#addBtn_"+treeNode.tId);
        if (addbtns) addbtns.bind("click", function(){
        	addOrgBi(treeNode);
        });
	}
 };
 function removeHoverDom(treeId, treeNode) {
     $("#addBtn_"+treeNode.tId).unbind().remove();
     $("#updateBtn_"+treeNode.tId).unbind().remove();
     $("#removeBtn_"+treeNode.tId).unbind().remove();
 };*/
function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		if(treeNode.pId){
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		} else {
			zTree.selectNode(treeNode);
			showRMenu("systemNode", event.clientX, event.clientY);
		}
	}
}
function showRMenu(type, x, y) {
	$("#rMenuOrgBi ul").show();
	if (type=="root") {
		$("#m_add_org").hide();
		$("#m_update_org").hide();
		$("#m_del_org").hide();
	} else if(type=="systemNode"){
		$("#m_add_org").show();
		$("#m_update_org").hide();
		$("#m_del_org").hide();
	} else {
		$("#m_add_org").show();
		$("#m_update_org").show();
		$("#m_del_org").show();
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenuOrgBi" || $(event.target).parents("#rMenuOrgBi").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}
function zTreeOnClick(event, treeId, treeNode){
	 if(treeNode.sid && treeNode.pId){
		 updateOrgBi(treeNode);
	 }
}
function addOrgBi(){
	 var treeNode = zTree.getSelectedNodes()[0];
	 var path = rootPath +"/orgbi/addInput.do";
	 if(treeNode.pId){
		 path = rootPath +"/orgbi/addInput.do?sid="+treeNode.sid+"&parentId="+treeNode.id+"&parentName="+treeNode.name;
	 } else {
		 path = rootPath +"/orgbi/addInput.do?sid="+treeNode.id+"&parentId=0&parentName="+treeNode.name;
	 }
	 $("#orgBiOperatorBox").panel("refresh", path);
	 hideRMenu();
}
function updateOrgBi(){
	 var treeNode = zTree.getSelectedNodes()[0];
	 var path = rootPath +"/orgbi/updateInput.do?id="+treeNode.id+"&parentName="+treeNode.name;
	 $("#orgBiOperatorBox").panel("refresh", path);
	 hideRMenu();
}
function removeOrgBi(){
	 var treeNode = zTree.getSelectedNodes()[0];
	 if(treeNode.children && treeNode.children.length>0){
		 Msg.confirm('确认信息','该条记录信息下还有子节点，删除将连子节点一起删除，确认删除该条记录吗？',function(r){
			if(r){
				var url = rootPath+"/orgbi/delete.do?id="+treeNode.id;
				$.post(url,{},function(data){
					if(data.status){
						Msg.alert("提示信息", data.info,'info');
						refreshParentNodeDel();
					} else {
						Msg.alert("提示信息", data.info,'info');
					}
				},'json')
			}
		});
	 } else {
		 Msg.confirm('确认信息','删除后将不可恢复，确认删除该条记录吗？',function(r){
			if(r){
				var url = rootPath+"/orgbi/delete.do?id="+treeNode.id;
				$.post(url,{},function(data){
					if(data.status){
						Msg.alert("提示信息", data.info,'info');
						refreshParentNodeDel();
					} else {
						Msg.alert("提示信息", data.info,'info');
					}
				},'json')
			}
		}); 
	 }
	 hideRMenu();
}
function refreshParentNodeDel() {  
    var zTree = $.fn.zTree.getZTreeObj("orgBiTree"),  
    type = "refresh",  
    silent = false,  
    nodes = zTree.getSelectedNodes();  
    /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/  
    var parentNode = zTree.getNodeByTId(nodes[0].parentTId);  
    /*选中指定节点*/  
    zTree.selectNode(parentNode);
    zTree.reAsyncChildNodes(parentNode, type, silent);  
}