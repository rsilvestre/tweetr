<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="Bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="Bootstrap/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tweetr</title>
</head>
<body>
<c:forEach var="cookieVal" items="${cookie}">
    <c:if test="${cookieVal.key == 'username'}"> <c:set var="usernameCookie" value="${cookieVal.value.value}"/> </c:if>
</c:forEach>

<div class="container">
    <div class="page-header">EPHEC - Projet de développement Intranet - Examen Javee</div>
    <div class="row">
        <div id=frame class="span6">
            <h2>Welcome to Tweetr</h2>

            <p>Start a conversation, explore your interests, and be in the know.</p>
        </div>
        <div id=frame class="span6">
            <form method="post" action="Login">
                <fieldset>
                    <h3>Login Information</h3>

                    <label for="userName">Username <span class="required">*</span></label>
                    <input type="text" id="userName" name="userName" value="${usernameCookie}" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['userName']}</span>

                    <label for="password">Password <span class="required">*</span></label>
                    <input type="password" id="password" name="password" value="" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['password']}</span>

                </fieldset>
                <input type="submit" value="Login"/>

                <br/>
                <br/>

                <h3>New to Tweetr?</h3>

                <p><a href="<c:url value="/CreateAccount"/>">Create account</a></p>
            </form>
        </div>
    </div>
    <div>
        <footer>Copyright Zhor OUKIDA and Mehdi OUELA</footer>
    </div>
</div>
</body>
</html>