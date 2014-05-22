<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(userlist) gt 0}">
    <div class="content-subtitle">
        <h3><c:out value="${subTitle}"/></h3>
    </div>
    <ol class="content-stream-items">
        <c:forEach var="user" items="${userlist}">
            <li class="content-stream-item">
                <div class="content-tweet">
                    <div class="contain-container">
                        <div class="col-md-2">
                            <c:if test="${user.image != '' && user.image != '0'}">
                                <img src="<c:url value='/Images/${user.image}'/>" alt="${user.userId}"
                                     width="60px"/>
                            </c:if>
                            <c:if test="${user.image == '' || user.image == '0'}">
                                <img src="<c:url value='/assets/images/no-profile-image.jpg'/>" alt="${user.userId}"
                                     width="60px"/>
                            </c:if>
                        </div>
                        <div class="col-md-3 center-middle-txt">
                            <a href="<c:url value="/User?id=${user.userId}"/>">@<c:out
                                    value="${user.userName}"/></a>
                        </div>
                        <div class="col-md-2 center-middle-btn">
                            <c:if test="${sessionScope.userSession.userId != user.userId}">
                                <c:if test="${user.follower == 1}">
                                    <form action="<c:url value="/${response}${idparam}"/>" method="post">
                                        <input type="hidden" name="stopfollow" value="${user.userId}"/>
                                        <input type="submit" class="btn btn-primary" id="${user.userId}"
                                               value="Stop Follow"/>
                                    </form>
                                </c:if>
                                <c:if test="${user.follower == 0}">
                                    <form action="<c:url value="/${response}${idparam}"/>" method="post">
                                        <input type="hidden" name="follow" value="${user.userId}"/>
                                        <input type="submit" class="btn btn-primary" value="Follow"/>
                                    </form>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ol>

</c:if>
