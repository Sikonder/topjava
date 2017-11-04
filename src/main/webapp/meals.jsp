<%--
  Created by IntelliJ IDEA.
  User: sikonder
  Date: 03.11.17
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<center>
<h3><a href="index.html">Home</a></h3>
<h1>Meals</h1>
<hr>
<h3><a href="addMeal.jsp">Add meal</a></h3>
<hr>
</center>


<table align="center" frame="border" bgcolor="aqua" border="5">
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceed</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${list}" var="meal">
        <c:if test="${meal.isExceed()}">
            <tr bgcolor="red">
        </c:if>
        <c:if test="${!meal.isExceed()}">
            <tr bgcolor="green">
        </c:if>
        <td>${meal.getId()}</td>
        <td>${meal.getDateTime()}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        <td>${meal.isExceed()}</td>
        <td bgcolor="white"><a href="editMeal">Edit</a> <a href="deleteMeal.jsp">Delete</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
