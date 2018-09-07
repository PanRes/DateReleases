<%@ page import="gr.pr.date_releases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 16/10/2017
  Time: 5:24 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<%--TODO : show series name in the title --%>
		<title>Series Info</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<jsp:useBean id="userService" class="gr.pr.date_releases.service.UserServiceImpl"/>
		<div class="panel panel-info">
			<p class="panel-heading">
				${series.name} Information &nbsp;
				<a href="/AddRemoveFavoritesServlet?seriesId=${series.seriesId}" class="favoritesBtn">
					<c:choose>
						<c:when test="${userService.hasUserFavoriteSeries(series)}">
							<abbr title="Remove from favorites">
								<i class="glyphicon glyphicon-heart"></i>
							</abbr>
						</c:when>
						<c:otherwise>
							<abbr title="Add to Favorites">
								<i class="glyphicon glyphicon-heart-empty"></i>
							</abbr>
						</c:otherwise>
					</c:choose>
				</a>
			</p>
			<div class="panel-body" id="seriesInfoPanelBody">
				<div class="media">
					<div class="col-lg-4">
						<c:choose>
							<c:when test="${series.imageUrl != null}">
								<img src="${series.imageUrl}" class="thumbnail text-center"/>
							</c:when>
							<c:otherwise>
								<%--FIXME : fix url to new one--%>
								<img src="${initParam['seriesImgs']}/not-found.png" class="thumbnail text-center"
									 style="height: auto;" width="300"/>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-lg-8">
						<p>
							<strong>First Aired:</strong>
							<fmt:formatDate value="${series.dateStarted}" pattern="dd/MM/yyyy"/>
						</p>
						<p>
							<strong>Channel:</strong> ${series.channel}
						</p>
						<p>
							<c:choose>
								<c:when test="${!series.ended}">
									Still going!
									</p>
									<p>
										<strong>Next Episode:</strong> TBA
								</c:when>
								<c:otherwise>
									Has Ended!
								</c:otherwise>
							</c:choose>
						</p>
						<c:if test="${!series.ended}">
							<p>
								<%--FIXME : change url when viewSeriesSchedule contoller is ready--%>
								<a href="/viewSeriesSchedule?seriesId=${series.seriesId}" class="btn btn-default">
									View ${series.name} Schedule
								</a>
							</p>
						</c:if>
						<p>
							<a href="/${series.name}/editSeries" class="btn btn-primary">
								Edit ${series.name} Info
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
