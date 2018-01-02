<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单页</title>
</head>
<body>
<div class="header">
    <ul class="nav">
        <c:forEach var="nav" items="${navs}">
            <li><a href="${nav.dir}">${nav.name}</a></li>
        </c:forEach>
    </ul>
</div>
<div>
    <h3>${catalog.name}</h3>
    <div>
        ${catalog.content}
    </div>
</div>
<div class="footer"></div>
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
