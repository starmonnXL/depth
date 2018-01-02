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
    <title><c:out value="${info.pageTitle}"/></title>
    <meta name="description" content="${info.pageDescription }"/>
    <meta name="keywords" content="${info.pageKeywords }">
    <link rel="stylesheet" href="${urlPrefix}css/site-base.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/information-public.css"/>
    <link rel="stylesheet" href="${urlPrefix}css/article-details.css"/>
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
        <c:if test="${!empty paths}">
            <c:forEach items="${paths}" var="path">
                <span class="dib arrow">&gt;</span>
                <a href="<c:if test="${!empty path.dir}">${urlPrefix}${path.dir}</c:if><c:if test="${empty path.dir}">javascript:void(0);</c:if>" class="dib txt" ><c:if test="${!empty path.name}">${path.name}</c:if></a>
            </c:forEach>
        </c:if>
    </div>
    <!--main content start-->
    <div class="page-piece-group">
        <div class="service-main-box">
            <div class="service-lists-box">
                <div class="article-box">
                    <div class="article-info">
                        <p class="tit">${info.title}</p>
                        <div class="data-info">
                            <span class="dib"><i class="dib source"></i><span class="dib">新闻来源：${info.source}</span></span>
                            <span class="dib"><i class="dib count"></i><span class="dib">阅读：${info.click}</span></span>
                            <span class="dib"><i class="dib time"></i><span class="dib">发布时间：${info.addtime}</span></span>
                        </div>
                        <p class="describe">${info.summary}</p>
                    </div>
                    <!--文章内容-->
                    ${info.content}

                    <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more">分享到：</a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信">微信</a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博">新浪微博</a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网">豆瓣网</a><a href="#" class="bds_copy" data-cmd="copy" title="分享到复制网址">复制网址</a></div>
                    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{"bdSize":16},"image":{"viewList":["weixin","tsina","douban","copy"],"viewText":"分享到：","viewSize":"24"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","tsina","douban","copy"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                    <div class="up_down_page">
                        <c:if test="${!empty up}">
                        <p class="up_page"><span>上一篇</span>
                            <a href="<c:if test="${!empty up.link}">${up.link}</c:if><c:if test="${empty up.link}">${urlPrefix}article/${up.id}.html</c:if>" class="dib txt" ><c:if test="${!empty up.title}">${up.title}</c:if></a></p>
                        </c:if>
                        <c:if test="${!empty down}">
                        <p class="down_page"><span>下一篇</span>
                            <a href="<c:if test="${!empty down.link}">${down.link}</c:if><c:if test="${empty down.link}">${urlPrefix}article/${down.id}.html</c:if>" class="dib txt" ><c:if test="${!empty down.title}">${down.title}</c:if></a></p>
                        </c:if>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="correlation-article">
                    <p class="tit"><i class="dib"></i><span class="dib">相关文章</span></p>
                    <ul class="article-lists">
                        <c:if test="${!empty relevantArticle}">
                            <c:forEach items="${relevantArticle}" var="cle" end="2">
                                <li>
                                    <a href="<c:if test="${!empty cle.link}">${cle.link}</c:if><c:if test="${empty cle.link}">${urlPrefix}article/${cle.id}.html</c:if>" class="db">
                                        <div class="article-img">
                                            <img src="<c:if test="${!empty cle.picName}">${cle.picName}</c:if>" alt=""/>
                                        </div>
                                        <p class="txt">${cle.title}</p>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="slide-adv-lists">
                <!--advert-->
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="569" style="width: 300px; height: 300px;display: block;"></div>

                <div class="right_hot_service">
                    <h4><i></i>推荐服务 <a href="http://www.${operateDomain}/goods/search.htm" target="_blank">更多&gt;</a></h4>
                    <ul>
                        <c:forEach items="${hotGoods}" var="goods">
                            <li><a target="_blank" href="http://www.${operateDomain}/goods/${goods.goodsId}.htm">
                                <span class="goodsname">${goods.goodsName}</span><span class="price">${goods.goodsPrice}</span></a>
                            </li>
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

                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="579"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="589"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="599"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="609"></div>
            </div>




            <div class="clear"></div>
        </div>
        <!--advert-->
    </div>
    <!--main content end-->

    <div class="fixed-left-image-box express_box">
        <p class="site_select" onclick="switchSite()"><img src="${urlPrefix}images/index/site.jpg">城市站点</p>
        <img class="two_img" src="${urlPrefix}images/index/left_img.png">
        <div class="bottom_text">
            <p>贷款APP</p>
            <p>定制最佳贷款方案</p>
        </div>
    </div>

</div>


<div class="switchSite" style="display: none;">
    <ul>
        <c:forEach items="${listSite}" var="site">
            <li><a href="http://${site.domain}">${site.siteName}</a></li>
        </c:forEach>
    </ul>
</div>
<jsp:include page="../commons/footer.jsp"/>
<script src="${urlPrefix}js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/define/public-v1.js"></script>
<script src="${urlPrefix}js/layer3.0.1/layer.min.js"></script>
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


/**
 * 选择站点
 * */
    function switchSite(){
        layer.open({
            title:"城市站点",
            content:$(".switchSite"),
            area:["500px"],
            type:1
        });
    }


    /**
     * 二维码固定在左侧
     * */


    function initFixedExpress() {

        var left = ($(window).width()-1180)/2-160-30;
        if(left <0){
            left = 15;
        }

        $(".express_box")
            .css("left",left)
            .css("top",($(window).height()-$(".express_box").height())/2);

        //bind resize event
        $(window).on("resize",function () {
            var left = ($(window).width()-1180)/2-160-30;
            if(left <0){
                left = 15;
            }
            $(".express_box").css("left",left);
        })

    }
    initFixedExpress();


    var pageId = "${articlesId}";
    var temp = $($(".page-site-nav a")[1]);
    var cId;
    if(temp == null){
        cId = 0;
    }else{
        console.log(temp);
        var tep = temp.data("topid");
        if(tep == "0"){
            cId = temp.data("id");
        }else{
            cId = tep;
        }
        console.log(cId)
    }


    $(".right_hot_service .price").each(function (index,value) {
        var span = $(value);
        var text = span.text();
        text = text.split("~")[0].trim();
        if("-1" == text){
            text = "面议";
        }
        span.html(text)

    })


    /**
     * 服务预约
     * @param ins
     * @param cusState 状态，1为贷款信息，2为预约服务信息
     */
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