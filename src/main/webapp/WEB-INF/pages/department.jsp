<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department List</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <label style="color: red">${message}</label>
    </c:when>
    <c:otherwise>
        <table style="width: 100%" border="1px">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
            </tr>
            <c:forEach items="${result}" var="department">
                <tr>
                    <td>${department.id}</td>
                    <td>${department.name}</td>
                    <td>${department.location}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>

