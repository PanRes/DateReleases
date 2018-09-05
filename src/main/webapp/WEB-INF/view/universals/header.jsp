<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 10:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="inclusions.jsp"%>

<c:set var="pageURI" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.language == null ? 'el' : sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<header>

	<jsp:useBean id="seriesService" class="gr.pr.date_releases.service.SeriesService"/>

	<div class="row">

		<div class="col-lg-8 col-lg-offset-2 h1 text-center">
			<fmt:message key="header.mainTitle" />
		</div>
		   <%-- <div class="col-lg-2 form-group">

			</div>--%>
	</div>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Date Releases</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="dropdown ${fn:contains(pageURI,'/series/schedule') ? 'active' : ''}">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Series <b class="caret"></b></a>
					<ul class="dropdown-menu multi-level">
						<li class="${fn:contains(pageURI, 'series/' + series.name ) ? 'active' : ''}">
							<a href="/series">All Series</a>
						</li>
						<li class="dropdown-submenu ${fn:contains(pageURI, 'series/' + series.name + '/info') ? 'active' : ''}">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Info</a>
							<ul class="dropdown-menu scrollable-menu" role="menu">
								<c:forEach var="series" items="<%=seriesService.getAllSeries()%>">
									<%--TODO : check if validation works correctly--%>
									<li class="${fn:contains(pageURI, 'series/' + series.name + '/info') ? 'active' : ''}">
										<a href="${'/series/' + series.name + '/info'}">${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown-submenu <li class="${fn:contains(pageURI,'/series/schedule') ? 'active' : ''}">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Schedule</a>
							<ul class="dropdown-menu scrollable-menu">
								<li class="${fn:contains(pageURI,'/series/schedule') ? 'active' : ''}">
									<a href="/series/schedule">All Series</a>
								</li>
								<li class="${fn:contains(pageURI,'/series/schedule/favorites') ? 'active' : ''}">
									<a href="/series/favorites">Favorite Series</a>
								</li>
								<c:forEach var="series" items="<%=seriesService.getAllSeries()%>">
									<%--TODO : check if validation works correctly--%>
									<li class="${fn:contains(pageURI, 'series/schedule/' + series.name) ? 'active' : ''}">
										<a href="${'/series/schedule/' + series.name}">${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<%--FIXME : find out wich page needs to be redirected--%>
						<li class="dropdown-submenu ${fn:contains(pageURI, 'series/editSeriesDate') ? 'active' : ''}">
							<a href="#" class="dta-toggle" data-toggle="dropdown">Edit Series</a>
							<ul class="dropdown-menu scrollable-menu">
								<c:forEach var="series" items="<%=seriesService.getAllSeries()%>">
									<%--TODO : check if validation works correctly--%>
									<li class="${fn:contains(pageURI, 'series/editSeriesDate/' + series.name) ? 'active' : ''}">
										<a href="${'series/' + series.name + '/editSeries'}">Edit ${series.name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="${servletPath == '/WEB-INF/view/mainPages/seriesPages/addSeriesDate.jsp' or servletPath == '/addSeriesDate' ? 'active' : ''}">
							<a href="${'/series/addSeriesDate/' + series.name}">Add Episode Date</a>
						</li>
						<li class="${fn:contains(pageURI, '/series/addSeries') ? 'active' : ''}">
							<a href="/series/addSeries">Add Series</a>
						</li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<form class="navbar-form navbar-left" action="/LanguageServlet" method="post">
					<select class="selectpicker btn-black" data-width="fit" id="language" name="language"
							onchange="submit()">
						<option  value="el" ${language == 'el' ? 'selected' : ''}
								 data-content='<span class="flag-icon flag-icon-gr"></span> Ελληνικά'>
							Ελληνικά
						</option>
						<option value="en" ${language == 'en' ? 'selected' : ''}
								data-content='<span class="flag-icon flag-icon-gb"></span> English'>
							English
						</option>
					</select>
				</form>
				<c:if test="${ user != null}">
					<li class="dropdown">
						<a href="#" class="data-toggle" data-toggle="dropdown">
							<i class="fa fa-user fa-fw"></i> Welcome ${user.userName}<span class="caret"></span>
						</a>
						<ul class="dropdown-menu scrollable-menu">
							<li class="${servletPath == '/userInfo' || servletPath == '/WEB-INF/view/userControlPanel/userInfo.jsp' ?
									'active' : ''}">
								<a href="/userInfo">User Info</a>
							</li>
							<li class="${servletPath == '/editUserInfo' ||
									servletPath == '/WEB-INF/view/userControlPanel/editUserInfo.jsp' ? 'active' : ''}">
								<a href="/editUserInfo">Edit User Info</a>
							</li>
							<li class="${servletPath == '/changeUserPassword' ||
									servletPath == '/WEB-INF/view/userControlPanel/changeUserPassword.jsp' ? 'active' : ''}">
								<a href="/changeUserPassword">Change Password</a>
							</li>
							<li class="divider"></li>
							<li>
								<form:form method="post" action="${pageContext.request.contextPath}/logout">
									<%--FIXME : fix view of logout button--%>
									<input type="submit" class="btn btn-primary" value="Logout"><i class="fa fa-sign-out"></i>
								</form:form>

								<a href="/WEB-INF/view/utils/userUtils/logout.jsp">
									Log out? <i class="fa fa-sign-out"></i>
								</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
</header>
<article id="content">

