<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 9:41 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-4">
    <p class="text-primary">
        <c:choose>
            <c:when test='<%=request.getParameter("imageUrl") != ""%>'>
                <img src='<%=request.getParameter("imageUrl")%>' width="200" height="200" class="img-thumbnail"/>
            </c:when>
            <c:otherwise>
                <img src="${initParam['seriesImgs']}/not-found.png" width="200" height="200" class="img-thumbnail"/>
            </c:otherwise>
        </c:choose>
    </p>
    <h3><%=request.getParameter("seriesName")%></h3>
    <p class="text-success">
        Premiere : <%=request.getParameter("dateStarted")%>
    </p>
    <%--<c:if test='<%=request.getParameter("dateStarted").equals("false")%>'>--%>
        <%--<p class="text-success">--%>
            <%--Still Playing--%>
        <%--</p>--%>
    <%--</c:if>--%>
    <p>
        <a href='/ScheduleServlet?seriesId=<%=request.getParameter("seriesId")%>' class="btn btn-default">
            View Schedule for <%=request.getParameter("seriesName")%>
        </a>
    </p>
</div>
