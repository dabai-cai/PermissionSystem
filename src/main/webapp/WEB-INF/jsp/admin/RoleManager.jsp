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
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/site.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="/resources/js/html5.js"></script>
    <![endif]-->
    <!--[if lte IE 8]><script src="/resources/js/excanvas.min.js"></script><![endif]-->
    <style type="text/css">
        html, body {
            height: 100%;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权利分配</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Admin</a>
            <div class="btn-group pull-right">
                <a class="btn" href="#"><i class="icon-user"></i>Admin</a>
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li class="divider"></li>
                    <li><a href="#">退出</a></li>
                </ul>
            </div>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="dropdown"><a href="/admin/index" class="dropdown-toggle" data-toggle="dropdown">首页 <b class="caret"></b></a>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">功能 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/addRole">新建角色</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<table align="center" class="table">
    <caption>
        管理员权限
    </caption>
    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
        新建角色
    </button>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        新建一个角色
                    </h4>
                </div>
                <form method="post" action="/admin/addRole">
                    <div class="modal-body">
                        角色名：<input name="role" type="text" value="如：总统" class="form-control input-lg website-input">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            新建
                        </button>
                    </div>
                </form>

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
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
                <a href="<%=request.getContextPath()%>/admin/delRole?id=<%=role.getRoleId()%>"> 删除</a>&nbsp;&nbsp;&nbsp;
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
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.flot.js"></script>
<script src="/resources/js/jquery.flot.resize.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
