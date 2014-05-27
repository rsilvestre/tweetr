<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!-- toto -->
<c:if test="${param.id != null && param.id == user.userId}">
    <c:set var="idparam" scope="request" value="?id=${user.userId}"/>
</c:if>

<t:layout>
    <jsp:body>
        <div class="block-sector block-surround">
            <h3>Affichage de vos données personnelles</h3>

            <div class="col-md-8">
                <div class="row">
                    <label class="col-md-4 control-label">Nom d'utilisateur</label>

                    <div class="col-md-4">
                        <c:out value="${user.userName}"/>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-4 control-label">Prénom</label>

                    <div class="col-md-4">
                        <c:out value="${user.firstName}"/>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-4 control-label">Nom</label>

                    <div class="col-md-4">
                        <c:out value="${user.lastName}"/>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-4 control-label">Email</label>

                    <div class="col-md-4">
                        <c:out value="${user.email}"/>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-4 control-label">Email</label>

                    <div class="col-md-4">
                        <c:out value="${user.email}"/>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${sessionScope.userSession.userId != user.userId}">
                        <label class="col-md-4 control-label">Follow : </label>

                        <div class="col-md-4 center-middle-btn">
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
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row">
                    <div class="col-md-4">
                        <c:if test="${user.image != '' && user.image != '0'}">
                            <img src="<c:url value='/Images/${user.image}'/>" alt="${user.image}" width="180px"/>
                        </c:if>
                        <c:if test="${user.image == '' || user.image == '0'}">
                            <img src="<c:url value='/assets/images/no-profile-image.jpg'/>" alt="${user.image}"
                                 width="180px"/>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>

        <div class="footer-space"></div>

        <div class="block-sector block-sector-left">
            <jsp:include page="partial/dashboard.jsp"/>
        </div>
        <div class="block-sector block-sector-body">
            <jsp:include page="partial/tweetList.jsp"/>
        </div>
    </jsp:body>
</t:layout>
