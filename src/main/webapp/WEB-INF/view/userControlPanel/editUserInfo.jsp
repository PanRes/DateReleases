
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Edit Info</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<form:form method="post" action="${pageContext.request.contextPath}/userPanel/updateUser" modelAttribute="user">
			<div class="container">
				<div class="row">
					<c:choose>
						<c:when test="${param.duplicateUserName != null}">
							<div class="alert alert-danger text-center">
								<spring:message code="user.edit.duplcateUserName"/>
							</div>
						</c:when>
						<c:when test="${param.duplicateEmail != null}">
							<div class="alert alert-danger text-center">
								<spring:message code="user.edit.duplicateEmail"/>
							</div>
						</c:when>
						<c:when test="${param.userUpdateFail != null}">
							<div class="alert alert-danger text-center">
								<spring:message code="user.edit.error"/>
							</div>
						</c:when>
						<%--TODO : edit image upload--%>
						<c:when test="${param.success == 'imgRemoved'}">
							<div class="alert alert-success text-center">
								<strong>Image Successfully Removed!</strong>
							</div>
						</c:when>
					</c:choose>
				</div>
				<div class="col-lg-3 row">
					<div class="row text-center">
						<c:choose>
							<c:when test="${user.userImgUrl == null}">
								<img id="profileImg" src="${pageContext.request.contextPath}${userImgsDir}/defaultUserImage.png" class="thumbnail center-block">
							</c:when>
							<c:otherwise>
								<img id="profileImg" src="${pageContext.request.contextPath}${user.userImgUrl}" class="thumbnail center-block">
							</c:otherwise>
						</c:choose>
					</div>
					<%--FIXME : fix image upload--%>
					<div class="row text-center">
						<p>
							<label class="btn btn-default">
								<span class="fa fa-file-image-o"></span>
								<spring:message code="user.edit.findImage"/>
								<input type="file" id="uploadProfileImage" name="uploadProfileImage"
										class="labelBtn" accept="image/*">
							</label>
						</p>
					</div>
					<%--FIXME : fix image remove--%>
					<div class="row text-center">
						<p>
							<label class="btn btn-danger">
								<span class="fa fa-times"></span>
								<spring:message code="user.edit.removeImage"/>
								<input type="submit" name="submitBtn" value="Remove Picture" class="labelBtn"/>
							</label>
						</p>

					</div>
				</div>
				<div class="col-lg-9">
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong><spring:message code="user.info.userName"/>:</strong>
							</div>
							<div class="col-lg-8 form-group ">
								<form:input type="text" class="form-control" name="userName" required="true" path="userName"/>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong><spring:message code="user.info.email"/>:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<form:input type="email" class="form-control" name="email" required="true" path="email"/>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong><spring:message code="user.info.firstName"/>:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<form:input type="text" name="firstName" class="form-control" path="firstName"/>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong><spring:message code="user.info.middleName"/>:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<form:input type="text" name="middleName" class="form-control" path="middleName"/>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong><spring:message code="user.info.lastName"/>:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<form:input type="text" name="lastName" class="form-control" path="lastName"/>
							</div>
						</p>
					</div>
					<div class="row text-center">
						<input type="submit" value="Submit Profile Changes" name="submitBtn" class="btn btn-Success btn-lg"/>
					</div>
				</div>
			</div>
		</form:form>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
