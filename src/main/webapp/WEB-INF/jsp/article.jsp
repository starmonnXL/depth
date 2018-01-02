<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>${info.pageTitle }</title>
    <meta name="description" content="${info.pageDescription }"/>
    <meta name="keywords" content="${info.pageKeywords }">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/base-v1.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/global-v1.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/news-wiki.css">
    <link rel="stylesheet" href="/cmsFront${urlPrefix}css/foot.css">
    <style>
        .h1{
            font-size: 16px;
        }
    </style>
</head>
<body>
<jsp:include page="../commons/head.jsp"/>
<jsp:include page="header.jsp"/>

<!--      文章详情    start     -->
<div class="f6-box">
    <!-- 广告位 start -->
    <div class="dgg-adv-box" adv-width="1180" adv-height="100" adv-id="9" style="margin:20px auto;">

    </div>
    <!-- 广告位 end -->
    <!--面包屑-->
    <div class="bread_nav">
        <span><img src="/cmsFront${urlPrefix}images/news-wiki/location.png" alt="" /></span>
        您当前的位置：
        <c:forEach items="${paths}" var="path">
            <a href="${urlPrefix}${path.dir}">${path.name}</a>
        </c:forEach>
        <a href="${urlPrefix}${catalog.dir}">${catalog.name}</a>
    </div>
    <!--/面包屑-->
    <div class="m-list-bg pages">
        <div class="m-list-fl">
            <ul class="m-patent-liti">
                <li class="m-patent">
                    <h1><i>${catalog.name}</i><strong class="m-patent-tt" style="color: ${info.titleColor}">${info.title}</strong></h1>
                </li>
                <li class="m-patent">
                    <span class="news-sources">新闻来源：${info.source}</span>
                    <span class="news-sources icon-bg2">阅读：${info.click}</span>
                    <span class="news-sources icon-bg3">发布时间: ${info.addtime}</span>
                    <div class="clearfix"></div>
                </li>
            </ul>
            <div class="summary">摘要：${info.summary}</div>
            <div class="m-patent-ct">
                <%--<c:if test="${!empty info.picName}"><img src="${info.picName}" alt="${info.title}" /></c:if>--%>
                ${info.content}
                <div class="m-link" style="margin-bottom: -5px">
                    <%--<div class="bdsharebuttonbox">
                        <a href="#" class="bds_more" data-cmd="more"></a>
                        <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                        <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                        <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                        <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
                        <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                    </div>--%>
                    <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more">分享到：</a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信">微信</a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博">新浪微博</a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网">豆瓣网</a><a href="#" class="bds_copy" data-cmd="copy" title="分享到复制网址">复制网址</a></div>
                    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{"bdSize":16},"image":{"viewList":["weixin","tsina","douban","copy"],"viewText":"分享到：","viewSize":"24"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","tsina","douban","copy"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                </div>
            </div>
            <ul class="f-flip">
                <c:if test="${!empty lastA}">
                    <li class="f-page">
                        <em>上一篇</em>
                        <a href="<c:if test="${!empty lastA.link}">${lastA.link}</c:if><c:if test="${empty lastA.link}">${urlPrefix}article/${lastA.id}.html</c:if>">
                            <strong>${lastA.title}</strong>
                        </a>
                    </li>
                </c:if>
                <c:if test="${!empty nextA}">
                    <li class="f-next">
                        <em>下一篇</em>
                        <a href="<c:if test="${!empty nextA.link}">${nextA.link}</c:if><c:if test="${empty nextA.link}">${urlPrefix}article/${nextA.id}.html</c:if>">
                            <strong> ${nextA.title}</strong>
                        </a>
                    </li>
                </c:if>
            </ul>
            <!--      /翻页  end     -->
            <!--      文章热评start     -->
            <%--<form class="hot-review">--%>
            <%--<u>文章热评</u>--%>
            <%--<textarea name="" id="" class="f-review" placeholder="请输入评论内容"></textarea>--%>
            <%--<button type="submit" class="f-review-btn">发表评论</button>--%>
            <%--</form>--%>
            <!--      /文章热评end    -->
            <%--<div class="m-comment-box">--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>挺好的一个想法。有心有力有坚持有自我特色</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>中国三星相关负责人称，公司一直尊重竞争公司的知识产权，希望通过友好协商，来和平解决专利纠纷问题</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>共同构建知识产权良好氛围，而非诉诸法律。针对华为早前提起的知识产权诉讼，三星开始展开反击。</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>挺好的一个想法。有心有力有坚持有自我特色</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>中国三星相关负责人称，公司一直尊重竞争公司的知识产权，希望通过友好协商，来和平解决专利纠纷问题</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<ul class="m-list-box">--%>
            <%--<li class="m-list w70"><img src="images/news-wiki/f-comment-img.png" alt=""></li>--%>
            <%--<li class="m-list">--%>
            <%--<b class="fontsize16">老炎炉430</b>--%>
            <%--<p>共同构建知识产权良好氛围，而非诉诸法律。针对华为早前提起的知识产权诉讼，三星开始展开反击。</p>--%>
            <%--<span class="deta"> <i class="icon"></i>今天08:12</span>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<div class="loadmore border-top-none">加载更多</div>--%>
            <%--</div>--%>
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
                                <a href="<c:if test="${!empty a.link}">${a.link}</c:if><c:if test="${empty a.link}">${urlPrefix}article/${a.id}.html</c:if>">
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
            <div class="dgg-adv-box" adv-width="260" adv-height="173" adv-id="19" style="">

            </div>
            <!-- 广告位 end -->
        </div>
    </div>
</div>
<jsp:include page="../commons/footer.jsp"/>

<script src="/cmsFront${urlPrefix}js/jquery-1.10.2.min.js"></script>
<script src="/cmsFront${urlPrefix}js/define/public-v1.js"></script>
<script src="/cmsFront${urlPrefix}js/superslide.js"></script>
<script src="/cmsFront${urlPrefix}js/advertisement.js"></script>
<script>
    $(".slideTxtBox").slide({trigger:"mouseover"});
    //选项切换结束
    $(".m-list-couent li").mouseenter(function() {
        $(".m-list-move").removeClass("displayblock");
        $(this).find(".m-list-move").addClass("displayblock");
    })
    //			加载更多  start
    $(".loadmore").click(function() {
        var more = $(this);
        var loading = "<span class='load'>加载中...</span>";
        if(more.html() != "加载更多") {
            return;
        }
        more.html(loading);
        $.ajax({
            type: "post",
            url: "",
            timeout: 3000,
            async: true,
            success: function(data, Status) {
                //TODO:加载列表
                /*if(total>size*page){计算是否还有更多数据
                 more.html("加载更多");
                 }else{
                 more.html("没有更多了");
                 }*/
            },
            error: function(XMLResponse) {
                //请求出错处理
                //						more.html("请求错误");
            }
        });
    })
    //			加载更多  end

    //share
    window._bd_share_config = {
        "common": {
            "bdSnsKey": {},
            "bdText": "",
            "bdMini": "2",
            "bdPic": "",
            "bdStyle": "0",
            "bdSize": "16"
        },
        "share": {}
    };
    with(document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
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