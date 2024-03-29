<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<link rel="stylesheet" href="css/dounutsTest.css" />
<title>GAME＿MAIN</title>
</head>
<body>
	<h1>GAME＿MAIN</h1>
	
	<p><c:out value="${Login.userId}" />さん、ログイン中</p>
	
	
	<p><a href="TetorisuServlet">テトリス</a></p>
	
	
	
	
	<p><a href="Logout">ログアウト</a></p>
</body>
</html>