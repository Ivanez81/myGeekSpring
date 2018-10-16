<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course by ID</title>
</head>
<body>
    <h1>Course by ID</h1>
    <br>
    Course ID - ${course.id}, Course Name - ${course.name}, Number of Students - ${course.students.size()}
    <br>
    Course Students:
    <table>
        <tr>
            <th>Student ID</th>
            <th>Student Name</th>
        </tr>
        <c:forEach var="item" items="${course.students}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
