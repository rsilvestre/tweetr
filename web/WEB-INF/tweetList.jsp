<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<table>
    <c:forEach var="tweet" items="${tweets}">
        <tr>
            <td width="70%">
                <img src="<c:url value='/Images/${tweet.uorig}'/>" alt="${tweet.uorig}" height="60px" width="60px"/>
                <c:out value="${tweet.message}"/>
                @<c:out value="${tweet.userName}"/>
                <c:if test="${tweet.ruid > 0}">retweete par <c:out value="${tweet.par}"/>
                </c:if>
            </td>
            <c:if test="${tweet.ruid ne sessionScope.userSession.userId}">
                <c:if test="${tweet.uorig ne sessionScope.userSession.userId}">
                    <td width="30%">
                        <form action="SendReTweet" method="post"><input type="hidden" name="userId"
                                                                        value="${sessionScope.userSession.userId}"/>
                            <input type="hidden" name="tweetId" value="${tweet.tweetId}"/>
                            <input type="submit" value="Retweeter"/></form>
                    </td>
                </c:if>
            </c:if>
        </tr>
        <tr>
            <td><br></td>
        </tr>
    </c:forEach>
</table>
</html>