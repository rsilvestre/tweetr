<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<c:forEach var="cookieVal" items="${cookie}">
    <c:if test="${cookieVal.key == 'username'}"> <c:set var="usernameCookie" value="${cookieVal.value.value}"/> </c:if>
</c:forEach>

<t:layout-home>
    <jsp:attribute name="header">
      <h1>Connection à Tweetr</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">

                <h3>Création d'un nouveau compte</h3>

                <p><a href="<c:url value="/CreateAccount"/>">Nouveau compte</a></p>

                <form method="post" action="Login" class="form-horizontal">
                    <h3>Connection</h3>

                    <div class="form-group">
                        <label for="userName" class="col-md-2">Nom d'utilisateur<span class="required">*</span></label>

                        <div class="col-md-4">
                            <input type="text" id="userName" name="userName" value="${usernameCookie}" size="20"
                                   maxlength="20" placeholder="Nom d'utilisateur"/>
                        </div>
                        <div class="col-md-3">
                            <span class="erreur">${form.erreurs['userName']}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2">Mot de passe <span class="required">*</span></label>

                        <div class="col-md-4">
                            <input type="password" id="password" name="password" value="" size="20" maxlength="20"
                                   placeholder="Mot de passe"/>
                        </div>
                        <div class="col-md-3">
                            <span class="erreur">${form.erreurs['password']}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-4">
                            <input type="submit" class="btn btn-primary" value="Login"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout-home>