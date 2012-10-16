<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <jsp:useBean id="categorie" class="com.uniprix.model.Categorie" scope="request"/>
    <% 
	
    if(categorie.getCategorieId() != 0)
    {
            out.println("<form><div>Êtes vous sûr de vouloir supprimer sauvagement la pauvre catégorie " +categorie.getNom() + " ?</div>");
            out.println("<input type='radio' name='group1' value='Oui' /> OUI ");
            out.println("<input type='radio' name='group1' value='Non' /> NON ");
            out.println("<input type='hidden' name='idCat' value='"+categorie.getCategorieId()+"'/>");
            out.println("<input type='submit'/></form>");
    }
		
    %>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>