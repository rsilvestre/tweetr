<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <c:if test="${fn:length(usernotfollowinglist) gt 0}">
        <div class="col-md-12">
            <h3>Utilisateur que vous ne suivez pas</h3>

            <c:forEach var="user" items="${usernotfollowinglist}">
                <div class="row follow-space">
                    <div class="col-md-2">
                        <c:if test="${user.image != '' && user.image != '0'}">
                            <img src="<c:url value='/Images/${user.image}'/>" alt="${user.userId}" width="60px"/>
                        </c:if>
                        <c:if test="${user.image == '' || user.image == '0'}">
                            <img src="<c:url value='/assets/images/no-profile-image.jpg'/>" alt="${user.userId}"
                                 width="60px"/>
                        </c:if>
                    </div>
                    <div class="col-md-3 center-middle-txt">
                        <a href="/ShowAccount?id=<c:out value="${user.userId}"/>">@<c:out value="${user.userName}"/></a>
                    </div>
                    <div class="col-md-2 center-middle-btn">
                        <form action="Recherche" method="post">
                            <input type="hidden" name="follow" value="${user.userId}"/>
                            <input type="submit" class="btn btn-primary" value="Follow"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${fn:length(userfollowinglist) gt 0}">
        <div class="col-md-12">
            <h3>Utilisateur que vous suivez</h3>
            <c:forEach var="user" items="${userfollowinglist}">
                <div class="row">
                    <div class="col-md-2">
                        <c:if test="${user.image != '' && user.image != '0'}">
                            <img src="<c:url value='/Images/${user.image}'/>" alt="${user.userId}" width="60px"/>
                        </c:if>
                        <c:if test="${user.image == '' || user.image == '0'}">
                            <img src="<c:url value='/assets/images/no-profile-image.jpg'/>" alt="${user.userId}"
                                 width="60px"/>
                        </c:if>
                    </div>
                    <div class="col-md-3 center-middle-txt">
                        <a href="/ShowAccount?id=<c:out value="${user.userId}"/>">@<c:out value="${user.userName}"/></a>
                    </div>
                    <div class="col-md-2 center-middle-btn">
                        <form action="Recherche" method="post">
                            <input type="hidden" name="stopfollow" value="${user.userId}"/>
                            <input type="submit" class="btn btn-primary" id="${user.userId}" value="Stop Follow"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>