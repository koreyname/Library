<%--
  Created by IntelliJ IDEA.
  User: tyz0809
  Date: 2023/9/11
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${requestScope.cars}" var="car">
<h1>汽车信息：${car.id}</h1>
<p>品牌：${car.brand}</p>
<p>颜色：${car.color}</p>
<p>价格：${car.price}</p>
</c:forEach>
</body>
</html>
