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
<title>Autorization</title>
</head>
<body>

	<table align="center">
			<tr>
				<td>
					<form id="loginForm" action="./auth" method="post">
						<div class="field">
							<label>Enter your login:</label>
							<div class="input">
								<input type="text" name="Login" value="" id="Login" />
							</div>
						</div>

						<div class="field">
							 <label>Enter your password:</label>
							<div class="input">
								<input type="password" name="Password" value="" id="Password" />
							</div>
						</div>

						<div class="submit">
							<button type="submit">Enter</button>
						</div>
					</form>
				</td>
			</tr>
		</table>
	
	<table>
		<c:if test="${isError == 'true'}">
			<tr>
				<td align='center'>Incorrect login or password</td>
			</tr>
		</c:if>

	</table>

</body>
</html>

<%@ include file="/WEB-INF/include/Footer.jsp"%>