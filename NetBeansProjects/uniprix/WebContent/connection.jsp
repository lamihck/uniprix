<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">
        Connexion
    </div>



    <form method="POST" action="j_security_check">
        <table>
            <tr>
                <th align="right">Nom d'utilisateur :</th>
                <td><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <th align="right">Mot de passe :</th>
                <td><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <td align="right"><input type="submit" value="Valider">
                </td>
                <td><input type="reset" value="Annuler"></td>
            </tr>
        </table>
    </form>

    <div class="clear"></div>

</div>
<%@ include file="include/footer.jsp"%>
