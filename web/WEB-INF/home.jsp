<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout-home>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="row block-sector block-surround">
            <div class="col-md-12">
                Bienvenu sur Tweetr
            </div>
        </div>
    </jsp:body>
</t:layout-home>