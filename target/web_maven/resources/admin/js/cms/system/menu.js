/**
 * Created by ranqibing on 2016/11/28.
 */
var treeSystemNode = null;
var treeCodeCurNode = null;
var treeHrightContextMenu = null;

$(function () {
    //获取系统信息
        $.fn.zTree.init($("#system"), {
            async :{
                enable : true,
                url : "system.do", // Ajax 获取数据的 URL 地址
                dataFilter:function (treeId, parentNode, responseData) {
                    return responseData.data;
                }
            },
            callback: {
                view: {
                    showLine: true,//不显示节点之间的连线
                },
                onAsyncSuccess:function (event, treeId, treeNode, msg) {
                    var zTree = $.fn.zTree.getZTreeObj("system");
                    var nodes = zTree.getNodes();
                    zTree.selectNode(nodes[0]);
                    treeSystemNode = nodes[0];
                    sys_menu(nodes[0].id);
                },
                onClick: function (event, treeId, treeNode) {
                    treeSystemNode = treeNode;
                    sys_menu(treeNode.id);
                },
                onRightClick:function (event, treeId, treeNode) {
                    if(null!=treeNode){
                        treeSystemNode = treeNode;
                        rightClick(event, treeId, treeNode,'treeSystem');
                    }
                }
        }
        });

    //系统点击事件
    $(document).on("click","#system ul li",function () {
        $(this).parent().children("li").removeClass("li_active");
        $(this).addClass("li_active");
        $("#handle_menu").parent().children().eq(0).css("display","none");
        $("#handle_menu").html("");
        var $sys = $(this).attr("id");
        sys_menu($sys);
    });

})

//加载菜单树并绑定事件
function sys_menu($sys) {
        $.fn.zTree.init($("#treeCode"),{
            async :{
                enable : true,
                url : "menu_sys/"+$sys+".do", // Ajax 获取数据的 URL 地址
                dataFilter:function (treeId, parentNode, responseData) {//处理返回数据
                    return eval('(' + responseData.data + ')');
                }
            },
            edit:{
                drag: {
                    autoExpandTrigger: false,//拖拽时父节点自动展开是否触发 onExpand 事件回调函数
                    prev: true,//拖拽到目标节点时，设置是否允许移动到目标节点前面的操作
                    isCopy: false,
                    isMove: true,
                    inner: true,//拖拽到目标节点时，设置是否允许成为目标节点的子节点
                    next: true,//拖拽到目标节点时，设置是否允许移动到目标节点后面的操作
                },
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            callback:{
                onDrop:function (event, treeId, treeNodes, targetNode, moveType) { //用于捕获节点拖拽操作结束的事件回调函数
                    if(null!=targetNode){
                        $.ajax({
                            url:"update_menu_parent.do",
                            type:'post',
                            data:{'id':treeNodes[0].id,'parentId':targetNode.id},
                            dataType:'json',
                            success:function (msg) {
                            }
                        });
                    }
                },
                onClick: function (event, treeId, treeNode) {
                    treeCodeCurNode = treeNode;
                    menuRights(treeNode.id);
                },
                onRightClick:function (event, treeId, treeNode) {
                    if(null!=treeNode){
                        if(treeNode.s=='1'){
                            $("#maintabs-update .menu-text").text("禁用");
                        }else{
                            $("#maintabs-update .menu-text").text("启用");
                        }
                        treeCodeCurNode = treeNode;
                        rightClick(event, treeId, treeNode,'treeCodeContextMenu');
                    }
                }
            }
        });
}
//加载操作权限树并绑定时间
function menuRights($menuId) {
    $.fn.zTree.init($("#handle_menu"), {
        async :{
            enable : true,
            url : "menu_hrights/"+$menuId+".do", // Ajax 获取数据的 URL 地址
            dataFilter:function (treeId, parentNode, responseData) {//处理返回数据
                return eval('(' + responseData.data + ')');
            }
        },
        view: {
            showLine: false
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                treeSystemNode = treeNode;
            },
            onRightClick:function (event, treeId, treeNode) {
                if(null!=treeNode){
                    if(treeNode.s==1){
                        $("#hrights_state .menu-text").text("禁用");
                    }else{
                        $("#hrights_state .menu-text").text("启用");
                    }
                    treeHrightContextMenu = treeNode;
                    rightClick(event, treeId, treeNode,'treeHrightContextMenu');
                }
            }
        }
    });
}

function add() {
    $("#ff")[0].reset();
    $.post("btype.do",function (msg) {
        var $html = "";
        $.each(msg,function (i,n) {
            $html += "<option value='"+n.code+"'>"+n.codedesc+"</option>>";
        })
        $("#btype").html($html);
    },'json');
    $("#ff input[name='sysId']").val(treeSystemNode.id);
    $("#ff input[name='parentName']").val("顶级菜单");
    $('#w').window('open');
}

function operation (modeType) {
    var parentId = treeCodeCurNode.id
    var parentName = treeCodeCurNode.name;
    if (modeType == "add" ) {
        $("#ff")[0].reset();
        $.post("btype.do",function (msg) {
            var $html = "";
            $.each(msg,function (i,n) {
                $html += "<option value='"+n.code+"'>"+n.codedesc+"</option>>";
            })
            $("#btype").html($html);
        },'json');
        $("#ff input[name='parentName']").val(parentName);
        $("#ff input[name='menuId']").val(parentId);
        $('#w').window('open');
    }else if(modeType == "edit"){
        $.ajax({
            url:'menu_'+parentId+'.do',
            type:'post',
            dataType:'json',
            success:function (msg) {
                if(msg.status){
                    var json = eval("(" + msg.data + ")");
                    $.post("btype.do",function (msg) {
                        var $html = "";
                        $.each(msg,function (i,n) {
                            if(json.btype==n.code){
                                $html += "<option value='"+n.code+"' selected>"+n.codedesc+"</option>>";
                            }else {
                                $html += "<option value='"+n.code+"'>"+n.codedesc+"</option>>";
                            }
                        })
                        $("#btypes").html($html);
                    },'json');
                    $("#update input[name='id']").val(json.id);
                    $("#update input[name='name']").val(json.name);
                    $("#update input[name='url']").val(json.uri);
                    $("#update input[name='sort']").val(json.sort);
                    // $("#update input[name='state'][value='"+json.state+"']").attr("checked",'checked');
                    $("#update input[name='mtype'][value='"+json.mtype+"']").attr("selected",true);
                    $("#update input[name='btype'][value='"+json.btype+"']").attr("selected",true);
                    $('#w_update').window('open');
                }
            }

        })
    }else if(modeType == "delete"){
        $.messager.confirm('消息提示', '确定要删除?', function(r){
            if (r){
                $.ajax({
                    url:'delete_menu_'+parentId+'.do',
                    type:'post',
                    dataType:'json',
                    success:function (msg) {
                        if(msg.status){
                            $.messager.alert('消息提示',msg.info);
                            window.location.reload();
                        }else{
                            $.messager.alert('消息提示',msg.info,'error');
                        }
                    }
                });
            }
        });
    }
}

function clearForm() {
    $('#w,#w_btn,#w_update').window('close');
}

//打开权限操作窗体
function hendle_save() {
    $("#f_btn")[0].reset();
    $("#f_btn input[name='id']").val(" ");
    $("#f_btn input[name='menuId']").val(treeCodeCurNode.id);
    $('#w_btn').window('open');
}

//菜单新增
function submit_form_menu(type) {
    $("#"+type).form('submit',{
        url:"add_update_menu.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");
            if ( json.status) {
                $.messager.alert("提示信息", "保存成功");
                //刷新当前页面
                clearForm();
                sys_menu(treeSystemNode.id);
            }else {
                $.messager.alert("提示信息", "保存失败");
            }
        },
        error: function () {
            $.messager.alert("提示信息", "保存失败!");
        }
    });
}

//操作权限新增
function submit_form_menu_hright() {
    $("#f_btn").form('submit',{
        url:"add_update_menuhrights.do",
        onSubmit: function () {
            return true;
        },
        success: function (msg) {
            var json = eval("(" + msg + ")");
            if (json.status) {
                $.messager.alert("提示信息", "保存成功");
                $('#w_btn').window('close');
                treeOnClick($("#f_btn #menuId").val());
            }else {
                $.messager.alert("提示信息", "保存失败");
            }
        },
        error: function () {
            $.messager.alert("提示信息", "保存失败!");
        }
    });
}

function update_hright() {
    var $id = treeHrightContextMenu.id;
    if(null==$id){
        $.messager.alert("提示信息", "请选择需要修改的操作权限!");
        return false;
    }
    $.ajax({
        url:'update_hrights_'+$id+'.do',
        type:'post',
        dataType:'json',
        success:function (msg) {
            var json = eval("(" + msg.data + ")");
            $("#f_btn input[name='id']").val(json.id);
            $("#f_btn input[name='name']").val(json.name);
            $("#f_btn input[name='code']").val(json.code);
            $("#f_btn input[name='sort']").val(json.sort);
            // $("#f_btn input[name='state'][value='"+json.state+"']").attr("checked",'checked');
            $('#w_btn').window('open');
        }
    })
}

//删除操作权限
function delete_hrights() {

    $.messager.confirm('消息提示', '确定要删除?', function(r){
        if(r){
            var $id = treeHrightContextMenu.id;
            if(null==$id){
                $.messager.alert("提示信息", "请选择需要修改的操作权限!");
                return false;
            }
            $.ajax({
                url:'delete_hrights_'+$id+'.do',
                type:'post',
                dataType:'json',
                success:function (msg) {
                    if(msg.status){
                        $.messager.alert("提示信息", "删除成功");
                        menuRights($("#menuId").val());
                    }else{
                        $.messager.alert("提示信息", "删除失败",'error');
                    }
                }
            })
        }
    })
}

function menu_state() {
    $.ajax({
        type:'post',
        url:'update_menu_state.do',
        data:{'menuId':treeCodeCurNode.id},
        dataType:'json',
        success:function (msg) {
            if(msg.status){
                if(msg.info=='1'){
                    $("#maintabs-update .menu-text").text("禁用");
                }else{
                    $("#maintabs-update .menu-text").text("启用");
                }
                var treeObj = $.fn.zTree.getZTreeObj("treeCode");
                treeCodeCurNode.s = msg.info;
                treeObj.updateNode(treeCodeCurNode);
            }
        }
    })
}

function hright_state() {
    $.ajax({
        type:'post',
        url:'update_hright_state.do',
        data:{"hrights":treeHrightContextMenu.id},
        dataType:'json',
        success:function (msg) {
            if(msg.status){
                if(msg.info==1){
                    $("#hrights_state .menu-text").text("禁用");
                }else{
                    $("#hrights_state .menu-text").text("启用");
                }
                var treeObj = $.fn.zTree.getZTreeObj("handle_menu");
                treeHrightContextMenu.s = msg.info;
                treeObj.updateNode(treeHrightContextMenu);
            }
        }
    })
}




// 在ztree上的右击事件
function rightClick(event, treeId, treeNode,id) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        showRMenu("root", event.clientX, event.clientY,id);
    } else if (treeNode && !treeNode.noR) {
        showRMenu("node", event.clientX, event.clientY,id);
    }
}
//显示右键菜单
function showRMenu(type, x, y,id) {
    $("#"+id).show();
    $("#"+id).css({"top":y+"px", "left":x+"px", "visibility":"visible"}); //设置右键菜单的位置、可见
    // $("body").bind("mousedown", function () {
    //     if (!(event.target.id == id || $(event.target).parents("#"+id).length>0)) {
    //         $("#"+id).css({"visibility" : "hidden"});
    //     }
    // });
}

$(document).ready(function(){
    var height1 = $(window).height()-20;
    $("#cc").attr("style","width:100%;height:"+height1+"px");
    $("#cc").layout("resize",{
        width:"100%",
        height:height1+"px"
    });
});
$(window).resize(function(){
    var height1 = $(window).height()-30;
    $("#cc").attr("style","width:100%;height:"+height1+"px");
    $("#cc").layout("resize",{
        width:"100%",
        height:height1+"px"
    });
});