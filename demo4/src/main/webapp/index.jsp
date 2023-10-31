<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<form action="/demo4_war_exploded/JDBC_Server" method="get">
<%--    用户名:<input type="text" name="name"/><br>--%>
<%--    密码：<input type="password" name="pwd"/><br>--%>
    <input type="submit" value="提交"/>
</form>
</body>
</html>