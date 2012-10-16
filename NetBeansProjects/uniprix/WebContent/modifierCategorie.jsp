<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <jsp:useBean id="categorie" class="com.uniprix.model.Categorie" scope="request"/>
    <% 
	
    if(categorie.getCategorieId() != 0)
    {
            out.println("<form><label>Nom</label><input name='nomCat' type='text' value='"+categorie.getNom()+"'/><br/>");
            out.println("<label>Image</label><input name='imageCat' type='text' value='"+categorie.getImage()+"'/><br/>");
            out.println("<input type='hidden' name='idCat' value='"+categorie.getCategorieId()+"'/>");
            out.println("<input type='submit'/></form>");
    }
		
    %>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>