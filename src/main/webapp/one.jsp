<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>简洁Bootstrap响应式后台管理系统模板下载</title>
    <meta name="keywords" content="Bootstrap模板,Bootstrap3模版,Bootstrap模板下载,Bootstrap后台模板,Bootstrap教程,Bootstrap中文,后台管理系统模板,后台模板下载,后台管理系统,后台管理模板" />
    <meta name="description" content="JS代码网提供Bootstrap模板,后台管理系统模板,后台管理界面,Bootstrap后台板版下载" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/site.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!--[if lte IE 8]><script src="/resources/js/excanvas.min.js"></script><![endif]-->
    <style type="text/css">
        html, body {
            height: 100%;
        }
    </style>
</head>
<body>
<button type="button" class="btn btn-default" aria-label="Left Align">
    <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
</button>
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
                <a class="btn" href="my-profile.html"><i class="icon-user"></i>User</a>
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="my-profile.html">个人资料</a></li>
                    <li class="divider"></li>
                    <li><a href="#">退出</a></li>
                </ul>
            </div>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="/user/index">首页</a></li>

                </ul>
            </div>
        </div>
    </div>
</div>
<table align="center" class="table">
    <form class="form col-md-6 col-md-offset-3" role="form" name="checkForm" ng-submit="submitCheck()">
        <div class="form-group" align="center">
            <input class="form-control input-lg website-input" type="text" placeholder="输入关键字查看用户" required ng-model="website" novalidate><button type="submit" class="btn btn-lg btn-primary website-submit">Submit</button>
        </div>
    </form>
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
</table>

<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.flot.js"></script>
<script src="/resources/js/jquery.flot.resize.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>

