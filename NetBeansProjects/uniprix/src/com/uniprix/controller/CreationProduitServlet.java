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
import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class CreationProduitServlet
 */
public class CreationProduitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationProduitServlet() {
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

		
		Client client = getClientConnected(request);
		request.setAttribute("client", client);

		List<CategorieProduit> listCategorieProduit = getCategorieProduit();

		request.setAttribute("listCategorieProduit", listCategorieProduit);
		
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		ProduitDAO produitDAO=new ProduitDAOImpl(emf);
		if(request.getParameter("nomProd") != null 
				&& !"".equals(request.getParameter("nomProd")) &&
				request.getParameter("Categorie") != null 
				&& !"".equals(request.getParameter("Categorie")))
		{
			try{
				System.out.println("categorie "+request.getParameter("Categorie"));
				System.out.println("nom prod "+request.getParameter("nomProd"));
				System.out.println("categorie "+Long.parseLong(request.getParameter("Categorie")));
				Produit produit=new Produit(request.getParameter("nomProd"));
				produitDAO.saveProduit(produit);
				System.out.println("id prod "+produit.getProduitId());
				produitDAO.attacheProdCategorie(Long.parseLong(request.getParameter("Categorie")),produit.getProduitId() );
			}
			catch(Exception e)
			{
				System.out.println("!!! Problème de requete insertion produit!");
				
			}
		}
		
		RequestDispatcher dispath = request.getRequestDispatcher("ListeProduit");
		dispath.forward(request, response);
	}

}
