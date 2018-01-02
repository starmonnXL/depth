/**
 * Created by ranqibing on 2016/12/6.
 */
var $treeCode = $("#treeCode");
var $treeCodeOperatorBox = $("#treeCodeOperatorBox");
var $treeCodeContextMenu = $('#treeCodeContextMenu');
var modes = {
    add: {
        title: "添加字典",
        href: "add_page.do?"
    },
    edit: {
        title: "修改字典",
        href: "edit_page.do?"
    },
    delete: {
        href: "delete.do?"
    }
};


var treeCodeCurNode = null;

function loadNodeList (sid, parentId) {
    var url = "getNodeList.do";
    var type = "node";

    if ( arguments.length == 0 ) {
        type = "system";
        url = rootPath+"/sysconf/getOwnerSystemList.do";
    }

    var result = [];

    var ajaxConfig = {
        type:'post',
        url: url,
        data: {
            sid: sid,
            parentId: parentId
        },
        dataType: "json",
        async: false,
        success: function (json) {
            if (json.status) {
                var datas = $.parseJSON(json.data);
                for ( var i = 0, len = datas.length; i < len; i++ ) {
                    var data = datas[ i ];
                    data.type = type;
                    result.push({
                        id: data.id,
                        text: data.name,
                        state: "closed",
                        children: [],
                        attributes: data
                    });
                }
            } else {
                $.messager.alert("提示", "数据字典加载失败" + (json.info || "") + "！");
            }
        },
        error: function () {
            $.messager.alert("提示", "数据字典加载失败！");
        }
    };

    $.ajax(ajaxConfig);

    return result;
}

function loadNodeData (node) {
    var id = node.attributes.type === "system" ? 0 : node.attributes.id;
    var sid = node.attributes.type === "system" ? node.attributes.id : node.attributes.sid;

    if ( node.target.isLoaded === undefined ) {
        var rest = loadNodeList(sid, id);
        if(null!=rest && ""!=rest && rest.length>0){
            $treeCode.tree("append", {
                parent: node.target,
                data: rest
            });
            node.target.isLoaded = true;
        }else{
            var icons = $(node.target).find("span.tree-icon");
            icons.addClass("tree-file");
            icons.removeClass("tree-offline");
            icons.removeClass("tree-expanded");
            $(node.target).find("span.tree-hit").removeClass("tree-hit");

        }

    }
}

function operation (modeType) {
    var mode = modes[ modeType ];

    $("#treeCode").tree('select', treeCodeCurNode.target);

    var parentNode = $treeCode.tree("getParent", treeCodeCurNode.target);
    var parentId = treeCodeCurNode.attributes.type === "node" ? treeCodeCurNode.attributes.id : 0;
    var parentName = treeCodeCurNode.attributes.type === "node" ? parentNode.attributes.name : treeCodeCurNode.attributes.name;
    var sid = treeCodeCurNode.attributes.type === "node" ? treeCodeCurNode.attributes.sid : treeCodeCurNode.attributes.id;
    var href = mode.href;

    if ( modeType === "add" ) {
        href += "&parentId=" + parentId;
    } else if ( modeType === "edit" || modeType === "delete" ) {
        href += "&id=" + treeCodeCurNode.attributes.id;
    }
    href += "&sid=" + sid;
    href += "&parentName=" + treeCodeCurNode.text;

    if ( modeType === "delete" ) {
        $.ajax({
            url: href,
            method: "post",
            dataType: "json",
            cache: false,
            success: function (json) {
                if ( json.status) {
                    $treeCode.tree("remove", treeCodeCurNode.target);
                } else {
                    $.messager.alert("提示", "删除失败" + (json.appmsg || "") + "!");
                }
            }
        });
    }else {
        $treeCodeOperatorBox.panel("refresh", href);
    }
}



$(function () {
    $treeCode.tree({
        data: loadNodeList(),
        lines: true,
        onBeforeExpand: function (node) {
            loadNodeData(node);
        },
        onContextMenu: function (e, node) {
            e.preventDefault();

            treeCodeCurNode = node;

            if ( node.attributes.type === "system" ) {
                $treeCodeContextMenu.find("#treeMenuEditBtn").hide();
                $treeCodeContextMenu.find("#treeMenuRemoveBtn").hide();
            } else if ( node.attributes.type === "node" ) {
                $treeCodeContextMenu.find("#treeMenuEditBtn").show();
                $treeCodeContextMenu.find("#treeMenuRemoveBtn").show();
            }

            // 显示快捷菜单
            $treeCodeContextMenu.menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }
    });
});

/**
 * 添加
 * */
function addTreeCode () {
    var $treeCodeAddForm = $("#treeCodeAddForm");

    $treeCodeAddForm.form("submit", {
        url: "add.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");

            if ( json.status) {
                $.messager.alert("提示信息", "添加成功");

//                    $treeCodeAddForm.form("reset");

                json.result.type = "node";


                $treeCode.tree("append", {
                    parent: treeCodeCurNode.target,
                    data: {
                        text: json.result.name,
                        id: json.result.id,
                        attributes: json.result
                    }
                });

                $treeCodeOperatorBox.html("");

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
/**
 * 修改
 * */
function editTreeCode () {
    var $treeCodeEditForm = $("#treeCodeEditForm");

    $treeCodeEditForm.form("submit", {
        url: "edit.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");

            if ( json.status) {
                $.messager.alert("提示信息", "修改成功");

                json.result.type = "node";

//                    $treeCodeEditForm.form("reset");
                $treeCode.tree("update", {
                    target: treeCodeCurNode.target,
                    text: json.result.name,
                    id: json.result.id,
                    attributes: json.result
                });
                $treeCodeOperatorBox.html("");
            }
            else {
                $.messager.alert("提示信息", "修改失败" + (json.appmsg || ""));
            }
        },
        error: function () {
            $.messager.alert("提示信息", "修改失败!");
        }
    });
}