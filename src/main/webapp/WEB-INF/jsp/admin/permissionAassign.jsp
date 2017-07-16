<%@ page import="cn.scau.hjr.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.scau.hjr.model.Role" %>
<%@ page import="java.util.Set" %>
<%@ page import="cn.scau.hjr.model.Permission" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14 0014
  Time: 下午 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Role role =(Role) request.getAttribute("role");
    ArrayList<Permission> permissions=(ArrayList<Permission>)request.getAttribute("permissions");
    Set<Permission> permissionsForRole=(Set<Permission>)request.getAttribute("permissionsForRole");
%>
<html>
<head>
    <title>权利分配</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <!--[if lt IE 9]>
    <script src="/resources/js/html5.js"></script>
    <![endif]-->
    <!--[if lte IE 8]><script src="/resources/js/excanvas.min.js"></script><![endif]-->
</head>
<body>
<div class="page-header">
    <h1>角色：<%=role.getRolename() %>
        <small>已经分配的权利</small>
    </h1>
</div>
<% int i=1;
    for(Permission permission:permissionsForRole)
    {
%>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">已分配权利<%=i%></h3>
    </div>
    <div class="panel-body">
        <%=permission.getPermission()%>
        <button onclick="javascript:window.location.href='/admin/delPermissionRole?roleId=<%=role.getRoleId()%>&permissionId=<%=permission.getPermissionId() %>';"
                class="btn btn-primary btn-lg">删除
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
    </div>
</div>
<% i++;
    }
%>
<div class="page-header">
    <h1>角色：<%=role.getRolename() %>
        <small>未分配的权利</small>
    </h1>
</div>
<%  int j=1;
    for(Permission permission:permissions)
    {
%>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 class="panel-title">未分配权利<%=j%></h3>
    </div>
    <div class="panel-body">
        <%=permission.getPermission()%>
        <button onclick="javascript:window.location.href='/admin/permissionForRole?roleId=<%=role.getRoleId()%>&permissionId=<%=permission.getPermissionId() %>';"
                class="btn btn-primary btn-lg">分配
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
    </div>
</div>
<%
j++;
    }
%>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
