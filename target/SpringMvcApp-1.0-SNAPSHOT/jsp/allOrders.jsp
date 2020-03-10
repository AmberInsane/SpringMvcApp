<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="home.title"/></title>
</head>
<body>
<jsp:include page="./language_bar.jsp"></jsp:include>
<h2><spring:message code="home.welcome"/></h2>
<table border="1">
    <tr>
        <th><spring:message code="order.title"/></th>
        <th><spring:message code="order.price"/></th>
        <th><spring:message code="order.update"/></th>
        <th><spring:message code="order.delete"/></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.name}"/>
            </td>
            <td>
                <c:out value="${order.price}"/>
            </td>
            <td>
                <a href="/update/${order.id}"><spring:message code="order.update"/></a>
            </td>
            <td>
                <a href="/delete/${order.id}"><spring:message code="order.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/add-order"><spring:message code="order.add"/></a>
</body>
</html>
