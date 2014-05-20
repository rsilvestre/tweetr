<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout-home>
    <jsp:attribute name="header">
      <h1>Profil</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="col-md-10 block-sector">
            <form method="post" action="CreateAccount" class="form-horizontal" role="form">
                <h3>Créer un compte utilisateur</h3>

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
                        <input type="submit" class="btn btn-primary" value="Créer"/>
                    </div>
                </div>
            </form>

        </div>
    </jsp:body>
</t:layout-home>