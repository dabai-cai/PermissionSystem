<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/hjrHahalogin" method="post">
	userName:<input type="text" name="userName"/><br/>
	password:<input type="password" name="password"/><br/>
	<input type="submit" value="登录"/>${errorInfo }
</form>
</body>
</html>