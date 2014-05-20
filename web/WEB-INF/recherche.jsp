<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Recherche</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p class="muted credit">
          Ephec 2014, <a href="http://michaelsilvestre.be">Michaël Silvestre</a>.
      </p>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="block-sector block-sector-left">
                        <jsp:include page="partial/sendTweet.jsp"/>
                    </div>
                    <div class="col-md-8">
                        <div class="block-sector block-sector-body">
                            <jsp:include page="partial/userList.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>