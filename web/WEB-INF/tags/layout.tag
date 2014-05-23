<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/assets/js/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/assets/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/assets/js/app.js"/>"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tweetr</title>
</head>
<body>

<div id="wrap">
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/"/>">Tweetr</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/"/>">Home</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profil <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/ShowAccount"/>">Afficher</a></li>
                            <li><a href="<c:url value="/ModifyAccount"/>">Modifier</a></li>
                            <li class="divider"></li>
                        </ul>
                    </li>
                </ul>
                <form method="post" action="/Search" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                        <input type="text" class="form-control" id="keyword" name="keyword"
                               placeholder="Rechercher utilisateur">
                    </div>
                    <button type="submit" class="btn btn-default">Rechercher</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <c:if test="${sessionScope.userSession == null}">
                            <a href="<c:url value="/Login"/>">Connexion</a>
                        </c:if>

                        <c:if test="${sessionScope.userSession != null}">
                            <a href="<c:url value="/Logout"/>">Déconnection</a>
                        </c:if>
                    </li>
                    <li><a href="<c:url value="/About"/>">A propos</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <div class="container">
        <c:if test="${error != null}">
            <p class="bg-danger show-error">
                <c:out value="${error}"/>
            </p>
        </c:if>
        <div id="pageheader">
        </div>
        <jsp:doBody/>
    </div>
    <div class="footer-space"></div>
</div>
<div id="footer">
    <div class="container">
        <p class="muted credit">
            Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
        </p>
    </div>
</div>
</body>
</html>
