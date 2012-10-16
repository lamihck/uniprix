<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <a href='partieadmin.jsp'>Retour menu</a>
    Liste des clients

    <table border="1px;solid;">
        <tr><td>Nom</td><td>Prenom</td><td>Adresse 1</td><td>Adresse 2</td><td>Cp</td><td>Ville</td>
            <td>Pays</td><td>Tel</td><td>Mail</td><td>Login</td><td>Nombre Commande</td><td>Modifier</td><!-- <td>Supprimer</td> -->
            <c:forEach var="listCommandes"  items="${listCommandes}">
            <tr>
                <td><a href='ListeCommande?clientId=<c:out value="${listCommandes.key.clientId}" />'><c:out value="${listCommandes.key.nom}" /></a></td>
                <td><c:out value="${listCommandes.key.prenom}" /></td>
                <td><c:out value="${listCommandes.key.adresse1}" /></td>
                <td><c:out value="${listCommandes.key.adresse2}" /></td>
                <td><c:out value="${listCommandes.key.cp}" /></td>
                <td><c:out value="${listCommandes.key.ville}" /></td>
                <td><c:out value="${listCommandes.key.pays}" /></td>
                <td><c:out value="${listCommandes.key.telephone}" /></td>
                <td><c:out value="${listCommandes.key.mail}" /></td>	
                <td><c:out value="${listCommandes.key.login.login}" /></td>
                <td><a href='ListeCommande?clientId=<c:out value="${listCommandes.key.clientId}" />'><c:out value="${listCommandes.value}" /></a></td>		
                <td><a href='ModifierClient?clientId=<c:out value="${listCommandes.key.clientId}" />'> modifier </a></td>
                <%-- <td><a href='SupprimerClient?clientId=<c:out value="${listCommandes.key.clientId}" />'> supprimer </a></td> --%>
            </tr>
        </c:forEach>
    </table>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>