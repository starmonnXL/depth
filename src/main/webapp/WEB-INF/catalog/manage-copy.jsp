<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--response.setHeader("Access-Control-Allow-Origin", "*");--%>
<%--response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");--%>
<%--response.setHeader("Access-Control-Allow-Headers", "POWERED-BY-MENGXIANHUI");--%>
<%--response.setHeader("Access-Control-Max-Age","30");--%>
<%--%>--%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>菜单管理</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
    <script src="<%=basePath%>resources/js/global/jscolor.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/global/crossdomain.js"></script>
    <style>
        .jscolor{
            width:80px;
        }
        .tab_yp tr{
            height:50px;
        }
    </style>
</head>
<body>
<input type="hidden" id="fileAccessUrl" value="${fileAccessUrl }">
<div id="cc" class="easyui-layout" style="min-width:800px;width:100%;height:100%;">
    <input type="hidden" id="fileprifix" value="${fileprifix }">
    <div data-options="region:'west',title:'&nbsp;&nbsp;栏目管理',split:true,iconCls:'icon-home'" style="width:260px;" id="catalogRe">
        <div title="About" style="padding:10px">
            <ul id="handle_menu" class="ztree"></ul>
        </div>
    </div>

    <div data-options="region:'center',title:'&nbsp;&nbsp;栏目新增',split:true,iconCls:'icon-user'" style="border: none;" id="cataLog">
        <div class="easyui-tabs" id="tabsAdd">
            <div title="基本信息" style="padding:10px">
                <form id="addCatalog" method="post">
                    <input type="hidden" name="reId">
                    <input type="hidden" name="topId">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="add_catalog">
                    <table cellpadding="5" class="tab_yp">
                        <tr id="choseCata">
                            <td>所属栏目:</td>
                            <td id="addCaOne" style="padding-top: 15px;">
                                <select id="articleEditCc" style="width: 315px;height: 28px;line-height: 28px;"></select>
                                <div id="articleEditSp">
                                    <div title="About" style="padding:10px">
                                        <ul id="catalogEditId" class="ztree "></ul>
                                    </div>
                                </div>
                                <p style="color:red;padding-left: 5px;">*注：保存之后，则无法修改，请确保栏目选择正确</p>
                            </td>
                            <td id="addCaTwo" style="display: none;">
                                <input type="text" disabled="disabled" name="parentName" style="width: 300px;">
                            </td>
                        </tr>
                        <tr>
                            <td>*栏目类型:</td>
                            <td>
                                <select class="easyui-validatebox validatebox-text validatebox-invalid" name="type"  style="width:315px;">
                                    <option value="0">栏目</option>
                                    <option value="1">单页</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>*栏目名称:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[0,30]'" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目简称:</td>
                            <td><input style="width: 300px;" type="text" name="abbreviation" data-options="required:true,validType:'length[0,5]'"/></td>
                        </tr>
                        <tr>
                            <td>*栏目目录:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" name="dir" data-options="required:true,validType:'length[0,30]'" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目列表选项:</td>
                            <td>
                                <input type="radio" value="1" name="defaultType" checked="checked"/>链接到默认页
                                <input type="radio" value="2" name="defaultType"/>链接到列表第一页
                                <input type="radio" value="3" name="defaultType"/>首页
                                <input type="radio" value="4" name="defaultType"/>业态首页
                            </td>
                        </tr>
                        <tr>
                            <td>默认页名称:</td>
                            <td><input style="width: 300px;" type="text" name="defaultName" data-options="required:true,validType:'length[0,30]'" /></td>
                        </tr>
                        <tr>
                            <td>*排序:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" min="1" max="999" precision="0"  name="sort" data-options="required:true" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目状态:</td>
                            <td>
                                <input type="radio" value="0" name="isNavigation" checked="checked"/>普通
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="isNavigation"/>导航
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="isNavigation"/>咨询新闻
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="isNavigation"/>业态导航
                            </td>
                        </tr>
                        <tr>
                            <td>业态名称:</td>
                            <td><input style="width: 300px;" type="text" name="serviceName" data-options="required:true,validType:'length[0,5]'"/></td>
                        </tr>
                        <tr>
                            <td>功能名称:</td>
                            <td>
                                <input type="radio" value="0" name="functionName" checked="checked"/>无功能
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="functionName"/>查公司
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="functionName"/>申请贷款
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="functionName"/>查商标
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="4" name="functionName"/>服务预约
                            </td>
                        </tr>
                        <tr>
                            <td>功能跳转链接:</td>
                            <td><input style="width: 300px;" type="text" name="jumpUrl" data-options="required:true,validType:'length[0,50]'"/></td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('addCatalog');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
            <div title="TKD" style="padding:10px">
                <form id="addCatalogTdk" method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="tkd">
                    <table cellpadding="5" class="tab_yp">
                        <tr>
                            <td>页面标题:</td>
                            <td><input style="width: 300px;" type="text" name="pageTitle" /></td>
                        </tr>
                        <tr>
                            <td>页面关键字:</td>
                            <td><input style="width: 300px;" type="text" name="pageKeywords" /></td>
                        </tr>
                        <tr>
                            <td>页面描述:</td>
                            <td> <textarea  style="height: 150px;width: 500px;" name="pageDescription"></textarea></td>
                        </tr>

                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('addCatalogTdk');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
            <div title="栏目内容"  style="padding:10px">
                <form id="addCatalogContent" method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="content">
                    <textarea  id="editorCata" name="content"></textarea>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('addCatalogContent');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
        </div>

        <div class="easyui-tabs" id="tabs">
            <div title="基本信息" style="padding:10px">
                <form id="updateCatalog" method="post" >
                    <input type="hidden" name="reId">
                    <input type="hidden" name="topId">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="catalog">
                    <table cellpadding="5" class="tab_yp">
                        <tr>
                            <td>*栏目类型:</td>
                            <td>
                                <select class="easyui-validatebox validatebox-text validatebox-invalid" name="type" id="select_id" style="width:315px;">
                                    <option value="0">栏目</option>
                                    <option value="1">单页</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>*栏目名称:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[0,30]'" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目简称:</td>
                            <td><input style="width: 300px;" type="text" name="abbreviation" data-options="required:true,validType:'length[0,5]'"/></td>
                        </tr>
                        <tr>
                            <td>*栏目目录:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" name="dir" data-options="required:true,validType:'length[0,30]'" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目列表选项:</td>
                            <td>
                                <input type="radio" value="1" name="defaultType" checked="checked"/>链接到默认页
                                <input type="radio" value="2" name="defaultType"/>链接到列表第一页
                                <input type="radio" value="3" name="defaultType"/>首页
                                <input type="radio" value="4" name="defaultType"/>业态首页
                            </td>
                        </tr>
                        <tr>
                            <td>默认页名称:</td>
                            <td><input style="width: 300px;" type="text" name="defaultName" data-options="required:true,validType:'length[0,30]'" /></td>
                        </tr>
                        <tr>
                            <td>*排序:</td>
                            <td><input style="width: 300px;" class="easyui-validatebox" type="text" min="1" max="999" name="sort" id="sort" data-options="required:true" required="required"/></td>
                        </tr>
                        <tr>
                            <td>栏目状态:</td>
                            <td>
                                <input type="radio" value="0" name="isNavigation" checked="checked"/>普通
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="isNavigation"/>导航
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="isNavigation"/>咨询新闻
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="isNavigation"/>业态导航
                            </td>
                        </tr>
                        <tr>
                            <td>业态名称:</td>
                            <td><input style="width: 300px;" type="text" name="serviceName" data-options="required:true,validType:'length[0,5]'"/></td>
                        </tr>
                        <tr>
                            <td>功能名称:</td>
                            <td>
                                <input type="radio" value="0" name="functionName" checked="checked"/>无功能
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="functionName"/>查公司
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="functionName"/>申请贷款
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="functionName"/>查商标
                                &nbsp;&nbsp;&nbsp;<input type="radio" value="4" name="functionName"/>服务预约
                            </td>
                        </tr>
                        <tr>
                            <td>功能跳转链接:</td>
                            <td><input style="width: 300px;" type="text" name="jumpUrl" data-options="required:true,validType:'length[0,50]'"/></td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('updateCatalog');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
            <div title="TKD" style="padding:10px">
                <form id="updateCatalogTdk" method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="tkd">
                    <table cellpadding="5" class="tab_yp">
                        <tr>
                            <td>页面标题:</td>
                            <td><input style="width: 300px;" type="text" name="pageTitle" /></td>
                        </tr>
                        <tr>
                            <td>页面关键字:</td>
                            <td><input style="width: 300px;" type="text" name="pageKeywords" /></td>
                        </tr>
                        <tr>
                            <td>页面描述:</td>
                            <td> <textarea  style="height: 150px;width: 500px;" name="pageDescription"></textarea></td>
                        </tr>

                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('updateCatalogTdk');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
            <div title="栏目内容"  style="padding:10px">
                <form id="updateCatalogContent" method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="addtype" value="content">
                    <textarea  id="editorUpdate" name="content"></textarea>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0);" onclick="submit_form_cata('updateCatalogContent');" class="easyui-linkbutton">保存</a>
                    <a href="javascript:clearForm_cata();" class="easyui-linkbutton">取消</a>
                </div>
            </div>
        </div>

    </div>

    <div id="treeCodeContextMenu" class="easyui-menu" style="width:120px;">
        <c:if test="${fn:contains(hrightsCodes,'CATALOGADD')}">
            <div onclick="operationCata('addFirst')" data-options="iconCls:'icon-add'">新增一级栏目</div>
        </c:if>
        <c:if test="${fn:contains(hrightsCodes,'CATALOGADD')}">
            <div onclick="operationCata('addCata')" data-options="iconCls:'icon-add'" id="add-cala">新增栏目</div>
        </c:if>
        <c:if test="${fn:contains(hrightsCodes,'CATALOGUPDATE')}">
            <div id="treeMenuEditBtn" onclick="operationCata('update')" data-options="iconCls:'icon-edit'">修改栏目</div>
        </c:if>
        <c:if test="${fn:contains(hrightsCodes,'CATALOGSTATUS')}">
            <div id="maintabs-update" onclick="operationCata('status')" data-options="iconCls:'icon-blank'">启用</div>
        </c:if>
    </div>
</div>


<!-- 上传图片 -->
<div style="display: none;">
    <div id="fileOperatorBox">
    </div>
    <div id="fileUploadBtn">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="addFileIntro">添加选中文件</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="cancelAddFile">取消选择</a>
    </div>
</div>

<%@include file="/WEB-INF/commons/include.footer.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/catalog.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/public_ueditor.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/img_ueditor.js"></script>
</body>
</html>
