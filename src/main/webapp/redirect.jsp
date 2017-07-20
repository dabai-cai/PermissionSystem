<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/13 0013
  Time: 上午 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath()+"/user/login");
%>
</body>
</html>
