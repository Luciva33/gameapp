<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TETORISU</title>
<link rel="stylesheet" href="tetorisu_css/tetorisumain.css">
</head>

<body onload="init()">
	<div id="container">
		<canvas id="cvs"></canvas>
	</div>
	
	
	
	<p>
		<c:if test = "${Login != null}">
		<c:out value = "${Login.userId}"/>さんログイン中
		</c:if>
	</p>
	

	<p>	<a href="GameMainServlet">戻る</a></p>

	<p>
		<a href="Logout">ログアウト</a>
	</p>


	<script src="tetorisu_js/tetorisumain.js"></script>
</body>


</html>