$(function () {
    // 文章修改下一步
    $("#next").on("click",function () {
        var tag = $("#editArticle").form("validate");
        if(tag){
            $("#articleContent").css("display", "block");
            $("#articleInfo").css("display", "none");
        }
    });
    // 文章修改上一步
    $("#back").on("click",function () {
        $("#articleInfo").css("display", "block");
        $("#articleContent").css("display", "none");
    });
    // 修改文章
    $("#editSave").on("click",function () {
        var $editArticleForm = $("#editArticle");
        var sid = $("#sid").val();
        var tag = $editArticleForm.form("validate");
        if(tag){
            $.post(
                rootPath+"article/edit.do",
                $editArticleForm.serialize(),
                function (response) {
                    if(response.status){
                        $.messager.alert("提示信息", "修改成功","info",function () {
                            window.parent.tabsClose(sid,rootPath+"article/manage.do","文章管理");
                        });
                    }
                    else {
                        $.messager.alert("提示信息", "修改失败" + (json.info));
                    }
                },"JSON"
            );
        }
    });
    // 加载栏目树，并设置默认值
    $('#cId').combotree({
        url: rootPath + 'catalog/all.do',
        loadFilter:function (response) {
            return eval('(' + response.data + ')');
        },
        onLoadSuccess: function (node, data) {
            if (data != null) {
                var cId = $("#catalogId").val();
                $('#cId').combotree('setValue', cId);
            }
        }
    });
});
/**
 * 实例化编辑器
 */
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
var height = $(window).height();
var ue = UE.getEditor('editor', {
    initialFrameHeight:height * 0.8 //初始化选项
});
//如果是单个文件上传参数为 1
//如果是多个文件上传参数为  2
function uploadDialog(method){
    var url = fileUpmsPath+method+"/uploadSelect.sec?mimeType=jpeg,gif,jpg,png,bmp,pic,tif";
    layer.open({
        type:2,
        content:url,
        title:"文件选择列表",
        area:["60%","600px"],
        btn:["添加选中文件","取消选择"],
        yes:function(index){
            addFileIntro(index);
        },
        btn2: function(index){
            layer.close(index);
        }
    })
    //MUI.openDialog("&nbsp;文件选择列表",url,'fileOperatorBox',{width:1000,height:600,modal:true,buttons:'#fileUploadBtn',iconCls:'icon-user'});
}
// 选择文件上传
function addFileIntro(index){
    //获取选中文件的所有行，固定写法
    //获取选中文件的所有行，固定写法
    //rows[i]中包含 id(文件ID)、sid（系统编号）、typeId（文件分类ID）、
    //typeName（分类名称）、fileName（文件名称）、mimeType（文件类型）、
    //mimeTypeName（文件类型名称）、filePath（文件路径）、fileSize（文件大小）属性
    var rows = $("#layui-layer-iframe"+index).contents().find("#fileListFrame")[0].contentWindow.$("#file-datagrid").bootstrapTable('getSelections');
    var ids = '';
    var fileNames = '';
    var params = {};
    var urlPath = $("#fileprifix").val();
    if(rows != null && rows.length > 0){
        for(var i=0; i<rows.length; i++){
            if(i == rows.length-1){
                ids += rows[i].id;
                fileNames += urlPath + rows[i].filePath;
            } else {
                ids += rows[i].id+",";
                fileNames += urlPath + rows[i].filePath + ",";
            }
        }
        $("#editArticle input[name='picId']").val(ids);
        $("#editArticle input[name='picName']").val(fileNames);
        $("#editArticle #pic").attr("src",fileNames);
        /**保存文件信息到自己业务**/
        layer.close(index);
        return params;
    } else {
        layer.alert('请选择要添加的文件', {icon: 2,skin: 'layer-ext-moon'})
        //Msg.alert("提示信息", "请选择要添加的文件!");
    }
}
//关闭文件窗口
function cancelAddFile(){
    $("#fileOperatorBox").dialog("close");
}
// 关闭当前窗口
function closeTab() {
    window.parent.tabsClose($("#sid").val());
}