<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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

<h1 align="center">
    Date Releases
</h1>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/mainMenu">Date Releases</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown ${servletPath == '/jsps/mainPages/viewSchedule.jsp' or servletPath == '/viewSchedule' ? 'active' : ''}">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Series<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="viewSchedule">All Series</a></li>
                    <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                        <c:set var="activeSeries" value=""/>
                        <c:if test="${servletPath == '/jsps/mainPages/viewSchedule.jsp' or servletPath == '/viewSchedule'}">
                            <c:if test="${param.seriesId == series.seriesId}">
                                <c:set var="activeSeries" value="active"/>
                            </c:if>
                        </c:if>
                        <li class="${activeSeries}">
                            <a href="viewSchedule?seriesId=${series.seriesId}">${series.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li class="${servletPath == '/jsps/mainPages/addDate.jsp' or servletPath == '/addDate' ? 'active' : ''}">
                <a href="/addDate">Add Date</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">
                <p >Welcome ${user}, <a href="/jsps/util/logout.jsp">sign out?</a></p>
            </li>
        </ul>
    </div>
</nav>

