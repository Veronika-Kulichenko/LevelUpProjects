<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="<c:url value="https://code.jquery.com/jquery-3.1.1.min.js"/>" rel="stylesheet" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/register.js"/>" rel="stylesheet" type="text/javascript"></script>
</head>
<body>

<%--<form action="${pageContext.request.contextPath}/register" method="post">--%>

    <div>
        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" id="email" required>
    </div>
    <div>
        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>
    </div>
    <div>
        <label><b>First Name</b></label>
        <input type="text" placeholder="Enter First Name" name="firstName" id="firstName">
    </div>
    <div>
        <label><b>Last Name</b></label>
        <input type="text" placeholder="Enter Last Name" name="lastName" id="lastName">
    </div>

    <input type="button" value="Register" onclick="register('${pageContext.request.contextPath}/register')"/>

<%--</form>--%>
</body>
</html>
