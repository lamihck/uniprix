<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">

    </div>
    <form>
        <label>Nom</label>			<input name='nomCli' type='text' value='${client.nom}'/><br/>
        <label>Prenom</label>			<input name='prenomCli' type='text' value='${client.prenom}'/><br/>
        <label>Adresse 1</label>	
        <textarea style="resize:none;" cols="60" rows="4" name="adr1">${client.adresse1}</textarea><br/>
        <label>Adresse 2</label>	
        <textarea style="resize:none;" cols="60" rows="4" name="adr2">${client.adresse2}</textarea><br/>
        <label>CP</label>		<input name='cpCli' type='text' value='${client.cp}'/><br/>
        <label>Ville</label>			<input name='villeCli' type='text' value='${client.ville}'/><br/>
        <label>Pays</label>		<input name='paysCli' type='text' value='${client.pays}'/><br/>
        <label>tel</label>			<input name='telCli' type='text' value='${client.telephone}'/><br/>
        <label>mail</label>		<input name='mailCli' type='text' value='${client.mail}'/><br/>
        <input type='hidden' name='idCli' value='${client.clientId}'/>
        <input type='submit'/>
    </form>		
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>