<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="content-main">
    <jsp:include page="_content-title.jsp"/>
    <div class="content-stream">
        <c:if test="${fn:length(usernotfollowinglist) gt 0}">
            <c:set var="userlist" scope="request" value="${usernotfollowinglist}"/>
            <c:set var="subTitle" scope="request" value="Utilisateur que vous ne suivez pas"/>
            <jsp:include page="_userList.jsp"/>
        </c:if>
        <c:if test="${fn:length(userfollowinglist) gt 0}">
            <c:set var="userlist" scope="request" value="${userfollowinglist}"/>
            <c:set var="subTitle" scope="request" value="Utilisateur que vous suivez"/>
            <jsp:include page="_userList.jsp"/>
        </c:if>
        <c:if test="${fn:length(userfollowerlist) gt 0}">
            <c:set var="userlist" scope="request" value="${userfollowerlist}"/>
            <c:set var="subTitle" scope="request" value="Utilisateurs qui vous suivent"/>
            <jsp:include page="_userList.jsp"/>
        </c:if>
    </div>
</div>
<span class="clearfix"></span>