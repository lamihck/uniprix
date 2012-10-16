<%@ include file="include/header.jsp"%>

<div id="center_content">

    <div class="title">
        Détail de votre commande ${idcom}
    </div>

    <div class="product_box" style="width:680px;height: 100%;">


        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>

        <table border="1px;solid;">
            <tr><td>Nom article</td><td>Prix Unitaire euro </td><td>Quantité</td><td>Prix total euro </td>

                <c:forEach var="infolignecom"  items="${infolignecom}">
                <tr>

                    <td>${infolignecom.key.article.nom}</td>
                    <td>${infolignecom.key.prix}</td>
                    <td>${infolignecom.key.quantite}</td>
                    <td>${infolignecom.value}</td>
                </tr>			
            </c:forEach>

        </table>
    </div>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>