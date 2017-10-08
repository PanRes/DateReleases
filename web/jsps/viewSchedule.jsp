<%@ page import="java.util.List" %>
<%@ page import="gr.pr.datereleases.models.SeriesEpisodesModel" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesEpisodesTools" %><%--
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
    <header>
        <%@include file="header.jsp"%>
    </header>
    <article>
        <table class="table table-striped table-hover">
             <tr>
                <th class="text-center">Name</th>
                <th class="text-center">Day</th>
                <th class="text-center">Seen</th>
                <th class="text-center">Episode</th>
                <th class="text-center">Date</th>
             </tr>
            <%
                int seriesId = Integer.valueOf((String) request.getParameter("seriesId"));
                for (SeriesEpisodesModel seriesEpisodesModel : SeriesEpisodesTools.getSeriesById(seriesId)) {
    //                out.println(seriesEpisodesModel + "<br>");
                    String seriesEpisodes = seriesEpisodesModel.toString();
                    String[] seriesEpisodesSplit = seriesEpisodes.split("  ");
                    out.print("<tr>");
                    for (String s : seriesEpisodesSplit){
            %>
                <td class="text-center">
                    <%=s%>
                </td>
            <%
                    }
                    out.print("</tr>");
                }
            %>
        </table>
    </article>
</body>
</html>
