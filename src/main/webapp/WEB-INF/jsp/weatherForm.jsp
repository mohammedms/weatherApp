<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Weather App</title>
</head>

<body>

	<h1>Weather App</h1>

	<form:form method="post" modelAttribute="weatherFrom" action="/weather">
		<form:input path="cityName" type="text" />
		
		<input type="submit" value="Submit" />
	</form:form>
	
	<br />
	<c:if test="${not empty formResult}">
		<c:out value="city name: ${formResult.cityName}"/>
		<br />
	    <c:out value="weather summary: ${formResult.weatherSummary}" />
	    <br />
	    <c:out value="temperture: ${formResult.tempertureInCelsius}C  ${formResult.tempertureInFahrenheit}F " />
	    <br />
    </c:if>
    
</body>
</html>