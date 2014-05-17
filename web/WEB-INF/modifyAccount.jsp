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
    <div class="page-header">EPHEC - Projet de développement Intranet - Examen Javee</div>
    <div class="row" style="background-color: blue;">
        <div id="frame1" class="span12">
            <h3><a href="HomePage">HOME</a></h3>
        </div>
    </div>
    <div class="row">
        <div id=frame class="span8">
            <form method="post" action="ModifyAccountInfo">
                <fieldset>
                    <h3>Modify Account Info</h3>

                    <label for="userName">Username</label>
                    <input type="text" id="userName" name="userName"
                           value="<c:out value="${sessionScope.userSession.userName}"/>" size="20" maxlength="20"
                           required/>
                    <span class="erreur">${form.erreurs['userName']}</span>


                    <label for="firstName">FirstName </label>
                    <input type="text" id="firstName" name="firstName"
                           value="<c:out value="${sessionScope.userSession.firstName}"/>" size="20" maxlength="20"
                           required/>
                    <span class="erreur">${form.erreurs['firstName']}</span>

                    <label for="lastName">LastName</label>
                    <input type="text" id="lastName" name="lastName"
                           value="<c:out value="${sessionScope.userSession.lastName}"/>" size="20" maxlength="20"
                           required/>
                    <span class="erreur">${form.erreurs['lastName']}</span>

                    <label for="email">Email</label>
                    <input type="email" id="email" name="email"
                           value="<c:out value="${sessionScope.userSession.email}"/>" size="60" maxlength="60"
                           required/>
                    <span class="erreur">${form.erreurs['email']}</span>

                    <label for="password">Old Password </label>
                    <input type="password" id="password" name="password" value="<c:out value=""/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['password']}</span>

                    <label for="password">New Password </label>
                    <input type="password" id="password" name="password" value="<c:out value=""/>" size="20"
                           maxlength="20"/>
                    <span class="erreur">${form.erreurs['password']}</span>

                    <label for="confirmation">New Password confirmation</label>
                    <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20"/>
                    <span class="erreur">${form.erreurs['confirmation']}</span>

                    <input type="submit" value="Modify Account"/>
                </fieldset>
            </form>
            <form method="post" action="ModifyAccountImage" enctype="multipart/form-data">
                <h3>Modify Account Info</h3>
                <img src="<c:url value='/Images/${tweet.uorig}'/>" alt="${tweet.uorig}" height="60px" width="60px"/>
                <input type="file" name="image" accept="image/*">


                <input type="submit" value="Modify Account"/>

            </form>

            <h5><a href="DeleteAccount">Delete Account</a></h5>

        </div>
    </div>
    <div>
        <footer>Copyright Zhor OUKIDA and Mehdi OUELA</footer>
    </div>
</div>
</body>
</html>