<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        Liste des articles
    </div>

    <a href='ListeArticleAll'>Tous les articles</a>
    </br>
    <a href='ListeProduitId?categorieId=${idCat}'>Retour aux produits</a>
    </br>
    <a href='ListeProduit?pageRedirect=1'> Nouvelle article </a>
    <table border="1px;solid;">
        <tr><td>Nom</td><td>Description</td><td>Image</td><td>Prix</td><td>Modifier</td><td>Supprimer</td>

            <c:forEach var="listarticle" varStatus="status" items="${requestScope['listArticle']}">
            <tr>
                <td>${listarticle.nom}</td>
                <td>${listarticle.description}</td>
                <td>${listarticle.image}</td>
                <td>${listarticle.prix}</td>
                <td><a href='ModifieArticle?articleId=${listarticle.articleId}&idproduit=${idprod}&idCat=${idCat}'> modifier </a></td>
                <td><a href='SupprimerArticle?articleId=${listarticle.articleId}&idproduit=${idprod}&idCat=${idCat}'> supprimer </a></td>
            </tr>			
        </c:forEach>

    </table>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>