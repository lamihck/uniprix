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
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;

/**
 * Servlet implementation class GestionCompteServlet
 */
public class GestionCompteServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCompteServlet() {
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
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		ClientDAO clientdao = new ClientDAOImpl(emf);
		List<CategorieProduit> listCategorieProduit = getCategorieProduit();
		request.setAttribute("listCategorieProduit", listCategorieProduit);
		
		
		Client client = getClientConnected(request);
		
		
		
		if(client == null)
		{
			request.setAttribute("client", null);
		}
		else
		{
			request.setAttribute("client", client);
		}
		
		CommandeDAO commandedao = new CommandeDAOImpl(emf);
		
		List<Commande> commandes = commandedao.getByIdClient(client.getClientId());
		System.out.println(commandes);
		
			request.setAttribute("commandes", commandes);
		
		if(request.getParameter("nomCli") != null && request.getParameter("prenomCli") != null
				&& request.getParameter("adr1") != null && request.getParameter("adr2") != null
				&& request.getParameter("cpCli") != null && request.getParameter("villeCli") != null
				&& request.getParameter("paysCli") != null && request.getParameter("telCli") != null)
		{
			
			ClientDAO clientDAO=new ClientDAOImpl(emf);
			System.out.println("partie modification");
			client.setNom(request.getParameter("nomCli"));
			client.setPrenom(request.getParameter("prenomCli"));
			client.setAdresse1(request.getParameter("adr1"));
			client.setAdresse2(request.getParameter("adr2"));
			client.setCp(request.getParameter("cpCli"));
			client.setVille(request.getParameter("villeCli"));
			client.setPays(request.getParameter("paysCli"));
			client.setTelephone(request.getParameter("telCli"));
			
			clientDAO.update(client);
			
			System.out.println("Client modifié !" + client);
			
			RequestDispatcher dispath = request.getRequestDispatcher("gestionCompte.jsp");
			dispath.forward(request, response);
		}
		else
		{
			RequestDispatcher dispath = request.getRequestDispatcher("gestionCompte.jsp");
			dispath.forward(request, response);
		}
		
		
	}

}
