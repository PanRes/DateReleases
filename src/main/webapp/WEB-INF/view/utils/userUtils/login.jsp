<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 5/10/2017
  Time: 9:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Welcome</title>
	</head>
	<body>
		<header>
			<c:if test="${user != null}">
				<c:redirect url="/"/>
			</c:if>
			<%@include file="/WEB-INF/view/universals/inclusions.jsp"%>

			<h1 align="center">
				Date Releases
			</h1>
		</header>
		<article id="content">
			<h2 class="page-header" align="center">Please Login</h2>
			<c:if test="${param.error != null}">
				<div class="alert alert-danger text-center">
					<strong>Warning!</strong> The User Name or the Password is incorrect. Please try again.
				</div>
			</c:if>
			<form:form class="form-horizontal" action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
				<div class="text-center">
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-user-circle-o fa-fw"></i></span>
							<input type="text" name="username" class="form-control" required
									placeholder="UserName or Email"/>
						</div>
					</div>
					<div class="row form-group">
						<div class="input-group text-center col-lg-4 col-lg-offset-4">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input type="password" name="password" class="form-control" required
									placeholder="Password">
						</div>
					</div>
					<div class="row form-group">
						<div class="text-center col-lg-4 col-lg-offset-4">
							<input type="submit" name="btnSubmit" class="btn btn-primary btn-lg" value="Login">
						</div>
					</div>
					<div class="row form-group">
						<div class="text-center col-lg-4 col-lg-offset-4">
							<a href="${pageContext.request.contextPath}/signUp">
								<spring:message code="login.href.signUp"/>
							</a>
						</div>
					</div>
				</div>
			</form:form>
			<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
