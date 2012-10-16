<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>

    <%@page import="java.lang.*" %>
    <%@page import="com.uniprix.model.Produit" %>

    <jsp:useBean id="listProduitId" type="java.util.List<com.uniprix.model.Produit>" scope="request"/>
    Produits

    <% out.println("<a href='ListeProduit'>Tous les produits</a>"); %>
    </br>
    <% out.println("<a href='ListeCategorie'>Retour aux catégories</a>"); %>
    </br>
    <%
out.println("<a href='CategoriesServlet?pageRedirect=1'> Nouveau produit </a>");%>
    <table border="1px;solid;">
        <tr><td>Nom</td><td>Modifier</td><td>Supprimer</td>
            <%
            out.println("<input type='hidden' name='idCat' value='"+request.getParameter("categorieId")+"'/>");

            for(Produit produit:listProduitId){
            %>
        <tr>
            <td><% out.println("<a href='ListeArticle?produitId="+produit.getProduitId()+"&idCat="+produit.getCategorie().getCategorieId()+"'>"+produit.getNom()+"</a>"); %></td>
            <td><% out.println("<a href='ModifieProduit?produitId="+produit.getProduitId()+"'> modifier </a>");%></td>
            <td><% out.println("<a href='SupprimerProduit?produitId="+produit.getProduitId()+"'> supprimer </a>");%></td>

        </tr>
        <%} %>
    </table>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>