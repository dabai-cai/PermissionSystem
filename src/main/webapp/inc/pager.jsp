<%--
    Document   : pager
    Created on : 2017-4-20, 22:43:36
    Author     : Administrator
--%>

<%@page import="cn.scau.hjr.model.SystemData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>


<%
    int items = Integer.parseInt(request.getParameter("items"));

%>

<pg:pager items="<%=items%>" export="curPage=pageNumber" maxPageItems="<%=SystemData.getPageSize()%>">
    <pg:first>
        <a href="<%=pageUrl%>">首页</a>
    </pg:first>
    <pg:prev>
        <a href="<%=pageUrl%>">上一页</a>
    </pg:prev>
    <pg:pages>
        <%
            if (curPage == pageNumber) {
        %>
        【<%=pageNumber%>】
        <%
        } else {
        %>
        <a href="<%=pageUrl%>"><%=pageNumber%></a>
        <%
            }
        %>
    </pg:pages>
    <pg:next>
        <a href="<%=pageUrl%>">下一页</a>
    </pg:next>
    <pg:last>
        <a href="<%=pageUrl%>">尾页</a>
    </pg:last>
</pg:pager>