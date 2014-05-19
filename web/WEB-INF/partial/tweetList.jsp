<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <c:if test="${fn:length(tweets) gt 0}">
        <div class="col-md-12">
            <c:forEach var="tweet" items="${tweets}">
                <div class="row follow-space">
                    <div class="col-md-2">
                        <c:if test="${tweet.uorigImage != '' && tweet.uorigImage != '0'}">
                            <img src="<c:url value='/Images/${tweet.uorigImage}'/>" alt="${tweet.uorig}" width="60px"/>
                        </c:if>
                        <c:if test="${tweet.uorigImage == '' || tweet.uorigImage == '0'}">
                            <img src="<c:url value='/assets/images/no-profile-image.jpg'/>" alt="${tweet.uorig}"
                                 width="60px"/>
                        </c:if>
                    </div>
                    <div class="col-md-<c:if test="${tweet.ruid <= 0}">5</c:if><c:if test="${tweet.ruid > 0}">3</c:if>">
                        <c:out value="${tweet.body}"/>
                    </div>
                    <div class="col-md-2 center-middle-txt">
                        <a href="/ShowAccount?id=<c:out value="${tweet.uorig}"/>">@<c:out
                                value="${tweet.userName}"/></a>
                    </div>
                    <c:if test="${tweet.ruid > 0}">
                        <div class="col-md-2 center-middle-txt">
                            retweete par <c:out value="${tweet.par}"/>
                        </div>
                    </c:if>
                    <c:if test="${tweet.ruid ne sessionScope.userSession.userId}">
                        <c:if test="${tweet.uorig ne sessionScope.userSession.userId}">
                            <div class="col-md-2 center-middle-btn">
                                <form action="SendReTweet" method="post">
                                    <input type="hidden" name="userId" value="${sessionScope.userSession.userId}"/>
                                    <input type="hidden" name="tweetId" value="${tweet.tweetId}"/>
                                    <input type="submit" class="btn btn-block btn-primary" value="Retweeter"/>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>