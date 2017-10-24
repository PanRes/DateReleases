<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesEpisodesTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 20/10/2017
  Time: 11:25 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="seriesEpisode" class="gr.pr.datereleases.models.SeriesEpisodesModel"/>
<c:set var="seriesEpisode" value='<%=SeriesEpisodesTools.getSeriesEpisodeById(Integer.valueOf(request.getParameter("seriesEpisodeId")))%>'/>
<div class="row">
    <form action="/AddDateServlet" method="post" name="frmEditDate" >
        <input type="hidden" class="form-control" value="frmEditDate" name="formName">
        <div class="form-group col-lg-3">
            <input type="text" value="${seriesEpisode.seriesBySeriesId.name}" class="text-center" readonly>
        </div>
        <div class="form-group col-lg-1">
            <input type="number" class="form-control text-center" name="season" value="${seriesEpisode.season}" readonly>
        </div>
        <div class="form-group col-lg-1">
            <input type="number" class="form-control text-center" name="episode" value="${seriesEpisode.episode}" readonly>
        </div>
        <div class="form-group col-lg-2">
            <input type="date" class="date form-control text-center" name="date" id="date"
                   required="required" value="${seriesEpisode.releaseDate}">
        </div>
        <div class="form-group col-lg-4">
            <input type="text" class="form-control text-center" name="notes" value="${seriesEpisode.notes}">
        </div>
        <div class="form-group col-lg-1">
            <input type="submit" id=addSingleEpisodeBtn" value="Submit Date"
                   class="btn btn-success">
        </div>
    </form>
</div>

<script type="application/javascript">
    $("#date").change(function() {
        $("#xlsxSubmitBtn").removeClass("disabled");
        $("#xlsxSubmitBtn").prop("disabled",false);
    });
</script>
