<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章添加</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
    <link rel="stylesheet" href="<%=basePath%>resources/css/article_update.css" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>resources/js/global/crossdomain.js"></script>
</head>
<body>
    <input type="hidden" value="${sid}" id="sid"/>
    <input type="hidden" value="${fileprifix}" id="fileprifix" />
    <input type="hidden" value="${catalogId}" id="catalogId"/>
    <input type="hidden" id="fileAccessUrl" value="${fileAccessUrl }">
    <form id="addArticle" method="post" class="easyui-form">
        <div id="articleInfo">
            <table id="articleTable" cellpadding="5">
                <tr>
                    <td class="first-td">选择栏目:</td>
                    <td class="second-td">
                        <select id="cId" name="catalogId" data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章标题:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="title" data-options="required:true,validType:'length[0,100]'"/>
                        <input class="jscolor" value="000000" name="titleColor" id="titleColor"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">副标题:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="title2" data-options="validType:'length[0,100]'"/>
                        <input class="jscolor" value="000000" name="title2Color" id="title2Color"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章简介:</td>
                    <td class="second-td">
                        <textarea name="summary"></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">外部链接:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="link" />
                    </td>
                </tr>
                <tr>
                    <td class="first-td">页面标题_T:</td>
                    <td class="second-td"><input class="easyui-validatebox input-text" type="text" name="pageTitle" data-options="validType:'length[0,80]'"/></td>
                </tr>
                <tr>
                    <td class="first-td">页面关键字_K:</td>
                    <td class="second-td">
                        <input type="text" class="easyui-validatebox input-text" name="pageKeywords" data-options="validType:'length[0,100]'" />
                    </td>
                </tr>
                <tr>
                    <td class="first-td">页面描述_D:</td>
                    <td class="second-td"><textarea name="pageDescription" maxlength="200"></textarea></td>
                </tr>
                <tr>
                    <td class="first-td">文章属性:</td>
                    <td class="second-td">
                        <ul>
                            <li class="flags">
                                <label for="f"><input type="checkbox" name="flags" value="f" id="f" />幻灯</label>
                            </li>
                            <li class="flags">
                                <label for="c"><input type="checkbox" name="flags" value="c" id="c" />推荐</label>
                            </li>
                            <li class="flags">
                                <label for="a"><input type="checkbox" name="flags" value="a" id="a" />广告</label>
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">首页显示位置:</td>
                    <td class="second-td">
                        <input type="radio" value="0" name="showHome" checked="checked" />默认
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="showHome"/>热搜
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="showHome"/>热点图
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="showHome"/>热点
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="4" name="showHome"/>业态推荐
                    </td>
                </tr>
                <tr>
                    <td class="first-td">业态首页显示位置:</td>
                    <td class="second-td">
                        <input type="radio" value="0" name="showOperation" checked="checked"/>默认
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="showOperation"/>热点图
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="showOperation"/>热点
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章标签:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text"
                               type="text" name="tags" data-options="validType:'length[0,100]'"/>&nbsp;&nbsp;(','号分开)
                    </td>
                </tr>
                <tr>
                    <td class="first-td">缩略图：</td>
                    <td class="second-td" valign="bottom">
                        <input name="picId" type="hidden"/>
                        <input name="picName" type="hidden" />
                        <img src="" id="pic" />
                        <br/>
                        <span>提示：前台显示大小,幻灯751*272,广告194*138</span>
                        <br/>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="uploadDialog(1)">上传</a>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">点击量:</td>
                    <td class="second-td"><input class="easyui-numberbox input-text-small"
                                                 type="text" name="click" data-options="min:0"/></td>
                </tr>
                <tr>
                    <td class="first-td">文章来源:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text-small" type="text" name="source"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">作者:</td>
                    <td class="second-td">
                        <input  class="easyui-validatebox input-text-small" type="text" name="writer"
                                data-options="validType:'length[0,10]'"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <a href="javascript:void(0);" id="next" class="easyui-linkbutton">下一步</a>
                            <a href="javascript:void(0);" id="closeInfo" class="easyui-linkbutton" onclick="closeTab()">取消</a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div id="articleContent">
            <div class="contentEditor">
                <textarea name="content" id="editor"></textarea>
            </div>
            <div class="buttons ">
                <a href="javascript:void(0);" id="back" class="easyui-linkbutton">上一步</a>
                <a href="javascript:void(0);" id="addSave" class="easyui-linkbutton">保存</a>
                <a href="javascript:void(0);" id="close" class="easyui-linkbutton" onclick="closeTab()">取消</a>
            </div>
        </div>
    </form>
    <div style="display: none">
        <div id="fileOperatorBox">
        </div>
        <div id="fileUploadBtn">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="addFileIntro">添加选中文件</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="cancelAddFile">取消选择</a>
        </div>
    </div>
    <%@include file="/WEB-INF/commons/include.footer.jsp"%>

    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/global/jscolor.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/layer/layer.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/img_ueditor.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/article/article_add.js"></script>
</body>
</html>