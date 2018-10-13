<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all students</title>
</head>
<body>
    <h1>Students:</h1>
    <br>
    <table>
        <tr>
            <th>Student ID</th>
            <th>Student Name</th>
            <th>Number of Courses</th>
        </tr>
        <c:forEach var="students" items="${students}">
        <tr>
            <td>${students.id}</td>
            <td>${students.name}</td>
            <td>${students.courses.size()}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
