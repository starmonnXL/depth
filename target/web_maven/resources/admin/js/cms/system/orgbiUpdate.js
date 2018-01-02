 /**修改业务组织机构**/
function editOrgBi() {
    var $orgBiEditForm = $("#orgBiEditForm");
    $orgBiEditForm.form("submit", {
        url: rootPath+"/orgbi/update.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {
            var data = eval('('+ data + ')');
            if (data.status) {
                refreshParentNode();
            	Msg.alert("提示信息",data.info,"info");
                $("#orgBiOperatorBox").panel('refresh');
            } else {
            	Msg.alert("提示信息",data.info,"info");
            }
        },
        error: function () {
        	Msg.alert("提示信息","修改失败!","info");
        }
    });
}
function refreshParentNode() {  
    var zTree = $.fn.zTree.getZTreeObj("orgBiTree"),  
    type = "refresh",  
    silent = false,  
    nodes = zTree.getSelectedNodes();  
    /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/  
    var parentNode = zTree.getNodeByTId(nodes[0].parentTId);  
    /*选中指定节点*/  
    zTree.reAsyncChildNodes(parentNode, type, silent);  
    zTree.selectNode(nodes[0]);
}
function resetEditOrgBi(){
	$('#orgBiEditForm').form('reset');
}