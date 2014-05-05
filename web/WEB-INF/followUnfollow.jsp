<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link href="Bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="Bootstrap/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweetr</title>
</head>
<body>
	<div class="container">
		<div class="page-header">EPHEC - Projet de développement
			Intranet - Examen Javee</div>
			<div class="row" style="background-color: blue;">
			<div id="frame1" class="span12">
				<h3><a href="HomePage">HOME</a></h3>
			</div>
		</div>
		<div class="row">
			<div id=frame class="span4">

				<jsp:include page="sendTweet.jsp" />

			</div>

			<div id=frame class="span4">
				<jsp:include page="searchUser.jsp" />
			</div>

			<div id=frame class="span4">
				<jsp:include page="userList.jsp" />
			</div>

		</div>
		<div>
			<footer>Copyright Zhor OUKIDA and Mehdi OUELA</footer>
		</div>
	</div>
</body>
</html>