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
    <title>用户界面</title>
</head>
<body>
 <%
     User user=(User) session.getAttribute("user");
 %>
 <h3 align="right">欢迎用户<%=user.getUsername()%></h3>
<a href="/admin/addUser">修改个人信息</a>
<table align="center" border="2">
    <caption>
       用户界面
    </caption>
    <form action="/user/searchUser" method="post">
        <tr>
            <td>
                查找用户
            </td>
            <td>
                <input type="text" name="searchUser" size="30">
            </td>
            <td>
                <input type="submit" name="search"  value="搜索">
            </td>

        </tr>
    </form>

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
                用户操作：
            </td>
        </tr>
    <tr>
        <td>
            <%=user.getUsername() %>
        </td>
        <td>
            <%=user.getPassword() %>
        </td>
        <td>
            <%=user.getRegisterTime().toString() %>
        </td>
        <td>
            <a href="#">修改个人信息</a>
        </td>
    </tr>
    <hr/>
    <tr>
      场景  <a href="/user/Vippermission?userId=<%=user.getUserId()%>">vip会所</a>&nbsp;<a href="/user/robPermission?userId=<%=user.getUserId() %>">抢劫天堂</a>
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
