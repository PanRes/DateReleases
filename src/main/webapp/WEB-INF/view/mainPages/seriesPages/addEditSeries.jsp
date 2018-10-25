
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/view/universals/header.jsp"%>

<form:form method="post" modelAttribute="series" action="${pageContext.request.contextPath}/series/saveOrUpdateSeries">
	<div class="container">

		<div class="row">
			<c:choose>
				<c:when test="${param.duplicateName != null}">
					<div class="alert alert-warning text-center">
						<h4><spring:message code="series.addEdit.duplicate"/></h4>
					</div>
				</c:when>
				<c:when test="${param.genericError != null}">
					<div class="alert alert-danger text-center">
						<h4><spring:message code="series.addEdit.genericError"/></h4>
					</div>
				</c:when>
			</c:choose>
		</div>


		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.name"/></strong>
			</div>
			<div class="col-lg-09 form-group">
				<form:input path="name" class="form-control" />
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.dateStarted"/></strong>
			</div>
			<div class="col-lg-09 form-group">
				<input type="date" name="dateStarted" class="form-control" value="${series.dateStarted}"/>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.channel"/></strong>
			</div>
			<div class="col-lg-09 form-group">
				<select class="form-control" name="tvChannel">
					<option value="0" > -- Select Channel --</option>
					<c:forEach items="${channels}" var="channel">
						<option value="${channel.id}" ${series.channel.name == channel.name ? 'selected' : ''} >
								${channel.name}
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.videoType"/></strong>
			</div>
			<div class="col-lg-09 form-group">
				<select class="form-control" name="videoType">
					<c:forEach items="${videoTypes}" var="videoType">
						<option value="${videoType.id}" ${series.videoType.videoType == videoType.videoType ? 'selected' : ''} >
								${videoType.videoType}
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row text-center">
			<input type="submit" value="Submit Profile Changes" name="submitBtn" class="btn btn-Success btn-lg"/>
		</div>
	</div>
</form:form>

<%@include file="/WEB-INF/view/universals/footer.jsp"%>
</body>
</html>
