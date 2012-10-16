<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.uniprix.model.Panier"%>
<%@page import="com.uniprix.model.LignePanier"%>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <html>
        <head>
            <style>

                table {
                    border: medium solid #6495ed;
                    border-collapse: collapse;
                    width: 50%;
                }
                th {
                    font-family: monospace;
                    border: thin solid #6495ed;
                    width: 50%;
                    padding: 5px;
                    background-color: #D0E3FA;
                    background-image: url(sky.jpg);
                }
                td {
                    font-family: sans-serif;
                    border: thin solid #6495ed;
                    width: 50%;
                    padding: 5px;
                    text-align: center;
                    background-color: #ffffff;
                }
                caption {
                    font-family: sans-serif;
                }
            </style>

            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Commande</title>
        </head>
        <body>
            <h1>Récapitulatif</h1>
            <% 
                    //session.invalidate();
                    out.println("Session :");
                    Panier panier = (Panier)request.getSession().getAttribute("panier");
	
                    if(panier !=null)
                    {
                            List<LignePanier> lignes = panier.getLignepanier();
                                    double total = 0;
                                    out.println("<table border='1'>");
                                    out.println("<tbody><td>Quantité</td><td>Article</td><td>Description</td><td>Prix unitaire</td><td>Prix total</td><td>Supprimer</td></tbody>");
                                    for(LignePanier ligne : lignes)
                                    {
                                            out.println(
                                                            "<form action='PanierServlet'>"+
                                                                    "<tr><td>"+
                                                                            "<span id='plus'><a href='#' onClick='plus("+ligne.getArticle().getArticleId()+",\"submit"+ligne.getArticle().getArticleId()+"\")'>(+) </a></span>"+
                                                                                    "<input onBlur='validate(\"submit"+ligne.getArticle().getArticleId()+"\",\""+ligne.getArticle().getArticleId()+"\")' id='"+ligne.getArticle().getArticleId()+"' type='text' name='quantite' value ='" + ligne.getQuantite() + "'/>"+
                                                                            "<span id='moins'><a href='#' onClick='moins("+ligne.getArticle().getArticleId()+",\"submit"+ligne.getArticle().getArticleId()+"\")''> (-)</a></span>"+
                                                                            "<input type='hidden' name='articleId' value = '"+ ligne.getArticle().getArticleId() +"'/>"+
                                                                            "<input id='submit"+ligne.getArticle().getArticleId()+"' type='submit' value='update'/>"+
                                                                    "</td><td>"+
                                                                            "<a href='truc.jsp?id="+ligne.getArticle().getArticleId()+"'>" + ligne.getArticle().getNom() + "</a>"+
                                                                    "</td><td>"+
                                                                            ligne.getArticle().getDescription() + "</a>"+
                                                                    "</td><td>"+
                                                                    ligne.getArticle().getPrix() + "e / unité"+
                                                                    "</td><td>"+
                                                                    ligne.getArticle().getPrix()*ligne.getQuantite() + "euro" +
							
                                                            "</td></tr>"+
                                                            "</form>"
                                                            );
                                            total += ligne.getArticle().getPrix()*ligne.getQuantite();
                                    }
                                    if(total == 0)
                                    {
                                            out.println("<tr><td colspan=5>Le panier est vide.</td></tr>");
                                    }
                                    else if(total > 0)
                                    {
                                            out.println("<tr><td/><td/><td/><td>Total :</td><td>"+total+"euro</td><td><a href='PanierServlet?delAll=true'>(X) all</a></td></tr>");
                                    }
                                    out.println("</table>");
                    }
	
		
	
	
            %>
            <a href="CommandeServlet">Payer</a>
        </body>
    </html>