var userIds = $("#userId").val();
var setting = {
	async: {
		enable: true,
		dataType: "json",
		url:rootPath+"/user/"+userIds+"/getRoleTreeDate.do",
		otherParam: {"uid":userIds},
		dataFilter: dataFilter,
		type: "post"
	},	
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
function dataFilter(treeId, parentNode, responseData) {
    if (responseData.status) {
    	var jsonData = $.parseJSON(responseData.data);
	    return jsonData;
    } else {
    	Msg.alert("提示信息", data.info,'info');
    }
};
$(function(){
	$.fn.zTree.init($("#userRoleList"), setting);
})