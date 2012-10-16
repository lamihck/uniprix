<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        Liste article all
    </div>
    <a href='ListeProduit'>Retour aux produits</a>
    </br>
    <a href='CategoriesServlet?pageRedirect=1'> Nouvelle article </a>
    <table border="1px;solid;">
        <tr><td>Nom</td><td>Description</td><td>Image</td><td>Prix</td><td>Modifier</td><td>Supprimer</td>

            <c:forEach var="listarticle" varStatus="status" items="${requestScope['listarticle']}">
            <tr>
                <td>${listarticle.nom}</td>
                <td>${listarticle.description}</td>
                <td>${listarticle.image}</td>
                <td>${listarticle.prix}</td>
                <td><a href='ModifiePArticle?articleId=${listarticle.prix}'> modifier </a></td>
                <td><a href='SupprimerArticle?articleId=${listarticle.prix}'> supprimer </a></td>
            </tr>			
        </c:forEach>
        <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>