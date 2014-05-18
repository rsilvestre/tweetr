<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<table>
    <c:forEach var="user" items="${usernotfollowinglist}">

        <tr>
            <td><img src="<c:url value='/Images/${sessionScope.userSession.image}'/>" alt="${user.userId}"
                     height="60px" width="60px"/></td>
            <td><c:out value="${user.userName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td>
                <form action="FollowUnFollow" method="post">
                    <input type="hidden" name="follow" value="${user.userId}"/>
                    <input type="submit" value="Follow"/>
                </form>
            </td>
        </tr>

    </c:forEach>
    <c:forEach var="user" items="${userfollowinglist}">
        <tr>
            <td><img src="<c:url value='/Images/${sessionScope.userSession.image}'/>" alt="${user.userId}"
                     height="60px" width="60px"/></td>
            <td><c:out value="${user.userName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td>
                <form action="FollowUnFollow" method="post">
                    <input type="hidden" name="stopfollow" value="${user.userId}"/>
                    <input type="submit" id="${user.userId}" value="Stop Follow"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</html>