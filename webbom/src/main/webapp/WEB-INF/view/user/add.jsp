<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>
	<sf:form mothed="post" modelAttribute="user" enctype="multipart/form-data">
	 UserName:<sf:input path="username"/><sf:errors path="username" /><br>
	 PassWord:<sf:password path="password"/><sf:errors path="password" /><br>
	 Email:<sf:input path="email"/><sf:errors path="email" /><br>
	 NickName:<sf:input path="nickname"/><br>
	 File:<input type="file" name="files"/>5M以下文件
	 <input type="submit" value="添加用户" /><br>
	</sf:form>
</body>
</html>