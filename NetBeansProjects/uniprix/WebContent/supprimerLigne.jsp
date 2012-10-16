<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <div>Êtes vous sûr de vouloir supprimer cette lignecommande ${lignecom.ligneCommandeId} ?</div>
        <input type='radio' name='group1' value='Oui' /> OUI 
        <input type='radio' name='group1' value='Non' /> NON 
        <input type='hidden' name='idClient' value='${clientId}'/>
        <input type='hidden' name='commandeId' value='${idcommande}'/>
        <input type='hidden' name='ligneId' value='${lignecom.ligneCommandeId}'/>
        <input type='submit'/>
    </form>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>