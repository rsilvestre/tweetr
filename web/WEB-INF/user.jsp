<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout>
    <jsp:body>
        <div class="block-sector block-sector-left">
            <jsp:include page="partial/dashboard.jsp"/>
        </div>
        <div class="block-sector block-sector-body">
            <jsp:include page="partial/tweetList.jsp"/>
        </div>
    </jsp:body>
</t:layout>
