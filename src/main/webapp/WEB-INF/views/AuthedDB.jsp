<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/Header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<link href="resources/styles/style.css" rel="stylesheet" />
<title>Authorizated</title>
</head>
<body>
	<table align="center">

		<tr>
			<td align="center" style='color: white'>
				<h3>Hello,${authUser.name}! </h3> <br /> 

				<h1>You are ${auth}</h1> <br /> <br />
				<div>Login: ${authUser.login}</div> <br />
				<div>Password: ${authUser.password}</div> <br />
				<div>Name: ${authUser.name}</div> <br />
				<div>Region: ${authUser.region}</div> <br />
				<div> Gender:
					<c:choose>
						<c:when test="${authUser.gender == true}"> Male </c:when>
						<c:otherwise> Female </c:otherwise>
					</c:choose>
				</div> <br />
				<div>Comment: ${authUser.comment}</div> <br />
			</td>
		</tr>
	</table>
</body>
</html>

<%@ include file="/WEB-INF/include/Footer.jsp"%>