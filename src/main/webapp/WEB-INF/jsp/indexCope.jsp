<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="opr" uri="http://news.xiaodingwang.com/jsp/operate"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>顶呱呱-顶呱呱资讯百科，专业的行业知识资讯</title>
    <meta name="description"
          content="顶呱呱一站式商业服务平台，专注为中小型企业提供在线服务，涉及注册公司、融资贷款、注册商标、代理记帐、法律咨询等16大业态，覆盖600多个服务项目，2014年挂牌上市，布局北京、上海、成都、杭州、、武汉、广州、重庆，旨在打造国内航母级商业服务平台。"/>
    <meta name="keywords" content="公司注册,贷款融资,商标注册,代理记帐,资质代办，法律咨询，顶呱呱">
    <link rel="stylesheet" href="${urlPrefix}css/site-base.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/information-public-v1.css"/>
</head>
<body>
<jsp:include page="../commons/head.jsp"/>
<div class="page-content">
    <!--advert-->
    <!-- 广告位 start -->
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="80" adv-id="219"></div>
    <!-- 广告位 end -->

    <!--logo and search start-->
    <div class="page-piece-group logo-search-piece">
        <a href="javascript:void(0);" class="db logo-img"></a>
        <div class="search-group">
            <div class="head-search-box">
                <input type="text" class="dib" placeholder="请输入关键字搜索行业资讯/创业百科"/>
                <a href="javascript:void(0);" class="dib search-btn"><i class="dib"></i><span class="dib">搜索</span></a>
            </div>
            <div class="hot-search-group">
                <c:if test="${!empty hot}">
                    <c:forEach items="${hot}" var="hot" end="2">
                        <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>"
                           target="_blank" class="dib" <c:if
                                test="${!empty hot.title2Color}"> style="color:${hot.title2Color}"</c:if>>${hot.title2}</a>
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
                        <a href="<c:if test="${!empty c.dir}">${urlPrefix}${c.dir}</c:if><c:if test="${empty c.dir}">javascript:void(0);</c:if>"
                           class="dib tit"><span class="dib">${c.abbreviation}</span></a>
                        <span class="dib sub-classify">
                            <c:if test="${!empty c.child}">
                                <c:forEach items="${c.child}" var="child" end="1">
                                    <a href="<c:if test="${!empty child.dir}">${urlPrefix}${child.dir}</c:if><c:if test="${empty child.dir}">javascript:void(0);</c:if>"
                                       class="dib">${child.abbreviation}</a>
                                </c:forEach>
                            </c:if>
                        </span>
                        <a href="<c:if test="${!empty c.dir}">${urlPrefix}${c.dir}</c:if><c:if test="${empty c.dir}">javascript:void(0);</c:if>"
                           class="dib more-btn">更多></a>
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
    <div class="page-piece-group news-piece-group mb20">
        <div class="img-news">
            <div class="hd">
                <ul></ul>
            </div>
            <div class="bd">
                <ul>
                    <c:if test="${!empty hotspotImg}">
                        <c:forEach items="${hotspotImg}" var="hotImg">
                            <li>
                                <a href="<c:if test="${!empty hotImg.link}">${hotImg.link}</c:if><c:if test="${empty hotImg.link}">${urlPrefix}article/${hotImg.id}.html</c:if>"
                                   class="db">
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
                    <c:forEach items="${hotspot}" var="hot" begin="1" end="7">
                        <li>
                            <span class="dib">${hot.catalogName}</span>
                            <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>"
                               class="dib text-overflow">${hot.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <div class="dgg-news-login">


            <div class="user-guarant-box">
                <div class="user-box">
                    <div class="user-head">
                        <img src="${staticDomain}/images/common/user-def.png"/>
                    </div>
                    <p>欢迎来到顶呱呱！</p>
                    <div class="link">
                        <a class="link-logo" href="${mainDomain}/member/toLogin.htm" target="_blank">登录</a>
                        <a class="link-register" href="${mainDomain}/member/register.htm" target="_blank">注册</a>
                    </div>
                </div>
                <div class="search-schedule">
                    <form id="queryOrerInIndex" style="display:none;">
                        <input type="text" class="schedule-input" placeholder="输入预留手机号即可查询" />
                        <button type="button" class="schedule-button">查询进度</button>
                    </form>
                    <div class="schedule-code">
                        <img src="${staticDomain}/images/common/code6.jpg"/>
                    </div>
                    <p class="schedule-title">微信扫一扫，分享领红包</p>
                </div>
            </div>


        </div>
        <div class="clear"></div>
        <%--公司新闻--%>
        <div class="dgg-nwes-company w1180 mt20 mb20">
            <div class="dgg-box">
                <div class="dgg-lg-9 dgg-laery-fl">
                    <c:if test="${!empty catalogDto}">
                        <div class="dgg-box mb20">
                            <div class="dgg-lg-10">
                                <h2 class="h2-tit">${catalogDto.name}</h2>
                            </div>
                            <div class="dgg-lg-2 f-tar">
                                <a class="dgg-a01" href="<c:if test="${!empty catalogDto.dir}">${urlPrefix}${catalogDto.dir}</c:if><c:if test="${empty catalogDto.dir}">javascript:void(0);</c:if>">更多&gt;</a>
                            </div>
                        </div>
                        <div class="dgg-box">
                            <c:if test="${!empty catalogDto.child}">
                                <div class="dgg-lg-6">
                                    <dl class="dgg-dl-base dgg-dl-1">
                                        <c:forEach items="${catalogDto.child}" var="child" varStatus="status" begin="0" end="5">
                                            <c:if test="${status.index == 0}" >
                                                <dt>
                                                    <a href="<c:if test="${!empty child.link}">${child.link}</c:if><c:if test="${empty child.link}">${urlPrefix}article/${child.id}.html</c:if>">
                                                        <img src="${child.picName}" width="100" height="67" alt="">
                                                        <h3 class="f-toe">${child.title}</h3>
                                                        <p>${child.summary}</p>
                                                    </a>
                                                </dt>
                                            </c:if>
                                            <c:if test="${status.index >= 1}" >
                                                <dd><a href="<c:if test="${!empty child.link}">${child.link}</c:if><c:if test="${empty child.link}">${urlPrefix}article/${child.id}.html</c:if>">${child.title}</a></dd>
                                            </c:if>
                                        </c:forEach>
                                    </dl>
                                </div>
                                <div class="dgg-lg-6">
                                    <dl class="dgg-dl-base dgg-dl-2">
                                        <c:forEach items="${catalogDto.child}" var="child" varStatus="status" begin="6" end="11">
                                            <c:if test="${status.index == 6}" >
                                                <dt>
                                                    <a href="<c:if test="${!empty child.link}">${child.link}</c:if><c:if test="${empty child.link}">${urlPrefix}article/${child.id}.html</c:if>">
                                                        <img src="${child.picName}" width="100" height="67" alt="">
                                                        <h3 class="f-toe">${child.title}</h3>
                                                        <p>${child.summary}</p>
                                                    </a>
                                                </dt>
                                            </c:if>
                                            <c:if test="${status.index >= 7}" >
                                                <dd><a href="<c:if test="${!empty child.link}">${child.link}</c:if><c:if test="${empty child.link}">${urlPrefix}article/${child.id}.html</c:if>">${child.title}</a></dd>
                                            </c:if>
                                        </c:forEach>
                                    </dl>
                                </div>
                            </c:if>
                        </div>
                    </c:if>
                </div>
                <div class="dgg-lg-3 dgg-laery-fr ">
                    <h2 class="h2-tit">快捷服务</h2>

                    <div class="hasMoreTab" style="margin:0 auto">
                        <div class="hd">
                            <ul>
                                <li class="on"><i
                                        class="dgg-icon dgg-icon-shortcut dgg-icon-shortcut-1"></i><span>商标注册</span>
                                </li>
                                <li><i class="dgg-icon dgg-icon-shortcut dgg-icon-shortcut-2"></i><span>公司注册</span></li>
                                <li><i class="dgg-icon dgg-icon-shortcut dgg-icon-shortcut-3"></i><span>版权登记</span></li>
                                <li><i class="dgg-icon dgg-icon-shortcut dgg-icon-shortcut-4"></i><span>申请贷款</span></li>
                            </ul>
                        </div>
                        <div class="bd">
                            <div class="conWrap">
                                <div class="con" style="display: block;">
                                    <input class="inpt1 searchTrademark1024" type="text" placeholder="请输入商标名称">
                                    <button class="btn1" onclick="searchTrademark(1024)">免费查询能否注册</button>
                                    <p class="p1 f-toe">已有2.4万+人 获取查询结果</p>
                                </div>
                                <div class="con">
                                    <input class="inpt1 searchCompany1024" type="text" placeholder="请输入公司名称">
                                    <button class="btn1" onclick="searchCompany(1024)">企业信息轻松查询</button>
                                    <p class="p1 f-toe">先查询后注册，成功率高达98.6%</p>
                                </div>
                                <div class="con">
                                    <input class="inpt1 appointment1024" type="text" placeholder="请输入您的联系方式">
                                    <button class="btn1" maxlength="11" onclick="appointment(1024,2)">免费查询能否登记</button>
                                    <p class="p1 f-toe">已有3.2万+人 获取查询结果</p>
                                </div>
                                <div class="con">
                                    <input class="inpt1 appointment1025" type="text" placeholder="请输入您的联系方式">
                                    <button class="btn1" maxlength="11" onclick="appointment(1025,2)">免费预约专属顾问</button>
                                    <p class="p1 f-toe">已有15万+人 成功放款</p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--公司新闻 end--%>
    </div>
    <!--news end-->
    <!--advert-->
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="229"></div>

    <%--------------------创业发展--------------------%>
    <c:if test="${!empty classificationList}">
        <c:forEach items="${classificationList}" var="calalog" varStatus="ca">
            <div class="dgg-news-laery-base w1180 mb20">
                <div class="dgg-box dgg-news-laery-box">
                    <div class="dgg-lg-9 dgg-laery-fl">
                        <div class="f-cb dgg-laery-top">
                            <h2 class="h2-tit fl">${calalog.name}</h2>
                            <ul class="ul-li fr f-cb">
                                <c:if test="${!empty calalog.childHot}">
                                    <c:forEach items="${calalog.childHot}" var="chi">
                                        <li><a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>">${chi.title}</a></li>
                                    </c:forEach>
                                </c:if>
                                <li><a href="<c:if test="${!empty calalog.dir}">${urlPrefix}${calalog.dir}</c:if><c:if test="${empty calalog.dir}">javascript:void(0);</c:if>">更多&gt;</a></li>
                            </ul>
                        </div>
                        <div class="dgg-box dgg-laery-conten">
                                <%--模板--%>

                                <%-- 左边服务模板  --%>
                            <c:if test="${not empty leftHotService}">
                                <opr:commonTemplateTag siteId="100000" templateId="${leftHotService}" names="${calalog.serviceName}" path="left_hot_service.ftl"/>
                            </c:if>

                            <div class="dgg-lg-4 laery-conten-2">
                                <dl class="dl-dt f-cb">
                                    <c:if test="${!empty calalog.child}">
                                        <c:forEach items="${calalog.child}" var="child">
                                            <c:if test="${fn:contains(child.name,'百科')}">
                                                <c:if test="${!empty child.child}">
                                                    <c:forEach items="${child.child}" var="chi" varStatus="va">
                                                        <c:if test="${va.index == 0}">
                                                            <dt>
                                                                <a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>">
                                                                    <img src="<c:if test="${!empty chi.picName}">${chi.picName}</c:if>" width="260" height="174" alt="">
                                                                    <p>${chi.title}</p>
                                                                </a>
                                                            </dt>
                                                        </c:if>
                                                        <c:if test="${va.index >= 1 && va.index <= 4}">
                                                            <dd>
                                                                <a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>">
                                                                    <img src="<c:if test="${!empty chi.picName}">${chi.picName}</c:if>" width="125" height="84" alt="">
                                                                    <p>${chi.title}</p>
                                                                </a>
                                                            </dd>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </dl>
                            </div>
                            <div class="dgg-lg-5 laery-conten-3">
                                <c:if test="${!empty calalog.child}">
                                    <c:forEach items="${calalog.child}" var="child">
                                        <c:if test="${fn:contains(child.name,'百科')}">
                                            <dl class="dl-dt">
                                                <dt><strong>${child.name}</strong></dt>
                                                <c:if test="${!empty child.child}">
                                                    <c:forEach items="${child.child}" var="chi" varStatus="va">
                                                        <c:if test="${va.index >= 5}">
                                                            <dd>
                                                                <a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>">${chi.title}</a>
                                                            </dd>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </dl>
                                        </c:if>
                                        <c:if test="${!fn:contains(child.name,'百科')}">
                                            <dl class="dl-dt">
                                                <dt><strong>${child.name}</strong></dt>
                                                <c:if test="${!empty child.child}">
                                                    <c:forEach items="${child.child}" var="chi" varStatus="va" begin="0" end="5">
                                                        <dd>
                                                            <a href="<c:if test="${!empty chi.link}">${chi.link}</c:if><c:if test="${empty chi.link}">${urlPrefix}article/${chi.id}.html</c:if>">${chi.title}</a>
                                                        </dd>
                                                    </c:forEach>
                                                </c:if>
                                            </dl>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="dgg-lg-3 dgg-laery-fr">
                        <c:if test="${calalog.functionName == 1 || calalog.functionName == 3 || calalog.functionName == 4 || calalog.functionName == 0}">
                            <div class="dgg-tool-company-inquiries mb15">
                                    <%--公司信息一键查询--%>
                                <c:if test="${calalog.functionName == 1}">
                                    <h3 class="h3-title">公司信息一键查</h3>
                                    <input class="tool-company-inpu searchCompany${ca.index}" type="text" placeholder="输入公司名称看能否注册">
                                    <button class="tool-company-btn" onclick="searchCompany(${ca.index})">查询</button>
                                </c:if>
                                    <%--商标免费查询--%>
                                <c:if test="${calalog.functionName == 3}">
                                    <h3 class="h3-title">商标免费查询</h3>
                                    <input class="tool-company-inpu searchTrademark${ca.index}" type="text" placeholder="1秒钟查询商标结果">
                                    <button class="tool-company-btn" onclick="searchTrademark(${ca.index})">查询</button>
                                </c:if>
                                    <%--服务预约--%>
                                <c:if test="${calalog.functionName == 4 || calalog.functionName == 0}">
                                    <h3 class="h3-title">服务预约</h3>
                                    <input class="tool-company-inpu appointment${ca.index}" maxlength="11" type="text" placeholder="输入您的手机号 专人服务">
                                    <button class="tool-company-btn" onclick="appointment(${ca.index},2)">免费预约</button>
                                </c:if>
                                <c:if test="${!fn:contains(calalog.name,'贷款')}">
                                    <ul class="ul-li">
                                        <c:if test="${!empty calalog.hotGoods}">
                                        <c:forEach items="${calalog.hotGoods}" var="goods" varStatus="goo" begin="0" end="5">
                                        <li><a href="${mainDomain}/goods/${goods.goodsId}.htm" target="_blank"><span class="goodsname">${goods.goodsName}</span><span title="${goods.goodsPrice}" class="price f-toe">${goods.goodsPrice}</span></a>
                                            </c:forEach>
                                            </c:if>
                                    </ul>
                                </c:if>
                            </div>
                        </c:if>
                            <%--申请贷款--%>
                        <c:if test="${calalog.functionName == 2}">
                            <div class="dgg-tool-apply-loan mb15">

                                <div class="f-cb tit-top-box">
                                    <h3 class="h3-title">申请贷款</h3>
                                    <span><em>15万人</em>成功放款</span>
                                </div>

                                <ul class="ul-li">
                                    <li class="first">
                                        <input type="text" maxlength="2" class="name${ca.index}" name="user" placeholder="您的姓氏">
                                        <div class="check">
                                            <label for="sex1${ca.index}"><input id="sex1${ca.index}" type="radio" name="sex${ca.index}" value="2"
                                                                                checked="">先生</label>
                                            <label for="sex2${ca.index}"><input id="sex2${ca.index}" type="radio" name="sex${ca.index}" value="1">女士</label>
                                        </div>
                                    </li>
                                    <li>
                                        <input type="text" maxlength="4" class="loan_money${ca.index}" name="money" placeholder="贷款金额(单位:万元)">
                                    </li>
                                    <li>
                                        <input type="text" maxlength="11" class="loan_phone${ca.index}" name="phone"
                                               placeholder="输入手机号10分钟快速响应">
                                    </li>
                                    <li>
                                        <button class="btn" type="button" onclick="appointment(${ca.index},1)">免费预约</button>
                                    </li>
                                </ul>
                            </div>
                        </c:if>

                            <%--顾问模板--%>
                        <c:if test="${not empty hotAdvicerList}">
                            <div class="dgg-tool-adviser-list">
                                <h3 class="h3-title">专业顾问团队</h3>
                                <opr:commonTemplateTag siteId="100000" templateId="${hotAdvicerList}" names="${calalog.serviceName}" path="hot_adviser_list.ftl"/>
                            </div>
                        </c:if>

                            <%--融资贷款专用模板--%>
                        <c:if test="${calalog.functionName == 2}">
                            <c:if test="${not empty leftHotService}">
                                <opr:commonTemplateTag siteId="100000" templateId="${leftHotService}" names="${calalog.serviceName}" path="money_server.ftl"/>
                            </c:if>
                        </c:if>
                            <%--公司信息一键查询 end--%>
                    </div>
                </div>
            </div>
            <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="${(ca.index*10)+349}">

            </div>
        </c:forEach>
    </c:if>
    <%--创业发展 end--%>


</div>
<jsp:include page="../commons/footer.jsp"/>
<script src="${urlPrefix}js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/layer3.0.1/layer.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/superslide.js"></script>
<script src="${urlPrefix}js/advertisement.js"></script>
<script>
    var mainDomain = '${mainDomain}';
    $(function (){
        //搜索时获取搜索字段
        $(".head-search-box>a").on("click", function (){
            var searchVal = $(".head-search-box>input").val();
            $(this).attr("href", "${urlPrefix}articleSearch/" + searchVal + "/catalogId.html")
        });
    });
    //img news
    var insideBannerLength = $(".news-piece-group>.img-news .bd ul>li").length;
    if(insideBannerLength > 1){
        $(".news-piece-group>.img-news").slide({
            mainCell: ".bd ul",
            titCell: ".hd>ul",
            effect: "leftLoop",
            autoPlay: true,
            delayTime: 500,
            interTime: 5000,
            autoPage: true
        });
    }

    (function ($){
        jQuery(".hasMoreTab").slide({mainCell: ".conWrap", targetCell: ".more a", effect: "fold"});
    })(jQuery);
</script>
<script>
    var _hmt = _hmt || [];
    (function (){
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?36f80bbd75d80e6e4023062b54e51ffd";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    /**
     * 商标查询
     * @param ins
     */
    function searchTrademark(ins){
        var searchTrademark = $(".searchTrademark"+ins).val();
        if(null == searchTrademark || "" == searchTrademark){
            layer.msg("请输入需要查询的商标名称或关键字");
            return;
        }
        //查商标
        var url = mainDomain+"/trademark/search.htm";
        buildFormndSubmit(url, {
            keyword: searchTrademark,
            v:Math.random()*1000
        });
        $(".searchTrademark"+ins).val('')
    }
    /**
     * 公司查询
     * @param ins
     */
    function searchCompany(ins){
        var searchCompany = $(".searchCompany"+ins).val();
        if(null == searchCompany || "" == searchCompany){
            layer.msg("请输入需要查询的公司名称或关键字");
            return;
        }
        //查公司
        var url = mainDomain+"/com/search.htm";
        buildFormndSubmit(url, {
            keyword: searchCompany,
            v:Math.random()*1000
        });
        $(".searchCompany"+ins).val('');
    }
    /**
     * 服务预约
     * @param ins
     * @param cusState 状态，1为贷款信息，2为预约服务信息
     */
    function appointment(ins,cusState){
        //提交预约信息
        if(cusState == 2 || cusState == "2"){
            var appointment = $(".appointment"+ins).val();
            if(null == appointment || "" == appointment ){
                layer.msg("请输入您的手机号");
                return;
            }else if(!(/^1[34578]\d{9}$/.test(appointment))){
                layer.msg("手机号格式不正确，请重新输入");
                $(".appointment"+ins).val('');
                return;
            }
            $.ajax({
                dataType: 'json',
                url: 'addClue.htm',
                data: {
                    cusState: cusState,
                    customerPhone:appointment
                },
                success:function(data) {
                    if (null != data.status && data.status) {
                        $(".appointment" + ins).val('');
                        layer.msg("保存成功");
                    } else {
                        $(".appointment" + ins).val('');
                        layer.msg("保存失败");
                    }
                },
                error:function(data){
                    $(".appointment" + ins).val('');
                    layer.msg("保存成功");
                }
            });
        }else{
            //提交贷款信息
            var sex = $("input[name='name"+ins+"']:checked").val();
            var name = $(".name"+ins).val();
            var loan_money = $(".loan_money"+ins).val();
            var loan_phone = $(".loan_phone"+ins).val();
            if(null == name || "" == name ){
                layer.msg("请输入您的姓氏");
                return;
            }
            if(null == loan_money || "" == loan_money ){
                layer.msg("请输入需要贷款金额");
                return;
            }else if(!(/^[0-9]*$/.test(loan_money))){
                layer.msg("金额格式不正确，请重新输入");
                $(".loan_money"+ins).val('');
                return;
            }
            if(null == loan_phone || "" == loan_phone ){
                layer.msg("请输入您的手机号");
                return;
            }else if(!(/^1[34578]\d{9}$/.test(loan_phone))){
                layer.msg("手机号格式不正确，请重新输入");
                $(".loan_phone"+ins).val('');
                return;
            }
            $.ajax({
                dataType: 'json',
                url: 'addClue.htm',
                data: {
                    cusState: cusState,
                    customerPhone:loan_phone,
                    sex:sex,
                    cusName:name,
                    money:loan_money
                },
                success: function(data) {
                    if (null != data.status && data.status) {
                        layer.msg("保存成功");
                        $(".name"+ins).val('');
                        $(".loan_money"+ins).val('');
                        $(".loan_phone"+ins).val('');
                        $("#sex1"+ins).attr("checked","checked");
                        $("#sex2"+ins).removeAttr("checked");
                    }else{
                        $(".appointment"+ins).val('');
                        layer.msg("保存失败");
                    }
                },
                error:function(data){
                    $(".appointment" + ins).val('');
                    layer.msg("保存成功");
                }
            });
        }
    }

    function buildFormndSubmit(url, keywords){
        var temp_form = document.createElement("form");
        temp_form.action = url;
        //如需打开新窗口，form的target属性要设置为'_blank'
        temp_form.target = "_blank";
        temp_form.method = "get";
        temp_form.style.display = "none";

        for (var key in keywords) {
            var opt = document.createElement("textarea");
            opt.name = key;
            opt.value = keywords[key];
            temp_form.appendChild(opt);
        }

        document.body.appendChild(temp_form);
        //提交数据
        temp_form.submit();
    }

    window.addEventListener("load", getCartGoodsNum);

    /**
     * 获取购物车数量
     */
    function getCartGoodsNum(){
        $.ajax({
            type: 'post',
            dataType: 'jsonp',
            url: mainDomain + "/getCartGoodsNum.htm",
            success: successHandel,
            error: function (data){
                if(data.status == 200 && data.readyState == 4){
                    successHandel(JSON.parse(data.responseText));
                    return;
                }
                // layer.open({
                //     type: 0,
                //     content: '数据加载失败，请稍后再试',
                //     title: false,
                // });
                var elem = $(".cartRightTop");
                if(elem.length == 0){
                    return;
                }
                elem.text(0);
                elem.show();
            }
        });

        function successHandel(data){
            var userName = [],
                    userStatus = [];
            //会员已登录
            if(data.status){
                //当前域名
                var domain = document.domain;
                //将域名去除得到当前页路径
                var url = window.location.href.replace('http://' + domain + '/', '').trim();
                //判断得到的路径是否含有项目名，最终得到的是退出登录的重定向页面
                if(url.indexOf("operate") != -1){
                    url = url.replace('operate', '').trim();
                } else {
                    url = "/" + url;
                }
                if(null == url || url == ""){
                    url = "/";
                }
                url = url.replace(/&/g, '%26').trim();
                url = url.replace(/=/g, '%3D').trim();
                if(null != data.member.name && data.member.name != ""){
                    userName.push('<a href="' + mainDomain + 'member/toLogin.htm?url=">' + data.member.name + '</a>');
                } else {
                    userName.push('<a href="' + mainDomain + 'member/toLogin.htm?url="> ' + data.member.loginName + ' </a>');
                }
                // userStatus.push('<a href="' + mainDomain + 'member/operateLogout.htm?url=' + url + '">退出</a>');
                userStatus.push('<a href="javascript:void(0);" onclick="operateLogout()">退出</a>');

                if(data.member.header.indexOf("http") != -1){
                    $(".user-head img").attr("src",data.member.header);
                }else{
                    $(".user-head img").attr("src",staticDomain+data.member.header);
                }
                $(".user-head img").attr("onclick","userIndex(1)");
                $(".user-head img").attr("onmouseover","this.style.cursor='pointer'");
                var username = data.member.name;
                if(username == null || username == ''){
                    username = data.member.loginName;
                }

                $(".user-guarant-box .user-box > p").text("Hi! "+username);
                $(".user-guarant-box .user-box > p").attr("onmouseover","this.style.cursor='pointer'");
                $(".user-guarant-box .user-box > p").attr("onclick","userIndex(2)");
                var htm;
            } else {
                //未登录
                userName.push('<a  onclick="_hmt.push([\'_trackEvent\', \'登录\', \'开始登录\'])" href="' + mainDomain + '/member/toLogin.htm" id="headLoginBtn" target="_blank">登录 </a>');
                userStatus.push('<a onclick="_hmt.push([\'_trackEvent\', \'注册\', \'开始注册\'])" href="' + mainDomain + '/member/register.htm" target="_blank" id="headSigninBtn">注册 </a>');
            }
            $("#userName").html(userName.join(''));
            $("#userStatus").html(userStatus.join(''));

            if(data.num > 99){
                data.num = 99;
            } else if(data.num < 0){
                data.num = 0;
            }
            if(data.num == null || data.num == ""){
                data.num = 0;
            }
            if(data.status) {
                if(data.num > 0){
                    $(".user-guarant-box .link").html("您购物车已有<a href='"+mainDomain+"/cart.htm?v="+new Date().getTime()+"' onmouseover='this.style.cursor="+'pointer'+"' style='width: auto;'><span>" + data.num + "</span></a>件商品");
                }else{
                    $(".user-guarant-box .link").html("您购物车暂无商品");
                }

            }

            var elem = $(".cartRightTop");
            if(elem.length == 0){
                return;
            }
            elem.text(data.num);
            elem.show();
        }
    }

    //跳转用户详情页
    function userIndex(i){
        var url = "",currentUrl = window.location.href;
        if(i === 1 || i === "1"){
            url = "";
        }else{
            url = "/userDetail/accountSettings.shtml";
        }
        top.window.location.href = mainDomain + "/member/toLogin.htm?url="+url;
    }

    // 退出登录
    function operateLogout(){
        $.get(mainDomain + "/member/logout.htm", function (data){
            if(data.state){
                if(window.location.href.indexOf("?") != -1){
                    top.window.location.href = window.location.href+"&t="+new Date().getTime();
                }else{
                    top.window.location.href = window.location.href+"?t="+new Date().getTime();
                }

                // window.location.reload(new Date().getTime());
            }
        }, 'jsonp');
    }

    $(".dgg-tool-company-inquiries .price").each(function (index,value) {
        var span = $(value);
        var text = span.text();
        text = text.split("~")[0].trim();
        if("-1" == text){
            text = "面议";
        }
        span.html(text)

    })
</script>
</body>
</html>