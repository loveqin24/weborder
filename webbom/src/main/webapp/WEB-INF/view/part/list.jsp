<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>list</title>
    <%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css"> --%>
    <!-- <link rel="stylesheet" href="../../resources/css/main.css" type="text/css"></link> -->
</head>
<body>
<a href="add">添加</a><br>
<c:forEach items="${partList}" var="um">
    <a href="${um.getPartNum() }">${um.getPartNum() }</a>
    -----${um.getPartDescription() }
    <a href="${um.getPartNum() }/udpate">udpate</a>
    <a href="${um.getPartNum() }/delete">delete</a>
    <a href="<%=request.getContextPath()%>/resources/css/main.css">main</a>
    <a href="<%=request.getContextPath()%>/resources/image/20151009172133.jpg">
        <img src="<%=request.getContextPath()%>/resources/image/20151009172133.jpg" height="20" width="80"/>
    </a>
    <br>
</c:forEach>
<%-- <a href="?0">首页</a><a href="?${page }">上一页</a><a href="?${page+1 }">下一页</a><a href="?${pageTimes }">末页</a> --%>
<div class="pagging">
    <div class="left">共${totalsize}条记录</div>
    <div class="right">
        <a href="parts?page=1">首页</a>
        <c:if test="${currentPage==1}">
            <span class="disabled"><< 前一页</span>
        </c:if>
        <c:if test="${currentPage != 1}">
            <a href="parts?page=${currentPage-1}"><< 前一页</a>
        </c:if>
        <%-- <c:if test="${currentPage == 1}">
        <span class="current">1</span>
        </c:if>
        <c:if test="${currentPage != 1}">
        <a href="parts?page=1">1</a>
        </c:if> --%>
        <%--${currentPage}--%>
        <c:forEach var="i"  begin="${currentPage}"
                   end="${pageTimes>10?(pageTimes<=(10+currentPage-1)?pageTimes:10+currentPage-1):pageTimes}">
            <%-- Item <c:out value="${i}"/><p> --%>
            <c:if test="${currentPage==page}"><span class="current">${i}</span>
            </c:if>
            <c:if test="${currentPage!=page}">
                <a href="parts?page=${i}">${i}</a>
            </c:if>
        </c:forEach>

        <c:if test="${currentPage==pageTimes}">
            <span class="disabled">后一页 >></span>
        </c:if>
        <c:if test="${currentPage!=pageTimes}">
            <a href="part/parts?page=${currentPage+1}">后一页 >></a>
        </c:if>
        <a href="parts?page=${pageTimes }">末页</a>
    </div>
</div>
</body>
</html>