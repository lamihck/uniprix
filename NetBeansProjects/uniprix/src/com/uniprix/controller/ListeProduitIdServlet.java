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
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class ListeProduitIdServlet
 */
public class ListeProduitIdServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeProduitIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		Client client = getClientConnected(request);
		request.setAttribute("client", client);

		List<CategorieProduit> listCategorieProduit = getCategorieProduit();

		request.setAttribute("listCategorieProduit", listCategorieProduit);
		
		EntityManagerFactory emf =(EntityManagerFactory) getServletContext().getAttribute("emf");
		
		int categorieId=0;
		
		ProduitDAO produitDAO= new ProduitDAOImpl(emf);
		System.out.println("idcat prod deb"+request.getAttribute("idCat"));
		if(request.getAttribute("idCat") !=null)
		{
			categorieId = (Integer) request.getAttribute("idCat");
		}
		else if(request.getParameter("categorieId") !=null){
			categorieId = Integer.parseInt(request.getParameter("categorieId"));
		}

		System.out.println("catégorie id"+categorieId);
		List<Produit> produits = produitDAO.getProduitByIdCategorie(categorieId);
		
		request.setAttribute("listProduitId", produits);
		
		RequestDispatcher dispath = request.getRequestDispatcher("listeproduitIdAdmin.jsp");
		dispath.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
