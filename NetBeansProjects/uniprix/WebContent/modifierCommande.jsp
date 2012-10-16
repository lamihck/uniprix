<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    Commande numéro ${commande.commandeId}

    <form>
        <label>Adresse : </label>	
        <textarea style="resize:none;" cols="60" rows="4" name="adr">${commande.adresse}</textarea><br/>

        <label>Montant euro : </label>	${montant}<br/>

        <input type='hidden' name='clientId' value='${clientId}'/>
        <input type='hidden' name='idCommande' value='${commande.commandeId}'/>
        <input type='submit'/>
    </form>		
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>