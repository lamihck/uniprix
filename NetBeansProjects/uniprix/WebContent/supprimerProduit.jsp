<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <jsp:useBean id="produit" class="com.uniprix.model.Produit" scope="request"/>
    <% 
	
    if(produit.getProduitId() != 0)
    {
            out.println("<form><div>Êtes vous sûr de vouloir supprimer ce produit " +produit.getNom() + " ?</div>");
            out.println("<input type='radio' name='group1' value='Oui' /> OUI ");
            out.println("<input type='radio' name='group1' value='Non' /> NON ");
            out.println("<input type='hidden' name='idProd' value='"+produit.getProduitId()+"'/>");
            out.println("<input type='hidden' name='idCat' value='"+produit.getCategorie().getCategorieId()+"'/>");
            out.println("<input type='submit'/></form>");
    }
		
    %>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>