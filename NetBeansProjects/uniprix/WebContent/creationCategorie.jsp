<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <p>Création nouvelle catégorie</p>
        <p>* champs obligatoire</p>
        <form action='CreationCategorie'>
            <label>Nom * </label><input name='nomCat' type='text' /><br/>
            <label>Image * </label><input name='imageCat' type='text' /><br/>
            <input type='submit'/>
        </form>
    </body>
</html>