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
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${showCorrForm == 'true'}">

			<form id="corrForm" action="./correct" method="post">
				<table border="0" align="center">
					<tr>
						<td>
							<table>
								<div class="field">
									<label>Login</label>
									<div class="input" align='center'>
										<input type="hidden" name="Login" value="${user.login}" />
										${user.login}
									</div>
								</div>


								<div class="field">
									<label>Password</label>
									<div class="input">
										<input type="password" name="Password"
											placeholder="min 8 symbols" required="required" value=""
											id="Password" />
									</div>
								</div>


								<div class="field">
									<label>Re-type Password</label>
									<div class="input">
										<input type="password" name="Re_Password"
											placeholder="min 8 symbols" required="required" value=""
											id="Re_Password" />
									</div>
								</div>

								<div class="field">
									<label>Name</label>
									<div class="input">
										<input type="text" name="Name" value="${user.name}" id="Name" />
									</div>
								</div>


								<div class="field">
									<label>Region</label>
									<div class="input">
										<select name="Region" />
										<option value="DNR"
											<c:choose>
								 				<c:when test = "${user.region eq 'DNR'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
											</c:choose>>DNR</option>
										<option value="LNR"
											<c:choose>
												 <c:when test = "${user.region eq 'LNR'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
												</c:choose>>LNR</option>
										<option value="Crimea"
											<c:choose>
								 				<c:when test = "${user.region eq 'Crimea'}">
								 				selected
												</c:when>
												<c:otherwise>
												""
												</c:otherwise>
												</c:choose>>Crimea</option>
										</select>
									</div>
								</div>


								<div class="gender">
									<label>Gender</label>
									<div>
										<label id="gender"> Male <input type="radio"
											width="10px" name="Gender" value="true"
											<c:choose>
								 					<c:when test = "${user.gender == 'true'}">
								 					checked
													</c:when>
													<c:otherwise>
													""
													</c:otherwise>
												</c:choose> />
										</label> <label id="gender"> Female <input type="radio"
											name="Gender" value="false"
											<c:choose>
								 					<c:when test = "${user.gender == 'false'}">
									 				checked
													</c:when>
													<c:otherwise>
													""
													</c:otherwise>
												</c:choose> />
										</label>
									</div>
								</div>


								<div class="field">
									<label>Comment</label>
									<div class="input">
										<textarea name="Comment" cols="20" raws="10" value="">${user.comment}</textarea>
									</div>
								</div>

								<div class="submit">
									<button type="submit" value="Register" />
									Correct
									</button>
									<label id="agree"> I agree <input name="Agree" input
										type="checkbox" /></label>
								</div>
							</table>
						</td>

						<td align=center>${error}</td>
					</tr>
				</table>
			</form>
		</c:when>

		<c:otherwise>
			<h1 align='center'>You corrected your data</h1>
			<br />
			<br />
			<table align='center' style='color: white'>
				<tr>
					<td>
						<div>Login: ${user.login}</div>
						<br />
						<div>Password: ${user.password}</div>
						<br />
						<div>Name: ${user.name}</div>
						<br />
						<div>Region: ${user.region}</div>
						<br />
						<div>
							Gender:
							<c:choose>
								<c:when test="${user.gender == true}"> Male </c:when>
								<c:otherwise> Female </c:otherwise>
							</c:choose>
						</div>
						<br />
						<div>Comment: ${user.comment}</div>
						<br />
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

</body>
</html>

<%@ include file="/WEB-INF/include/Footer.jsp"%>