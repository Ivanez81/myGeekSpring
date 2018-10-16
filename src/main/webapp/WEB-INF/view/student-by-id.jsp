<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student by ID</title>
</head>
<body>
    <h1>Student by ID</h1>
    <br>
    Student ID - ${student.id}, Student Name - ${student.name}
    <br>
    Student Courses:
    <table>
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Course Duration</th>
        </tr>
        <c:forEach var="courses" items="${student.courses}">
            <tr>
                <td>${courses.id}</td>
                <td>${courses.name}</td>
                <td>${courses.duration}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
