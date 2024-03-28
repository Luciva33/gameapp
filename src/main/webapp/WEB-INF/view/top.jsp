<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String LogoutMsg = (String)request.getAttribute("LogoutMsg"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GAME_TOP画面</title>
</head>
<body>
	<h1>トップ画面</h1>
	<%
	if (LogoutMsg != null) {
	%>
	<p><%=LogoutMsg%></p>
	<%
	}
	%>
 <li><a href="LoginServlet">ログイン</a></li>
 <li><a href="AccountRegServlet">ユーザー登録<li/>

</body>
</html>