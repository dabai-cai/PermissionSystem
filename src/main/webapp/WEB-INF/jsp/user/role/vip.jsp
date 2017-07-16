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
    <title>会员专享</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
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
<div class="page-header">
    <h1>会员特权</h1>
</div>
<% ArrayList<Permission> permissions=(ArrayList<Permission>)request.getAttribute("permissions");%>
<%  int i=1;
    for(Permission permission:permissions)
    {
        %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">特权<%=i%></h3>
    </div>
    <div class="panel-body">
        <%=permission.getPermission()%>
    </div>
</div>
<%
  i++;  }
%>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
