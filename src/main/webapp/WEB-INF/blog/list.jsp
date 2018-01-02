<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/29
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>goodbye Summer</title>
    <meta name="Keywords" content="" >
    <meta name="Description" content="" >
    <link href="<%=basePath%>/css/blog/index.css" rel="stylesheet">
    <link href="<%=basePath%>/css/blog/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
    <!--[if lt IE 9]>
    <script src="<%=basePath%>/js/blog/modernizr.js"></script>
    <![endif]-->
</head>
<body>
<div id="menu">
    <div class="hamburger">
        <div class="line"></div>
        <div class="line"></div>
        <div class="line"></div>
    </div>
    <div class="menu-inner">
        <ul>
            <li><i class="fa fa-leaf fa-1g"></i>最新行业咨询</li>
            <li><i class="fa fa-free-code-camp fa-1g"></i>热门行业咨询</li>
            <li><i class="fa fa-object-ungroup fa-1g"></i>互联网</li>
            <li><i class="fa fa-id-badge fa-1g"></i>移动互联网</li>
            <li><i class="fa fa-industry fa-1g"></i>自媒体专栏</li>
            <li><i class="fa fa-binoculars fa-1g"></i>电商</li>
            <li><i class="fa fa-address-book-o fa-1g"></i>人物</li>
            <li><i class="fa fa-camera fa-1g"></i>数码</li>
            <li><i class="fa fa-cubes fa-1g"></i>技术</li>
            <li><i class="fa fa-creative-commons fa-1g"></i>创业</li>
            <li><i class="fa fa-american-sign-language-interpreting fa-1g"></i>娱乐新闻</li>
            <li><i class="fa fa-tint fa-1g"></i>酣畅一笑</li>
        </ul>
    </div>
    <svg version="1.1" id="blob"xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <path id="blob-path" d="M60,500H0V0h60c0,0,20,172,20,250S60,900,60,500z"/>
    </svg>
</div>
<h2 id="sss"></h2>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<%=basePath%>/js/blog/index.js"></script>
<header>
    <h1><a href="index.html">Depth Blog</a></h1>
    <p>欢迎来到深度博客,我是Starmoon,一个java程序媛,这里有相关技术介绍,流行技术发展等的相关文章，喜欢请收藏！</p>
</header>
<div id="nav">
    <ul>
        <li><a href="blogList.html?typeId=0">技术咨询</a></li>
        <li><a href="about.html">前端</a></li>
        <li><a href="moodlist.html">服务端</a></li>
        <li><a href="ablumlist.html">UI设计</a></li>
        <li><a href="book.html">搜索引擎</a></li>
        <li><a href="newlist.html">AR/VR增强现实</a></li>
        <li><a href="newlist.html">AI视觉</a></li>
    </ul>
    <script src="<%=basePath%>/js/blog/silder.js"></script><!--获取当前页导航 高亮显示标题-->
</div>
<div class="blank"></div>
<div class="article">
    <div class="content">
        <div class="bloglist">
            <!--article begin-->
            <div class="blog box">
                <h2>Life</h2>
                <ul>

                    <div><img src="<%=basePath%>/images/blog/01.jpg"></div>
                    <h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
            <!--article begin-->
            <div class="blog box">
                <h2>UI设计</h2>
                <ul>
                    <div><img src="<%=basePath%>/images/blog/01.jpg"></div><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
            <!--article begin-->
            <div class="blog box">
                <h2>Life</h2>
                <ul>
                    <div><img src="<%=basePath%>/images/blog/01.jpg"></div><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
            <!--article begin-->
            <div class="blog box">
                <h2>Life</h2>
                <ul>
                    <div><img src="<%=basePath%>/images/blog/01.jpg"></div><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
            <div class="blog box">
                <h2>Life</h2>
                <ul>
                    <div><img src="<%=basePath%>/images/blog/01.jpg"></div><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
        </div>
    </div>
    <aside class="navsidebar">
        <h2><p>热门文章</p></h2>
        <ul class="news">
            <li><a href="/">慢生活(3)</a></li>
            <li><a href="/">程序人生(25)</a></li>
            <li><a href="/">经典美文(39)</a></li>
        </ul>

        <h2><p>推荐文章</p></h2>
        <ul class="news">
            <li><a href="/">女孩都有浪漫的小情怀</a></li>
            <li><a href="/">也许下个路口就会遇见希望</a></li>
            <li><a href="/">6月毕业季，祝福送给你</a></li>
            <li><a href="/">生活常有缺席的-可搞笑从来不缺席</a></li>
            <li><a href="/">为了一个不存在的梦，执念了那么多年</a></li>
            <li><a href="/">妹妹，明天你就要嫁人啦</a></li>
        </ul>

        <div class="share">
            <h2></h2>
            <p><!-- Baidu Button BEGIN -->
            <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">
                <a class="bds_tsina"></a>
                <a class="bds_qzone"></a>
                <a class="bds_tqq"></a>
                <a class="bds_renren"></a>
                <span class="bds_more"></span>
                <a class="shareCount"></a>
            </div>
            <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script>
            <script type="text/javascript" id="bdshell_js"></script>
            <script type="text/javascript">
                document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
            </script>
            <!-- Baidu Button END --></p>
        </div>
    </aside>
</div>
<div id="copright">Design by <a href="http://www.yangqq.com" target="_blank">DanceSmile</a></div>
</body>
</html>

