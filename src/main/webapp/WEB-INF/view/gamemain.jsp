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
<link rel="stylesheet" href="css/gamemain.css" />
<title>GAME＿MAIN</title>
</head>
<body>
<div id="wrapper">
	<h1>GAME＿MAIN</h1>
	<div id="menu">
	
	
	<p><c:out value="${Login.userId}" />さん、ログイン中</p>
	<div>
	
	<p><a href="TetorisuServlet">テトリス</a></p>
	</div>
	
	<div>
	<p><a href="RPGServlet">サンプルクエスト</a></p>
	<p>音量注意！</p>
	</div>
	
	
	<p><a href="Logout">ログアウト</a></p>
</body>
</html>