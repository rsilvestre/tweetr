<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="content-main">
    <div class="content-header">
        <div class="header-inner">
            <h2 id="content-main-heading" class="js-timeline-title">Tweets</h2>
        </div>
    </div>
    <div class="content-stream">
        <c:if test="${fn:length(tweets) gt 0}">
            <ol class="content-stream-items">
                <c:forEach var="tweet" items="${tweets}">
                    <li class="content-stream-item">
                        <div class="content-tweet">
                            <div class="contain-container">
                                <div class="content-stream-item-header">
                                    <c:if test="${tweet.ruid < 1}">
                                    <a href="<c:url value="/User?id=${tweet.uorig}"/>">
                                        </c:if>
                                        <c:if test="${tweet.ruid > 0}">
                                        <a href="<c:url value="/User?id=${tweet.ruid}"/>">
                                            </c:if>
                                            <c:if test="${tweet.uorigImage != '' && tweet.uorigImage != '0'}">
                                                <img class="stream-item-avatar"
                                                     src="<c:url value='/Images/${tweet.uorigImage}'/>"
                                                     alt="${tweet.uorig}"
                                                     width="60px"/>
                                            </c:if>
                                            <c:if test="${tweet.uorigImage == '' || tweet.uorigImage == '0'}">
                                                <img class="stream-item-avatar"
                                                     src="<c:url value='/assets/images/no-profile-image.jpg'/>"
                                                     alt="${tweet.uorig}"
                                                     width="60px"/>
                                            </c:if>
                                            <strong class="center-middle-txt">
                                                <c:if test="${tweet.ruid < 1}">
                                                    <c:out value="@${tweet.userName}"/>
                                                </c:if>
                                                <c:if test="${tweet.ruid > 0}">
                                                    <c:out value="@${tweet.par}"/>
                                                </c:if>
                                            </strong>
                                        </a>
                                        <small class="time">
                                            <c:out value="${tweet.updatedAt}"/>
                                        </small>
                                        <c:if test="${tweet.ruid > 0}">
                                    <span class="center-middle-txt">
                                        retweet de
                                        <a href="<c:url value="/User?id=${tweet.uorig}"/>">
                                            <strong class="center-middle-txt">
                                                @<c:out value="${tweet.userName}"/>
                                            </strong>
                                        </a>
                                    </span>
                                        </c:if>
                                </div>

                                <p class="tweet-text">
                                        ${tweet.body}
                                </p>

                                <div class="content-stream-item-footer">
                                    <c:if test="${tweet.ruid != sessionScope.userSession.userId && tweet.uorig != sessionScope.userSession.userId}">
                                        <ul class="tweet-action center-middle-btn">
                                            <li class="action-retweet-container">
                                                <form action="RetweetMessage" method="post">
                                                    <input type="hidden" name="userId"
                                                           value="${sessionScope.userSession.userId}"/>
                                                    <input type="hidden" name="tweetId" value="${tweet.tweetId}"/>
                                                    <input type="submit" class="btn btn-action-retweet"
                                                           value="Retweeter"/>
                                                </form>
                                            </li>
                                        </ul>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </c:if>
    </div>
</div>
<span class="clearfix"></span>