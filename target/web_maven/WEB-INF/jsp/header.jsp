<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div class="comm_head">
    <div class="w1200">
        <ul class="fl">
            <li>
                <a href="http://www.dgg.net/">顶呱呱首页</a>
            </li>
        </ul>
        <ul class="fr" id="head-menu">
            <li>
                <span>您好，欢迎来到顶呱呱</span>
            </li>
            <li><a href="javascript:openlive800()">联系客服</a></li>
            <li class="sub-navigation web-nav hoverClick">
                <a href="#"><i class="head-icon webnav-icon"></i>网站导航<i class="drop-down"></i></a>
                <div class="service-nav hoverOpen">
                    <p class="site-map"><i></i>顶呱呱 一站式商业服务平台</p>
                    <a href="http://www.dgg.net/icsite/index.htm" target="_blank">工商服务</a>
                    <a href="http://www.dgg.net/accounting/index.htm" target="_blank">会计服务</a>
                    <a href="http://www.dgg.net/loan/index.htm" target="_blank">融资贷款</a>
                    <a href="http://www.dgg.net/lawsite/index.htm" target="_blank">法律咨询</a>
                    <a href="http://www.dgg.net/tmbrandsearch/index.htm " target="_blank">商标服务</a>
                    <a href="http://www.dgg.net/patent/index.htm " target="_blank">专利服务</a>
                    <a href="http://www.dgg.net/certificate/index.htm" target="_blank">建筑资质</a>
                    <a href="http://www.dgg.net/credentials/cd/index.htm" target="_blank">互联网资质</a>
                    <a href="http://vi.xiaodingwang.com" target="_blank">品牌设计</a>
                    <a href="http://www.dgg.net/hrsite/index.htm" target="_blank">人事外包</a>
                    <a href="http://sydc.dggjqw.com" target="_blank">商业地产</a>
                    <a href="http://www.dgg.net/comprehensive/index.htm" target="_blank">综合服务</a>
                    <a href="http://www.dgg.net/netsite/index.htm" target="_blank">互联网营销</a>
                    <a href="http://www.dgg.net/fatsite/index.htm" target="_blank">财税服务</a>
                    <a href="http://jiazhuang.xiaodingwang.com" target="_blank">小顶家装</a>
                    <a href="http://www.dgg.net/pasite/index.htm" target="_blank">项目申报</a>
                </div>
            </li>
        </ul>
    </div>
</div>--%>
<div class="index-search">
    <h1 class="logo-box inside-logo-box">
        <a href="${urlPrefix}"><img src="/cmsFront${urlPrefix}images/global-v1/logo.png"></a>
        <%--<p>资讯平台</p>--%>
    </h1>
    <%--<div class="search-form">
        <div class="inlineb news-head">
            <div class="tabBox">
                <div class="search-form-box">
                    <input type="text" placeholder="请输入相关信息"/>
                    <button><i class="size25 icon1"></i><span class="inline">资讯搜索</span></button>
                </div>
            </div>
        </div>
        <form id="querySearch" method="get" class="inlineb" target="_blank" >
            <ul class="search-form-tab">
                <li class="active" id="li1"><a href="javascript:void(0)">找服务</a><i></i></li>
                <li id="li3"><a href="javascript:void(0)">找顾问</a><i></i></li>
                <li id="li2"><a href="javascript:void(0)">查公司</a><i></i></li>
                <li id="li4"><a href="javascript:void(0)">查商标</a><i></i></li>
            </ul>
            <div class="tabBox">
                <div class="search-form-box">
                    <input type="text" id="keyword" name="keyword" maxlength="20" placeholder="请输入关键字查询"/>
                    <button type="button"><i class="size25 icon1"></i><span class="inline">搜索</span></button>
                </div>
            </div>
        </form>
    </div>--%>
    <div class="clear"></div>
</div>
<!--搜索 end-->
<div class="nav-main-box">
    <div class="nav-box">
        <div class="pull-nav">
            <a href="javascript:;" class="pull-nav-a">顶呱呱一站式商业服务</a>
        </div>
        <ul class="main-menu fl">
            <c:forEach var="nav" items="${navs}">
                <li<c:if test="${nav.id==topCatalog.id}"> class="active"</c:if>><a href="${urlPrefix}${nav.dir}">${nav.name}</a></li>
            </c:forEach>
        </ul>
        <label class="fr nav-tel"><i class="size25 icon3"></i><span>400-0055-002</span></label>
        <div class="clear"></div>
    </div>
</div>