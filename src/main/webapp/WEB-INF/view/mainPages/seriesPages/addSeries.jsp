<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 18/10/2017
  Time: 5:08 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Add New Series</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<c:choose>
			<c:when test="${successCreation == 'success'}">
				<div class="alert alert-success text-center">
					<h4>Series successfully Created</h4>
				</div>
			</c:when>
			<c:when test="${successCreation == 'exists'}">
				<div class="alert alert-warning text-center">
					<h4>Series Already Exists</h4>
				</div>
			</c:when>
			<c:when test="${successCreation == 'fail'}">
				<div class="alert alert-danger text-center">
					<h4>Series failed to be Created</h4>
				</div>
			</c:when>
		</c:choose>

		<div class="container">
			<form:form action="${pageContext.request.contextPath}/series/saverOrUpdateSeries"
					method="post" modelAttribute="series" enctype="multipart/form-data">

				<form:hidden path="id"/>

				<div class="row">
					<div class="col-lg-3">
						<h5>Series Name:</h5>
					</div>
					<div class="col-lg-9 form-group">
						<form:input path="name" cssClass="form-control" />
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<h5>Series Premiere:</h5>
					</div>
					<div class="col-lg-9 form-group">
						<form:input type="date" path="dateStarted" cssClass="form-control"/>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<h5>Series Channel:</h5>
					</div>
					<div class="col-lg-9 form-group">
						<form:input path="channel" cssClass="form-control" />
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<h5>Ended?:</h5>
					</div>
					<div class="col-lg-9 form-group">
						<form:checkbox path="ended" />
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<h5>Series Image:</h5>
					</div>
					<div class="col-lg-9">
						<label class="btn btn-warning">
							<span class="glyphicon glyphicon-upload"></span>
							Choose Image for <small id="seriesNamebtn">new Series</small>
							<input type="file" class="labelBtn" name="imgUrl" id="imgUrl" accept="image/*">
						</label>
						<small id="fileName"></small>
					</div>
				</div>
				<div class="row text-center">
					<input type="submit" class="btn btn-success btn-lg" value="Save Changes">
				</div>
			</form:form>
		</div>

		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>

<script type="application/javascript">
	$("#seriesName").change(function () {
		document.getElementById("seriesNamebtn").innerHTML = this.value();
	});

	$("#imgUrl").change(function () {
		var fileName = this.value;
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
		document.getElementById("fileName").innerHTML = fileName;
	});
</script>
