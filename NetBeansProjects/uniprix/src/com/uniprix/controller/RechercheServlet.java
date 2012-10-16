package com.uniprix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.ArticleDAO;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.model.Article;

/**
 * Servlet implementation class RechercheServlet
 */
public class RechercheServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RechercheServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// initialise  EntityManagerFactory
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext()
				.getAttribute("emf");
		
		// Recupere les paramètre
		String articleRechercher = request.getParameter("articleRechercher");
		System.out.println("article rechercher " + articleRechercher);
		List<Article> listArticle = null;
		
		// Recupere les categories eet les produits associés pour le menu deroulant
		List<CategorieProduit> listCategorieProduit = getCategorieProduit();
		
		// Dispatch la list des produit
		request.setAttribute("listCategorieProduit", listCategorieProduit);
		
		// Initialise articleDAO et lance la method  findByLike
		ArticleDAO articleDAO = new ArticleDAOImpl(emf);
		
		listArticle = articleDAO.findByLike(articleRechercher);
		
		// Dispatch la liste des articles trouvé 
		request.setAttribute("listArticle", listArticle);
		request.setAttribute("rechercheArticle", articleRechercher);
		
		RequestDispatcher dispath = request.getRequestDispatcher("Recherche.jsp");
		dispath.forward(request, response);
	}

}
