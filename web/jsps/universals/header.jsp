<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 10:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="inclusions.jsp"%>

<c:set var="servletPath" value="${pageContext.request.servletPath}"/>
<header>
    <h1 align="center">
        Date Releases
    </h1>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/mainMenu">Date Releases</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown ${servletPath == '/jsps/mainPages/seriesInfo.jsp' or servletPath == '/seriesInfo' ? 'active' : ''}">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Series Info<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu scrollable-menu" role="menu">
                        <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                            <c:set var="activeInfoSeries" value=""/>
                            <c:if test="${(servletPath == '/jsps/maiPages/seriesInfo.jsp' or
                                    servletPath == '/seriesInfo') and series.seriesId == param.seriesId}">
                                <c:set var="activeInfoSeries" value="active"/>
                            </c:if>
                            <li class="${activeInfoSeries}">
                                <a href="seriesInfo?seriesId=${series.seriesId}">${series.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown ${servletPath == '/jsps/mainPages/viewSchedule.jsp' or servletPath == '/viewSchedule' ? 'active' : ''}">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Series Schedule<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu scrollable-menu">
                        <li class="${(servletPath == '/jsps/mainPages/viewSchedule.jsp' or servletPath == '/viewSchedule') and
                                param.seriesId == null ? 'active' : ''}"><a href="viewSchedule">All Series</a></li>
                        <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                            <c:set var="activeScheduleSeries" value=""/>
                            <c:if test="${(servletPath == '/jsps/maiPages/viewSchedule.jsp' or
                                    servletPath == '/viewSchedule') and series.seriesId == param.seriesId}">
                                <c:set var="activeScheduleSeries" value="active"/>
                            </c:if>
                            <li class="${activeScheduleSeries}">
                                <a href="viewSchedule?seriesId=${series.seriesId}">${series.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown ${servletPath == '/jsps/maiPages/editSeriesDate.jsp' or
                        servletPath == '/editSeries' ? 'active' : ''}">
                    <a href="#" class="dta-toggle" data-toggle="dropdown">
                        Edit Series<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu scrollable-menu">
                        <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                            <c:set var="activeEditSeries" value=""/>
                            <c:if test="${(servletPath == '/jsps/maiPages/editSeriesDate.jsp' or
                                    servletPath == '/editSeries') and series.seriesId == param.seriesId}">
                                <c:set var="activeEditSeries" value="active"/>
                            </c:if>
                            <li class="${activeEditSeries}">
                                <a href="/editSeries?seriesId=${series.seriesId}">Edit ${series.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="${servletPath == '/jsps/seriesPages/addSeriesDate.jsp' or servletPath == '/addSeriesDate' ? 'active' : ''}">
                    <a href="/addSeriesDate">Add Episode Date</a>
                </li>
                <li class="${servletPath == '/jsps/mainPages/addPage.jsp' or servletPath == '/addPage' ? 'active' : ''}">
                    <a href="/addSeries">Add Series</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="data-toggle" data-toggle="dropdown">
                        Welcome ${user}<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu scrollable-menu">
                        <li class="${servletPath == '/userInfo' || servletPath == '/jsps/userControlPanel/userInfo.jsp' ?
                                'active' : ''}">
                            <a href="/userInfo">User Info</a>
                        </li>
                        <li class="${servletPath == '/editUserInfo' ||
                                servletPath == '/jsps/userControlPanel/editUserInfo.jsp' ? 'active' : ''}">
                            <a href="/editUserInfo">Edit User Info</a>
                        </li>
                        <li class="${servletPath == '/changeUserPassword' ||
                                servletPath == '/jsps/userControlPanel/changeUserPassword.jsp' ? 'active' : ''}">
                            <a href="/changeUserPassword">Change Password</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/jsps/util/logout.jsp">
                                sign out? <i class="fa fa-sign-out"></i>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<article id="content">

