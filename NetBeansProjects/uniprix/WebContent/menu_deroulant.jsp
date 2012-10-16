<html>

    <head>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.uniprix.model.*,java.util.List"%>

        <script type="text/javascript" src="JS/menu_deroulant.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/menu_deroulant.css">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


        <title>Menu deroulant vertical</title>
    </head>
    <body onload="montrer()">

        <dl id="menu">

            <c:forEach var="list" varStatus="status" items="${requestScope['listCategorieProduit']}">

                <%-- <dt onclick="javascript:montrer('sousmenu${status.count}');">${list.categorie.nom}</dt> --%>

                <dt onclick="javascript:montrer('sousmenu${status.count}');">
                <a href="#"><img src="Images/Categories/${list.categorie.image}" alt="${list.categorie.nom}" style="float:left;font-size:20px;" width="40" height="40"/><div style="float:left;font-size:16px;">${list.categorie.nom}</div></a></dt>

                <dd id="sousmenu${status.count}">

                    <%-- <c:out value="${status.count}"></c:out> --%>

                    <ul>
                        <c:forEach var="prod" items="${list.listProduit}">
                            <li><a href="ArticleServlet?articles=${prod.produitId}">${prod.nom} ${count}</a></li>
                        </c:forEach>
                    </ul>
                </dd>

            </c:forEach>
        </dl>

    </body>
</html>