var treeObj;
//加载菜单树并绑定事件
$(function () {
	$("#cataLog").panel({region:'center',title:'&nbsp;&nbsp;'});
	$("#tabsAdd").hide();
	$("#tabs").hide();
	sys_menu();

	operationCata("addFirst");

	$('#articleEditCc').combo({
		editable:false,
	});

	$('#articleEditSp').appendTo($('#articleEditCc').combo('panel'));

});

// 在ztree上的右击事件
function rightClick(event, treeId, treeNode,id) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		showRMenu("root", event.clientX, event.clientY,id);
	} else if (treeNode && !treeNode.noR) {
		showRMenu("node", event.clientX, event.clientY,id);
	}

}

//显示右键菜单 148
function showRMenu(type, x, y,id) {

    var height = $(window).height() - y;

    var treeMenuHeight = $("#treeCodeContextMenu").height();

    if(height < treeMenuHeight){
       y = y-(treeMenuHeight - height*0.7)
      //  alert(height);
    }

	$("#"+id).show();
	$("#"+id).css({"top":y+"px", "left":x+"px", "visibility":"visible","margin-bottom":"200px"}); //设置右键菜单的位置、可见
}

var firstNodel;
//右键菜单的操作方法
function operationCata (modeType) {

	if (modeType == "addCata" ) {		//新增栏目
		var parentId = treeCodeCurNode.id
		getParentNodes(treeCodeCurNode);
		var topId = firstNodel.id;
		var parentName = treeCodeCurNode.name;

		addTree();

		$("#tabsAdd").tabs("select",0) ;		//每一次新增都默认选中第一个选项卡
		$("#addCatalog")[0].reset();
		$("#addCatalog #choseCata").show();

		$("#addCaOne").show();
		$("#addCaTwo").hide();

		$("#addCatalog input[name='parentName']").val(parentName);
		$("#addCatalog input[name='reId']").val(parentId);
		$("#addCatalog input[name='topId']").val(topId);

		$('#articleEditCc').combo('setValue', parentId).combo('setText', parentName).combo('hidePanel');

		$("#addCatalog input[name='id']").val("");
		$("#addCatalogTdk input[name='id']").val("");
		$("#addCatalogContent input[name='id']").val("");

		$.post("findDir_"+parentId+".do",function (msg) {
			$("#addCatalog input[name='dir']").val(msg.dir);
		},'json');

		$("#cataLog").panel({region:'center',title:'&nbsp;&nbsp;栏目'});

		$("#tabsAdd").show();
		$("#tabs").hide();
	}else if(modeType == "addFirst" ) {		//新增一级栏目
		$("#tabsAdd").tabs("select",0) ;		//每一次新增都默认选中第一个选项卡
		$("#addCatalog")[0].reset();
		$("#addCatalog #choseCata").hide();
		$("#addCatalog input[name='reId']").val("0");
		$("#addCatalog input[name='topId']").val("0");

		$("#addCatalog input[name='id']").val("");
		$("#addCatalogTdk input[name='id']").val("");
		$("#addCatalogContent input[name='id']").val("");
		$("#addCatalog input[name='dir']").val("");

		$("#cataLog").panel({region:'center',title:'&nbsp;&nbsp;一级栏目'});
		$("#tabsAdd").show();
		$("#tabs").hide();
	}else if(modeType == "update"){
		var parentId = treeCodeCurNode.id

		$("#tabs").tabs("select",0) ;		//每一次编辑都默认选中第一个选项卡

		$.ajax({
			url:'catalog_'+parentId+'.do',
			type:'post',
			dataType:'json',
			success:function (msg) {
				if(msg.status){
					var json = eval("(" + msg.data + ")");

					//基本信息
					$("#updateCatalog input[name='reId']").val(json.reId);
					$("#updateCatalog input[name='topId']").val(json.topId);
					$("#updateCatalog input[name='id']").val(json.id);
					$("#updateCatalog input[name='name']").val(json.name);
					$("#updateCatalog input[name='dir']").val(json.dir);
					$("#updateCatalog input[name='defaultType'][value='"+json.defaultType+"']").prop("checked",'checked');
					$("#updateCatalog #select_id option[value='"+json.type+"']").prop("selected", true);
					$("#updateCatalog input[name='defaultName']").val(json.defaultName);
					$("#updateCatalog input[name='serviceName']").val(json.serviceName);
					$("#updateCatalog input[name='functionName'][value='"+json.functionName+"']").prop("checked",'checked');
					$("#updateCatalog input[name='jumpUrl']").val(json.jumpUrl);

					$("#updateCatalog input[name='abbreviation']").val(json.abbreviation);

					$("#updateCatalog input[name='isNavigation'][value='"+json.isNavigation+"']").prop("checked",'checked');

					$("#updateCatalog #sort").val(json.sort);
					$("#updateCatalog input[name='sort']").val(json.sort);

					//tkd
					$("#updateCatalogTdk input[name='id']").val(json.id);
					$("#updateCatalogTdk input[name='pageTitle']").val(json.pageTitle);
					$("#updateCatalogTdk input[name='pageKeywords']").val(json.pageKeywords);
					$("#updateCatalogTdk input[name='pageDescription']").text(json.pageDescription);

					//内容
					$("#updateCatalogContent input[name='id']").val(json.id);
					if(json.content != null && json.content != ""){
						UE.getEditor('editorUpdate').setContent(json.content);
					}else{
						UE.getEditor('editorUpdate').setContent("");
					}

					$("#cataLog").panel({region:'center',title:'&nbsp;&nbsp;编辑栏目'});

					$("#tabsAdd").hide();
					$("#tabs").show();
				}
			}
		})
	}else if(modeType == "deleteCata"){
		var parentId = treeCodeCurNode.id

		$.messager.confirm('消息提示', '确定要删除?', function(r){
			if (r){
				$.ajax({
					url:'delete_catalog_'+parentId+'.do',
					type:'post',
					dataType:'json',
					success:function (msg) {
						if(msg.status){
							$.messager.alert('消息提示',msg.info);
							//$("#catalogRe").panel('refresh');
							//window.location.reload();
						}else{
							$.messager.alert('消息提示',msg.info,'error');
						}
					}
				});
			}
		});
	}else if(modeType == "status"){
		var parentId = treeCodeCurNode.id

		$.ajax({
			url:'status_catalog_'+parentId+'.do',
			type:'post',
			dataType:'json',
			success:function (msg) {
				if(msg.status){
					var cata = eval("(" + msg.data + ")");
					var node = treeObj.getNodeByParam("id", parentId, null);
					//表示修改
					node.status = cata.status;
					treeObj.setting.view.fontCss = setFontCss("handle_menu",node);
					treeObj.updateNode(node);

					$.messager.alert('消息提示',msg.info);

				}else{
					$.messager.alert('消息提示',msg.info,'error');
				}
			}
		});
	}
}

//栏目新增
function submit_form_cata(type) {
	var ref = true;
	if(type == "addCatalogTdk"){
		var id = $("#addCatalogTdk input[name='id']").val();
		if(id == null || id == ""){
			$.messager.alert("提示信息", "请先保存【基本信息】");
			ref = false;
		}
	}

	if(type == "addCatalogContent"){
		var id = $("#addCatalogContent input[name='id']").val();
		if(id == null || id == ""){
			$.messager.alert("提示信息", "请先保存【基本信息】");
			ref = false;
		}
	}

	if(type == "addCatalog" || type == "updateCatalog"){
		var sort;
		var dir;
		if(type == "updateCatalog"){
			sort = $("#updateCatalog input[name='sort']").val();
			dir = $("#updateCatalog input[name='dir']").val();
		}else{
			sort = $("#addCatalog input[name='sort']").val();
			dir = $("#addCatalog input[name='dir']").val();
		}

		var pat = /^[0-9]*[1-9][0-9]*$/;
		if(sort != null && sort != "" && !pat.test(sort)){
			$.messager.alert("提示信息", "排序字段只能是正整数");
			ref = false;
		}

		var pas = /^[\u4e00-\u9fa5a-zA-Z\d]*$/;
		if(dir != null && dir != "" && !pas.test(dir.substring(0,1))){
			$.messager.alert("提示信息", "栏目目录第一个字符只能是数字、字母、汉字");
			ref = false;
		}

		if(dir != null && dir != "" && dir.substring(dir.length-1,dir.length+1) != "/"){
			$.messager.alert("提示信息", "栏目目录必须以 ‘/’ 结尾");
			ref = false;
		}
	}

	if(ref){
		var isValid = $("#"+type).form('validate');
		if(isValid){
			$.post(
				"add_update_catalog.do",
				$("#"+type).serialize(),
				function(response) {
					var cata = eval("(" + response.data + ")");
					if ( response.status) {

						var cataId = $("#addCatalog input[name='id']").val();

						var node = treeObj.getNodeByParam("id", cata.id, null);		//获取当前节点

						var parentNode = treeObj.getNodeByParam("id", cata.pid, null);		//获取当前节点的父节点

						if(type == "addCatalog"){
							if(cataId != null && cataId != ""){
								//表示修改
								node.name = cata.name;
								node.type = cata.type;
								treeObj.updateNode(node);
							}else{
								//表示新增
								treeObj.setting.view.fontCss = setFontCssAdd("handle_menu",node);		//新增的节点设置为绿色
								treeObj.addNodes(parentNode, cata);
								$("#catalogRe").panel('refresh');

								$("#addCaOne").hide();
								$("#addCaTwo").show();
							}
						}

						if(type == "updateCatalog"){
							//表示修改
							node.name = cata.name;
							node.type = cata.type;
							treeObj.updateNode(node);
						}

						$("#addCatalog input[name='id']").val(cata.id);
						$("#addCatalogTdk input[name='id']").val(cata.id);
						$("#addCatalogContent input[name='id']").val(cata.id);



						$.messager.alert("提示信息", response.info,"info",function () {
							if(type == "addCatalog"){
								$("#tabsAdd").tabs("select",1) ;
							}
							if(type == "addCatalogTdk"){
								$("#tabsAdd").tabs("select",2) ;
							}
							if(type == "updateCatalog"){
								$("#tabs").tabs("select",1) ;
							}
							if(type == "updateCatalogTdk"){
								$("#tabs").tabs("select",2) ;
							}
						});

					}else {
						$.messager.alert("提示信息", response.info,'error');
					}
				},
				"json"
			);
		}
	}
}

function clearForm_cata() {
	$('#w_cata,#w_btn_cata,#w_update_cata').window('close');

	$("#catalogRe").panel('refresh');

	$("#cataLog").panel({region:'center',title:'&nbsp;&nbsp;'});
	$("#tabsAdd").hide();
	$("#tabs").hide();
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
};

function setFontCssAdd(treeId, treeNode) {
    var color = {color:"#3CB371"};
    return color;
};

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
				treeObj.expandAll(true);
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

function addTree() {
	$.fn.zTree.init($("#catalogEditId"),{
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
			onClick: function (event, treeId, treeNode) {
				treeCodeCurNode = treeNode;
				var id = treeNode.id;
				var name = treeNode.name;
				$('#articleEditCc').combo('setValue', id).combo('setText', name).combo('hidePanel');

				$("#addCatalog input[name='parentName']").val(name);

				getParentNodes(treeNode);
				var topId = firstNodel.id;
				$("#addCatalog input[name='reId']").val(id);
				$("#addCatalog input[name='topId']").val(topId);

				$.post("findDir_"+id+".do",function (msg) {
					$("#addCatalog input[name='dir']").val(msg.dir);
				},'json');
			}
		}
	});
}

//根据点击的栏目，获取栏目下的文章
function cataRights() {
	operationCata('update');
}

//判断当前选择的节点是否有父级节点，用于获取顶级节点
function getParentNodes(node){
	var node1 = node.getParentNode();
	if(node1!=null){
		getParentNodes(node1);
	}else{
		//根节点
		firstNodel = node;
	}
}