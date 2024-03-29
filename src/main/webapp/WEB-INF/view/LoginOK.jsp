<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
</head>
<body>
	<p>ようこそ<c:out value="${Login.userId}" />さん</p>
	<a href="GameMainServlet">GAME_MAINへ</a><br>
	<a href="Logout">ログアウト</a>
</body>
</html>