<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
欢迎你!
<shiro:hasRole name="root">
    欢迎有admin角色的用户！<shiro:principal/>
</shiro:hasRole>
<shiro:hasRole name="user">
    欢迎有user角色的用户！<shiro:principal/>
</shiro:hasRole>
<shiro:hasPermission name="换皮肤">
    欢迎有换皮肤权限的用户！<shiro:principal/>
</shiro:hasPermission>
</body>
</html>