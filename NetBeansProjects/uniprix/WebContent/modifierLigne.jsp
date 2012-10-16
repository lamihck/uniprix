<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <label>Nom de l'article</label>		${lignecommande.article.nom}<br/>
        <label>Prix unitaire euro</label>		<input name='prixU' type='text' value='${lignecommande.prix}'/><br/>
        <label>Quantité</label>			<input name='qtt' type='text' value='${lignecommande.quantite}'/><br/>

        <input type='hidden' name='idClient' value='${idClient}'/>
        <input type='hidden' name='commandeId' value='${idcommande}'/>
        <input type='hidden' name='idligne' value='${lignecommande.ligneCommandeId}'/>

        <input type='submit'/>
    </form>		
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>