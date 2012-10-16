<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <div>Êtes vous sûr de vouloir supprimer cette commande ${commande.commandeId} ?
            (Cela supprimera les lignes de commande liées)</div>
        <input type='radio' name='group1' value='Oui' /> OUI 
        <input type='radio' name='group1' value='Non' /> NON 
        <input type='hidden' name='clientId' value='${clientId}'/>
        <input type='hidden' name='idCom' value='${commande.commandeId}'/>
        <input type='submit'/>
    </form>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>