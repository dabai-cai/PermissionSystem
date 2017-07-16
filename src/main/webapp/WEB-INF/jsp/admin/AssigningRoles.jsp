<%@ page import="cn.scau.hjr.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.scau.hjr.model.Role" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/14 0014
  Time: 下午 11:59
  To change this template use File | Settings | File Templates.
--%>
<%
    User user=(User)request.getAttribute("user");
    ArrayList<Role> roles=(ArrayList<Role>)request.getAttribute("roles");
    Set<Role> roleForUser=(Set<Role>)request.getAttribute("roleForUser");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <!--[if lt IE 9]>
    <script src="/resources/js/html5.js"></script>
    <![endif]-->
    <!--[if lte IE 8]><script src="/resources/js/excanvas.min.js"></script><![endif]-->
</head>
<body>
<div class="page-header">
    <h1>用户：<%=user.getUsername() %>
        <small>已经分配的角色</small>
    </h1>
</div>


<%  int i=1;
    for(Role role:roleForUser)
    {
      %>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">已分配角色<%=i%></h3>
    </div>
    <div class="panel-body">
       <h5><%=role.getRolename()%></h5>
        <button onclick="javascript:window.location.href='/admin/roledelUser?userId=<%=user.getUserId() %>&roleId=<%=role.getRoleId() %>';"
                class="btn btn-primary btn-lg">删除
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
    </div>
</div>
<% i++;
    }
%>
<div class="page-header">
    <h1>用户：<%=user.getUsername() %>
        <small>未分配的角色</small>
    </h1>
</div>

<%  int j=1;
    for(Role role:roles)
    {
        %>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 class="panel-title">未分配角色<%=j%></h3>
    </div>
<div class="panel-body">
    <h5><%=role.getRolename()%></h5>
    <button onclick="javascript:window.location.href='/admin/roleForUser?userId=<%=user.getUserId() %>&roleId=<%=role.getRoleId() %>';"
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
