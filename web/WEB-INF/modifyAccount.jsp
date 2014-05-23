<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout>
    <jsp:body>
        <div class="col-md-10 block-sector block-surround">
            <form method="post" action="ModifyAccount" class="form-horizontal" role="form">
                <h3>Modifier vos informations</h3>

                <div class="form-group">
                    <label for="userName" class="col-md-3 control-label">Nom d'utilisateur</label>

                    <div class="col-md-5">
                        <input type="text" class="form-control" id="userName" name="userName"
                               value="<c:out value="${sessionScope.userSession.userName}"/>" size="20" maxlength="20"
                               placeholder="Nom d'utilisateur"
                               required/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['userName']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-md-3 control-label">Prénom</label>

                    <div class="col-md-5">
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               value="<c:out value="${sessionScope.userSession.firstName}"/>" size="20" maxlength="20"
                               placeholder="Prénom"
                               required/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['firstName']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-md-3 control-label">Nom</label>

                    <div class="col-md-5">
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               value="<c:out value="${sessionScope.userSession.lastName}"/>" size="20" maxlength="20"
                               placeholder="Nom"
                               required/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['lastName']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-md-3 control-label">Email</label>

                    <div class="col-md-5">
                        <input type="email" class="form-control" id="email" name="email"
                               value="<c:out value="${sessionScope.userSession.email}"/>" size="60" maxlength="60"
                               placeholder="Email"
                               required/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['email']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="oldpassword" class="col-md-3 control-label">Ancien mot de passe</label>

                    <div class="col-md-5">
                        <input type="password" class="form-control" id="oldpassword" name="oldpassword"
                               value="<c:out value=""/>" size="20"
                               placeholder="Ancien mot de passe"
                               maxlength="20"/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['oldpassword']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-3 control-label">Nouveau mot de passe</label>

                    <div class="col-md-5">
                        <input type="password" class="form-control" id="password" name="password"
                               value="<c:out value=""/>"
                               size="20"
                               placeholder="Nouveau mot de passe"
                               maxlength="20"/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['password']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmation" class="col-md-3 control-label">Confirmation Mot de passe</label>

                    <div class="col-md-5">
                        <input type="password" class="form-control" id="confirmation" name="confirmation" value=""
                               size="20"
                               placeholder="Confirmation Mot de passe"
                               maxlength="20"/>
                    </div>
                    <div class="col-md-3">
                        <span class="erreur">${form.erreurs['confirmation']}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-4">
                        <input type="submit" class="btn btn-primary" value="Modifier"/>
                    </div>
                </div>
            </form>
            <form method="post" action="ModifyImage" enctype="multipart/form-data" class="form-horizontal"
                  role="form">
                <h3>Modifier votre photos de profil</h3>

                <div class="form-group">
                    <div class="col-md-3">
                        <c:if test="${sessionScope.userSession.image != '' && sessionScope.userSession.image != '0'}">
                            <img src="<c:url value='/Images/${sessionScope.userSession.image}'/>"
                                 alt="${sessionScope.userSession.userId}" width="80px"/>
                        </c:if>
                        <c:if test="${sessionScope.userSession.image == '' || sessionScope.userSession.image == '0'}">
                            <img src="<c:url value='/assets/images/no-profile-image.jpg'/>"
                                 alt="${sessionScope.userSession.userId}"
                                 width="80px"/>
                        </c:if>
                    </div>

                    <div class="col-md-4">
                        <input type="file" class="form-control" name="image" accept="image/*">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-4">
                        <input type="submit" class="btn btn-primary" value="Modifier!"/>
                    </div>
                </div>

            </form>

            <p><a href="DeleteAccount">Supprimer votre profil</a></p>

        </div>
    </jsp:body>
</t:layout>
