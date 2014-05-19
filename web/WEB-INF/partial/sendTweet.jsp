<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Send Tweet</h3>

<p>
    <a href="/ShowAccount">@<c:out value="${sessionScope.userSession.userName}"/></a>
</p>

<p>
    <c:out value="${sessionScope.userSession.firstName}"/>
    <c:out value="${sessionScope.userSession.lastName}"/>
</p>

<p>
    <c:out value="${dashboard.tweetNumber}"/>
    Tweets
</p>

<p>
    <c:out value="${dashboard.followingNumber}"/>
    Following
</p>

<p>
    <c:out value="${dashboard.followerNumber}"/>
    Followers
</p>


<!--<p><c:out value="${tweet.body}"/></p> -->


<form method="post" action="SendTweet" class="form-horizontal" role="form">
    <div class="form-group">
        <input type="hidden" id="userId" name="userId"
               value="<c:out value="${sessionScope.userSession.userId}"/>"/>
        <textarea id="body" name="body" rows="3" cols="35"></textarea>

        <div class="col-md-offset-1 col-md-2">
            <input type="submit" class="btn btn-primary" value="Send tweet"/>
        </div>
    </div>

</form>
