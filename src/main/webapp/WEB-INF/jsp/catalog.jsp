<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="/pager"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title><c:out value="${catalog.pageTitle}"/></title>
    <meta name="keywords" content="${catalog.pageKeywords}"/>
    <meta name="description" content="${catalog.pageDescription}"/>
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/base-v1.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/global-v1.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/news-wiki.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/foot.css">
</head>
<body>
<jsp:include page="../commons/head.jsp"/>
<jsp:include page="header.jsp"/>

<div class="f6-box bk-fff">
    <!-- 广告位 start -->
    <div class="dgg-adv-box" adv-width="1180" adv-height="100" adv-id="29" style="margin:20px auto;">

    </div>
    <!-- 广告位 end -->
    <!--面包屑-->
    <div class="bread_nav">
        <span><img src="/cmsFront${urlPrefix}images/news-wiki/location.png"/></span>
        您当前的位置：
        <c:forEach items="${paths}" var="path">
            <a href="${urlPrefix}${path.dir}">${path.name}</a>
        </c:forEach>
        <a href="${urlPrefix}${catalog.dir}">${catalog.name}</a>
    </div>
    <!--/面包屑-->
    <div class="m-list-bg pages">
        <div class="m-list-fl">
            <div class="m-list-tit">
                <h3>${catalog.name}</h3>
            </div>
            <c:if test="${!empty pageData.obj}">
                <ul class="m-article-lists">
                    <c:forEach items="${pageData.obj}" var="a">
                        <li>
                            <a href="<c:if test="${!empty a.link}">${a.link}</c:if><c:if test="${empty a.link}">${urlPrefix}article/${a.id}.html</c:if>" target="_blank"><i></i><span style="color: ${a.titleColor}">${a.title}</span></a>
                            <span>[${a.addtime}]</span>
                            <div class="clear"></div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${empty pageData.obj}">
            <ul class="m-article-lists">
                <li>该栏目下还没有内容！</li>
            </ul>
            </c:if>
            <!-- 分页 start -->
            <p:pager pageSize="${pageData.page.showCount}" pageNo="${pageData.page.currentPage}" url="${urlPrefix}${catalog.dir}${url}" recordCount="${pageData.page.totalResult}" />

            <!-- 分页 end -->
        </div>
        <!--顾问推荐    右  start-->
        <div class="Pages-bg">
            <div class="m-list-fr slideTxtBox">
                <div class="hd">
                    <ul class="m-fr-top">
                        <li class="on">热门排行</li>
                        <li class="border-rg-none">推荐阅读</li>
                    </ul>
                </div>
                <div class="bd">
                    <ul class="m-list-couent">
                        <c:forEach items="${hotList}" var="a" varStatus="statu">
                            <li>
                                <a href="<c:if test="${!empty a.link}">${a.link}</c:if><c:if test="${empty a.link}">${urlPrefix}article/${a.id}.html</c:if>" target="_blank">
                                    <i <c:if test="${statu.index < 3}">class="top"</c:if>>${statu.index +1}</i>
                                    <span>${a.title}</span>
                                    <div class="m-list-move clearfix <c:if test="${statu.index == 0}">displayblock</c:if>">
                                        <p>${a.summary}</p>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <ul class="m-list-couent">
                        <c:forEach items="${recommList}" var="a" varStatus="statu">
                            <li>
                                <a href="<c:if test="${!empty a.link}">${a.link}</c:if><c:if test="${empty a.link}">${urlPrefix}article/${a.id}.html</c:if>" target="_blank">
                                    <i <c:if test="${statu.index < 3}">class="top"</c:if>>${statu.index +1}</i>
                                    <span>${a.title}</span>
                                    <div class="m-list-move clearfix <c:if test="${statu.index == 0}">displayblock</c:if>">
                                        <p>${a.summary}</p>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!-- 广告位 start -->
            <div class="dgg-adv-box" adv-width="260" adv-height="173" adv-id="39" style="">

            </div>
            <!-- 广告位 end -->
        </div>
    </div>
</div>
<jsp:include page="../commons/footer.jsp"/>

<script src="/cmsFront${urlPrefix}js/jquery-1.10.2.min.js"></script>
<script src="/cmsFront${urlPrefix}js/define/public-v1.js"></script>
<script src="/cmsFront${urlPrefix}js/superslide.js"></script>
<script src="/cmsFront${urlPrefix}js/define/effect.js"></script>
<script src="/cmsFront${urlPrefix}js/advertisement.js"></script>

<script>
    $(function($) {
        $(".slideTxtBox").slide({trigger:"mouseover"});
        //选项切换结束
        $(".m-list-couent li").mouseenter(function() {
            $(".m-list-move").removeClass("displayblock");
            $(this).find(".m-list-move").addClass("displayblock");
        });
    });
</script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?36f80bbd75d80e6e4023062b54e51ffd";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>
