<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="opr" uri="http://news.xiaodingwang.com/jsp/operate"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title><c:out value="${dto.catalog.pageTitle}"/></title>
    <meta name="description" content="${dto.catalog.pageDescription }"/>
    <meta name="keywords" content="${dto.catalog.pageKeywords }">
    <link rel="stylesheet" href="${urlPrefix}css/site-base.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/information-public.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/inner-index.css"/>
</head>
<body>
<jsp:include page="../commons/head.jsp"/>
<div class="page-content">
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
    <!--page site nav-->
    <div class="page-piece-group page-site-nav">
        <a href="/" class="dib txt">首页</a>
        <span class="dib arrow">&gt;</span>
        <a href="<c:if test="${!empty dto.catalogDir}">${urlPrefix}${dto.catalogDir}</c:if><c:if test="${empty dto.catalogDir}">javascript:void(0);</c:if>" class="dib txt" ><c:if test="${!empty dto.catalogName}">${dto.catalogName}</c:if></a>

        <c:if test="${!empty dto.catalogDirOne}">
            <span class="dib arrow">&gt;</span>
            <a href="<c:if test="${!empty dto.catalogDirOne}">${urlPrefix}${dto.catalogDirOne}</c:if><c:if test="${empty dto.catalogDirOne}">javascript:void(0);</c:if>" class="dib txt" ><c:if test="${!empty dto.catalogNameOne}">${dto.catalogNameOne}</c:if></a>
        </c:if>
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
                </ul>
            </div>
        </div>
        <div class="hot-news today-news">
            <div class="tit">
                <img src="${urlPrefix}images/index/today-hot.png" alt=""/>
            </div>
            <c:if test="${!empty hotspot}">
                <c:forEach items="${hotspot}" var="hot" end="0">
                    <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>" class="news-txt dib">${hot.title}</a>
                </c:forEach>
                <ul class="hot-news-lists">
                    <c:forEach items="${hotspot}" var="hot" begin="1" end="2">
                        <li>
                            <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>" class="news-txt">${hot.title}</a>
                            <p class="describe">摘要:${hot.summary}
                                <a href="<c:if test="${!empty hot.link}">${hot.link}</c:if><c:if test="${empty hot.link}">${urlPrefix}article/${hot.id}.html</c:if>" class="details-btn">[详情]</a>
                            </p>
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
    <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="449">

    </div>
    <!--main content start-->
    <div class="page-piece-group main-content-piece">
        <div class="cont-nav">
            <a href="${urlPrefix}${dto.dir}" class="dib <c:if test="${empty dto.selectCatalogId}">on</c:if>">全部</a>
            <c:if test="${!empty catalogOne}">
                <c:forEach items="${catalogOne}" var="one">
                    <a href="<c:if test="${!empty one.dir}">${urlPrefix}${one.dir}</c:if><c:if test="${empty one.dir}">javascript:void(0);</c:if>" class="dib <c:if test="${!empty dto.selectCatalogId}"><c:if test="${dto.selectCatalogId == one.id}">on</c:if></c:if>">${one.name}</a>
                </c:forEach>
            </c:if>
        </div>
        <div class="service-navs">
            <div class="service-navs-lists">
                <c:if test="${!empty catalogList}">
                    <c:forEach items="${catalogList}" var="list">
                        <a href="<c:if test="${!empty list.dir}">${urlPrefix}${list.dir}</c:if><c:if test="${empty list.dir}">javascript:void(0);</c:if>" class="dib <c:if test="${!empty dto.catalogIdOnt}"><c:if test="${dto.catalogIdOnt == list.id}">on</c:if></c:if>">${list.name}</a>
                    </c:forEach>
                </c:if>
                <c:if test="${empty dto.selectCatalogId}">
                    <c:if test="${!empty catalogTwo}">
                        <c:forEach items="${catalogTwo}" var="two">
                            <a href="<c:if test="${!empty two.dir}">${urlPrefix}${two.dir}</c:if><c:if test="${empty two.dir}">javascript:void(0);</c:if>" class="dib">${two.name}</a>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
            <!--load more arrow-->
            <a href="javascript:void(0);" class="dib load-more"></a>
        </div>
        <div class="service-main-box">
            <div class="service-lists-box">
                <ul class="service-lists">
                    <c:if test="${!empty article}">
                        <c:forEach items="${article}" var="article" end="7">
                            <li>
                                <a href="<c:if test="${!empty article.link}">${article.link}</c:if><c:if test="${empty article.link}">${urlPrefix}article/${article.id}.html</c:if>" class="db">
                                    <div class="serve-img">
                                        <img src="<c:if test="${!empty article.picName}">${article.picName}</c:if>" alt=""/>
                                    </div>
                                    <div class="service-info">
                                        <p class="tit">${article.title}</p>
                                        <p class="describe">${article.summary}</p>
                                        <div class="data-info">
                                            <span class="dib"><i class="dib source"></i><span class="dib"><c:if test="${!empty article.source}">新闻来源：${article.source}</c:if></span></span>
                                            <span class="dib"><i class="dib count"></i><span class="dib"><c:if test="${!empty article.click}">阅读：${article.click}</c:if></span></span>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
                <c:if test="${!empty articleTotal && articleTotal > 1}">
                    <a id="shows" href="javascript:void (0);" class="db browse-more-btn transition-05" onclick="articlePage()">浏览更多&gt;</a>
                </c:if>
            </div>
            <div class="slide-adv-lists">
                <div class="hot_news_line">
                    <h4><i></i>热门文章排行</h4>
                    <ul>
                        <c:forEach items="${hotNews}" var="a" varStatus="statu">
                            <li><a href="<c:if test="${!empty a.link}">${a.link}</c:if><c:if test="${empty a.link}">${urlPrefix}article/${a.id}.html</c:if>">${a.title}</a></li>
                        </c:forEach>
                    </ul>
                </div>


                <div class="service_zixun">
                    <h4><i></i>服务预约</h4>
                    <p class="input_line">
                        <input type="text" class="nowzixunClass" placeholder="输入您的手机号 专人服务" maxlength="11">
                    </p>
                    <p class="sub_line">
                        <button class="now_question" onclick="submitZixun(1)">免费预约</button>
                    </p>
                    <h4><i></i>推荐顾问</h4>
                    <c:if test="${not empty hotAdvicerList}">
                        <opr:commonTemplateTag siteId="100000" templateId="${hotAdvicerList}" names="${typeName}" path="hot_adviser_list.ftl"/>
                    </c:if>
                </div>

                <div class="right_hot_service">
                    <h4><i></i>推荐服务 <a href="http://www.${operateDomain}/goods/search.htm" target="_blank">更多&gt;</a></h4>
                    <ul>
                        <c:forEach items="${hotGoods}" var="goods">
                            <li>
                                <a target="_blank" href="http://www.${operateDomain}/goods/${goods.goodsId}.htm">
                                    <span class="goodsname">${goods.goodsName}</span><span class="price">${goods.goodsPrice}</span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>


                <!--advert-->
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="459"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="469"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="479"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="489"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="499"></div>
            </div>
            <div class="clear"></div>
        </div>
        <!--advert-->
        <div class="page-piece-group dgg-adv-box" adv-width="1180" adv-height="100" adv-id="509">

        </div>
    </div>
    <!--main content end-->
    <c:if test="${!empty dto.catalogIdOnt}">
        <input type="hidden" id="path" value="${dto.catalogIdOnt}">
    </c:if>
    <c:if test="${empty dto.catalogIdOnt}">
        <input type="hidden" id="path" value="${dto.path}">
    </c:if>
    <input type="hidden" class="selectCatalog" value="${dto.selectCatalogId}">
    <input type="hidden" id="articleTotal" value="${articleTotal}">
</div>
<jsp:include page="../commons/footer.jsp"/>
<script src="${urlPrefix}js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/layer3.0.1/layer.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/superslide.js"></script>
<script src="${urlPrefix}js/advertisement.js"></script>
<script>
    $(function(){
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
        // service navs
        var navEleBox = $(".service-navs"),
            navEle = $(".service-navs>.service-navs-lists"),
            moreBtn = $(".service-navs>.load-more");
        if(navEle.height() <28){
            navEleBox.css({"border":"none"});
        }
        if(navEle.height() > 56){
            moreBtn.addClass("showEle");
        }
        moreBtn.on("click", function(){
            navEle.animate({
                "max-height": "100%"
            }, 1000);
            $(this).hide();
        });
        var locationNum = $(".cont-nav").offset().top;
        var selectCatalog = $(".selectCatalog").val();
        if(selectCatalog != "" && selectCatalog != null){
            $(window).scrollTop(locationNum);
        }

        //搜索时获取搜索字段
        $(".head-search-box>a").on("click",function(){
            var searchVal = $(".head-search-box>input").val();
            $(this).attr("href", "${urlPrefix}articleSearch/" + searchVal + "/catalogId.html")
        });
    });

    var pg = 2;
    function articlePage(){
        var articleTotal = $("#articleTotal").val(),path = $("#path").val();
        if(parseInt(articleTotal) >= parseInt(pg)){
            if(parseInt(articleTotal) == parseInt(pg)){
                $("#shows").hide();
            }
            $.ajax({
                type: "post",
                url: "${urlPrefix}articlePage.html",
                dataType: "json",
                data: {currentPage:pg,path:path},
                success: function(data, value) {
                    if(data != null && data != "" ){
                        var articles = [],list = $(".service-lists");
                        $.each(data, function(i,article){
                            articles.push('<li>');
                            if(article.link == null || article.link == ""){
                                articles.push('<a href="/article/'+article.id+'.html" class="db">');
                            }else{
                                articles.push('<a href="'+article.link+'" class="db">');
                            }
                            articles.push('<div class="serve-img">');
                            articles.push('<img src="'+article.picName+'" alt=""/>');
                            articles.push('</div>');
                            articles.push('<div class="service-info">');
                            articles.push('<p class="tit">'+article.title+'</p>');
                            articles.push('<p class="describe">'+article.summary+'</p>');
                            articles.push('<div class="data-info">');
                            articles.push('<span class="dib"><i class="dib source"></i><span class="dib">新闻来源：'+article.source+'</span></span>');
                            articles.push('<span class="dib"><i class="dib count"></i><span class="dib">阅读：'+article.click+'</span></span>');
                            articles.push('</div>');
                            articles.push('</div>');
                            articles.push('<div class="clear"></div>');
                            articles.push('</a>');
                            articles.push('</li>');
                        });
                        list.append(articles.join(''));
                    }
                    pg++;
                },
                error: function(XMLResponse) {
                    alert("错误！"+XMLResponse.status);
                }
            });
        }
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

    $(".right_hot_service .price").each(function (index,value) {
        var span = $(value);
        var text = span.text();
        text = text.split("~")[0].trim();
        if("-1" == text){
            text = "面议";
        }
        span.html(text)

    })

    function submitZixun(ins) {
        var appointment = $(".nowzixunClass").val();
        if(null == appointment || "" == appointment ){
            layer.msg("请输入您的手机号");
            return;
        }else if(!(/^1[34578]\d{9}$/.test(appointment))){
            layer.msg("手机号格式不正确，请重新输入");
            $(".appointment"+ins).val('');
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/addClue.htm',
            async:true,
            data: {
                cusState: 2,
                customerPhone:appointment
            },
            success: function(data) {
                if (data.status) {
                    $(".appointment"+ins).val('');
                    layer.msg("保存成功");
                }
            },
            error:function(data){
                $(".appointment"+ins).val('');
                layer.msg("保存成功");
            }
        });
    }
</script>
</body>
</html>