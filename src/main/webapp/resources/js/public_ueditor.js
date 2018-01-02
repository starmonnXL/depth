
var height = $(window).height();

/**
 * 实例化编辑器
 */
var ue = UE.getEditor('editorCata', {
    initialFrameHeight:height/2         //初始化选项
});

/**
 * 实例化编辑器
 */
var ue = UE.getEditor('editorUpdate', {
    initialFrameHeight:height/2         //初始化选项
});


$(function(){
    $("#addFileIntro").on("click",addFileIntro);
    //取消操作
    $("#cancelAddFile").on("click",cancelAddFile);
})

//如果是单个文件上传参数为 1
//如果是多个文件上传参数为  2
function uploadDialog(method,type){
    var url = fileUpmsPath+method+"/uploadSelect.sec?mimeType=jpeg,gif,jpg,png,bmp,pic,tif";
    layer.open({
        type:2,
        content:url,
        title:"文件选择列表",
        area:["60%","600px"],
        btn:["添加选中文件","取消选择"],
        yes:function(index){
            addFileIntro(index,type);
        },
        btn2: function(index){
            layer.close(index);
        }
    })
    //MUI.openDialog("&nbsp;文件选择列表",url,'fileOperatorBox',{width:1000,height:600,modal:true,buttons:'#fileUploadBtn',iconCls:'icon-user'});
}

function addFileIntro(index,type){
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
        if(type == 1){
            $("#saveArticle input[name='picId']").val(ids);
            $("#saveArticle input[name='picName']").val(fileNames);
            $("#saveArticle #pic").attr("src",fileNames);
        }
        else if(type == 2){
            $("#editArticle input[name='picId']").val(ids);
            $("#editArticle input[name='picName']").val(fileNames);
            $("#editArticle #picEdit").attr("src",fileNames);
        }
        /**保存文件信息到自己业务**/
        layer.close(index);
        return params;
    } else {
        layer.alert('请选择要添加的文件', {icon: 2,skin: 'layer-ext-moon'})
        //Msg.alert("提示信息", "请选择要添加的文件!");
    }
}

function cancelAddFile(){
    $("#fileOperatorBox").dialog("close");
}