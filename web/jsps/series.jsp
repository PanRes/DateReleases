<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 9:41 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="i" value="0" scope="page"/>
<c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
    <c:set var="n" value="${i%3}"/>
    <c:if test="${n==0}">
        </div>
        <div class="row text-center">
    </c:if>

    <div class="col-md-4">
        <p class="text-primary">
            <c:choose>
                <c:when test="${series.imageUrl != null}">
                    <img src="${series.imageUrl}" width="200" height="200" class="img-thumbnail">
                </c:when>
                <c:otherwise>
                    <img src="/imgs/not-found.png" width="200" height="200" class="img-thumbnail"/>
                </c:otherwise>
            </c:choose>
        </p>
        <h3>${series.name}</h3>
        <p class="text-success">
            Premiere : ${series.dateStarted}
        </p>
        <c:if test="${not seires.ended}">
            <p class="text-success">
                Still Playing
            </p>
        </c:if>
        <p>
            <a href="/viewSchedule?seriesId=${series.seriesId}" class="btn btn-default">
                View Schedule for ${series.name}
            </a>
        </p>
    </div>
    <c:set var="i" value="${i+1}"/>
</c:forEach>
