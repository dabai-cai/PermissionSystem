<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.scau.hjr.model.User" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user=(User) session.getAttribute("user"); %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>用户界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">User</a>
            <div class="btn-group pull-right">
                <a class="btn" href="#"><i class="icon-user"></i>User</a>
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
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">功能 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/Vippermission?userId=<%=user.getUserId()%>">会员专享</a></li>
                            <li class="divider"></li>
                            <li><a href="/user/studentPermission?userId=<%=user.getUserId() %>">学生特权</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</div>
<table align="center" class="table">
    <div class="form-group" align="center">
        <div class="form-group">
            <form class="form col-md-6 col-md-offset-3" role="form" name="checkForm" ng-submit="submitCheck()" action="/user/searchUser">
                <input class="form-control input-lg website-input" name="searchUser"  type="text" placeholder="输入关键字查看用户" required ng-model="website" novalidate ><button type="submit" class="btn btn-lg btn-primary website-submit">查询</button>
            </form>
        </div>
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            修改个人信息
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
                            个人信息
                        </h4>
                    </div>
                    <form method="post" action="/user/update?userId=<%=user.getUserId() %>">
                        <div class="modal-body">
                            用户名：<input name="username" type="text" value="<%=user.getUsername()%>" class="form-control input-lg website-input">
                        </div>
                        <div class="modal-body">
                            密码：<input name="password" type="text" value="<%=user.getPassword()%>" class="form-control input-lg website-input">
                        </div>
                        <div class="modal-body">
                            年龄：<input name="age" type="text" value="<%=user.getAge()%>" class="form-control input-lg website-input">
                        </div>
                        <div class="modal-body">
                            电话：<input name="phone" type="text" value="<%=user.getPhone()%>" class="form-control input-lg website-input">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-primary">
                                提交修改
                            </button>
                        </div>
                    </form>

                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>
    <tr>
        <td>
            用户名：
        </td>
        <td>
            年龄
        </td>
        <td>
            性别
        </td>
        <td>
            电话
        </td>
    </tr>
    <% ArrayList<User> users=(ArrayList<User>)request.getAttribute("searchUsers"); %>
    <%
        for(User user1:users)
        {
    %>
    <td><%=user1.getUsername()%></td>
    <td><%=user1.getAge()%></td>
    <td><%=user1.getSex()%></td>
    <td><%=user1.getPhone()%></td><tr></tr>
    <%
        }
    %>
</table>

<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.flot.js"></script>
<script src="/resources/js/jquery.flot.resize.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>
