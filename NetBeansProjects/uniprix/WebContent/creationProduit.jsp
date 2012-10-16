<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Création Produit</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>
    </head>
    <body>

        <p>Création nouveau Produit</p>
        <p>* champs obligatoire</p>
        <form action='CreationProduit'>
            <label>Nom * </label><input name='nomProd' type='text' /><br/>
            <label>Categorie * </label>

            <SELECT name="Categorie">
                <c:forEach var="listcategorie" varStatus="status" items="${requestScope['listCategorieProduit']}">
                    <OPTION VALUE="${listcategorie.categorie.categorieId}">${listcategorie.categorie.nom}</OPTION>
                </c:forEach>
            </SELECT><br/>
            <input type='submit'/>
        </form>
    </body>
</html>