package com.uniprix.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;

/**
 * Servlet implementation class CreationCategorieServlet
 */
public class CreationCategorieServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		CategorieDAO categorieDAO = new CategorieDAOImpl(emf);	
		if(request.getParameter("nomCat") != null 
				&& !"".equals(request.getParameter("nomCat")))
		{
			try{
				Categorie categorie = new Categorie(request.getParameter("nomCat"), request.getParameter("imageCat"));
				categorieDAO.saveCategorie(categorie);
			}
			catch(Exception e)
			{
				System.out.println("!!! Problème de requete insertion categorie!");
				
			}
		}
		RequestDispatcher dispath = request.getRequestDispatcher("ListeCategorie");
		dispath.forward(request, response);
	}

}
