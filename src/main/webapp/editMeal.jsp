<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sikonder
  Date: 04.11.17
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
    <h1>Edit</h1>
    <form action="editMeal" method="post">

        <table border="1" cellpadding="5">
            <c:if test="${book != null}">
                <input type="hidden" name="id" value="<c:out value='${book.id}' />"/>
            </c:if>
            <tr>
                <th>Id:</th>
                <td>
                    <input type="number" name="id" size="45" readonly
                           value=${id}
                    />
                </td>
            </tr>
            <tr>
                <th>Date:</th>
                <td>
                    <input type="datetime-local" name="date" size="45"
                           value=${date}
                    />
                </td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>
                    <input type="text" name="description" size="45"
                           value=${description}
                    />
                </td>
            </tr>
            <tr>
                <th>Calories:</th>
                <td>
                    <input type="number" name="calories" size="5"
                           value=${calories}
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Edit"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
