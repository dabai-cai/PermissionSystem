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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员界面</title>  <link href="/resources/css/bootstrap.css" rel="stylesheet">
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
</head>
<body>

<!-- 导航栏 -->
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
                    <li class="dropdown"><a href="/admin/userManager" class="dropdown-toggle" data-toggle="dropdown">功能 <b class="caret"></b></a>
                    </li>
                    <li><a href="/admin/userManager">返回用户管理</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<table align="center" class="table">
    <caption>
        管理员权限
    </caption>
    <tr>
        <td>
            用户名：
        </td>
        <td>
            账号
        </td>
        <td>
            用户密码：
        </td>
        <td>
            性别
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
            int acount=user.getAccount();
            int age=0;int phone=0;
            try{
                age=user.getAge();
            }catch (NullPointerException npe)
            {
            }
            try {
                phone=user.getPhone();
            }catch (NullPointerException npe)
            {

            }


    %>
    <tr>
        <td >
            <%=name%>
        </td>
        <td>
            <%=acount %>
        </td>
        <td >
            <%=password%>
        </td>
        <td >
            <%=sex%>
        </td >
        <td>
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModa<%=i+1%>">
                修改
            </button>
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModa<%=i+1%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel<%=i+1%>" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel<%=i+1%>">
                                修改用户信息
                            </h4>
                        </div>
                        <form method="post" action="/admin/updateUser?id=<%=user.getUserId()%>">
                            <div class="modal-body">
                                用户名：<input name="username" type="text" value="<%=name%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                密码：<input name="password" type="text" value="<%=password%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                性别：<input name="sex" type="text" value="<%=sex%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                年龄：<input name="age" type="text" value="<%=age%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                电话：<input name="phone" type="text" value="<%=phone%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    修改
                                </button>
                            </div>
                        </form>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <button onclick="javascript:window.location.href='<%=request.getContextPath()%>/admin/delUser?id=<%=user.getUserId()%>';"
                    class="btn btn-primary btn-lg">删除
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
