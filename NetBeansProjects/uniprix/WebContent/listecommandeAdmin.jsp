<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <a href='partieadmin.jsp'>Retour menu</a>
    <a href='ListeClient'>Retour à la liste de client</a>

    Liste des commande du client :${infoClient.nom} ${infoClient.prenom} 

    <table border="1px;solid;">
        <tr><td>Numero Commande</td><td>Adresse de la commande</td><td>Date</td><td>Prix €</td><td>Modifier</td><td>Supprimer</td>

            <c:forEach var="infocommandetot"  items="${infocommandetot}">
            <tr>
                <td><a href='ListeLigneCommande?commandeId=<c:out value="${infocommandetot.key.commandeId}&idClient=${infoClient.clientId}" />'>${infocommandetot.key.commandeId}</a></td>
                <td>${infocommandetot.key.adresse}</td>
                <td>${infocommandetot.key.date}</td>
                <td>${infocommandetot.value}</td>
                <td><a href='ModifieCommande?commandeId=${infocommandetot.key.commandeId}&montant=${infocommandetot.value}&idClient=${infoClient.clientId} '> modifier </a></td>
                <td><a href='SupprimerCommande?commandeId=${infocommandetot.key.commandeId}&idClient=${infoClient.clientId}'> supprimer </a></td>
            </tr>			
        </c:forEach>

    </table>

    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>