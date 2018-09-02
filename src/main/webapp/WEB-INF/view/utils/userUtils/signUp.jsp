<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 3/11/2017
  Time: 1:05 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Sign Up</title>
	</head>
	<body>
	<header>
		<c:if test="${user != null}">
			<c:redirect url="/"/>
		</c:if>
		<%@include file="/jsps/universals/inclusions.jsp"%>

		<h1 align="center">
			Date Releases
		</h1>
	</header>
		<article id="content">
			<h2 class="page-header" align="center">Sign In</h2>
			<c:choose>
				<c:when test="${param.signUpError == 'duplicateUserName'}">
					<div class="alert alert-danger text-center">
						<strong>Warning!</strong> The User Name is already in use.
					</div>
				</c:when>
				<c:when test="${param.signUpError == 'duplicateEmail'}">
					<div class="alert alert-danger text-center">
						<strong>Warning!</strong> This email is already in use.
					</div>
				</c:when>
				<c:when test="${param.signUpError == 'passwordNotMatch'}">
					<div class="alert alert-danger text-center">
						<strong>Warning!</strong> Passwords does not much.
					</div>
				</c:when>
			</c:choose>
			<form class="form-horizontal" action="/SignUpServlet" name="loginForm" method="post">
				<div class="text-center">
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-user-circle-o fa-fw"></i></span>
							<input type="text" name="userName" class="form-control" required
								   placeholder="UserName"/>
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
							<input type="email" name="email" class="form-control" required
								   placeholder="Email"/>
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input type="password" name="password" id="passwordSignUp" class="form-control" required
								   placeholder="Password">
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input type="password" name="passwordVerify" id="passwordVerifySignUp" class="form-control" required
								   placeholder="Verify Password" >
						</div>
						<div class="col-lg-3">
							<div class="pull-left">
								<small class="text-red" id="verifyMessageSignUp"></small>
							</div>
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon">FN</span>
							<input type="text" name="firstName" class="form-control"
								   placeholder="First Name" >
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon">MN</span>
							<input type="text" name="middleName" class="form-control"
								   placeholder="Middle Name" >
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon">LN</span>
							<input type="text" name="lastName" class="form-control"
								   placeholder="Last Name" >
						</div>
					</div>
					<div class="row form-group">
						<div class="text-center col-lg-4 col-lg-offset-4">
							<input type="submit" name="btnSubmit" id="btnSubmitSignUp" value="Sign Up"
								   class="btn btn-primary btn-lg">
						</div>
					</div>
					<div class="row form-group">
						<div class="text-center col-lg-4 col-lg-offset-4">
							<a href="/login">
								<fmt:message key="signUp.href.login"/>
							</a>
						</div>
					</div>
				</div>
			</form>
			<%@include file="/jsps/universals/footer.jsp"%>
	</body>
</html>
