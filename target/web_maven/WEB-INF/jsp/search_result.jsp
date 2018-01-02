<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="p" uri="/pager"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title><c:out value="${catalog.pageTitle}"/></title>
    <meta name="description" content="${catalog.pageDescription }"/>
    <meta name="keywords" content="${catalog.pageKeywords }">
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
                <%--${urlPrefix}articleSearch/测试.html--%>
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
    <!--page site nav-->
    <div class="page-piece-group page-site-nav">
        <a href="/" class="dib txt">首页</a>
        <c:if test="${!empty paths}">
            <c:forEach items="${paths}" var="path">
                <span class="dib arrow">&gt;</span>
                <a href="<c:if test="${!empty path.dir}">${urlPrefix}${path.dir}</c:if><c:if test="${empty path.dir}">javascript:void(0);</c:if>" class="dib txt" ><c:if test="${!empty path.name}">${path.name}</c:if></a>
            </c:forEach>
        </c:if>
        <c:if test="${!empty catalog}">
            <span class="dib arrow">&gt;</span>
            <a href="<c:if test="${!empty catalog.dir}">${urlPrefix}${catalog.dir}</c:if><c:if test="${empty catalog.dir}">javascript:void(0);</c:if>" class="dib txt" ><c:if test="${!empty catalog.name}">${catalog.name}</c:if></a>
        </c:if>
    </div>
    <!--main content start-->
    <div class="page-piece-group">
        <c:if test="${empty paths}">
            <c:if test="${!empty title}">
                <p class="search-prompt">含“<span>
                    ${title}
                </span>”&nbsp;&nbsp;&nbsp;的搜索结果共<span class="num">${totalNum}</span>条</p>
            </c:if>
        </c:if>
        <div class="service-main-box">
            <div class="service-lists-box">
                <ul class="service-lists">
                    <c:if test="${!empty pageData.obj}">
                        <c:forEach items="${pageData.obj}" var="article">
                            <li>
                                <a href="<c:if test="${!empty article.link}">${article.link}</c:if><c:if test="${empty article.link}">${urlPrefix}article/${article.id}.html</c:if>" class="db">
                                    <div class="serve-img">
                                        <img src="<c:if test="${!empty article.picName}">${article.picName}</c:if>" alt=""/>
                                    </div>
                                    <div class="service-info">
                                        <p class="tit">${article.title}</p>
                                        <p class="describe">${article.summary}</p>
                                        <div class="data-info">
                                            <span class="dib"><i class="dib source"></i><span class="dib"><c:if test="${!empty article.picName}">新闻来源：${article.source}</c:if></span></span>
                                            <span class="dib"><i class="dib count"></i><span class="dib"><c:if test="${!empty article.picName}">阅读：${article.click}</c:if></span></span>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
                <%--<div class="paging-piece">--%>
                    <%--<div class="page-num dib">--%>
                        <%--<a href="javascript:void(0);" class="dib page-btn on">1</a>--%>
                        <%--<a href="javascript:void(0);" class="dib page-btn">2</a>--%>
                        <%--<a href="javascript:void(0);" class="dib page-btn">3</a>--%>
                    <%--</div>--%>
                    <%--<span class="dib omit">...</span>--%>
                    <%--<a href="javascript:void(0);" class="dib page-btn last-btn">尾页</a>--%>
                    <%--<a href="javascript:void(0);" class="dib page-btn next-btn">下一页</a>--%>
                    <%--<div class="jump-to dib">到第<input type="text"/>页</div>--%>
                    <%--<a href="javascript:void(0);" class="dib sure-btn">确定</a>--%>
                <%--</div>--%>
                <!-- 之前页面的分页 start -->
                <c:if test="${!empty catalogId}">
                    <p:pager pageSize="${pageData.page.showCount}" pageNo="${pageData.page.currentPage}" url="${urlPrefix}articleSearch/title/${catalogId}.html" recordCount="${pageData.page.totalResult}" />
                </c:if>
                <c:if test="${!empty title}">
                    <p:pager pageSize="${pageData.page.showCount}" pageNo="${pageData.page.currentPage}" url="${urlPrefix}articleSearch/${title}/catalogId.html" recordCount="${pageData.page.totalResult}" />
                </c:if>
                <!-- 分页 end -->
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
                    <h4><i></i>免费电话咨询</h4>
                    <p class="input_line">
                        <input type="text" class="nowzixunClass" placeholder="请输入电话，立即免费咨询" maxlength="11">
                    </p>
                    <p class="sub_line">
                        <button class="now_question" onclick="submitZixun(1)">免费咨询</button>
                    </p>
                    <p class="bottom_text">一对一专属服务，<span class="red">10分钟</span>内快速响应</p>
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
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="519"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="529"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="539"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="549"></div>
                <div class="dgg-adv-box" adv-width="300" adv-height="300" adv-id="559"></div>
            </div>
            <div class="clear"></div>
        </div>
        <!--advert-->
    </div>
    <!--main content end-->
</div>
<jsp:include page="../commons/footer.jsp"/>
<script src="${urlPrefix}js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/vue/vue.js" type="text/javascript"></script>
<script src="${urlPrefix}js/layer3.0.1/layer.min.js" type="text/javascript"></script>
<script src="${urlPrefix}js/superslide.js"></script>
<script src="${urlPrefix}js/advertisement.js"></script>
<script>
    $(function(){
        //搜索时获取搜索字段
       $(".head-search-box>a").on("click",function(){
          var searchVal = $(".head-search-box>input").val();
          $(this).attr("href", "${urlPrefix}articleSearch/" + searchVal + "/catalogId.html");
       });
    });

    $(".right_hot_service .price").each(function (index,value) {
        var span = $(value);
        var text = span.text();
        text = text.split("~")[0].trim();
        if("-1" == text){
            text = "面议";
        }
        span.html(text)

    })
</script>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?36f80bbd75d80e6e4023062b54e51ffd";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();


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