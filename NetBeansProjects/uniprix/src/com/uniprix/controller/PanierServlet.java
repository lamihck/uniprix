package com.uniprix.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniprix.model.Article;
import com.uniprix.model.Panier;
import com.uniprix.dao.hibernate.ArticleDAOImpl;

/**
 * Servlet implementation class PanierServlet
 */
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int quantite = 0;
		int articleId = 0;
		Panier panier = null;
		
		//System.out.println(request.getParameter("delAll"));
		
		if(request.getParameter("delAll") != null && request.getParameter("delAll").equals("true"))
		{
			request.getSession().invalidate();
		}
		
		//System.out.println("PanierServlet : Début du doPost");
		
		if (request.getParameter("articleId") != null)
		{
			try {
				articleId = Integer.parseInt(request.getParameter("articleId"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("!!! L'id n'est pas un entier !");
			}
		}
		
		
		if (request.getParameter("quantite") != null)
		{
			try {
				quantite = Integer.parseInt(request.getParameter("quantite")
						.toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("!!! La quantité n'est pas un entier !");
			}
		}
		
		
		//System.out.println("Donnée de la requête : quantite : " + quantite + " / articleId = " + articleId);
		
		if(request.getSession().getAttribute("panier") == null){
			request.getSession().setAttribute("panier", new Panier());
			request.getSession().setAttribute("NbArticlePanier", 0);
		}
		
		
		if ( request.getSession().getAttribute("panier") != null)
		{
			
			//System.out.println("La session n'est pas vide.");
			
			panier = (Panier) request.getSession().getAttribute("panier");
			
			//System.out.println("La session contient un panier");
			
			
			panier.edit(articleId, quantite);
			
			//System.out.println("Panier mis a jour !");
			
			request.getSession().setAttribute("panier", panier);
			request.getSession().setAttribute("NbArticlePanier",panier.getLignepanier().size() );
			
			//System.out.println("Panier remis en session");
		}
		
		RequestDispatcher dispath = null;
		if(request.getParameter("rechercheArticle") != null)
		{
			dispath = request.getRequestDispatcher("RechercheServlet?articleRechercher=" + request.getParameter("rechercheArticle"));
		}
		else if(request.getParameter("articles") != null)
		{
			if(request.getParameter("articles").equals("null"))
				dispath = request.getRequestDispatcher("index.html");
			else
				dispath = request.getRequestDispatcher("ArticleServlet?articles="+ request.getParameter("articles"));
		}
		else
		{
			 dispath = request.getRequestDispatcher("panier.jsp");
		}
		dispath.forward(request, response);


	}

}
