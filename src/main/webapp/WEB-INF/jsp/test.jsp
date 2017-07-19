<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/19 0019
  Time: 下午 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${users}" var="user">
    用户：${user.username}

    <hr border="2" color="blue" >
</c:forEach>
</body>
</html>
