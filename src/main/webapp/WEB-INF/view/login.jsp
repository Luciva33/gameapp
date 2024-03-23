<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<form action ="LoginServlet" method="post">
ユーザーネーム:<input type ="text" name ="name"><br>
パスワード:<input type ="password" name = "pass"><br>
<input type = "submit" value="ログイン">
</form>
<% if(errorMsg !=null){ %>
<p><%=errorMsg%></p>
<%} %>
</body>
</html>