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

<%
    String user = (String) session.getAttribute("user");
    String servletPath = request.getServletPath();
%>

<h1 align="center">
    Date Releases
</h1>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Date Releases</a>
        </div>
        <ul class="nav navbar-nav">
            <li class='<%=(servletPath.equals("/jsps/mainMenu.jsp") || servletPath.equals("/mainMenu")) ? "active" : "" %>'>
                <a href="/mainMenu">Main Menu</a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Series<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                        <li><a href="viewSchedule?seriesId=${series.seriesId}">${series.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li class='<%=(servletPath.equals("/jsps/addDate.jsp") || servletPath.equals("/addDate")) ? "active" : "" %>'>
                <a href="/addDate">Add Date</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-text">
                <p >Welcome <%=user%>, <a href="/jsps/util/logout.jsp">sign out?</a></p>
            </li>
        </ul>
    </div>
</nav>

