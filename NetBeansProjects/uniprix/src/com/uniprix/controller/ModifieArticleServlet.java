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
 * Servlet implementation class ModifieArticleServlet
 */
public class ModifieArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifieArticleServlet() {
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
		
		EntityManagerFactory emf =(EntityManagerFactory) getServletContext().getAttribute("emf");
		ArticleDAO articleDAO= new ArticleDAOImpl(emf);
		
		if(request.getParameter("articleId") != null 
				&& !"".equals(request.getParameter("articleId")))
		{
			// recherche des informations
			try{
				long idarticle = Long.parseLong(request.getParameter("articleId"));
				
				Article article=articleDAO.getByIdArticle(idarticle);
				
				request.setAttribute("idCat", request.getParameter("idCat"));
				request.setAttribute("article", article);
				request.setAttribute("idproduit", request.getParameter("idproduit"));
			}
			catch(Exception e)
			{
				System.out.println("!!! Ce n'est pas un id entier !");
				
			}
			
			RequestDispatcher dispath = request.getRequestDispatcher("modifierArticle.jsp");
			dispath.forward(request, response);
		}
		else if(request.getParameter("nomArt") != null && request.getParameter("descArt") != null
				&& request.getParameter("imgArt") != null && request.getParameter("prixArt") != null)
		{
			Article article =articleDAO.getByIdArticle(Long.parseLong(request.getParameter("idArt")));
			article.setNom(request.getParameter("nomArt"));
			article.setDescription(request.getParameter("descArt"));
			article.setImage(request.getParameter("imgArt"));
			article.setPrix(Double.parseDouble(request.getParameter("prixArt")));
			articleDAO.update(article);
			
			System.out.println("valeur id prod modifie"+request.getParameter("idProd"));
			
			//long idProd=Long.parseLong(request.getParameter("idProd"));
			request.setAttribute("idProd", request.getParameter("idProd"));
			request.setAttribute("idCat", request.getParameter("idCat"));
			System.out.println("id de la catégorie dans modifie"+request.getParameter("idCat"));
			RequestDispatcher dispath = request.getRequestDispatcher("ListeArticle");
			dispath.forward(request, response);
		}
	}

}
