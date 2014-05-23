<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${param.id != null && param.id == dashboard.user.userId}">
    <c:set var="idparam" scope="request" value="?id=${dashboard.user.userId}"/>
</c:if>

<div class="dashboard">
    <a class="dashboard-head" href="<c:url value="/ShowAccount?id=${dashboard.user.userId}"/>"> </a>

    <div class="dashboard-content">
        <a class="dashboard-avatar" href="<c:url value="/ShowAccount?id=${dashboard.user.userId}"/>">
            <c:if test="${dashboard.user.image != '' && dashboard.user.image != '0'}">
                <img class="dashboard-avatarimage" src="<c:url value='/Images/${dashboard.user.image}'/>"
                     alt="${dashboard.user.userId}" width="60px"/>
            </c:if>
            <c:if test="${dashboard.user.image == '' || dashboard.user.image == '0'}">
                <img class="dashboard-avatarimage" src="<c:url value='/assets/images/no-profile-image.jpg'/>"
                     alt="${dashboard.user.userId}"/>
            </c:if>
        </a>

        <div class="dashboard-userfields">
            <div class="dashboard-name">
                <a href="<c:url value="/ShowAccount?id=${dashboard.user.userId}"/>">
                    <c:out value="${dashboard.user.firstName}"/>
                    <c:out value="${dashboard.user.lastName}"/>
                </a>
            </div>
            <span class="dashboard-screenname">
                <a href="<c:url value="/ShowAccount?id=${dashboard.user.userId}"/>">@<c:out
                        value="${dashboard.user.userName}"/></a>
            </span>
        </div>
        <div class="dashboard-stats">
            <ul class="dashboard-statslist Grid">
                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="<c:out value="/Tweet${idparam}"/>">
                        <span class="dashboard-statlabel">
                            Tweets
                        </span>
                        <span class="dashboard-statvalue">
                            <c:out value="${dashboard.tweetNumber}"/>
                        </span>
                    </a>
                </li>

                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="<c:out value="/Following${idparam}"/>">
                        <span class="dashboard-statlabel">
                            Following
                        </span>
                        <span class="dashboard-statvalue">
                            <c:out value="${dashboard.followingNumber}"/>
                        </span>
                    </a>
                </li>

                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="<c:out value="/Follower${idparam}"/>">
                        <span class="dashboard-statlabel">
                            Followers
                        </span>
                        <span class="dashboard-statvalue">
                            <c:out value="${dashboard.followerNumber}"/>
                        </span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="dashboard-tweet-box">
            <form method="post" action="/Tweet" class="form-horizontal" role="form">
                <div class="">
                    <input type="hidden" id="userId" name="userId"
                           value="<c:out value="${sessionScope.userSession.userId}"/>"/>
                    <textarea id="body" name="body" rows="3" cols="34"></textarea>

                    <div class="dashboard-send-button">
                        <input type="submit" class="btn btn-primary" value="Send tweet"/>
                    </div>
                    <div class="dashboard-word-counter">
                        <span id="word-counter">140</span> Caractères
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
