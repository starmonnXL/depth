//自定义按钮“单图上传”
UE.registerUI('单图上传', function(editor, uiName1) {
    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
    editor.registerCommand(uiName1, {
        execCommand: function() {
            uploadDialog_img(1,this);
        }
    });
    //创建一个button
    var btn = new UE.ui.Button({
        //按钮的名字
        name: uiName1,
        //提示
        title: uiName1,
        //添加额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules: 'background-position: -380px 0;',
        //点击时执行的命令
        onclick: function() {
            //这里可以不用执行命令,做你自己的操作也可
            editor.execCommand(uiName1);
        }
    });
    //当点到编辑内容上时，按钮要做的状态反射
    editor.addListener('selectionchange', function() {
        var state = editor.queryCommandState(uiName1);
        if (state == -1) {
            btn.setDisabled(true);
            btn.setChecked(false);
        } else {
            btn.setDisabled(false);
            btn.setChecked(state);
        }
    });
    //因为你是添加button,所以需要返回这个button
    return btn;
},56);

//自定义按钮，多图上传
UE.registerUI('多图上传', function(editor, uiName) {
    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
    editor.registerCommand(uiName, {
        execCommand: function() {
            uploadDialog_img(2,this);
        }
    });
    //创建一个button
    var btn = new UE.ui.Button({
        //按钮的名字
        name: uiName,
        //提示
        title: uiName,
        //添加额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules: 'background-position: -727px -77px;',
        //点击时执行的命令
        onclick: function() {
            //这里可以不用执行命令,做你自己的操作也可
            editor.execCommand(uiName);
        }
    });
    //当点到编辑内容上时，按钮要做的状态反射
    editor.addListener('selectionchange', function() {
        var state = editor.queryCommandState(uiName);
        if (state == -1) {
            btn.setDisabled(true);
            btn.setChecked(false);
        } else {
            btn.setDisabled(false);
            btn.setChecked(state);
        }
    });
    //因为你是添加button,所以需要返回这个button
    return btn;
},57);

//如果是单个文件上传参数为 1
//如果是多个文件上传参数为  2
function uploadDialog_img(method,obj){
    var url = fileUpmsPath+method+"/uploadSelect.sec?mimeType=jpeg,gif,jpg,png,bmp,pic,tif";
    layer.open({
        type:2,
        content:url,
        title:"文件选择列表",
        area:["60%","600px"],
        btn:["添加选中文件","取消选择"],
        yes:function(index){
            addFileIntro_img(index,obj);
        },
        btn2: function(index){
            layer.close(index);
        }
    })
    //MUI.openDialog("&nbsp;文件选择列表",url,'fileOperatorBox',{width:1000,height:600,modal:true,buttons:'#fileUploadBtn',iconCls:'icon-user'});
}

function addFileIntro_img(index,obj){
    //获取选中文件的所有行，固定写法
    //获取选中文件的所有行，固定写法
    //rows[i]中包含 id(文件ID)、sid（系统编号）、typeId（文件分类ID）、
    //typeName（分类名称）、fileName（文件名称）、mimeType（文件类型）、
    //mimeTypeName（文件类型名称）、filePath（文件路径）、fileSize（文件大小）属性
    var rows = $("#layui-layer-iframe"+index).contents().find("#fileListFrame")[0].contentWindow.$("#file-datagrid").bootstrapTable('getSelections');
    var ids = '';
    var fileNames = ''
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

        var html = "";
        var names = fileNames.split(",");
        for (var i = 0; i < names.length; i++) {
            html += "<img src='"+names[i]+"' />"
        }

        UE.getEditor(""+obj.key+"").execCommand('insertHtml', html)

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