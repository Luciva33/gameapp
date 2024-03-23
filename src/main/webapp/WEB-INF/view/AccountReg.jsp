<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h1>ユーザー登録</h1>
<form action ="AccountRegServlet" method="post">
ユーザー名:<input type ="text" name ="name"><br>
パスワード:<input type ="password" name = "pass"><br>
<input type = "submit" value="登録"><br>
</form>
<% if(errorMsg !=null){ %>
<p><%=errorMsg%></p>
<%} %>
<a href="WelcomeServlet">戻る</a>

</body>
</html>