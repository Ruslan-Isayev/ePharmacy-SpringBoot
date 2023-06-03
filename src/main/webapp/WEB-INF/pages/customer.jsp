<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty message}">
        <label style="color: red">${message}</label>
    </c:when>
    <c:otherwise>
        <table style="width: 100%" border="1px">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Dob</th>
                <th>Phone</th>
                <th>Cif</th>
                <th>Hesablara bax</th>
            </tr>
            <c:forEach items="${result}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.name}</td>
                    <td>${r.surname}</td>
                    <td>${r.dob}</td>
                    <td>${r.phone}</td>
                    <td>${r.cif}</td>
<%--                    <td><a href="${pageContext.request.contextPath}/getAccountList/${r.id}"><input type="button" value="Hesablara bax"></a></td>--%>
                    <td><a href="${pageContext.request.contextPath}/getAccountList/${r.id}/${reqToken}"><input type="button" value="Hesablara bax"></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
