<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="dashboard">
    <a class="dashboard-head" href="<c:url value="/ShowAccount"/>"> </a>

    <div class="dashboard-content">
        <a class="dashboard-avatar" href="<c:url value="/ShowAccount"/>">
        <c:if test="${sessionScope.userSession.image != '' && sessionScope.userSession.image != '0'}">
                <img class="dashboard-avatarimage" src="<c:url value='/Images/${sessionScope.userSession.image}'/>"
                     alt="${sessionScope.userSession.userId}" width="60px"/>
            </c:if>
            <c:if test="${sessionScope.userSession.image == '' || sessionScope.userSession.image == '0'}">
                <img class="dashboard-avatarimage" src="<c:url value='/assets/images/no-profile-image.jpg'/>"
                     alt="${sessionScope.userSession.userId}"/>
            </c:if>
        </a>

        <div class="dashboard-userfields">
            <div class="dashboard-name">
                <a href="<c:url value="/ShowAccount"/>">
                <c:out value="${sessionScope.userSession.firstName}"/>
                    <c:out value="${sessionScope.userSession.lastName}"/>
                </a>
            </div>
            <span class="dashboard-screenname">
                <a href="<c:url value="/ShowAccount"/>">@<c:out value="${sessionScope.userSession.userName}"/></a>
            </span>
        </div>
        <div class="dashboard-stats">
            <ul class="dashboard-statslist Grid">
                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="#">
                        <span class="dashboard-statlabel">
                            Tweets
                        </span>
                        <span class="dashboard-statvalue">
                            <c:out value="${dashboard.tweetNumber}"/>
                        </span>
                    </a>
                </li>

                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="#">
                        <span class="dashboard-statlabel">
                            Following
                        </span>
                        <span class="dashboard-statvalue">
                            <c:out value="${dashboard.followingNumber}"/>
                        </span>
                    </a>
                </li>

                <li class="dashboard-stat Grid-cell">
                    <a class="dashboard-statlink" href="#">
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
            <form method="post" action="SendTweet" class="form-horizontal" role="form">
                <div class="">
                    <input type="hidden" id="userId" name="userId"
                           value="<c:out value="${sessionScope.userSession.userId}"/>"/>
                    <textarea id="body" name="body" rows="3" cols="34"></textarea>

                    <div class="">
                        <input type="submit" class="btn btn-primary" value="Send tweet"/>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
