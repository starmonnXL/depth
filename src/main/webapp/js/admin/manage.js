$(function(){
    sys_menu();
});

function sys_menu() {
    treeObj = $.fn.zTree.init($("#handle_menu"),{
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        async :{
            enable : true,
            url : "list.do", // Ajax 获取数据的 URL 地址
            dataFilter:function (treeId, parentNode, responseData) {//处理返回数据
                return eval('(' + responseData.data + ')');
            }
        },
        view: {
            fontCss: setFontCss
        },
        callback:{
            onAsyncSuccess:function () {
                treeObj.expandAll(false);
            },
            onClick: function (event, treeId, treeNode) {
                treeCodeCurNode = treeNode;
                if(null!=treeNode){
                    var $length = $("#treeCodeContextMenu").children(".menu-item").length;
                    if($length>0){
                        cataRights(treeNode.id);
                    }
                }
            },
            onRightClick:function (event, treeId, treeNode) {
                if(null!=treeNode){
                    var $length = $("#treeCodeContextMenu").children(".menu-item").length;
                    if($length>0){
                        if(treeNode.type == '1'){		//表示是单页，不能新增栏目
                            $("#add-cala").hide();
                        }else{
                            $("#add-cala").show();
                        }

                        if(treeNode.status == '1') {		//表示启用
                            $("#maintabs-update .menu-text").text("禁用");
                        }else if(treeNode.status == '0') {
                            $("#maintabs-update .menu-text").text("启用");
                        }
                        treeCodeCurNode = treeNode;
                        rightClick(event, treeId, treeNode,'treeCodeContextMenu');
                    }
                }
            }
        }
    });
}

function setFontCss(treeId, treeNode) {
    var color = {};
    if(null!=treeNode){
        if(treeNode.status == '0') {		//禁用
            color = {color:"#a5a5a5"};
        }else{
            color = {color:"#333"};		//启用
        }
    }
    return color;
}