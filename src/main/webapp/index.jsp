<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Maxis Video Store</title>
	</head> 
	<body>
	<c:choose>
	    <c:when test="${not empty header['msisdn']}">
	        <c:set var="msisdn" value="${header['msisdn']}"/>
	        Header Parameter Name : msisdn </br>
	        msidn value : <c:out value="${msisdn}"/></br></br>
	    </c:when>
	    <c:when test="${ not empty header['x-up-calling-line-id']}">
	        <c:set var="msisdn" value="${header['x-up-calling-line-id']}"/>
	        Header Parameter Name      : x-up-calling-line-id </br>
	        x-up-calling-line-id value : <c:out value="${header['x-up-calling-line-id']}"/></br></br>
	    </c:when>
   </c:choose>
	<c:url var="subscriptionUrl" value="/mvs/subscribe">
	   <c:param name="msisdn" value="${msisdn}"/>
	</c:url>
	<a href="${subscriptionUrl}">Click Here to Subscribe</a>
	</body>
</html>