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
        <%@include file="/jsps/universals/header.jsp"%>
    </header>
    <article id="content">
        <div class="row text-center">
            <p>
                <a href="/viewSchedule?seriesId=0" class="btn btn-default text-center">
                    Schedule for all Series
                </a>
            </p>


            <jsp:include page="/jsps/util/series.jsp"/>
        </div>
    </article>
    <footer id="footer">
        <%@include file="/jsps/universals/footer.jsp"%>
    </footer>
</body>
</html>
