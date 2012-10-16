<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>
    </head>
    <body>
        <p>Création nouvel Article</p>
        <p>* champs obligatoire</p>
        <form action='CreationArticle'>
            <label>Nom * </label><input name='nomArt' type='text' /><br/>
            <label>Description </label>	
            <textarea style="resize:none;" cols="60" rows="4" name="descArt"></textarea><br/>
            <label>Image * </label>		<input name='imgArt' type='text' /><br/>
            <label>Prix * </label>			<input name='prixArt' type='text' /><br/>

            <label>Produit * </label>

            <SELECT name="Produit">
                <c:forEach var="listproduit" varStatus="status" items="${requestScope['listProduit']}">
                    <OPTION VALUE="${listproduit.produitId}">${listproduit.nom}</OPTION>
                </c:forEach>
            </SELECT><br/>
            <input type='submit'/>
        </form>
    </body>
</html>