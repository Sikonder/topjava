<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sikonder
  Date: 04.11.17
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<center>
    <h1>Add new Meal</h1>
    <hr>
    <h2><a href="meals">Show all meals</a></h2>
</center>
<div align="center">
    <c:if test="${meal != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="addMeal" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <c:if test="${book != null}">
                    <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                </c:if>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input type="datetime-local" name="date" size="45"
                               value="<c:out value='${meal.dateTime}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Description: </th>
                    <td>
                        <input type="text" name="description" size="45"
                               value="<c:out value='${meal.description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Calories: </th>
                    <td>
                        <input type="number" name="calories" size="5"
                               value="<c:out value='${meal.calories}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
