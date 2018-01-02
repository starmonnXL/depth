<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章管理</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
    <script type="text/javascript" src="<%=basePath%>resources/js/layer/layer.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/global/crossdomain.js"></script>
    <style>
        .datebox span {
            margin-top: -6px;
        }
    </style>
</head>
<body>
<div id="cc" class="easyui-layout" style="min-width:800px;width:100%;height:100%;">
    <%-- 栏目列表部分 --%>
    <div data-options="region:'west',title:'&nbsp;&nbsp;栏目列表',split:true,iconCls:'icon-001'" style="width:260px;" id="catalogRe">
        <div title="About" style="padding:10px">
            <ul id="handle_menu" class="ztree"></ul>
        </div>
    </div>
    <%-- 文章管理部分 --%>
    <div data-options="region:'center',title:'&nbsp;&nbsp;文章列表',split:true,iconCls:'icon-001'"
         style="border: none;" id="article">
        <div class="search-div" id="articletools">
            <input type="hidden" value="${sid}" id="sid" />
            <input value="" id="cId" type="hidden"/>
            <input value="0" id="cType" type="hidden"/>
            <input type="hidden" id="fileAccessUrl" value="${fileAccessUrl }">
            <div class="search-content">
                <span>
                    <label>标题：</label>
                    <input id="title" name="title" type="text" placeholder="标题关键字" class="easyui-validatebox" />
                </span>
                <span>
                    <label>更新时间：</label>
                    <input class="easyui-datebox" name="timeBegin" id="timeBegin" style="height: 30px;line-height: 30px;"
                           data-options="buttons:buttons,editable:false"/>
                    ~~<input class="easyui-datebox" name="timeEnd" id="timeEnd" style="height: 30px;line-height: 30px;"
                            data-options="buttons:buttons,editable:false,validType:'equaldDate[\'#timeBegin\']'" />
                </span>
                <span>
                    <button class="btn btn-primary btn-small" type="submit" id="articleSearchBtn">查询</button>
                </span>
            </div>
            <div class="search-toolbar">
                <span class="span-left" id="handle-bar">
                    <a href="javascript:void(0)" class="easyui-linkbutton data-author" data-code="ADD" iconCls="icon-add"
                       plain="true" id="addArticle">添加文章</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton data-author" data-code="UPDATE" iconCls="icon-edit"
                       plain="true" id="updateArticle">修改文章</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton data-author" data-code="DELETE" iconCls="icon-remove"
                       plain="true" id="deleteArticle">删除文章</a>
                </span>
            </div>
        </div>
        <table id="article-datagrid">
            <thead>
                <tr>
                    <th field="ck" checkbox="true"></th>
                    <th field="id" width="100">文章编号</th>
                    <th field="title" width="100">文章标题</th>
                    <th field="click" width="100">点击量</th>
                    <th field="aa01" width="100" >发布人</th>
                    <th field="status" width="100" formatter="statusFormat">文章状态</th>
                    <th field="notHtml" width="100" formatter="htmlFormat">静态状态</th>
                    <th field="addtime" width="100">更新时间</th>
                    <th field="articleopt" width="150" formatter="articleOperFormat">操作</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/commons/include.footer.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/article/article.js"></script>
</body>
</html>
