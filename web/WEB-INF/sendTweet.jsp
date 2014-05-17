<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="SendTweet">
    <fieldset>
        <h3>Send Tweet</h3>

        <input type="hidden" id="userId" name="userId"
               value="<c:out value="${sessionScope.userSession.userId}"/>"/>

        <p>
            @ <c:out value="${sessionScope.userSession.userName}"/>
        </p>

        <p>
            <c:out value="${sessionScope.userSession.firstName}"/>
            <c:out value="${sessionScope.userSession.lastName}"/>
        </p>

        <p>
            <c:out value="${param.tweets}"/>
            Tweets
            <c:out value="${param.following}"/>
            Following
            <c:out value="${param.followers}"/>
            Followers
        </p>

        <!--<p><c:out value="${tweet.body}"/></p>  -->

        <label for="mesbody</label>
        <textarea id=" messagbody="message" rows="3" cols="3"></textarea>

    </fieldset>
    <input type="submit" value="Send tweet"/>

    <p><a href="<c:url value="/ModifyAccount"/>">Modify account</a></p>

</form>