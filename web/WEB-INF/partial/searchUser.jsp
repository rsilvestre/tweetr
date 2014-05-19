<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Rechercher un utilisateur</h2>

<form method="post" action="FollowUnFollow" class="form-inline" role="form">
    <div class="form-group">
        <input type="text" class="form-control" id="keyword" name="keyword" value=""
               placeholder="Nom d'utilisateur"/>
        <input type="submit" class="btn btn-primary" value="Rechercher">
    </div>
</form>