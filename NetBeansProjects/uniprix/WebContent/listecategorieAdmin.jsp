<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>

    <%@page import="java.lang.*" %>
    <%@page import="com.uniprix.model.Categorie" %>

    <jsp:useBean id="listCategorie" type="java.util.List<com.uniprix.model.Categorie>" scope="request"/>
    <!-- <a href="logout">logout</a> -->
    Catégorie
    <a href='partieadmin.jsp'>Retour menu</a>

    <% out.println("<a href='creationCategorie.jsp'> Nouvelle catégorie </a>");%>
    <table border="1px;solid;">
        <tr><td>Nom</td><td>Image</td><td>Modifier</td><td>Supprimer</td>
            <%
            for(Categorie categorie:listCategorie){
            %>
        <tr>
            <td><% out.println("<a href='ListeProduitId?categorieId="+categorie.getCategorieId()+"'>"+categorie.getNom()+"</a>"); %></td>
            <td><%=categorie.getImage() %></td>
            <td><% out.println("<a href='ModifieCategorie?categorieId="+categorie.getCategorieId()+"'> modifier </a>");%></td>
            <td><% out.println("<a href='SupprimerCategorie?categorieId="+categorie.getCategorieId()+"'> supprimer </a>");%></td>
        </tr>
        <%} %>
    </table>

    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>
