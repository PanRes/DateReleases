<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 30/10/2017
  Time: 4:50 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Change Password</title>
	</head>
	<body>
		<%@ include file="/WEB-INF/view/universals/header.jsp"%>
		<div class="container text-center">
			<h4>${user.getUserName()} Change Password</h4>
			<c:choose>
				<c:when test="${success == 'success'}">
					<div class="alert alert-success">
						Successfully Changed Password
					</div>
				</c:when>
				<c:when test="${success == 'wrongPassword'}">
					<div class="alert alert-danger">
						<strong>The old Password given was wrong!</strong> Please Try again
					</div>
				</c:when>
				<c:when test="${success == 'differentPasswords'}">
					<div class="alert alert-warning">
						<strong>The new Passwords did not match,</strong> Please Try again
					</div>
				</c:when>
			</c:choose>
			<form class="form-horizontal" method="post" action="/ChangePasswordServlet">
				<div class="row form-group">
					<div class="col-lg-2 col-lg-offset-3 vCenter">
						Old Password:
					</div>
					<div class="col-lg-4">
						<input type="password" name="oldPassword" class="form-control" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-2 col-lg-offset-3 vCenter">
						New Password:
					</div>
					<div class="col-lg-4">
						<input type="password" name="password" id="password"
							   class="form-control" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-2 col-lg-offset-3 vCenter vCenter">
						Verify New Password:
					</div>
					<div class="col-lg-4">
						<input type="password" name="passwordVerify" id="passwordVerify"
							   class="form-control" required>
					</div>
					<div class="col-lg-3">
					   <div class="pull-left">
							<small class="text-red" id="verifyMessage"></small>
					   </div>
					</div>
				</div>
				<div class="row form-group">
					<input type="submit" class="btn btn-success btn-lg disabled"
						   id="btnChangePassword" value="Change Password" disabled>
				</div>
			</form>
		</div>

		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>

<script type="application/javascript">
   /* $("#passwordVerify").change(function(){
	   if ($("#passwordVerify").value() != $("#password").value()){
		   console.log("inside if");
		   alert("Both new password must be the same!");
	   }
	});*/


</script>
