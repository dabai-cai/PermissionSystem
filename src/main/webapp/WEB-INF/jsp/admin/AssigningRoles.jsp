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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配</title>
</head>
<body>
<%
    User user=(User)request.getAttribute("user");
    ArrayList<Role> roles=(ArrayList<Role>)request.getAttribute("roles");
    Set<Role> roleForUser=(Set<Role>)request.getAttribute("roleForUser");
    %>
   用户：<%=user.getUsername() %>
   已经分配角色:
<%
    for(Role role:roleForUser)
    {
      %>
<%=role.getRolename()+"," %><a href="/admin/roledelUser?userId=<%=user.getUserId() %>&roleId=<%=role.getRoleId() %>">删除</a>
<%
    }
%>
   未分配角色：
<%
    for(Role role:roles)
    {
        %>
<%=role.getRolename()+"," %><a href="/admin/roleForUser?userId=<%=user.getUserId() %>&roleId=<%=role.getRoleId() %>">分配</a>
<%


    }
%>
</body>
</html>
