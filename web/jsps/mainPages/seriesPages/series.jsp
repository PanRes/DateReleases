<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 11:40 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Main Menu</title>
</head>
<body>
    <%@include file="/jsps/universals/header.jsp"%>
    <div class="row text-center">
        <p>
            <a href="/viewSeriesSchedule" class="btn btn-default text-center">
                Schedule for all Series
            </a>
        </p>


        <jsp:include page="/jsps/utils/seriesUtil/series.jsp"/>
    </div>
    <%@include file="/jsps/universals/footer.jsp"%>
</body>
</html>
