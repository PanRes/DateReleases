<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 30/10/2017
  Time: 1:40 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Personal Info</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<div class="container">
			<div class="col-lg-3 text-center">
				<c:choose>
					<c:when test="${user.userImgUrl == null}">
						<img src="${userImgsDir}/defaultUserImage.png" class="thumbnail center-block">
					</c:when>
					<c:otherwise>
						<img src="${user.userImgUrl}" class="thumbnail center-block">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-lg-9">
				<div class="row col-lg-12">
					<p>
						<div class="col-lg-4">
							<strong><spring:message code="user.info.userName"/>:</strong>
						</div>
						<div class="col-lg-8">
							${user.userName}
						</div>
					</p>
				</div>
				<div class="row col-lg-12">
					<p>
						<div class="col-lg-4">
							<strong><spring:message code="user.info.email"/>:</strong>
						</div>
						<div class="col-lg-8">
							${user.email}
						</div>
					</p>
				</div>
				<div class="row col-lg-12">
					<p>
						<div class="col-lg-4">
							<strong><spring:message code="user.info.firstName"/>:</strong>
						</div>
						<div class="col-lg-8">
							${user.firstName}
						</div>
					</p>
				</div>
				<div class="row col-lg-12">
				   <p>
						<div class="col-lg-4">
							<strong><spring:message code="user.info.middleName"/>:</strong>
						</div>
						<div class="col-lg-8">
							${user.middleName}
						</div>
				   </p>
				</div>
				<div class="row col-lg-12">
					<p>
						<div class="col-lg-4">
							<strong><spring:message code="user.info.lastName"/>:</strong>
						</div>
						<div class="col-lg-8">
							${user.lastName}
						</div>
					</p>
				</div>
				<div class="row col-lg-offset-4">
					<a href="${pageContext.request.contextPath}/userPanel/editInfo" class="btn btn-primary">
						<i class="fa fa-pencil-square-o"></i> <spring:message code="header.user.edit"/>
					</a>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
