<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Détail de la commande</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>
    </head>
    <body>
        <a href='partieadmin.jsp'>Retour menu</a><br>
        <a href='ListeClient'>Retour à la liste de client</a><br>
        <a href='ListeCommande?clientId=${client.clientId}'>Retour à la liste des commande du client ${client.nom} ${client.prenom}</a>
        <br>
        <table border="1px;solid;">
            <tr><td>Article numéro</td><td>Nom article</td><td>prix Unitaire € </td><td>Quantité</td><td>Prix total € </td><td>Modifier</td><td>Supprimer</td>

                <c:forEach var="infolignecom"  items="${infolignecom}">
                <tr>
                    <td>${infolignecom.key.article.articleId}</td>
                    <td>${infolignecom.key.article.nom}</td>
                    <td>${infolignecom.key.prix}</td>
                    <td>${infolignecom.key.quantite}</td>
                    <td>${infolignecom.value}</td>
                    <td><a href='ModifieLigneCommande?lignecommandeId=${infolignecom.key.ligneCommandeId}&clientId=${client.clientId}&commandeId=${idcom}'> modifier </a></td>
                    <td><a href='SupprLigneCommande?lignecommandeId=${infolignecom.key.ligneCommandeId}&clientId=${client.clientId}&commandeId=${idcom}'> supprimer </a></td>
                </tr>			
            </c:forEach>

        </table>
    </body>
</html>