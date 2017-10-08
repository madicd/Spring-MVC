<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
</head>
<body>
<h1>Register</h1>

<sf:form method="POST" commandName="spitter">
    <sf:errors path="*" element="div" cssClass="errors"/>
    <sf:label path="firstName" cssErrorClass="error">First Name:</sf:label> <sf:input path="firstName"/> <br/>
    <sf:label path="lastName" cssErrorClass="error">First Name:</sf:label> <sf:input path="lastName"/> <br/>
    <sf:label path="username" cssErrorClass="error">First Name:</sf:label> <sf:input path="username"/> <br/>
    <sf:label path="password" cssErrorClass="error">First Name:</sf:label> <sf:input path="password"/> <br/>
    <input type="submit" value="Register">
</sf:form>
</body>
</html>