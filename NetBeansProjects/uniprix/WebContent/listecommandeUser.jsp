<%@ include file="include/header.jsp"%>

<div id="center_content">

    <div class="title">
        Liste de vos commandes
    </div>

    <div class="product_box" style="width:680px;height: 100%;">


        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>
        <table border="1px;solid;">
            <tr><td>Numero Commande</td><td>Adresse de la commande</td><td>Date</td><td>Prix </td>

                <c:forEach var="infocommandetot"  items="${infocommandetot}">
                <tr>
                    <td><a href='LstLigneCommande?commandeId=<c:out value="${infocommandetot.key.commandeId}&idClient=${infoClient.clientId}" />'>${infocommandetot.key.commandeId}</a></td>
                    <td>${infocommandetot.key.adresse}</td>
                    <td>${infocommandetot.key.date}</td>
                    <td>${infocommandetot.value}</td>
                </tr>			
            </c:forEach>

        </table>

    </div>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>