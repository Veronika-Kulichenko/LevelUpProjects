<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="<c:url value="https://code.jquery.com/jquery-3.1.1.min.js"/>" rel="stylesheet" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/welcome.js"/>" rel="stylesheet" type="text/javascript"></script>
    <script>function doMagic() {
        console.log('page loaded successfully')
    }</script>
</head>
<body>
<h1>Spring MVC Hello World Example</h1>

<h2>${msg}</h2>
<input type="button" onclick="renderUser('${pageContext.request.contextPath}/register/getUserByEmail')"value="Get User"/>
<span><input type="text" id="email" placeholder="Enter your email"/></span>
<div id="userData"></div>
</body>
</html>
