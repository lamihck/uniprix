<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <div>Êtes vous sûr de vouloir supprimer ce client ${client.nom} ${client.prenom} ?</div>
        <input type='radio' name='group1' value='Oui' /> OUI 
        <input type='radio' name='group1' value='Non' /> NON 
        <input type='hidden' name='idCli' value='${client.clientId}'/>
        <input type='submit'/>
    </form>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>