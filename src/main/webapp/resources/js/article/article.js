$(function () {
    var treeObj;
    // 栏目树形菜单设置启用禁用颜色
    function setFontCss(treeId, treeNode) {
        var color = {};
        if (null != treeNode) {
            if (treeNode.type == 1) {     // 单页
                color = {color: "#0000FF"};
                if (treeNode.status == '0') {		//禁用
                    color = {color: "#a5a5a5"};
                }
            }
        }
        return color;
    }
    // 获取栏目树形菜单
    function sys_menu() {
        treeObj = $.fn.zTree.init($("#handle_menu"), {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                }
            },
            async: {
                enable: true,
                url: rootPath + "catalog/list.do", // Ajax 获取数据的 URL 地址
                dataFilter: function (treeId, parentNode, responseData) {//处理返回数据
                    return eval('(' + responseData.data + ')');
                }
            },
            view: {
                fontCss: setFontCss
            },
            callback: {
                // 设置树形展开
                onAsyncSuccess: function () {
                    treeObj.expandAll(true);
                },
                // 点击获取该栏目下的文章
                onClick: function (event, treeId, treeNode) {
                    treeCodeCurNode = treeNode;
                    if (null != treeNode) {
                        var param = {};
                        param.cId = treeNode.id;
                        $("#cId").val(treeNode.id);
                        $("#cType").val(treeNode.type);
                        initDataGrid(param);
                    }
                }
            }
        });
    }
    sys_menu();
    // 初始化文章列表
    var params = {};
    initDataGrid(params);
    // 文章搜索
    $("#articleSearchBtn").on("click", function () {
        var tag = $("#timeEnd").datebox("isValid");
        if (tag) {
            var cId = $("#cId").val();
            var title = $("#title").val();
            var timeBegin = $("#timeBegin").datebox("getValue");
            var timeEnd = $("#timeEnd").datebox("getValue");
            params.cId = cId;
            params.title = title;
            params.timeBegin = timeBegin;
            params.timeEnd = timeEnd;
            initDataGrid(params);
        }
        else {
            $.messager.alert("提示信息", "结束日期不能早于开始日期!");
        }
    });
    // 权限验证
    $('#handle-bar').defineAuthor({
        authority: hrightsCodes
    });
    // 验证日期
    $.extend($.fn.validatebox.defaults.rules, {
        equaldDate: {
            validator: function (value, param) {
                var d1 = $(param[0]).datetimebox('getValue'); //获取开始时间
                return value >= d1; //有效范围为大于开始时间的日期
            },
            message: '结束日期不能早于开始日期!'
        }
    });
    // 打开新增文章窗口
    $("#addArticle").on("click", function () {
        if ($("#cType").val() == '1') {
            $.messager.alert("提示信息", "该栏目为单页栏目，不能在该栏目下新增文章");
            return false;
        }
        window.parent.addNewTab($("#sid").val(), rootPath + 'article/add_page.do?cId=' + $("#cId").val(), '新增文章');
        //MUI.openSimpleDialog("添加文章", rootPath + "article/add_page.do", 'articleOperatorBox', 1050, 650);
    });
    // 打开修改文章窗口
    $("#updateArticle").on("click", function () {
        var row = $("#article-datagrid").datagrid("getSelected");
        var rows = $("#article-datagrid").datagrid("getSelections");
        if (!row) {
            $.messager.alert("提示信息", "请选择您需要修改的数据!");
            return false;
        }
        if (rows.length > 1) {
            $.messager.alert("提示信息", "只能选择一篇文章进行修改");
            return false;
        }
        window.parent.updateTab($("#sid").val(), rootPath + 'article/edit_page.do?id=' + row.id, '修改文章');
        // MUI.openSimpleDialog("修改文章", rootPath + "article/edit_page.do?id=" + row.id, 'articleOperatorBox', 1050, 650);
    });
    // 删除文章
    $("#deleteArticle").on("click", function () {
        var row = $("#article-datagrid").datagrid("getSelected");
        var rows = $("#article-datagrid").datagrid("getSelections");
        if (!row) {
            $.messager.alert("提示", "请选择您需要删除的数据!");
            return false;
        }
        if (rows && rows.length > 0) {
            $.messager.confirm("提示", "是否确定删除该记录?", function (isConfirm) {
                if (isConfirm) {
                    var url;
                    var id;
                    if (rows.length > 1) {
                        url = rootPath + "article/delete_ids.do";
                        id = new Array();
                        for (var i = 0; i < rows.length; i++) {
                            id[i] = rows[i].id;
                        }
                    } else {
                        url = rootPath + "article/delete.do";
                        id = rows[0].id
                    }
                    $.ajax({
                        url: url,
                        cache: false,
                        dataType: "json",
                        type: "POST",
                        traditional: true,
                        data: {
                            id: id
                        },
                        success: function (json) {
                            if (json.status) {
                                $.messager.alert("提示信息", "删除成功!");
                            } else {
                                $.messager.alert("提示信息", "删除失败");
                            }
                            $("#article-datagrid").datagrid("clearSelections");
                            $("#article-datagrid").datagrid("reload");
                        },
                        error: function () {
                            $.messager.alert("提示信息", "删除失败!");
                        }
                    });
                }
            })
        }
    });
});
// 日期插件添加清空按钮
var buttons = $.extend([], $.fn.datebox.defaults.buttons);
buttons.splice(1, 0, {
    text: '清空',//按钮文本
    handler: function (target) {
        $("#" + target.id + "").datebox('setValue', "");//根据ID清空
        $("#" + target.id + "").datebox('hidePanel', "");
    }
});
// 获取文章列表
function initDataGrid(params) {
    var path = rootPath + "article/pageList.do";
    $("#article-datagrid").mydatagrid({
        singleSelect: false,
        url: path,
        queryParam: params,
        toolbar: '#articletools'
    });
}
// 文章状态属性
function statusFormat(value, rowData, rowIndex) {
    if (value == '1') {
        return '启用';
    } else if (value == '2') {
        return '禁用';
    } else {
        return '草稿';
    }
}
// 静态属性
function htmlFormat(value, rowData, rowIndex) {
    return value == 1 ? "禁止" : "允许";
}
// 操作按钮
function articleOperFormat(value, row, index) {
    var btns = "<div class='handle-td'>";
    if (row.status == '1') {
        btns += '<a class="disenableBtns data-author" data-code="ENABLE" onclick="enableArticle(' + row.id + ')"></a>&nbsp;&nbsp';
    } else {
        btns += '<a class="enableBtns data-author" data-code="ENABLE" onclick="enableArticle(' + row.id + ')"></a>&nbsp;&nbsp';
    }
    btns += "</div>"
    return btns;
}
// 启用禁用操作
function enableArticle(id){
    var url = rootPath+"article/"+id+"/enable.do";
    $.post(url,{},function(data){
        if(data.status){
            $("#articleSearchBtn").click();
            $.messager.alert("提示信息",data.info);
        } else {
            $.messager.alert("提示信息",data.info);
        }
    },'json')
}