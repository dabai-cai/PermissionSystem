<%--
    Document   : root
    Created on : 2017-4-20, 21:33:05
    Author     : Administrator
--%>

<%@page import="cn.scau.hjr.model.Role"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.scau.hjr.model.Pager"%>
<%@ page import="cn.scau.hjr.model.Role" %>
<%@ page import="cn.scau.hjr.model.User" %>
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
<a href="/admin/index">返回主页</a> <h3 align="right">欢迎管理员<%=((User) session.getAttribute("rootuser")).getUsername()%></h3>
<a href="/admin/addrole">新建角色</a>
<table align="center" border="2">
    <caption>
        管理员权限
    </caption>

    <form action="/admin/updare" method="post">
        <tr>
            <td>
                角色名：
            </td>
            <td>
                角色id
            </td>
            <td>
                管理员操作：
            </td>
        </tr>

        <%
            Pager pager =(Pager) session.getAttribute("pager");
            ArrayList roleList = (ArrayList) pager.getpagerData();
            for (int i = 0; i < roleList.size(); i++) {
                Role role = (Role) roleList.get(i);
                String name = role.getRolename();
                int id=role.getRoleId();

        %>
        <tr >
            <td contenteditable="true" name="">
                <%=name%>
            </td>
            <td contenteditable="true">
                <%=id%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/admin/delrole?id=<%=role.getRoleId()%>"> 删除</a>&nbsp;&nbsp;&nbsp;
                <a href="/admin/permissionAassign?id=<%=role.getRoleId()%>"> 权限分配</a>
            </td>
        </tr>
        <%
            }
        %>
    </form>
    <tr>

        <td>
            <a href="/admin/roleManager/">首页</a>&nbsp;第
            <%                for (int i = 1; i <= pager.getTotalPage(); i++) {
            %>
            <a href="/admin/roleManager?pageindex=<%=i%>"><%=i%></a>&nbsp;
            <%
                }
            %>页
        </td>

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
