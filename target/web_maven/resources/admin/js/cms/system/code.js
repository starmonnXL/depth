$(function(){
	var prarams = {};
	initDataGrid(prarams);
	
	$("#codeSearchBtn").on("click",function(){
		var sid = $("#codeSearchSidSelect").val();
		var field = $("#field").val();
		prarams.sid = sid;
		prarams.field = field;
		initDataGrid(prarams);
	});
	
	$('#handle-bar').defineAuthor({
    	authority:hrightsCodes
    });
	$("#addCode").on("click",function(){
		MUI.openDialog("添加数据字典",rootPath+"/code/add_page.do",'codeOperatorBox',{width:450,height:530,modal:true})	
	});
	$("#updateCode").on("click",function(){
		var row = $("#code-datagrid").datagrid("getSelected");
		if(!row) {
            $.messager.alert("提示", "请选择您需要修改的数据!");
            return false;
        }
        if(row.editmode == 0) {
            $.messager.alert("提示", "该记录不可编辑");
            return false;
        }
		MUI.openDialog("修改数据字典",rootPath+"/code/edit_page.do?id="+row.id,'codeOperatorBox',{width:450,height:530,modal:true})	
	});
	$("#deleteCode").on("click",function(){
		var row = $("#code-datagrid").datagrid("getSelected");
		if(!row) {
            $.messager.alert("提示", "请选择您需要删除的数据!");
            return false;
        }
        if(row.editmode == 0) {
            $.messager.alert("提示", "该记录不可编辑，不能删除!");
            return false;
        }
        removeCode();
	});
})
function initDataGrid(params){
	var path = rootPath+"/code/pageList.do";
	$("#code-datagrid").mydatagrid({
		url:path,
		queryParam:params
	});
}
function codeCancel(){
	$("#codeOperatorBox").dialog("close");
}
function enableFormat (value, rowData, rowIndex) {
    return value == 1 ? "启用" : "禁用";
}
function editmodeFormat(value, rowData, rowIndex) {
    return value == 1 ? "可编辑" : "不可编辑";
}
function codeOperFormat(value,row,index){
	var btns = "<div class='handle-td'>"
	if(row.enabled == 1){
		btns += '<a class="disenableBtns data-author" data-code="ENABLE" onclick="enableCode('+row.id+')"></a>&nbsp;&nbsp';
	} else {
		btns += '<a class="enableBtns data-author" data-code="ENABLE" onclick="enableCode('+row.id+')"></a>&nbsp;&nbsp';
	}
	if(row.editmode == 1){
		btns += '<a class="unEditBtns data-author" data-code="EDITSTATE" onclick="setEdit('+row.id+')"></a>&nbsp;&nbsp';	
	} else {
		btns += '<a class="editBtns data-author" data-code="EDITSTATE" onclick="setEdit('+row.id+')"></a>&nbsp;&nbsp';	
	}
	btns += "</div>"
	return btns;
}
function enableCode(id){
	var url = rootPath+"/code/"+id+"/enable.do";
	$.post(url,{},function(data){
		if(data.status){
			$("#codeSearchBtn").click();
			$.messager.alert("提示信息",data.info);
		} else {
			$.messager.alert("提示信息",data.info);
		}
	},'json')
}
function setEdit(id){
	var url = rootPath+"/code/"+id+"/edit.do";
	$.post(url,{},function(data){
		if(data.status){
			$("#codeSearchBtn").click();
			$.messager.alert("提示信息",data.info);
		} else {
			$.messager.alert("提示信息",data.info);
		}
	},'json')
}
/**删除**/
function removeCode () {
    var rows = $("#code-datagrid").datagrid("getSelections");
    if ( rows && rows.length > 0 ) {
        var row = rows[ 0 ];
        if ( row.editmode == 0 ) {
            $.messager.alert("提示", "该记录不可删除");
            return;
        }
        $.messager.confirm("提示", "是否确定删除该记录?", function(isConfirm) {
            if (isConfirm ) {
                $.ajax({
                    url: rootPath+"/code/delete.do",
                    cache: false,
                    dataType: "json",
                    data: {
                        id: row.id
                    },
                    success: function (json) {
                        if (json.status) {
                            $.messager.alert("提示信息", "删除成功!");
                        } else {
                            $.messager.alert("提示信息", "删除失败");
                        }
                        $("#code-datagrid").datagrid("clearSelections");
                        $("#code-datagrid").datagrid("reload");
                    },
                    error: function () {
                        $.messager.alert("提示信息", "删除失败!");
                    }
                });
            }
        })
    }
}
/**添加**/
function addCode () {
    var $codeAddForm = $("#codeAddForm");
    $codeAddForm.form("submit", {
        url: rootPath+"/code/add.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");
            if ( json.status) {
                $.messager.alert("提示信息", "添加成功");
                $("#code-datagrid").datagrid("clearSelections");
                $("#code-datagrid").datagrid("reload");
                $codeAddForm.form("reset");
                $("#codeOperatorBox").dialog("close");
            }
            else {
                $.messager.alert("提示信息", "添加失败" + (json.appmsg || ""));
            }
        },
        error: function () {
            $.messager.alert("提示信息", "添加失败!");
        }
    });
}
/**修改**/
function editCode () {
    var $editForm = $("#editForm");
    $editForm.form("submit", {
        url: rootPath+"/code/edit.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");
            if (json.status) {
                $.messager.alert("提示信息", "修改成功");
                $("#code-datagrid").datagrid("reload");
                $editForm.form("reset");
                $("#codeOperatorBox").dialog("close");
            }
            else {
                $.messager.alert("提示信息", "修改失败" + (json.info));
            }
        },
        error: function () {
            $.messager.alert("提示信息", "修改失败!");
        }
    });
}