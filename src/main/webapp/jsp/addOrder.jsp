<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="order.add"/></title>
</head>
<body>
<jsp:include page="./language_bar.jsp"></jsp:include>
<h2><spring:message code="order.add"/></h2>
<form action="/add-order" method="post">
    <spring:message code="order.title"/>
    <input type="text" name="title" minlength="1"/>
    <spring:message code="order.price"/>
    <input type="number" pattern="0.00" step=".01" required name="price"/>
    <input type="submit" name="save" value="<spring:message code="order.save"/>"/>
</form>
</body>
</html>
