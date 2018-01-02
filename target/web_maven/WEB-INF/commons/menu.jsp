<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/24
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="tpl-left-nav tpl-left-nav-hover" style="position: fixed;">
    <div class="tpl-left-nav-title">
        <%--Amaze UI 列表--%>
    </div>
    <div class="tpl-left-nav-list">
        <ul class="tpl-left-nav-menu">
            <li class="tpl-left-nav-item">
                <a href="<%=basePath%>admin/index.do" class="nav-link url">
                    <i class="am-icon-home"></i>
                    <span>首页</span>
                </a>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:void(0);" class="nav-link tpl-left-nav-link-list url">
                    <i class="am-icon-table"></i>
                    <span>博客文章管理</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>
                        <a href="<%=basePath%>catalog/manage.do" class="url">
                            <i class="am-icon-angle-right"></i>
                            <span>文章分类管理</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>

                        <a href="table-images-list.html" class="url">
                            <i class="am-icon-angle-right"></i>
                            <span>文章列表</span>
                            <i class="tpl-left-nav-content tpl-badge-success">
                                18
                            </i>
                        </a>
                        <a href="form-news.html" class="url">
                            <i class="am-icon-angle-right"></i>
                            <span>文章评论列表</span>
                            <i class="tpl-left-nav-content tpl-badge-primary">
                                5
                            </i>
                        </a>

                        <a href="form-news-list.html" class="url">
                            <i class="am-icon-angle-right"></i>
                            <span>文字列表</span>

                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
