<%@ include file="include/header.jsp"%>
<div id="center_content">

    <div class="title">Inscription</div>

    <div class="product_box" style="width:860px;height:510px;center;">
        <!-- <center> -->


        <jsp:useBean id="clients" class="com.uniprix.model.Client" scope="request"/>
        <jsp:useBean id="login" class="com.uniprix.model.Login" scope="request"/>

        <% 
                String champNom=null;
                String champPrenom=null;
                String champMail=null;
                String champAdr1=null;
                String champAdr2=null;
                String champVille=null;
                String champPays=null;
                String champCp=null;
                String champTel=null;
                String champLogin=null;
                String champPass1=null;
                String champPass2=null;
                champNom=clients.getNom();
                champPrenom=clients.getPrenom();
                champMail=clients.getMail();
                champAdr1=clients.getAdresse1();
                champAdr2=clients.getAdresse2();
                champVille=clients.getVille();
                champPays=clients.getPays();
                champCp=clients.getCp();
                champTel=clients.getTelephone();
                champLogin=login.getLogin();
        %>

        <table border="0"> 

            <form action="inscription" method="Get">
                * Champs obligatoires
                <br>
                <td>
                    <%if(request.getAttribute("errorNull") !=null){%>
                    <%=(String)request.getAttribute("errorNull") %>
                    <%} %>
                </td>
                <tr> <td align="right"> nom * : 	</td>
                    <td>
                        <% 
                        if(champNom!=null && champNom!=""){
                        %> 
                        <input value=<%=champNom %> onBlur='notNUll2(nom)' type="text" name="nom" size="40" />
                        <% }else{ %><input  onBlur='notNUll2(nom)' type="text" name="nom" size="40" />
                        <%} %>

                        <%if(request.getAttribute("errorClient") !=null){%>
                        <%=(String)request.getAttribute("errorClient") %>
                        <%} %>
                    </td> </tr>

                <tr> <td align="right"> prenom * : 	</td>
                    <td>
                        <%if(champPrenom!=null && champPrenom!=""){ %>
                        <input value=<%=champPrenom %> onBlur='notNUll2(prenom)' type="text" name="prenom" size="40" />
                        <% }else{ %><input onBlur='notNUll2(prenom)' type="text" name="prenom" size="40" /><%} %>
                    </td> </tr>

                <tr> <td align="right"> mail * : 	</td> 
                    <td>
                        <%if(champMail!=null && champMail!=""){ 
			
                        %>
                        <input value=<%=champMail %> onBlur='verifmail(mail.value);notNUll2(mail);' type="text" name="mail" size="40" />
                        <% }else{ %>
                        <input onBlur='verifmail(mail.value);notNUll2(mail);' type="text" name="mail" size="40" />
                        <%} %>
                    </td> </tr>

                <tr> <td align="right"> Adresse * :</td>
                    <td>
                        <%if(champAdr1!=null && champAdr1!=""){ %>
                        <textarea onBlur='notNUll2(adresse1)' style="resize:none;" cols="60" rows="4" name="adresse1"><%=champAdr1.trim() %></textarea>
                        <% }else{ %><textarea onBlur='notNUll2(adresse1)' style="resize:none;" cols="60" rows="4" name="adresse1"></textarea><%} %>
                    </td> </tr>

                <tr> <td align="right"> Complément d'adresse :</td>
                    <td>
                        <%if(champAdr2!=null && champAdr2!=""){ %>
                        <textarea  style="resize:none;" cols="60" rows="4" name="adresse2"><%=champAdr2.trim() %></textarea>
                        <% }else{ %><textarea style="resize:none;" cols="60" rows="4" name="adresse2"></textarea><%} %>
                    </td> </tr>

                <tr> <td align="right"> ville * :</td>
                    <td>
                        <%if(champVille!=null && champVille!=""){ %>
                        <input value=<%=champVille %>  onBlur='notNUll2(ville)'  type="text" name="ville" size="40" />
                        <% }else{ %><input onBlur='notNUll2(ville)'  type="text" name="ville" size="40" /><%} %>
                    </td> </tr>

                <tr> <td align="right"> pays * :</td>
                    <td>
                        <%if(champPays!=null && champPays!=""){ %>
                        <input value=<%=champPays %> onBlur='notNUll2(pays)' type="text" name="pays" size="40" />
                        <% }else{ %> <input onBlur='notNUll2(pays)' type="text" name="pays" size="40" /><%} %>
                    </td> </tr>

                <tr> <td align="right"> code postal * :</td>
                    <td>
                        <%if(champCp!=null && champCp!=""){ %>
                        <input value=<%=champCp %> onBlur='notNUll2(cp)' type="text" name="cp" size="40" />
                        <% }else{ %><input onBlur='notNUll2(cp)' type="text" name="cp" size="40" /><%} %>
                    </td> </tr>

                <tr> <td align="right"> tel  :</td>
                    <td> 
                        <%if(champTel!=null && champTel.length()!=0 && champTel!=""){ %>
                        <input value=<%=champTel %>  type="text" name="tel" size="40" /> 
                        <% }else{%><input  type="text" name="tel" size="40" /> <%} %>
                    </td> </tr>

                <tr> <td align="right"> login * :</td>
                    <td>
                        <%if(champLogin!=null && champLogin!=""){ %>
                        <input value=<%=champLogin %>  onBlur='notNUll2(login)' type="text" name="login" size="40" />
                        <% }else{ %><input onBlur='notNUll2(login)' type="text" name="login" size="40" /><%} %>
                        <%if(request.getAttribute("errorLogin") !=null){%>
                        <%=(String)request.getAttribute("errorLogin") %>
                        <%} %>
                    </td> </tr>

                <tr> <td align="right"> mot de passe * :</td>
                    <td>
                        <input onBlur='notNUll2(pass1)' type="password" name="pass1" size="40" />
                        <%if(request.getAttribute("errorPass") !=null){%>
                        <%=(String)request.getAttribute("errorPass") %>
                        <%} %>
                    </td> </tr>

                <tr> <td align="right"> vérification mot de passe * :</td> 
                    <td>
                        <input onBlur='notNUll2(pass2)' type="password" name="pass2" size="40" />
                    </td> </tr>

                <tr> <td colspan="2" align="right"> <input type="submit" name="Inscription" value="Inscription" /></td> </tr>
            </form>
        </table>

        <div class="clear"></div>

        <!-- </center> -->
    </div>

</div>


<%@ include file="include/footer.jsp"%>

