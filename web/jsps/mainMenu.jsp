<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 11:40 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main Menu</title>
</head>
<body>
    <header>
        <%@include file="header.jsp"%>
    </header>
    <article>
        <div class="row text-center">
            <p>
                <a href="/viewSchedule?seriesId=0" class="btn btn-default text-center">
                    Schedule for all Series
                </a>
            </p>


            <jsp:include page="series.jsp"/>
        </div>
    </article>
</body>
</html>
