<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="inclusions.jsp"%>

<c:set var="pageURI" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<header>


	<div class="h1 text-center">
		<spring:message code="header.mainTitle" />
	</div>


	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Date Releases</a>
			</div>
			<ul class="nav navbar-nav">
				<%--BEST : invert colors when active--%>
				<li class="dropdown ${fn:contains(pageURI,'/series') ? 'active' : ''}">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Series <b class="caret"></b></a>
					<ul class="dropdown-menu multi-level">
						<li class="${viewAllSeries ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/series">All Series</a>
						</li>
						<li class="dropdown-submenu ${seriesInfo ? 'active' : ''}">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Info</a>
							<ul class="dropdown-menu scrollable-menu" role="menu">
								<c:forEach var="series" items="${allSeries}">
									<li class="${fn:endsWith(fn:replace(pageURI,'%20',' '), series.name)&& seriesInfo ? 'active' : ''}">
										<a href="${pageContext.request.contextPath}/series/info/${series.name}">${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown-submenu ${fn:contains(pageURI,'/series/schedule') ? 'active' : ''}">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Schedule</a>
							<ul class="dropdown-menu scrollable-menu">
								<li class="${fn:contains(pageURI,'/series/schedule') && param.series == 'allSeries' ? 'active' : ''}">
									<a href="${pageContext.request.contextPath}/series/schedule?series=allSeries">All Series</a>
								</li>
								<security:authorize access="isAuthenticated()">
									<li class="${fn:contains(pageURI,'series/schedule') && param.series == 'favorites' ? 'active' : ''}">
										<a href="${pageContext.request.contextPath}/series/schedule?series=favorites">Favorite Series</a>
									</li>
								</security:authorize>
								<c:forEach var="series" items="${allSeries}">
									<%--TODO : check if validation works correctly--%>
									<li class="${fn:contains(pageURI, 'series/schedule') && param.series == series.name ? 'active' : ''}">
										<a href="${pageContext.request.contextPath}/series/schedule?series=${series.name}">${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown-submenu ${fn:contains(pageURI, 'series/edit/') ? 'active' : ''}">
							<a href="#" class="dta-toggle" data-toggle="dropdown">Edit Series</a>
							<ul class="dropdown-menu scrollable-menu">
								<c:forEach var="series" items="${allSeries}">
									<li class="${fn:contains(pageURI, 'series/edit') && fn:endsWith(fn:replace(pageURI,'%20',' '), series.name) ? 'active' : ''}">
										<a href="${pageContext.request.contextPath}/series/edit/${series.name}">Edit ${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="${fn:endsWith(pageURI, 'series/addSeriesEpisodeDate') ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/series/addSeriesEpisodeDate">Add Episode Date</a>
						</li>
						<li class="${fn:endsWith(pageURI, 'series/addSeries') ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/series/addSeries">Add Series</a>
						</li>
					</ul>
				</li>
			</ul>
			<%--TODO : add spring localization--%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<spring:message code="header.language.select"/><b class="caret"></b>
					</a>
					<ul class="dropdown-menu multi-level">
						<li class="${locale == 'el' ? 'active' : ''}">
							<a class="dropdown-item" href="?lang=el">
								<spring:message code="header.lang.greek"/>
							</a>
						</li>
						<li class="${locale == 'en' ? 'active' : ''}">
							<a class="dropdown-item" href="?lang=en">
								<spring:message code="header.lang.english"/>
							</a>
						</li>
					</ul>
				</li>

				<li class="dropdown">
					<security:authorize access="isAuthenticated()" >
						<a href="#" class="data-toggle" data-toggle="dropdown">
							<i class="fa fa-user fa-fw"></i>
							<spring:message code="header.user.welcome"/><security:authentication property='principal.username'/>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu scrollable-menu">
							<li class="${userInfo ? 'active' : ''}">
								<a href="${pageContext.request.contextPath}/userPanel">
									<spring:message code="header.user.info"/>
								</a>
							</li>
							<li class="${editUserInfo ? 'active' : ''}">
								<a href="${pageContext.request.contextPath}/userPanel/editInfo">
									<spring:message code="header.user.edit"/>
								</a>
							</li>
							<li class="${changeUserPassword ? 'active' : ''}">
								<a href="${pageContext.request.contextPath}/userPanel/changePassword">
									<spring:message code="header.user.password"/>
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="${pageContext.request.contextPath}/logout">
									<spring:message code="header.user.logout"/><i class="fa fa-sign-out"></i>
								</a>
							</li>
						</ul>
					</security:authorize>
					<c:if test="${pageURI != pageContext.request.contextPath.concat('/')}">
						<security:authorize access="!isAuthenticated()">
							<a href="#" class="data-toggle" data-toggle="dropdown">
								<spring:message code="header.user.connect"/>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu scrollable-menu">
								<li >
									<a href="${pageContext.request.contextPath}/login">
										<i class="fa fa-sign-in"></i>
										<spring:message code="login"/>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/signUp">
										<i class="fa fa-user-plus"></i>
										<spring:message code="signUp"/>
									</a>
								</li>
							</ul>
						</security:authorize>
					</c:if>
				</li>
			</ul>
		</div>
	</nav>
</header>
<%--INFO : article is closed in each jsp--%>
<article id="content">

