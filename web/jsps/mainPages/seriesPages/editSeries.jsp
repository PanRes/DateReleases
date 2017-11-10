<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 17/10/2017
  Time: 11:03 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit <%=SeriesTools.getSeriesNameBySeriesId(Integer.valueOf(request.getParameter("seriesId")))%> Info</title>
</head>
<body>
    <%@include file="/jsps/universals/header.jsp"%>
    <jsp:useBean id="series" scope="page" class="gr.pr.datereleases.models.SeriesModel"/>
    <c:set var="series" scope="page"
           value='<%=SeriesTools.getSeriesById(Integer.valueOf(request.getParameter("seriesId")))%>'/>
    <c:choose>
        <c:when test="${successUpdate == true}">
            <div class="alert alert-success text-center">
                Series successfully Updated
            </div>
        </c:when>
        <c:when test="${successUpdate == false}">
            <div class="alert alert-danger text-center">
                Series failed to Update
            </div>
        </c:when>
    </c:choose>

    <form action="/EditSeriesServlet" method="post" name="frmEditSeries" enctype="multipart/form-data">
        <input type="hidden" name="seriesId" value="${series.seriesId}"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <h5>Series Name:</h5>
                </div>
                <div class="col-lg-9 form-group">
                    <input type="text" name="seriesName" class="form-control" value="${series.name}" required>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <h5>Series Premiere:</h5>
                </div>
                <div class="col-lg-9 form-group">
                    <input type="date" name="seriesPremiere" class="form-control" value="${series.dateStarted}">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <h5>Series Channel:</h5>
                </div>
                <div class="col-lg-9 form-group">
                    <input type="text" name="seriesChannel" class="form-control" value="${series.channel}">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <h5>Ended?:</h5>
                </div>
                <div class="col-lg-9 form-group">
                    <label class="radio-inline">
                        <input type="radio" name="seriesEnded" id="radioEnded"
                               value="0" ${!series.ended ? "checked" : ""}> Series has ended
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="seriesEnded" id="radioGoing"
                               value="1" ${series.ended ? "checked" : ""}> Series still going
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <h5>Series Image:</h5>
                </div>
                <div class="col-lg-9 form-group">
                    <label class="btn btn-warning">
                        <span class="glyphicon glyphicon-upload"></span>Choose Image for ${series.name}
                        <input type="file" name="imgUrl" class="labelBtn" id="imgUrl" accept="image/*">
                    </label>
                    <small id="fileName"></small>
                </div>
            </div>
            <div class="row text-center">
                <input type="submit" class="btn btn-success btn-lg" value="Save Changes">
            </div>
        </div>
    </form>
    <%@include file="/jsps/universals/footer.jsp"%>
</body>
</html>

<script type="application/javascript">
    $("#imgUrl").change(function () {
        var fileName = this.value;
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
        document.getElementById("fileName").innerHTML = fileName;
    });
</script>