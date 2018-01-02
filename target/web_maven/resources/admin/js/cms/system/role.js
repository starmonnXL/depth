/**
 * Created by ranqibing on 2016/11/30.
 */
var treeRoleCurNode;
var treeMenuCurNode;
$(function () {
    //加载系统角色信息
    loadSystemRole();
    //操作权限复选框选中事件
    $(document).on("click", "#btn_save", function () {
        var treeObj = $.fn.zTree.getZTreeObj("treeCode");
        var $treeChecked = treeObj.getCheckedNodes(true);
        var data = [];
        $.each($treeChecked, function (i, n) {
            var parmar = {};
            parmar.id = n.id;
            parmar.type = n.type;
            data.push(parmar);
        })
        $.post("save_menu.do", {"data": JSON.stringify(data), "roleId": treeRoleCurNode.id}, function (msg) {
            if (msg.status) {
                $.messager.alert('提示消息', '保存成功!');
            } else {
                $.messager.alert('提示消息', '保存失败!', 'error');
            }
        })
    })
})

function loadSystemRole() {
    $.fn.zTree.init($("#system"), {
        async: {
            enable: true,
            url: "system_role.do", // Ajax 获取数据的 URL 地址
            dataFilter: function (treeId, parentNode, responseData) {
                return eval('(' + responseData.data + ')');
            }
        },
        callback: {
            view: {
                showLine: false
            },
            onClick: function (event, treeId, treeNode) {
                var $parent = treeNode.getParentNode();
                $("#btn_save").parent().css("display", "none");
                $("#treeCode li").remove();
                if(null!=$parent){
                    treeRoleCurNode = treeNode;
                    sys_menu(treeNode.id);
                }
            },
            onRightClick: function (event, treeId, treeNode) {
                var $parent = treeNode.getParentNode();
                if (null != $parent) {
                    if(treeNode.s=='1'){
                        $("#maintabs-update .menu-text").text("禁用");
                    }else{
                        $("#maintabs-update .menu-text").text("启用");
                    }
                    treeRoleCurNode = treeNode;
                    rightClick(event, treeId, treeNode, 'treeCodeContextRole');
                } else {
                    treeRoleCurNode = treeNode;
                    rightClick(event, treeId, treeNode, 'treeCodeContextRoleAdd');
                }
            }
        }
    });
}

//加载树并绑定事件
function sys_menu($sys) {
    $.fn.zTree.init($("#treeCode"), {
        async: {
            enable: true,
            url: "menu_role_" + $sys + ".do", // Ajax 获取数据的 URL 地址
            dataFilter: function (treeId, parentNode, responseData) {
                $("#btn_save").parent().css("display", "block");
                return eval('(' + responseData.data + ')');
            }
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    });
}

function operation(modeType) {
    var parentId = treeRoleCurNode.id;
    if (modeType == "add") {
        $("#roleAddForm")[0].reset();
        $("#roleAddForm input[name='sid']").val(parentId);
        $.post("role_type.do", function (msg) {
            var $html = "";
            $.each(msg, function (i, n) {
                $html += "<option value='" + n.code + "' selected>" + n.codedesc + "</option>>";
            })
            $("#type").html($html);
        }, 'json');
        $('#w').window('open');
    } else if (modeType == "edit") {
        $.ajax({
            url: 'role_' + parentId + '.do',
            type: 'post',
            dataType: 'json',
            success: function (msg) {
                if (msg.status) {
                    var json = eval("(" + msg.data + ")");
                    $.post("role_type.do", function (msg) {
                        var $html = "";
                        $.each(msg, function (i, n) {
                            if (json.type == n.code) {
                                $html += "<option value='" + n.code + "' selected>" + n.codedesc + "</option>>";
                            } else {
                                $html += "<option value='" + n.code + "'>" + n.codedesc + "</option>>";
                            }
                        });
                        $("#type").html($html);
                    }, 'json');
                    $("#roleAddForm input[name='id']").val(json.id);
                    $("#roleAddForm input[name='name']").val(json.name);
                    $("#roleAddForm input[name='sort']").val(json.sort);
                    $("#roleAddForm input[name='state'][value='" + json.state + "']").attr("checked", "checked");
                    if(null!=json.validity){
                        var date = new Date(json.validity);
                        $("#roleAddForm input[name='validity']").val(date.pattern("yyyy-MM-dd hh:mm:ss"));
                        $("#roleAddForm input[autocomplete='off']").val(date.pattern("yyyy-MM-dd hh:mm:ss"));
                    }
                    $('#w').window('open');
                }
            }
        })
    } else if (modeType == "delete") {
        $.messager.confirm('消息提示', '确定要删除?', function (r) {
            if (r) {
                $.post('delete_' + parentId + '.do', function (msg) {
                    var json = eval("(" + msg + ")");
                    if (json.state) {
                        $.messager.alert("提示信息", msg.info);
                    } else {
                        $.messager.alert("提示信息", msg.info, 'error');
                    }
                }, 'json')
            }
        });
    }
}

function submit_form_role() {
    $("#roleAddForm").form('submit', {
        url: "add_update_role.do",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (response) {
            var json = eval("(" + response + ")");
            if (json.status) {
                $.messager.alert("提示信息", "保存成功");
                //刷新当前页面
                loadSystemRole();
                clearForm();
            } else {
                $.messager.alert("提示信息", "保存失败");
            }
        },
        error: function () {
            $.messager.alert("提示信息", "保存失败!");
        }
    });
}


function update_state() {
    $.ajax({
        type:'post',
        url:'update_state.do',
        data:{'roleId':treeRoleCurNode.id},
        dataType:'json',
        success:function (msg) {
            if(msg.status){
                if(msg.info==1){
                    $("#maintabs-update .menu-text").text("禁用");
                }else{
                    $("#maintabs-update .menu-text").text("启用");
                }
                var treeObj = $.fn.zTree.getZTreeObj("treeCode");
                treeRoleCurNode.s = msg.info;
                treeObj.updateNode(treeRoleCurNode);
            }
        }
    })
}


function clearForm() {
    $("#w").window("close")
}

// 在ztree上的右击事件
function rightClick(event, treeId, treeNode, id) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        showRMenu("root", event.clientX, event.clientY, id);
    } else if (treeNode && !treeNode.noR) {
        showRMenu("node", event.clientX, event.clientY, id);
    }
}
//显示右键菜单
function showRMenu(type, x, y, id) {
    $("#" + id).show();
    $("#" + id).css({"top": y + "px", "left": x + "px", "visibility": "visible"}); //设置右键菜单的位置、可见
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


/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern=function(fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
        "H+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    var week = {
        "0" : "/u65e5",
        "1" : "/u4e00",
        "2" : "/u4e8c",
        "3" : "/u4e09",
        "4" : "/u56db",
        "5" : "/u4e94",
        "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

