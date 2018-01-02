(function($,window,document){
	$.fn.mydatagrid = function(options){
		$.extend($.fn.mydatagrid.options,options||null);
		var param = {};
		if($.fn.mydatagrid.options.queryParam != null && $.fn.mydatagrid.options.queryParam){
			param = $.fn.mydatagrid.options.queryParam;
		}
		return this.each(function(){
			var $self_obj = $(this);
			$(this).datagrid({
				url:$.fn.mydatagrid.options.url,
				fitColumns: $.fn.mydatagrid.options.fitColumns,
				fit: $.fn.mydatagrid.options.fit,
				pagination: true,
				singleSelect: $.fn.mydatagrid.options.singleSelect,
				toolbar:$.fn.mydatagrid.options.toolbar,
				queryParams: param,
				onLoadSuccess:function(data){
					var length = $self_obj.find("thead tr th").length;
					var namemessage = $($self_obj.find("thead tr th")[0]).attr("field");
					var norows = {};
					norows[namemessage] = '<div style="padding:15px 0;text-align:center;color:red">没有相关记录！</div>';
					if(data.status){
						if (data.total == 0) {
							$('.datagrid-body').html(norows[namemessage]);
							//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
//			                $(this).datagrid('appendRow',norows).datagrid('mergeCells', { index: 0, field:namemessage, colspan: length})
							//隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui datagrid没有提供相关方法隐藏导航条
							$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
							return;
						} else {
							datenum=0;
							//如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
							$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
							$('.updateBtns').linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
							$('.disenableBtns').linkbutton({text:'禁用',plain:true,iconCls:'icon-no'});
							$('.enableBtns').linkbutton({text:'启用',plain:true,iconCls:'icon-ok'});
							$('.removeBtns').linkbutton({text:'删除',plain:true,iconCls:'icon-mini-cancel'});
							$('.userAuthorBtns').linkbutton({text:'授权角色',plain:true,iconCls:'icon-users'});
							$('.unEditBtns').linkbutton({text:'禁用编辑',plain:true,iconCls:'icon-edit-no'});
							$('.editBtns').linkbutton({text:'启用编辑',plain:true,iconCls:'icon-edit-yes'});
							$('.dowmBtns').linkbutton({text:'下载',plain:true,iconCls:'icon-down'});
							$('.handle-td').defineAuthor({
								authority:hrightsCodes
							});
						}
					} else {
						Msg.alert("提示信息", "您没有查询数据的权限!",'info');
						return;
					}
				},
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!cmenu){
						createColumnMenu();
					}
					cmenu.menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});

			/**begin开始**/
			var cmenu;
			function createColumnMenu(){
				cmenu = $('<div/>').appendTo('body');
				cmenu.menu({
					onClick: function(item){
						if (item.iconCls == 'icon-ok'){
							$self_obj.datagrid('hideColumn', item.name);
							cmenu.menu('setIcon', {
								target: item.target,
								iconCls: 'icon-empty'
							});
						} else {
							$self_obj.datagrid('showColumn', item.name);
							cmenu.menu('setIcon', {
								target: item.target,
								iconCls: 'icon-ok'
							});
						}
					}
				});
				var fields = $self_obj.datagrid('getColumnFields');
				for(var i=0; i<fields.length; i++){
					var field = fields[i];
					var col = $self_obj.datagrid('getColumnOption', field);
					cmenu.menu('appendItem', {
						text: col.title,
						name: field,
						iconCls: 'icon-ok'
					});
				}
			}
			/**begin结束**/

		});
	};
	$.fn.mydatagrid.options = {
		url:null, //地址
		queryParam:null,//传递的字段
		searchBtnId:null,//搜索的按钮
		fitColumns:true,
		fit:true,
		singleSelect:true,//是否单选
		toolbar:null,//绑定操作栏
		datenum:'' //layer图片预览开始图片序号初始化
	};
})(jQuery,window,document);