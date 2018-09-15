<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 2:45 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>Schedule</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<c:choose>
			<c:when test="${series.size() == 0}">
				<div class="text-center">
					<h4>There are no episodes coming</h4>
				</div>
			</c:when>
			<c:otherwise>
				<table class="table table-striped table-hover">
					<tr>
						<th class="text-center">Name</th>
						<th class="text-center">Day</th>
						<th class="text-center">Seen</th>
						<th class="text-center">Episode</th>
						<th class="text-center">Date</th>
						<th class="text-center" width="300">Notes</th>
						<th class="text-center" width="50">Delete</th>
					</tr>
					<c:forEach var="seriesEpisodeLine" items="${seriesEpisodes}">
						<tr>
							<td class="text-center">
								<a href="${pageContext.request.contextPath}/series/${seriesEpisodeLine.series.name}">
									${seriesEpisodeLine.series.name}
								</a>
							</td>
							<td class="text-center">${seriesEpisodeLine.releaseDay()}</td>
							<td class="text-center">${seriesEpisodeLine.viewDay()}</td>
							<td class="text-center">
								${seriesEpisodeLine.getEpisodeToString()}
							</td>
							<td class="text-center">
								<c:choose>
									<c:when test="${seriesEpisodeLine.releaseDate == null}">
										TBA
									</c:when>
									<c:when test="${seriesEpisodeLine.releaseDate < now}">
										Released
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${seriesEpisodeLine.releaseDate}" pattern="dd/MM/yyyy"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="text-center" width="300">
								${seriesEpisodeLine.notes}
							</td>
							<td class="text-center" width="50">
								<a href="/editSeriesEpisodeDate?seriesEpisodeId=${seriesEpisodeLine.id}">
									<abbr title="Edit This Row">
										<span class="glyphicon glyphicon-edit editSeriesEpisodeRowbtn"></span>
									</abbr>
								</a>
								<a href="/deleteSeriesEpisodeDate/${seriesEpisodeLine.series.name}?
										s=${seriesEpisodeLine.season}&e=${seriesEpisodeLine.episode}">
									<abbr title="Delete this Row">
										<span class="glyphicon glyphicon-remove deleteSeriesEpisodeBtn"></span>
									</abbr>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<%@include file="/WEB-INF/view/universals/footer.jsp"%>
	</body>
</html>
