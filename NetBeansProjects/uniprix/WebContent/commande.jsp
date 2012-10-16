<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">Récapitulatif</div>

    <div class="product_box" style="width:860px;height:350px;center;">
        <center>
            <%
                    //session.invalidate();
                    Panier panier = (Panier) request.getSession()
                                    .getAttribute("panier");

                    if (panier != null) {
                            List<LignePanier> lignes = panier.getLignepanier();
                            double total = 0;
                            out.println("<table border='1'>");
                            out.println("<tbody><td>Quantité</td><td>Article</td><td>Prix unitaire</td><td>Prix total</td></tbody>");
                            for (LignePanier ligne : lignes) {
                                    out.println("<tr><td>" + ligne.getQuantite() + "</td><td>"
                                                    + "<a href='RechercheServlet?articleRechercher="
                                                    + ligne.getArticle().getNom() + "'>"
                                                    + ligne.getArticle().getNom() + "</a>"
                                                    + "</td><td>" + ligne.getArticle().getPrix()
                                                    + "e / unité" + "</td><td>"
                                                    + ligne.getArticle().getPrix()
                                                    * ligne.getQuantite() + "euro" +

                                                    "</td></tr>");
                                    total += ligne.getArticle().getPrix() * ligne.getQuantite();
                            }
                            if (total == 0) {
                                    out.println("<tr><td colspan=5>Le panier est vide.</td></tr>");
                            } else if (total > 0) {
                                    out.println("<tr><td/><td/><td>Total :</td><td>" + total
                                                    + "euro</td></tr>");
                            }
                            out.println("</table>");
                    }
            %>


            <%-- <jsp:useBean id="client" class="com.uniprix.model.Client" scope="request"/> --%>


            <br/><br/>
            <form>

                <table>

                    <tr><td><label>Nom : </label></td>
                        <td>${client.nom}</td></tr> 

                    <tr><td><label>Prénom : </label></td>
                        <td>${client.prenom}</td></tr>

                    <tr><td><label>Adresse :</label></td>
                        <td><textarea style="resize: none;" name="paiementAdresse">${client.adresse1}
                                ${client.adresse2}</textarea></td></tr>

                    <tr><td><label>Numéro de carte banquaire :</label></td>
                        <td><input type="text" onBlur='notNUll(numcb)' name="numcb" value="" /></td></tr>

                    <tr><td><label>Date de fin de la carte :</label></td>
                        <td><input type="text" onBlur='notNUll(date)' name="date" value="" /></td></tr>

                    <tr><td><label>Numéro de sécurité :</label></td>
                        <td><input type="text" onBlur='notNUll(numsecu)' name="numsecu" value="" /></td></tr>

                </table>
                <input type="hidden" value="${client.nom}" name="paiementNom"/>
                <input type="hidden" value="${client.prenom}" name="paiementPrenom"/>

                <input type="submit" value="Finaliser la commande" />

            </form>
        </center>
        <div class="clear"></div>
    </div>

</div>


<%@ include file="include/footer.jsp"%>

