<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<c:if test="${sessionScope.userSession == null}">
    <t:layout-home>
    <jsp:attribute name="header">
      <h1>Page d'erreur</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
        <jsp:body>
            <div class="row block-error block-surround">
                <jsp:include page="_404.jsp"/>
            </div>
        </jsp:body>
    </t:layout-home>
</c:if>
<c:if test="${sessionScope.userSession != null}">
    <t:layout>
    <jsp:attribute name="header">
      <h1>Page d'erreur</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
        <jsp:body>
            <c:out value="${error}"/>
            <div class="row block-error block-surround">
                <jsp:include page="_404.jsp"/>
            </div>
        </jsp:body>
    </t:layout>
</c:if>