
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/view/universals/header.jsp"%>

<form:form method="post" modelAttribute="series"  id="addEditForm"
		   action="${pageContext.request.contextPath}/series/saveOrUpdateSeries">
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
				<form:input path="dateStarted" type="date" name="dateStarted" id="dateStarted" class="form-control"/>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.channel"/></strong>
			</div>
			<div class="col-lg-06 form-group">
				<%--BEST : put onChnage function on js file and make it universal--%>
				<select class="form-control" name="tvChannel" id="channelDropdown"
						onchange="if (this.value=='-1'){this.form['newChannel'].style.display='inline'}else {this.form['newChannel'].style.display='none'};">
					<option value="0" ><spring:message code="series.addEdit.channelDropdown"/> </option>
					<c:forEach items="${channels}" var="channel">
						<option value="${channel.id}" ${series.channel.name == channel.name ? 'selected' : ''} >
								${channel.name}
						</option>
					</c:forEach>
					<option value="-1"><spring:message code="series.addEdit.newChannel"/> </option>
				</select>
			</div>
			<div class="col-lg-3 form-group">
				<input type="text" class="form-control" name="newChannel" id="newChannel" style="display: none">
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-3">
				<strong><spring:message code="series.addEdit.videoType"/></strong>
			</div>
			<div class="col-lg-06 form-group">
				<select class="form-control" name="videoType" >
					<c:forEach items="${videoTypes}" var="videoType">
						<option value="${videoType.id}" ${series.videoType.id == videoType.id ? 'selected' : ''} >
								${locale == 'el' && videoType.typeEl != null ? videoType.typeEl : videoType.typeEn}
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row text-center">
			<button class="btn btn-Success btn-lg" onclick="checkNewChannel('addEditForm', 'dateStarted')">
				<spring:message code="series.addEdit.button"/>
			</button>
		</div>
	</div>
</form:form>

<%@include file="/WEB-INF/view/universals/footer.jsp"%>
</body>
</html>
