<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PR
  Date: 09-Oct-17
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>${pageContext.request.servletPath == 'edit' ? "Edit" : "Add"} Date for Episode</title>


		<script type="application/javascript">


			/*$(document).ready(function () {
				$("#addSingleEpisodeBtn").addClass("disabled");
				$("#addSingleEpisodeBtn").prop("disabled", true);
				if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
					console.log("in if");
					$("#addSingleEpisodeBtn").removeClass("disabled");
					$("#addSingleEpisodeBtn").prop("disabled",false);
				}
			});*/
			$("#season").change(function() {
				if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
					$("#addSingleEpisodeBtn").removeClass("disabled");
					$("#addSingleEpisodeBtn").prop("disabled",false);
				}

			});

			$("#episode").change(function() {
				if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
					$("#addSingleEpisodeBtn").removeClass("disabled");
					$("#addSingleEpisodeBtn").prop("disabled",false);
				}
			});

			$("#date").change(function() {
				if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
					$("#addSingleEpisodeBtn").removeClass("disabled");
					$("#addSingleEpisodeBtn").prop("disabled",false);
				}
			});
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>

		<c:choose>
			<c:when test="${pageContext.request.servletPath == '/editSeriesDate'}">
				<h3 class="text-center"><u>Edit episode release date</u></h3>
				<c:choose>
					<c:when test="${param.success == true}">
						<div class="alert alert-success text-center">
							Successfully Edited Date!
						</div>
					</c:when>
					<c:when test="${param.success == false}">
						<div class="alert alert-danger text-center">
							Failed to Edit Date!
						</div>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<h3 class="text-center"><u>Add single episode release date or add upload xlsx file to add multiple</u></h3>
				<c:choose>
					<c:when test="${param.success == true}">
						<div class="alert alert-success text-center">
							Successfully Added Date!
						</div>
					</c:when>
					<c:when test="${param.success == false}">
						<div class="alert alert-danger text-center">
							Failed to Add Date!
						</div>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="col-lg-12 container text-center">
			<div class="row">
				<div class="col-lg-3">
					<h4>Series</h4>
				</div>
				<div class="col-lg-1">
					<h4>Season</h4>
				</div>
				<div class="col-lg-1">
					<h4>Episode</h4>
				</div>
				<div class="col-lg-2">
					<h4>Date</h4>
				</div>
				<div class="col-lg-4">
					<h4>Notes</h4>
				</div>
				<div class="col-lg-1">
					<h4>Submit</h4>
				</div>
			</div>
			<div class="row">
				<form:form action="${pageContext.request.contextPath}/series/saveOrUpdateSeriesEpisode" method="post" modelAttribute="seriesEpisode">
					<input type="hidden" class="form-control" value="frmAddDateManually" name="formName">
					<div class="form-group col-lg-3">
						<select name="seriesId" class="form-control text-center" required="required">
							<c:forEach var="series" items="${allSeries}">
								<option value="${series.seriesId}">${series.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-lg-1">
						<input type="number" class="form-control text-center" min="1" name="season" id="season"
							   required="required">
					</div>
					<div class="form-group col-lg-1">
						<input type="number" class="form-control text-center" min="0" name="episode" id="episode"
							   required="required">
					</div>
					<div class="form-group col-lg-2">
						<input type="date" class="date form-control text-center" name="date" id="date">
					</div>
					<div class="form-group col-lg-4">
						<input type="text" class="form-control text-center" name="notes">
					</div>
					<div class="form-group col-lg-1">
						<input type="submit" id=addSingleEpisodeBtn" value="Submit Date"
							   class="btn btn-success">
					</div>
				</form:form>
			</div>
			<%--FIXME : fix upload from xlsx--%>
<%--
			<div class="row">
				<form action="/AddDateServlet" method="post" name="frmAddDatesWithXlsx" enctype="multipart/form-data">
					<input type="hidden" name="formName" value="frmAddDatesWithXlsx">
					<div align="center">
						<label class="btn btn-default">
							<span class="fa  fa-file-excel-o"></span> Browse Xlsx
							<input type="file" id="uploadXlsx" name="uploadXlsx" class="labelBtn"
								   accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
						</label>
						<input type="submit" id="xlsxSubmitBtn" class="btn btn-success disabled"
							   value="Submit from Xlsx" disabled>
					</div>
				</form>
			</div>
--%>
		</div>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
