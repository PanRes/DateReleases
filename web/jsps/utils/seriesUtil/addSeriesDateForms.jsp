<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 20/10/2017
  Time: 11:35 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <form action="/AddDateServlet" method="post" name="frmAddDateManually" >
        <input type="hidden" class="form-control" value="frmAddDateManually" name="formName">
        <div class="form-group col-lg-3">
            <select name="seriesId" class="form-control text-center" required="required">
                <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                    <option value="${series.seriesId}">${series.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-lg-1">
            <input type="number" class="form-control text-center" min="1" name="season" id="season"
                   required="required">
        </div>
        <div class="form-group col-lg-1">
            <input type="number" class="form-control text-center" min="0" name="episode" id="episode"
                   required="required">
        </div>
        <div class="form-group col-lg-2">
            <input type="date" class="date form-control text-center" name="date" id="date">
        </div>
        <div class="form-group col-lg-4">
            <input type="text" class="form-control text-center" name="notes">
        </div>
        <div class="form-group col-lg-1">
            <input type="submit" id=addSingleEpisodeBtn" value="Submit Date"
                   class="btn btn-success">
        </div>
    </form>
</div>
<div class="row">
    <form action="/AddDateServlet" method="post" name="frmAddDatesWithXlsx" enctype="multipart/form-data">
        <input type="hidden" name="formName" value="frmAddDatesWithXlsx">
        <div align="center">
            <label class="btn btn-default">
                <span class="fa  fa-file-excel-o"></span> Browse Xlsx
                <input type="file" id="uploadXlsx" name="uploadXlsx" class="labelBtn"
                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
            </label>
            <input type="submit" id="xlsxSubmitBtn" class="btn btn-success disabled"
                   value="Submit from Xlsx" disabled>
        </div>
    </form>
</div>

<script type="application/javascript">


    /*$(document).ready(function () {
        $("#addSingleEpisodeBtn").addClass("disabled");
        $("#addSingleEpisodeBtn").prop("disabled", true);
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            console.log("in if");
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });*/
    $("#season").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }

    });

    $("#episode").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });

    $("#date").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });
</script>