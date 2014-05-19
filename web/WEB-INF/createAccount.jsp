<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link href="Bootstrap/css/bootstrap.css" rel="stylesheet"
          type="text/css">
    <link href="Bootstrap/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tweetr</title>
</head>
<body>
<div class="container">
    <div class="page-header">EPHEC - Projet de développement
        Intranet - Examen Javee
    </div>
    <div class="row">
        <div id=frame class="span8">
            <form method="post" action="CreateAccount">
                <fieldset>
                    <h3>Join Tweetr today.</h3>

                    <label for="userName">Username <span class="required">*</span></label>
                    <input type="text" id="userName" name="userName" value="<c:out value="${user.userName}"/>" size="20"
                           maxlength="20" required/>
                    <span class="erreur">${form.erreurs['userName']}</span>


                    <label for="firstName">FirstName <span class="required">*</span></label>
                    <input type="text" id="firstName" name="firstName" value="<c:out value="${user.firstName}"/>"
                           size="20" maxlength="20" required/>
                    <span class="erreur">${form.erreurs['firstName']}</span>

                    <label for="lastName">LastName<span class="required">*</span></label>
                    <input type="text" id="lastName" name="lastName" value="<c:out value="${user.lastName}"/>" size="20"
                           maxlength="20" required/>
                    <span class="erreur">${form.erreurs['lastName']}</span>

                    <label for="email">Email <span class="required">*</span></label>
                    <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="60"
                           maxlength="60" required/>
                    <span class="erreur">${form.erreurs['email']}</span>

                    <input type="hidden" name="mail" value=""/>

                    <label for="password">Password <span class="required">*</span></label>
                    <input type="password" id="password" name="password" value="<c:out value="${user.password}"/>"
                           size="20" maxlength="20" required/>
                    <span class="erreur">${form.erreurs['password']}</span>

                    <label for="confirmation">Password confirmation <span class="required">*</span></label>
                    <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20"
                           required/>
                    <span class="erreur">${form.erreurs['confirmation']}</span>

                </fieldset>
                <input type="submit" value="Create my account"/>

                <p class="${empty erreurs ? 'succes' : 'erreur'}">${form.result}</p>
            </form>
        </div>
    </div>
    <div>
        <footer>Copyright Zhor OUKIDA and Mehdi OUELA</footer>
    </div>
</div>
</body>
</html>