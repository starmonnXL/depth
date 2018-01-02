<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>顶呱呱-顶呱呱资讯百科，专业的行业知识资讯</title>
    <meta name="description" content="顶呱呱一站式商业服务平台，专注为中小型企业提供在线服务，涉及注册公司、融资贷款、注册商标、代理记帐、法律咨询等16大业态，覆盖600多个服务项目，2014年挂牌上市，布局北京、上海、成都、杭州、、武汉、广州、重庆，旨在打造国内航母级商业服务平台。"/>
    <meta name="keywords" content="公司注册,贷款融资,商标注册,代理记帐,资质代办，法律咨询，顶呱呱">
    <link rel="stylesheet" href="${urlPrefix}css/site-base.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/information-public-old.css"/>
</head>
<body>
<jsp:include page="../commons/head.jsp"/>
<div class="page-content">
    <!--advert-->
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="80" adv-id="219">

    </div>
    <!-- 广告位 start -->
    <%--<div class="dgg-adv-box" adv-width="1180" adv-height="80" adv-id="89">--%>

    <%--</div>--%>
    <%--<div class="dgg-adv-box" adv-width="751" adv-height="272" adv-id="89">--%>

    <%--</div>--%>
    <!-- 广告位 end -->
    <!--logo and search start-->
    <div class="page-piece-group logo-search-piece">
        <a href="javascript:void(0);" class="db logo-img"></a>
        <div class="search-group">
            <div class="head-search-box">
                <input type="text" class="dib" placeholder="请输入关键字搜索行业资讯/创业百科" />
                <a href="javascript:void(0);" class="dib search-btn"><i class="dib"></i><span class="dib">搜索</span></a>
            </div>
            <div class="hot-search-group">
                <c:if test="${!empty hot}">
                    <c:forEach items="${hot}" var="hot" end="2">
                        <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>"
                           target="_blank" class="dib" <c:if test="${!empty hot.title2Color}"> style="color:${hot.title2Color}"</c:if>>${hot.title2}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <!--logo and search end-->
    <!--navigation bars start-->
    <div class="page-piece-group service-nav-piece">
        <ul>
            <c:if test="${!empty classification}">
                <c:forEach items="${classification}" var="c">
                    <li>
                        <a href="<c:if test="${!empty c.dir}">${urlPrefix}${c.dir}</c:if><c:if test="${empty c.dir}">javascript:void(0);</c:if>" class="dib tit"><span class="dib">${c.abbreviation}</span></a>
                        <span class="dib sub-classify">
                            <c:if test="${!empty c.child}">
                                <c:forEach items="${c.child}" var="child" end="1">
                                    <a href="<c:if test="${!empty child.dir}">${urlPrefix}${child.dir}</c:if><c:if test="${empty child.dir}">javascript:void(0);</c:if>" class="dib">${child.abbreviation}</a>
                                </c:forEach>
                            </c:if>
                        </span>
                        <a href="<c:if test="${!empty c.dir}">${urlPrefix}${c.dir}</c:if><c:if test="${empty c.dir}">javascript:void(0);</c:if>" class="dib more-btn">更多></a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
    <!--navigation bars end-->
    <!--advert-->
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="229">
        <a href="javascript:void(0);" class="db dgg-adv-piece" adv-id="229">
            <img src="http://temp.im/1180x100" alt="" adv-id="229"/>
        </a>
    </div>
    <!--news start-->
    <div class="page-piece-group news-piece-group">
        <div class="img-news">
            <div class="hd">
                <ul></ul>
            </div>
            <div class="bd">
                <ul>
                    <c:if test="${!empty hotspotImg}">
                        <c:forEach items="${hotspotImg}" var="hotImg">
                            <li>
                                <a href="<c:if test="${!empty hotImg.link}">${hotImg.link}</c:if><c:if test="${empty hotImg.link}">${urlPrefix}article/${hotImg.id}.html</c:if>" class="db">
                                    <img src="<c:if test="${!empty hotImg.picName}">${hotImg.picName}</c:if>" alt=""/>
                                    <div class="img-news-mask">
                                        <p>${hotImg.title}</p>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </c:if>
                    <%--<li>--%>
                        <%--<a href="javascript:void(0);" class="db">--%>
                            <%--<img src="http://temp.im/418x280" alt=""/>--%>
                            <%--<div class="img-news-mask">--%>
                                <%--<p>顶呱呱北京分公司领跑行业速度暨海淀区开业庆典</p>--%>
                            <%--</div>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
        <div class="hot-news">
            <div class="tit">
                <img src="${urlPrefix}images/index/my-hot-icon.png" alt=""/>
            </div>
            <c:if test="${!empty hotspot}">
                <c:forEach items="${hotspot}" var="hot" end="0">
                    <p class="news-txt">
                        <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>">${hot.title}</a>
                    </p>
                </c:forEach>
                <ul class="hot-news-lists">
                    <c:forEach items="${hotspot}" var="hot" begin="1" end="5">
                        <li>
                            <span class="dib">${hot.catalogName}</span>
                            <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>" class="dib text-overflow">${hot.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <div class="my-news">
            <c:if test="${!empty catalogDto}">
                <div class="title-group">
                    <p class="tit"><i class="dib"></i><span class="dib">${catalogDto.name}</span></p>
                    <a href="<c:if test="${!empty catalogDto.dir}">${urlPrefix}${catalogDto.dir}</c:if><c:if test="${empty catalogDto.dir}">javascript:void(0);</c:if>" class="dib more-btn">更多&gt;</a>
                    <div class="clear"></div>
                </div>
                <ul class="information-lists-piece">
                    <c:if test="${!empty catalogDto.child}">
                        <c:forEach items="${catalogDto.child}" var="child" end="5">
                            <li><i class="dib"></i><a href="<c:if test="${!empty child.link}">${child.link}</c:if><c:if test="${empty child.link}">${urlPrefix}article/${child.id}.html</c:if>" class="dib text-overflow">${child.title}</a></li>
                        </c:forEach>
                    </c:if>
                </ul>
            </c:if>
        </div>
        <div class="clear"></div>
    </div>
    <!--news end-->
    <!--advert-->
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="239">

    </div>
    <c:if test="${!empty classificationList}">
        <c:forEach items="${classificationList}" var="calalog" varStatus="ca">
            <!--business classify start-->
            <div class="page-piece-group business-classify-piece">
                <div class="business-classify-main">
                    <div class="title-group">
                        <p class="tit">${calalog.name}</p>
                        <a href="<c:if test="${!empty calalog.dir}">${urlPrefix}${calalog.dir}</c:if><c:if test="${empty calalog.dir}">javascript:void(0);</c:if>" class="dib more-btn">更多&gt;</a>
                        <div class="clear"></div>
                    </div>
                    <ul class="information-encyclopedia">
                        <c:forEach items="${calalog.child}" var="child" end="1">
                            <li>
                                <a href="javascript:void(0);" class="dib tit">${child.name}</a>
                                <ul class="information-lists-piece">
                                    <c:forEach items="${child.child}" var="chi" end="5">
                                        <li><i class="dib"></i><a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>" class="dib text-overflow">${chi.title}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <%--<a href="javascript:void(0);" class="db dgg-adv-piece" adv-id="">--%>
                    <%--<img class="page-piece-group" src="http://temp.im/300x300" alt="" adv-width="300" adv-height="300" adv-id="129"/>--%>
                <%--</a>--%>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="${(ca.index*10)+249}">

                </div>
                <div class="clear"></div>
            </div>
            <!--business classify end-->
            <!--advert-->
            <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="${(ca.index*10)+349}">

            </div>
        </c:forEach>
    </c:if>
</div>
<jsp:include page="../commons/footer.jsp"/>
<script src="${urlPrefix}js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/superslide.js"></script>
<script src="${urlPrefix}js/advertisement.js"></script>
<script>
    $(function(){
        //搜索时获取搜索字段
        $(".head-search-box>a").on("click",function(){
            var searchVal = $(".head-search-box>input").val();
            $(this).attr("href", "${urlPrefix}articleSearch/" + searchVal + "/catalogId.html")
        });
    });
    //img news
    var insideBannerLength = $(".news-piece-group>.img-news .bd ul>li").length;
    if(insideBannerLength>1){
        $(".news-piece-group>.img-news").slide({
            mainCell:".bd ul",
            titCell:".hd>ul",
            effect:"leftLoop",
            autoPlay:true,
            delayTime:500,
            interTime:5000,
            autoPage:true
        });
    }
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