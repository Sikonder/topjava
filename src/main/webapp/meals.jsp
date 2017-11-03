<%--
  Created by IntelliJ IDEA.
  User: sikonder
  Date: 03.11.17
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>

    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Exceed</th>
        </tr>
        <c:forEach items="${list}" var="meal">
            <tr>
                <td>${meal.getDateTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>${meal.isExceed()}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
