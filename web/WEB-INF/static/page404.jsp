<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<c:if test="${sessionScope.userSession == null}">
    <t:layout-home>
        <jsp:body>
            <div class="row block-error block-surround">
                <jsp:include page="_404.jsp"/>
            </div>
        </jsp:body>
    </t:layout-home>
</c:if>
<c:if test="${sessionScope.userSession != null}">
    <t:layout>
        <jsp:body>
            <c:out value="${error}"/>
            <div class="row block-error block-surround">
                <jsp:include page="_404.jsp"/>
            </div>
        </jsp:body>
    </t:layout>
</c:if>