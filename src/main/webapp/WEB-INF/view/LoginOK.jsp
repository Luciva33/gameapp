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
	<p>ようこそ<c:out value="${name}" />さん</p>
	<a href="">GAME_MAINへ</a>
	<a href="WelcomeServlet">戻る</a>
</body>
</html>