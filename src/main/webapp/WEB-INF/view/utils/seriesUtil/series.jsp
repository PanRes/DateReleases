<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="i" value="0" scope="page"/>
<c:forEach var="series" items="${allSeries}">
	<c:set var="n" value="${i%3}"/>
	<c:if test="${n==0}">
		</div>
		<div class="row text-center">
	</c:if>

	<div class="col-md-4">
		<div class="col-md-1">
			<security:authorize access="isAuthenticated()">
				<c:choose>
					<c:when test="${series.hasUser()}">
						<a href="${pageContext.request.contextPath}/series/removeSeriesToUserFavorites?seriesId=${series.id}" class="favoritesBtn">
							<abbr title="<spring:message code='favorites.remove'/>">
								<i class="glyphicon glyphicon-heart"></i>
							</abbr>
						</a>
						</c:when>
						<c:otherwise>
						<a href="${pageContext.request.contextPath}/series/addSeriesToUserFavorites?seriesId=${series.id}" class="favoritesBtn">
							<abbr title="<spring:message code='favorites.add'/> ">
								<i class="glyphicon glyphicon-heart-empty"></i>
							</abbr>
						</a>
					</c:otherwise>
				</c:choose>
			</security:authorize>
		</div>
		<div class="col-md-11">
			<p>
				<c:choose>
					<c:when test="${not empty series.imageUrl}">
						<img src="${pageContext.request.contextPath}${series.imageUrl}" style="height: auto;" width="200" class="img-thumbnail">
					</c:when>
					<c:otherwise>
						<%--FIXME : reduce width--%>
						<img src="${pageContext.request.contextPath}${seriesImgsDir}/not-found.png"
								style="height: auto;" width="200" class="img-thumbnail"/>
					</c:otherwise>
				</c:choose>
			</p>
			<h3>${series.name}</h3>
			<p class="text-success">
				<spring:message code="series.premiere"/> : <fmt:formatDate value="${series.dateStarted}" pattern="dd/MM/yyyy"/>
			</p>
			<c:if test="${!series.ended}">
				<p class="text-success">
					<spring:message code="series.still.playing"/>
				</p>
			</c:if>
			<p>
				<a href="${pageContext.request.contextPath}/series/schedule?series=${series.name}" class="btn btn-default">
					<spring:message code="series.schedule.for">
						<spring:argument value="${series.name}"/>
					</spring:message>
				</a>
			</p>
			<p>
				<a href="${pageContext.request.contextPath}/series/${series.name}" class="btn btn-info">
					<spring:message code="series.info.view">
						<spring:argument value="${series.name}"/>
					</spring:message>
				</a>
			</p>
			<p>
				<a href="${pageContext.request.contextPath}/series/${series.name}/editSeries" class="btn btn-primary">
					<spring:message code="series.edit">
						<spring:argument value="${series.name}"/>
					</spring:message>
				</a>
			</p>
		</div>
	</div>
	<c:set var="i" value="${i+1}"/>
</c:forEach>
