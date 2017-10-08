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
             <tr class="bg-primary ">
                <th>Name</th>
                <th>Day</th>
                <th>Seen</th>
                <th>Episode</th>
                <th>Date</th>
             </tr>
            <%
                int seriesId = Integer.valueOf((String) request.getParameter("seriesId"));
                for (SeriesEpisodesModel seriesEpisodesModel : SeriesEpisodesTools.getSeriesById(Integer.valueOf(seriesId))) {
    //                out.println(seriesEpisodesModel + "<br>");
                    String seriesEpisodes = seriesEpisodesModel.toString();
                    String[] seriesEpisodesSplit = seriesEpisodes.split("  ");
                    out.print("<tr>");
                    for (String s : seriesEpisodesSplit){
            %>
                <td>
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
