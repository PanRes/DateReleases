<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesEpisodesTools" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Locale" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 2:45 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Schedule</title>
    </head>
    <body>
        <%@include file="/jsps/universals/header.jsp"%>
        <jsp:useBean id="SeriesEpisodesTools" class="gr.pr.datereleases.hibernatetools.SeriesEpisodesTools"/>
        <jsp:useBean id="SeriesTools" class="gr.pr.datereleases.hibernatetools.SeriesTools"/>
        <%

            String seriesIdString = (String) request.getParameter("seriesId");
            int seriesId = 0;
            if (seriesIdString != null) {
                seriesId = Integer.valueOf(seriesIdString);
            }
            Calendar now = Calendar.getInstance(Locale.US);
            now.add(Calendar.DATE,-1);/*Because US series displayed at the night of that day,
                                                       *I sub one day from now for released label
                                                       * */
        %>
        <c:choose>
            <c:when test="${SeriesEpisodesTools.getSeriesEpisodesRowsCountBySeriesId(param.seriesId) == 0}">
                <div class="text-center">
                    <h4>There were no entries for ${SeriesTools.getSeriesNameBySeriesId(param.seriesId)}</h4>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-hover">
                    <tr>
                        <th class="text-center">Name</th>
                        <th class="text-center">Day</th>
                        <th class="text-center">Seen</th>
                        <th class="text-center">Episode</th>
                        <th class="text-center">Date</th>
                        <th class="text-center" width="300">Notes</th>
                        <th class="text-center" width="50">Delete</th>
                    </tr>
                    <c:forEach var="seriesLine" items="<%=SeriesEpisodesTools.getSeriesEpisodeBySeriesId(seriesId)%>">
                        <tr>
                            <td class="text-center">
                                <a href="seriesInfo?seriesId=${seriesLine.seriesBySeriesId.seriesId}">
                                    ${seriesLine.seriesBySeriesId.name}
                                </a>
                            </td>
                            <td class="text-center">${seriesLine.releaseDay()}</td>
                            <td class="text-center">${seriesLine.viewDay()}</td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${seriesLine.episode == -1}">
                                        <c:choose>
                                            <c:when test="${seriesLine.season < 9}">
                                                Season 0${seriesLine.season}
                                            </c:when>
                                            <c:otherwise>
                                                Season ${seriesLine.season}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        ${seriesLine.seasonEpisode()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <c:set var="now" value="<%=new Date(now.getTimeInMillis())%>"/>
                                <c:choose>
                                    <c:when test="${seriesLine.releaseDate == null}">
                                        TBA
                                    </c:when>
                                    <c:when test="${seriesLine.releaseDate < now}">
                                        Released
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${seriesLine.releaseDate}" pattern="dd/MM/yyyy"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center" width="300">
                                ${seriesLine.notes}
                            </td>
                            <td class="text-center" width="50">
                                <a href="/addDate?modeDate=edit&seriesEpisodeId=${seriesLine.seriesEpisodesId}">
                                    <span class="glyphicon glyphicon-edit editSeriesEpisodeRowbtn"></span>
                                </a>
                                <a href="/DeleteSeresEpisodeRowServlet?seriesEpisodeId=${seriesLine.seriesEpisodesId}&seriesId=${param.seriesId}">
                                    <span class="glyphicon glyphicon-remove deleteSeriesEpisodebtn"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>
