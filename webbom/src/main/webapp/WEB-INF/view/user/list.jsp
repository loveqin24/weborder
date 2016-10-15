<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
<!-- <link rel="stylesheet" href="../../resources/css/main.css" type="text/css"></link> -->
</head>
<body>
	<a href="add">添加</a><br>
	<c:forEach items="${userList}" var="um">
		<a href="${um.getUsername() }">${um.getUsername() }</a> 
		-----${um.getPassword() }
		-----${um.getEmail() }
		-----${um.getNickname() }
		<a href="${um.getUsername() }/udpate">udpate</a>
		<a href="${um.getUsername() }/delete">delete</a><br>
	</c:forEach>
</body>
</html>