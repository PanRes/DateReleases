<%@ page import="gr.pr.datereleases.hibernatetools.HibernateTools" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 16/10/2017
  Time: 5:24 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title><%=SeriesTools.getSeriesNameBySeriesId(Integer.valueOf(request.getParameter("seriesId")))%> Info</title>
    </head>
    <body>
        <header>
            <%@include file="/jsps/universals/header.jsp"%>
            <jsp:useBean id="series" scope="page" class="gr.pr.datereleases.models.SeriesModel"/>
            <c:set var="series" value='<%=SeriesTools.getSeriesById(Integer.valueOf(request.getParameter("seriesId")))%>'/>
        </header>
        <article>
            <div class="container panel">
                <div class="panel-body">
                    <div class="media">
                        <div class="col-lg-4 media-left">
                            <c:choose>
                                <c:when test="${series.imageUrl != null}">
                                    <img src="${series.imageUrl}" class="thumbnail text-center"
                                         style="height: auto;" width="300"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="/imgs/not-found.png" class="thumbnail text-center"
                                         style="height: auto;" width="300"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-lg-8 text-left media-body">
                            <div class="row">
                                <strong>SeriesName:</strong> ${series.name}
                            </div>
                            <div class="row">
                                <strong>First Aired:</strong>
                                <fmt:formatDate value="${series.dateStarted}" pattern="dd/MM/yyyy"/>
                            </div>
                            <div class="row">
                                <strong>Channel:</strong> ${series.channel}
                            </div>
                            <div class="row">
                                <c:choose>
                                <c:when test="${series.ended == 0}">
                                Still going!
                            </div>
                            <div class="row">
                                <strong>Next Episode:</strong> TBA
                                </c:when>
                                <c:otherwise>
                                    Has Ended!
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <footer>
            <%@include file="/jsps/universals/footer.jsp"%>
        </footer>
    </body>
</html>
