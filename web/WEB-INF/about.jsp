<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout-home>
    <jsp:attribute name="header">
      <h1>A propos</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                Système réalisé dans le cadre du cours de : "Projet de développement sous Java" organisé par Mr Degroote
            </div>
        </div>
    </jsp:body>
</t:layout-home>