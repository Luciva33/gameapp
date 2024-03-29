<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account"%>
<%
String Msg = (String) request.getAttribute("Msg");
String errorMsg = (String) request.getAttribute("errorMsg");
Account account = (Account) session.getAttribute("account");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<h1>ユーザー登録</h1>
	<form action="AccountRegServlet" method="post">
	
		ユーザー名:<input type="text" name="userId"><br> 		
		パスワード:<input type="password" name="pass"><br> 
		
		<input type="submit" value="登録"><br>
		
	</form>
	<%if (Msg != null) {%>
	<p><%=Msg%></p>
	<a href="AccountRegLogin">そのままログインする</a><br>
	<%}%>
	<%if (errorMsg != null) {%>
	<p><%=errorMsg%></p>
	<%}%>
	<%if (account != null) {%>
	<a href="Logout">ログアウト</a>
	<%} else {%>
	<a href="WelcomeServlet">戻る</a>
	<%}%>
</body>
</html>