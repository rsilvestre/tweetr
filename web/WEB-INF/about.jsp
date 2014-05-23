<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<c:if test="${sessionScope.userSession == null}">
    <t:layout-home>
        <jsp:body>
            <jsp:include page="partial/_about.jsp"/>
        </jsp:body>
    </t:layout-home>
</c:if>
<c:if test="${sessionScope.userSession != null}">
    <t:layout>
        <jsp:body>
            <jsp:include page="partial/_about.jsp"/>
        </jsp:body>
    </t:layout>
</c:if>