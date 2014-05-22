<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="block-sector block-sector-left">
            <jsp:include page="partial/dashboard.jsp"/>
        </div>
        <div class="block-sector block-sector-body">
            <c:set var="content_title" scope="request" value="Recherche"/>
            <jsp:include page="partial/userList.jsp"/>
        </div>
    </jsp:body>
</t:layout>