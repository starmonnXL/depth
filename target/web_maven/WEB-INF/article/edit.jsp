<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文章修改</title>
    <%@ include file="/WEB-INF/commons/include.head.jsp" %>
    <link rel="stylesheet" href="<%=basePath%>resources/css/article_update.css" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>resources/js/global/crossdomain.js"></script>
</head>
<body>
    <form id="editArticle" method="post" class="easyui-form">
        <input type="hidden" value="${article.catalogId}" id="catalogId">
        <input type="hidden" value="${article.id}" name="id"/>
        <input type="hidden" value="${sid}" id="sid"/>
        <input type="hidden" value="${fileprifix}" id="fileprifix" />
        <input type="hidden" id="fileAccessUrl" value="${fileAccessUrl }">
        <div id="articleInfo">
            <table id="articleTable" cellpadding="5">
                <tr>
                    <td class="first-td">栏目:</td>
                    <td class="second-td">
                        <select id="cId" name="catalogId" data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章标题:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="title" value="${article.title}"
                               data-options="required:true,validType:'length[0,100]'"/>
                        <input class="jscolor" value="${article.titleColor}" name="titleColor" />
                    </td>
                </tr>
                <tr>
                    <td class="first-td">副标题:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="title2" value="${article.title2}"
                               data-options="validType:'length[0,100]'"/>
                        <input class="jscolor" value="${article.title2Color}" name="title2Color" id="title2Color"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章简介:</td>
                    <td class="second-td"><textarea name="summary">${article.summary}</textarea></td>
                </tr>
                <tr>
                    <td class="first-td">外部链接:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="link" value="${article.link}" />
                    </td>
                </tr>
                <tr>
                    <td class="first-td">页面标题_T:</td>
                    <td><input type="text" class="easyui-validatebox input-text" name="pageTitle" value="${article.pageTitle}" data-options="validType:'length[0,80]'"/></td>
                </tr>
                <tr>
                    <td class="first-td">页面关键字_K:</td>
                    <td>
                        <input type="text" class="easyui-validatebox input-text" name="pageKeywords" value="${article.pageKeywords}"
                               data-options="validType:'length[0,100]'"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">页面描述_D:</td>
                    <td class="second-td"><textarea name="pageDescription" maxlength="200">${article.pageDescription}</textarea></td>
                </tr>
                <tr>
                    <td class="first-td">文章属性:</td>
                    <td class="second-td">
                        <label class="flags" for="f">
                            <input type="checkbox" id="f" name="flags" value="f" <c:if test="${fn:contains(article.flags,'f')}">
                                checked="checked"</c:if> />幻灯
                        </label>
                        <label class="flags" for="c">
                            <input type="checkbox" id="c" name="flags" value="c" <c:if test="${fn:contains(article.flags,'c')}">
                                checked="checked"</c:if> />推荐
                        </label>
                        <label class="flags" for="a">
                            <input type="checkbox" id="a" name="flags" value="a" <c:if test="${fn:contains(article.flags,'a')}">
                                checked="checked"</c:if> />广告
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">首页显示位置:</td>
                    <td class="second-td">
                        <input type="radio" value="0" name="showHome" <c:if test="${fn:contains(article.showHome,0)}">
                            checked="checked"</c:if>/>默认
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="showHome" <c:if test="${fn:contains(article.showHome,1)}">
                        checked="checked"</c:if>/>热搜
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="showHome" <c:if test="${fn:contains(article.showHome,2)}">
                        checked="checked"</c:if>/>热点图
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="showHome" <c:if test="${fn:contains(article.showHome,3)}">
                        checked="checked"</c:if>/>热点
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="4" name="showHome" <c:if test="${fn:contains(article.showHome,4)}">
                        checked="checked"</c:if>/>业态推荐
                    </td>
                </tr>
                <tr>
                    <td class="first-td">业态首页显示位置:</td>
                    <td class="second-td">
                        <input type="radio" value="0" name="showOperation" <c:if test="${fn:contains(article.showOperation,0)}">
                            checked="checked"</c:if>/>默认
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="showOperation" <c:if test="${fn:contains(article.showOperation,2)}">
                        checked="checked"</c:if>/>热点图
                        &nbsp;&nbsp;&nbsp;<input type="radio" value="3" name="showOperation" <c:if test="${fn:contains(article.showOperation,3)}">
                        checked="checked"</c:if>/>热点
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章标签:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text" type="text" name="tags" value="${article.tags}"
                               data-options="validType:'length[0,100]'"/>&nbsp;&nbsp;(','号分开)
                    </td>
                </tr>
                <tr>
                    <td class="first-td">缩略图：</td>
                    <td class="second-td" valign="bottom">
                        <input name="picId" type="hidden"/>
                        <input name="picName" type="hidden" />
                        <img src="${article.picName}" id="pic">
                        <br/>
                        <span>提示：前台显示大小,幻灯751*272,广告194*138</span>
                        <br/>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="uploadDialog(1)">上传</a>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">点击量:</td>
                    <td class="second-td">
                        <input class="easyui-numberbox input-text-small" type="text" name="click" value="${article.click}"
                                                 data-options="min:0"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">文章来源:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text-small" type="text" value="${article.source}" name="source"/>
                    </td>
                </tr>
                <tr>
                    <td class="first-td">作者:</td>
                    <td class="second-td">
                        <input class="easyui-validatebox input-text-small" type="text" name="writer" value="${article.writer}"
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
                <textarea name="content" id="editor">${article.content}</textarea>
            </div>
            <div class="buttons">
                <a href="javascript:void(0);" id="back" class="easyui-linkbutton">上一步</a>
                <a href="javascript:void(0);" id="editSave" class="easyui-linkbutton">保存</a>
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
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/global/crossdomain.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/global/jscolor.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/layer/layer.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/img_ueditor.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/article/article_edit.js"></script>
</body>
</html>