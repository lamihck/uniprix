<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        Votre panier
    </div>

    <div class="product_box" style="width:100%;">
        <c:choose>
            <c:when test="${sessionScope.NbArticlePanier==1}" >
                <div style="display: inline;color: red; font: bold;"> 1 Article</div>
            </c:when>

            <c:when test="${sessionScope.NbArticlePanier>1}" > 
                <div style="display: inline;color: red; font: bold;"> ${sessionScope.NbArticlePanier} Articles</div>
            </c:when>

            <c:otherwise>
                <div style="display: inline;color: red; font: bold;"> 0 Article</div>
            </c:otherwise>
        </c:choose>

        <a href='ArticleServlet?articles=${IdProduitSelectionner}' style="top: 70px;display: inline;"> continuer achat </a>

        <% 
//session.invalidate();
Panier panier = (Panier)request.getSession().getAttribute("panier");
if(panier == null)
panier = new Panier();
	
if(panier !=null)
{
List<LignePanier> lignes = panier.getLignepanier();
double total = 0;
out.println("<center style='top: 100px;'>");
out.println("<table border='1'>");
out.println("<tbody><td>Quantité</td><td>Article</td><td>Prix unitaire</td><td>Prix total</td><td>Supprimer</td></tbody>");
for(LignePanier ligne : lignes)
{
out.println(
                "<form action='PanierServlet'>"+
                        "<tr><td>"+
                                "<input type='image' src='Images/Icon/plus.jpg' alt='+' id='plus' onClick='plus("+ligne.getArticle().getArticleId()+",\"submit"+ligne.getArticle().getArticleId()+"\")'>"+
                            "<input onBlur='validate(\"submit"+ligne.getArticle().getArticleId()+"\",\""+ligne.getArticle().getArticleId()+"\")' id='"+ligne.getArticle().getArticleId()+"' type='text' name='quantite' value ='" + ligne.getQuantite() + "'/>"+
                                "<input type='image' src='Images/Icon/moins.jpg' alt='-' id='moins' onClick='moins("+ligne.getArticle().getArticleId()+",\"submit"+ligne.getArticle().getArticleId()+"\")''>"+
                                "<input type='hidden' name='articleId' value = '"+ ligne.getArticle().getArticleId() +"'/>"+
                                "<input type='image' src='Images/Icon/actualiser.png' alt='actualiser' id='submit"+ligne.getArticle().getArticleId()+"' value='update'/>"+
                        "</td><td>"+
                                "<a href='RechercheServlet?articleRechercher="+ligne.getArticle().getNom()+"'>" + ligne.getArticle().getNom() + "</a>"+
                        "</td><td>"+
                        ligne.getArticle().getPrix() + "e / unité"+
                        "</td><td>"+
                        ligne.getArticle().getPrix()*ligne.getQuantite() + "euro" +
                        "</td><td>"+
                        " <input type='image' src='Images/Icon/Del.bmp' alt='(X)' onClick='del("+ligne.getArticle().getArticleId()+",\"submit"+ligne.getArticle().getArticleId()+"\")'>" +
                "</td></tr>"+
                "</form>"
                );
total += ligne.getArticle().getPrix()*ligne.getQuantite();
}
if(total == 0)
{
out.println("<tr><td colspan=5>Le panier est vide.</td></tr>");
}
else if(total > 0)
{
out.println("<tr><td/><td/><td>Total :</td><td>"+total+"euro</td><td><a href='PanierServlet?delAll=true'>(X) all</a></td></tr>");
}
out.println("</table>");
out.println("</center>");
}
	
		
	
	
        %>
        <br/>
        <br/>
        <a href="CommandeServlet">Valider la commande</a>

    </div>

    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>