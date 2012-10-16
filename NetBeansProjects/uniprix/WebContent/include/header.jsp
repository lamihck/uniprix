<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>

        <title>Home</title>

        <link rel="stylesheet" type="text/css" href="style.css" />
        <script type="text/javascript" src="unitpngfix.js"></script>


    </head>
    <body>
        <div id="main_container">

            <!-- Header et menu -->
            <div class="header" id="header">

                <div class="logo">
                    <a href="index.html"> <img src="Images/LOGO1.png" width="150"
                                               alt="" title="" border="0">
                    </a>
                </div>

                <%
                    int panierNb = 0;
                    if (request.getSession().getAttribute("panier") != null) {
                        panierNb = ((Panier) request.getSession().getAttribute("panier")).getLignepanier().size();
                    }

                %>

                <div id="menu_tab">
                    <ul class="menu">
                        <li class="divider"></li>
                        <li><a href="index.html" class="nav"> Accueil </a></li>
                        <li><a href="panier.jsp" class="nav"> Panier (<% out.print(panierNb);%>)</a></li>
                        <%@ include file="user.jsp"%>
                    </ul>

                </div>

                <div class="search_tab">
                    <form action="RechercheServlet">
                        <input type="text" class="search" placeholder="Rechercher"
                               name="articleRechercher">
                        <!-- <input type="image" src="images/search.gif" class="search_bt"> -->
                        <input type="submit" value="Go  " class="search_bt">
                    </form>
                </div>
            </div>





            <div id="main_content">

                <div class="left_sidebar">
                    <div class="menu">
                        <%@ include file="../menu_deroulant.jsp"%>
                    </div>
                </div>
