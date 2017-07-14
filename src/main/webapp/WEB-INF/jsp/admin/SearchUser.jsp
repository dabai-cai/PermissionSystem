<%--
    Document   : root
    Created on : 2017-4-20, 21:33:05
    Author     : Administrator
--%>

<%@page import="cn.scau.hjr.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.scau.hjr.model.Pager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="/resources/register/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/register/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/register/css/form-elements.css">
    <link rel="stylesheet" href="/resources/register/css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员界面</title>
</head>
<body>
<a href="/admin/userManager">返回用户管理界面</a> <h3 align="right">欢迎管理员<%=((User) session.getAttribute("rootuser")).getUsername()%></h3>

<table align="center" border="2">
    <caption>
        管理员权限
    </caption>
    <tr>
        <td>
            用户名：
        </td>
        <td>
            用户密码：
        </td>
        <td>
            注册时间
        </td>
        <td>
            管理员操作：
        </td>
    </tr>

    <%
        ArrayList userList  =(ArrayList<User>) request.getAttribute("searchUsers");
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);
            String name = user.getUsername();
            String password = user.getPassword();
            String sex = user.getSex();

    %>
    <tr >
        <td contenteditable="true">
            <%=name%>
        </td>
        <td contenteditable="true">
            <%=password%>
        </td>
        <td contenteditable="true">
            <%=sex%>
        </td >
        <td>
            <a href="<%=request.getContextPath()%>/admin/delUser?id=<%=user.getUserId()%>"> 删除</a>&nbsp;&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/updateUser?id=<%=user.getUserId()%>"> 修改</a>
        </td>
    </tr>
    <%
        }
    %>

    <tr>

    </tr>
</table>
<!-- Javascript -->
<script src="/resources/register/js/jquery-1.11.1.min.js"></script>
<script src="/resources/register/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/register/js/jquery.backstretch.min.js"></script>
<script src="/resources/register/js/retina-1.1.0.min.js"></script>
<script src="/resources/register/js/scripts.js"></script>
</body>
</html>
