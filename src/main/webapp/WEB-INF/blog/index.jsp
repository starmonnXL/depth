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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
        <div class="topblog">
            <h3><p>热门文章</p></h3>
            <img src="<%=basePath%>/images/blog/03.jpg" alt="" title="" width="315" height="205">
            <ul>
                <li><a href="/">女孩都有浪漫的小情怀<span>当玫瑰花瓣飘飞的那一刻，风会带走所有的想念...</span></a></li>
                <li><a href="/">6月毕业季，祝福送给你<span>世界上最幸福世界上最幸福的国家世界上最幸福的国家的...</span></a></li>
                <li><a href="/">也许下个路口就会遇见希望<span>为了一个不存在的梦，执念了那么多年...</span></a></li>
            </ul>
        </div>


        <article>
            <div class="picshow">
                <div class="ac01"><img src="<%=basePath%>/images/blog/ac01.jpg">
                    <p class="ac01_text"><a href="/">Nature</a></p>
                </div>
                <div class="ac02"><img src="<%=basePath%>/images/blog/ac02.jpg">
                    <p class="ac02_text"><a href="/">Fashion</a></p>
                </div>
                <div class="ac03"><img src="<%=basePath%>/images/blog/ac03.jpg">
                    <p class="ac03_text"><a href="/">Sport</a></p>
                </div>
            </div>
        </article>


        <div class="bloglist">
            <!--article begin-->
            <div class="blog">
                <h2>Life</h2>
                <ul>
                    <img src="<%=basePath%>/images/blog/01.jpg"><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic01">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
            <!--article begin-->
            <div class="blog">
                <h2>Life</h2>
                <ul>
                    <img src="<%=basePath%>/images/blog/01.jpg"><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic02">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>

            <div class="blog">
                <h2>Life</h2>
                <ul>
                    <img src="<%=basePath%>/images/blog/01.jpg"><h3 class="title"><a href="http://www.yangqq.com/news/read/2013-06-11/212.html" id="pic03">有一种思念，是淡淡的幸福,一个心情一行文字</a></h3>
                    <p>1) 有一种思念，是淡淡的幸福；有一种幸福，是常常的牵挂；有一种牵挂，是远远地欣赏。不是所有的梦都能实现；不是所有的话都来得及告诉；不是所有的爱都有结果。请为爱珍重，等到你的发丝有了白雪的痕迹，还能记起曾有这么一段美好，还有这么一个让自己怀念的人，何尝不是一种幸福。</p>
                    <p class="dateview"><span>2013-05-11</span><span>作者：杨青</span><span>分类：<a href="/" target="_blank">心得笔记</a></span><span>阅读(229)</span><span>评论(124)</span></p>
            </div>
            <!--article end-->
        </div>
    </div>
    <aside class="navsidebar">
        <nav>
            <ul>
                <li class="ab"><a href="#" >关于我</a></li>
                <li class="sy"><a href="#">碎言碎语</a></li>
                <li class="js"><a href="#">技术探讨</a></li>
                <li class="msh"><a href="#">慢生活</a></li>
                <li class="xc"><a href="#">我的相册</a></li>
                <li class="ly"><a href="#">留言版</a></li>
            </ul>
        </nav>
        <div class="slide">
            <!-- 代码 开始 -->
            <div id="frame" >
                <a id="a1" class="num">1</a>
                <a id="a2" class="num">2</a>
                <a id="a3" class="num">3</a>
                <a id="a4" class="num">4</a>
                <a id="a5" class="num">5</a>
                <div id="photos" class="play">
                    <img src="<%=basePath%>/images/blog/ac01.jpg" >
                    <img src="<%=basePath%>/images/blog/ac02.jpg" >
                    <img src="<%=basePath%>/images/blog/ac03.jpg" >
                    <img src="<%=basePath%>/images/blog/ac01.jpg" >
                    <img src="<%=basePath%>/images/blog/ac02.jpg" >
                    <ul id="dis">
                        <li>中国标志性建筑：天安门</li>
                        <li>中国标志性建筑：东方明珠</li>
                        <li>中国标志性建筑：布达拉宫</li>
                        <li>中国标志性建筑：长城</li>
                        <li>中国标志性建筑：天坛</li>
                    </ul>
                </div>
            </div>
            <!-- 代码 结束 -->
        </div>
        <!--<h2><p>个人档案</p></h2>
        <div class="vcard">
          <p class="fn">姓名：杨青</p>
          <p class="nickname">网名：DanceSmile | 即步非烟</p>
          <p class="url">主页：<a href="http://www.yangqq.com" target="_blank">www.yangqq.com</a></p>
          <p class="address">现居：天津市―滨海新区</p>
          <p class="role">职业：网站设计、网站制作</p>
        </div>   -->

        <div class="hot">
            <h2><p>热门文章</p></h2>
            <ul class="news">
                <li><a href="/">慢生活(3)</a></li>
                <li><a href="/">程序人生(25)</a></li>
                <li><a href="/">经典美文(39)</a></li>
            </ul>
        </div>

        <div>
            <h2><p>推荐文章</p></h2>
            <ul class="news">
                <li><a href="/">女孩都有浪漫的小情怀</a></li>
                <li><a href="/">也许下个路口就会遇见希望</a></li>
                <li><a href="/">6月毕业季，祝福送给你</a></li>
                <li><a href="/">生活常有缺席的-可搞笑从来不缺席</a></li>
                <li><a href="/">为了一个不存在的梦，执念了那么多年</a></li>
                <li><a href="/">妹妹，明天你就要嫁人啦</a></li>
            </ul>
        </div>

        <div>
            <h2><p>热门推荐工具</p></h2>
            <ul class="news">
                <li><a href="/">IntelliJ IDEA </a></li>
                <li><a href="/">mysql</a></li>
                <li><a href="/">redis</a></li>
            </ul>
        </div>

        <div>
            <h2><p>友情链接</p></h2>
            <ul class="news">
                <li><a href="http://www.yangqq.com">菜鸟教程</a></li>
                <li><a href="http://www.yangqq.com">w3cSchool</a></li>
                <li><a href="http://www.yangqq.com">菜鸟教程</a></li>
            </ul>
        </div>

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
