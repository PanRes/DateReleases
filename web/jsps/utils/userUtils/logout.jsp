<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 1:03 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    session.setAttribute("user",null);
    response.sendRedirect("/");
    return;
%>