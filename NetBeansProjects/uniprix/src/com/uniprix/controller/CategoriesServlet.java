package com.uniprix.controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.model.Article;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class Categories
 */
public class CategoriesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesServlet() {
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
		
		Client client = getClientConnected(request);
		request.setAttribute("client", client);

		List<CategorieProduit> listCategorieProduit = getCategorieProduit();

		request.setAttribute("listCategorieProduit", listCategorieProduit);

		System.out.println(" page redirect : "
				+ request.getParameter("pageRedirect"));
		if (request.getParameter("pageRedirect") != null) {
			System.out.println("pas null");

			if (request.getParameter("pageRedirect").equals("1")) {
				System.out.println("dans 1");
				RequestDispatcher dispath = request
						.getRequestDispatcher("creationProduit.jsp");
				dispath.forward(request, response);
			}
		} else {
			
			// Offre du jour renvoie un produit généré aléatoirement
			int valeur;
			Random r = null;
//			valeur = 0 + r.nextInt((listCategorieProduit.size() - 1) - 0);
//			CategorieProduit categorieProduitDuJour = listCategorieProduit.get(valeur);
//			List<Produit> listProduitDuJour = categorieProduitDuJour.getListProduit();
//
//			// liste article tiré d'un produit alléatoire
//			r = new Random();
//			valeur = 0 + r.nextInt((listProduitDuJour.size() - 1) - 0);
//			Produit produitDuJour = listProduitDuJour.get(valeur);
//			List<Article> listArticleDuJour = produitDuJour.getArticles();
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			ArticleDAOImpl articleDAO = new ArticleDAOImpl(emf);
			List<Article> listArticle = articleDAO.getAll();
			List<Article> listArticleDuJour =  new ArrayList<Article>();
			int nbArticleDuJour = 2;
			if (nbArticleDuJour< listArticle.size())
			 
			 for (int i = 0; i < nbArticleDuJour; i++) {
					 r = new Random();
					valeur = 0 + r.nextInt((listArticle.size() - 1) - 0);
					listArticleDuJour.add(listArticle.get(valeur));

			}
			request.setAttribute("listArticleDuJour", listArticleDuJour);
			RequestDispatcher dispath = request.getRequestDispatcher("home.jsp");
			dispath.forward(request, response);

		}
	}
}
