<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.scau.hjr.model.Permission" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/15 0015
  Time: 下午 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vip会所</title>
</head>
<body>
这里是vip会所，拥有权限  &nbsp;&nbsp;<br>
<% ArrayList<Permission> permissions=(ArrayList<Permission>)request.getAttribute("permissions");%>
<%
    for(Permission permission:permissions)
    {
        %>
&nbsp;&nbsp; <%=permission.getPermission()%><br>
<%
    }
%>
</body>
</html>
