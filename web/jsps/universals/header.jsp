<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 10:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="inclusions.jsp"%>

<c:set var="servletPath" value="${pageContext.request.servletPath}"/>

<fmt:setLocale value="${param.language}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>
<header>
    <div class="row">


        <div class="col-lg-8 col-lg-offset-2 h1 text-center">
            Date Releases
        </div>
        <div class="col-lg-2 form-group">
            <form>
                <select class="selectpicker pull-right" data-width="fit" id="language" name="language" onchange="submit()">
                    <option  value="el" ${param.language == 'el' ? 'selected' : ''}
                             data-content='<span class="flag-icon flag-icon-gr"></span>'>
                    </option>
                    <option value="en" ${param.language == 'en' ? 'selected' : ''}
                            data-content='<span class="flag-icon flag-icon-gb"></span>'>
                    </option>
                </select>
            </form>
        </div>
    </div>

    ${param.language}
    <fmt:message key="hello"/>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Date Releases</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown ${servletPath.startsWith("/jsps/seriesPages") ? 'active' : ''}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Series <b class="caret"></b></a>
                    <ul class="dropdown-menu multi-level">
                        <li class="${servletPath == '/series' || servletPath == '/jsps/mainPages/seriesPages/series.jsp' ?
                                    'active' : ''}">
                            <a href="/series">All Series</a>
                        </li>
                        <li class="dropdown-submenu ${servletPath == '/jsps/mainPages/seriesInfo.jsp' or servletPath == '/seriesInfo' ? 'active' : ''}">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Info</a>
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
                        <li class="dropdown-submenu ${servletPath == '/jsps/seriesPages/viewSeriesSchedule.jsp' or servletPath == '/viewSeriesSchedule' ? 'active' : ''}">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Series Schedule</a>
                            <ul class="dropdown-menu scrollable-menu">
                                <li class="${(servletPath == '/jsps/seriesPages/viewSeriesSchedule.jsp' or servletPath == '/viewSeriesSchedule') and
                                param.seriesId == null ? 'active' : ''}"><a href="/viewSeriesSchedule">All Series</a></li>
                                <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                                    <c:set var="activeScheduleSeries" value=""/>
                                    <c:if test="${(servletPath == '/jsps/seriesPages/viewSeriesSchedule.jsp' or
                                    servletPath == '/viewSeriesSchedule') and series.seriesId == param.seriesId}">
                                        <c:set var="activeScheduleSeries" value="active"/>
                                    </c:if>
                                    <li class="${activeScheduleSeries}">
                                        <a href="/viewSeriesSchedule?seriesId=${series.seriesId}">${series.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="dropdown-submenu ${servletPath == '/jsps/maiPages/editSeriesDate.jsp' or
                        servletPath == '/editSeries' ? 'active' : ''}">
                            <a href="#" class="dta-toggle" data-toggle="dropdown">Edit Series</a>
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
                        <li class="${servletPath == '/jsps/mainPages/seriesPages/addSeriesDate.jsp' or servletPath == '/addSeriesDate' ? 'active' : ''}">
                            <a href="/addSeriesDate">Add Episode Date</a>
                        </li>
                        <li class="${servletPath == '/jsps/mainPages/seriesPages/addSeries.jsp' or servletPath == '/addSeries' ? 'active' : ''}">
                            <a href="/addSeries">Add Series</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%--<div id="navbar" class="navbar-collapse collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><img id="imgNavSel" src="" alt="..." class="img-thumbnail icon-small">  <span id="lanNavSel">ITA</span> <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a class="flag-icon flag-icon-gr" href="#" class="language"><span class="flag-icon flag-icon-gr"></span>Italiano</a></li>
                                <li><a id="navDeu" href="#" class="language"> <img id="imgNavDeu" src="" alt="..." class="img-thumbnail icon-small">  <span id="lanNavDeu">Deutsch</span></a></li>
                                <li><a id="navFra" href="#" class="language"><img id="imgNavFra" src="" alt="..." class="img-thumbnail icon-small">  <span id="lanNavFra">Francais</span></a></li>
                                <li><a id="navEng" href="#" class="language"><img id="imgNavEng" src="" alt="..." class="img-thumbnail icon-small">  <span id="lanNavEng">English</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>--%>
                <c:if test="${servletPath != '//jsps/welcomePage.jsp' || user != null}">
                    <li class="dropdown">
                        <a href="#" class="data-toggle" data-toggle="dropdown">
                            <i class="fa fa-user fa-fw"></i> Welcome ${user.userName}<span class="caret"></span>
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
                                <a href="/jsps/utils/userUtils/logout.jsp">
                                    sign out? <i class="fa fa-sign-out"></i>
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

