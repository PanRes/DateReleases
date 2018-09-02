<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 9:41 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="gr.pr.date_releases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="UserFavoriteSeriesTools" class="gr.pr.date_releases.hibernatetools.UserFarvoriteSeriesTools"/>
<c:set var="i" value="0" scope="page"/>
<c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
	<c:set var="n" value="${i%3}"/>
	<c:if test="${n==0}">
		</div>
		<div class="row text-center">
	</c:if>

	<div class="col-md-4">
		<div class="col-md-1">
			<a href="/AddRemoveFavoritesServlet?seriesId=${series.seriesId}" class="favoritesBtn">
				<c:choose>
					<c:when test="${UserFavoriteSeriesTools.isUsersFavoriteSeries(series.seriesId,userId)}">
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
		</div>
		<div class="col-md-11">
		<p>
			<c:choose>
				<c:when test="${series.imageUrl != null}">
					<img src="${series.imageUrl}" style="height: auto;" width="200" class="img-thumbnail">
				</c:when>
				<c:otherwise>
					<img src="${initParam['seriesImgs']}/not-found.png" style="height: auto;" width="200" class="img-thumbnail"/>
				</c:otherwise>
			</c:choose>
		</p>
		</div>
		<h3>${series.name}</h3>
		<p class="text-success">
			Premiere : <fmt:formatDate value="${series.dateStarted}" pattern="dd/MM/yyyy"/>
		</p>
		<c:if test="${!series.ended}">
			<p class="text-success">
				Still Playing
			</p>
		</c:if>
		<p>
			<a href="/viewSeriesSchedule?seriesId=${series.seriesId}" class="btn btn-default">
				Schedule for ${series.name}
			</a>
		</p>
		<p>
			<a href="/seriesInfo?seriesId=${series.seriesId}" class="btn btn-info">
				View ${series.name} Info
			</a>
		</p>
		<p>
			<a href="/editSeries?seriesId=${series.seriesId}" class="btn btn-primary">
				Edit ${series.name} Info
			</a>
		</p>
	</div>
	<c:set var="i" value="${i+1}"/>
</c:forEach>
