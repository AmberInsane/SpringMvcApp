<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="order.update"/></title>
</head>
<body>
<jsp:include page="./language_bar.jsp"></jsp:include>
<form action="/update-order/${order.id}" method="post">
    <spring:message code="order.title"/>
    <input type="text" name="title" value="${order.name}" minlength="1"/>
    <spring:message code="order.price"/>
    <input type="number" name="price" pattern="0.00" step=".01" required value="${order.price}"/>
    <input type="submit" name="update" value="<spring:message code="order.save"/>"/>
</form>
</body>
</html>
