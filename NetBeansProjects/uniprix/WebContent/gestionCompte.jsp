<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        Modifier votre compte
    </div>

    <div class="product_box" style="width:680px;">


        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>



        <div class="commande" style="float:right; margin-right:10px;text-align:right;">
            <div style="float:none;margin-right:50px;">Listes des commandes</div> 
            <a href="ListeCommandeUser?clientId=${client.clientId}"><IMG SRC="Images/LOGO2.png" WIDTH=250 HEIGHT=250 /></a>


        </div>


        <form>

            <table>
                <tr><td><label>Nom :	</label></td><td><input type="text" name="nomCli" value="${client.nom}"/></td></tr>
                <tr><td><label>Prénom :	</label></td><td><input type="text" name="prenomCli" value="${client.prenom}"/></td></tr>
                <tr><td><label>Adresse :</label></td><td><input type="text" name="adr1" value="${client.adresse1}"/></td></tr>
                <tr><td><label>         </label></td><td><input type="text" name="adr2" value="${client.adresse2}"/></td></tr>
                <tr><td><label>Ville :	</label></td><td><input type="text" name="villeCli" value="${client.ville}"/></td></tr>
                <tr><td><label>Code Postal :</label></td><td><input type="text" name="cpCli" value="${client.cp}"/></td></tr>
                <tr><td><label>Pays :	</label></td><td><input type="text" name="paysCli" value="${client.pays}"/></td></tr>
                <tr><td><label>Téléphone :</label></td><td><input type="text" name="telCli" value="${client.telephone}"/></td></tr>
            </table>	
            <input type="submit" value="Valider les changements"/>
        </form>

    </div>
    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>