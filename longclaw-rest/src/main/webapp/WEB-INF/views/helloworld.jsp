<%@ page language="java" contentType="text/html; charset=UTF8" 
         pageEncoding="UTF8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Spring4 MVC -HelloWorld</title>
</head>
<body>
<h1>Hello : ${name};</h1>
<h1><c:out value="${name}"/></h1>
<h1><% out.println(request.getAttribute("name")); %></h1>
</body>
</html>