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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<%--TODO : show series name in the title --%>
		<title>${series.name} Info</title>
	</head>
	<body>
		<%@include file="/WEB-INF/view/universals/header.jsp"%>
		<div class="panel panel-info">
			<p class="panel-heading">
				${series.name} Information &nbsp;
				<security:authorize access="isAuthenticated()">
					<c:choose>
						<c:when test="${series.hasUser()}">
							<a href="${pageContext.request.contextPath}/series/addSeriesToUserFavorites?seriesId=${series.id}" class="favoritesBtn">
								<abbr title="Remove from favorites">
									<i class="glyphicon glyphicon-heart"></i>
								</abbr>
							</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/series/removeSeriesToUserFavorites?series=${series.name}" class="favoritesBtn">
								<abbr title="Add to Favorites">
									<i class="glyphicon glyphicon-heart-empty"></i>
								</abbr>
							</a>
						</c:otherwise>
					</c:choose>
				</security:authorize>
			</p>
			<div class="panel-body" id="seriesInfoPanelBody">
				<div class="media">
					<div class="col-lg-4">
						<c:choose>
							<c:when test="${series.imageUrl != null}">
								<img src="${pageContext.request.contextPath}${series.imageUrl}" class="thumbnail text-center"/>
							</c:when>
							<c:otherwise>
								<%--TODO : put img url in a init param--%>
								<img src="${pageContext.request.contextPath}${seriesImgsDir}/not-found.png"
									 class="thumbnail text-center" style="height: auto;" width="300"/>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-lg-8">
						<c:if test="episodeSaved">
							<c:if test="${param.error != null}">
								<div class="alert alert-success text-center">
									Episode saved successfully
								</div>
							</c:if>

						</c:if>
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
										<%--TODO : show next episodes date--%>
										<strong>Next Episode:</strong> TBA
								</c:when>
								<c:otherwise>
									Has Ended!
								</c:otherwise>
							</c:choose>
						</p>
						<c:if test="${!series.ended}">
							<p>
								<a href="${pageContext.request.contextPath}/series/schedule?series=${series.name}" class="btn btn-default">
									View ${series.name} Schedule
								</a>
							</p>
						</c:if>
						<p>
							<a href="${pageContext.request.contextPath}/series/${series.name}/editSeries" class="btn btn-primary">
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
