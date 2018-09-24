<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="${sessionScope.language}">
	<head>
		<title><spring:message code="home.head.title"/></title>
	</head>
	<body>

		<%@include file="universals/header.jsp"%>
		<c:set var="user" value='<%=session.getAttribute("user")%>'/>
		<div class="container text-center">
			<div class="row">
				<p><h2><spring:message code="home.body.title"/></h2></p>
			</div>
			<div class="row">
				<p><spring:message code="home.body.banner"/></p>
			</div>
			<security:authorize access="!isAuthenticated()">
				<div class="row">
					<p>
						<a href="login" class="btn btn-success">
							<i class="fa fa-sign-in"></i>
							<spring:message code="login"/>
						</a>
						<a href="signUp" class="btn btn-primary">
							<i class="fa fa-user-plus"></i>
							<spring:message code="signUp"/>
						</a>
					</p>
				</div>
			</security:authorize>
		</div>
		<%@include file="universals/footer.jsp"%>
	</body>
</html>
