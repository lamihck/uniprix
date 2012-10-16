<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.uniprix.model.Panier"%>
<%@page import="com.uniprix.model.Client" %>
<%@page import="com.uniprix.model.LignePanier"%>
<%@page import="java.lang.*" %>
<%@page import="java.util.List" %>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.uniprix.model.*,java.util.List"%>

<c:if test="${client.login == null}">
    <li><a href='ConnecterServlet' class="nav"> Connection</a></li>
    <li><a href='inscription.jsp' class="nav"> Inscription</a></li>
</c:if>

<c:if test="${client.login != null}">
    <li><a href='GestionCompteServlet' class="nav">Mon compte</a></li>
    <li><a href='Logout' class="nav">Déconnexion</a></li>
</c:if>



