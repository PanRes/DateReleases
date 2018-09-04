<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 1/11/2017
  Time: 4:37 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Edit Info</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<jsp:useBean id="user" class="gr.pr.date_releases.models.UsersModel"/>
		<c:set var="user" value='<%=session.getAttribute("user")%>'/>
		<form method="post" enctype="multipart/form-data" action="/EditUserInfoServlet">
			<div class="container">
				<div class="row">
					<c:choose>
						<c:when test="${param.success == 'success'}">
							<div class="alert alert-success text-center">
								<strong>Successfully</strong> changed user profile
							</div>
						</c:when>
						<c:when test="${param.success == 'duplicateUserName'}">
							<div class="alert alert-danger text-center">
								<strong>Duplicate UserName!</strong> The userName is already in use, Please try again.
							</div>
						</c:when>
						<c:when test="${param.success == 'duplicateEmail'}">
							<div class="alert alert-danger text-center">
								<strong>Duplicate Email!</strong> This email is in use by another user, Please try again.
							</div>
						</c:when>
						<c:when test="${param.success == 'fail'}">
							<div class="alert alert-danger text-center">
								<strong>Generic Error!</strong> Please try again.
							</div>
						</c:when>
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
							<c:when test="${user.imageUrl == null}">
								<img id="profileImg" src="${initParam['userProfileImgs']}/defaultUserImage.png" class="thumbnail center-block">
							</c:when>
							<c:otherwise>
								<img id="profileImg" src="${user.imageUrl}" class="thumbnail center-block">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="row text-center">
					   <p>
							<label class="btn btn-default">
								<span class="fa fa-file-image-o"></span> Browse Profile Image
								<input type="file" id="uploadProfileImage" name="uploadProfileImage"
									   class="labelBtn" accept="image/*">
							</label>
					   </p>
					</div>
					<div class="row text-center">
						<p>
							<label class="btn btn-danger">
								<span class="fa fa-times"></span> Remove Picture
								<input type="submit" name="submitBtn" value="Remove Picture" class="labelBtn"/>
							</label>
						</p>

					</div>
				</div>
				<div class="col-lg-9">
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong>User Name:</strong>
							</div>
							<div class="col-lg-8 form-group ">
								<input type="text" class="form-control" name="userName" value="${user.userName}" required>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong>Email:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<input type="email" class="form-control" name="email" value="${user.email}" required>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong>First Name:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<input type="text" name="firstName" class="form-control" value="${user.firstName}">
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong>Middle Name:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<input type="text" name="middleName" class="form-control" value="${user.middleName}"/>
							</div>
						</p>
					</div>
					<div class="row col-lg-12">
						<p>
							<div class="col-lg-4">
								<strong>Last Name:</strong>
							</div>
							<div class="col-lg-8 form-group">
								<input type="text" name="lastName" class="form-control" value="${user.lastName}">
							</div>
						</p>
					</div>
					<div class="row text-center">
						<input type="submit" value="Submit Profile Changes" name="submitBtn" class="btn btn-Success btn-lg"/>
					</div>
				</div>
			</div>
		</form>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
