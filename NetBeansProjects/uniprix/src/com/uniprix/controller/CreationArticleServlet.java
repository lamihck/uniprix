package com.uniprix.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.ArticleDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Article;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class CreationArticleServlet
 */
public class CreationArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Client client = getClientConnected(request);
		request.setAttribute("client", client);

		List<CategorieProduit> listCategorieProduit = getCategorieProduit();

		request.setAttribute("listCategorieProduit", listCategorieProduit);
		
		
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		ArticleDAO articleDAO=new ArticleDAOImpl(emf);
		
		if(request.getParameter("nomArt") != null && !"".equals(request.getParameter("nomArt")) &&
		   request.getParameter("descArt") != null && !"".equals(request.getParameter("descArt")) &&
		   request.getParameter("imgArt") != null && !"".equals(request.getParameter("imgArt"))
		   && request.getParameter("prixArt") != null && !"".equals(request.getParameter("prixArt")))
		{
			try{
				System.out.println("produit "+request.getParameter("Produit"));
				System.out.println("nom art "+request.getParameter("nomArt"));
				Article article=new Article(request.getParameter("nomArt"),
						Double.parseDouble(request.getParameter("prixArt")),
						request.getParameter("descArt"),
						request.getParameter("imgArt"));
				articleDAO.saveArticle(article);
				System.out.println("id prod "+article.getArticleId());
				articleDAO.attacheProduitArticle(Long.parseLong(request.getParameter("Produit")), article.getArticleId());
			}
			catch(Exception e)
			{
				System.out.println("!!! Problème de requete insertion article!");
				
			}
		}
		request.setAttribute("idProd", request.getParameter("Produit"));
		
		RequestDispatcher dispath = request.getRequestDispatcher("ListeArticle");
		dispath.forward(request, response);
	}

}
