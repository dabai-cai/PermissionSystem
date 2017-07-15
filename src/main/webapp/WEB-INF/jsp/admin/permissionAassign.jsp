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
<html>
<head>
    <title>角色分配</title>
</head>
<body>
<%
    Role role =(Role) request.getAttribute("role");
    ArrayList<Permission> permissions=(ArrayList<Permission>)request.getAttribute("permissions");
    Set<Permission> permissionsForRole=(Set<Permission>)request.getAttribute("permissionsForRole");
%>
角色：<%=role.getRolename()%>
已经分配权利:
<%
    for(Permission permission:permissionsForRole)
    {
%>
<%=permission.getPermission()+"," %>
<%
    }
%>
未分配权利：
<%
    for(Permission permission:permissions)
    {
%>
<%=permission.getPermission()+"," %><a href="/admin/permissionForRole?roleId=<%=role.getRoleId()%>&permissionId=<%=permission.getPermissionId() %>">分配</a>
<%


    }
%>
</body>
</html>
