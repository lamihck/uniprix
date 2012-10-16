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
 * Servlet implementation class SupprimerCommandeServlet
 */
public class SupprimerCommandeServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Client client = getClientConnected(request);
        request.setAttribute("client", client);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        CommandeDAO commandeDAO = new CommandeDAOImpl(emf);

        if (request.getParameter("commandeId") != null
                && !"".equals(request.getParameter("commandeId"))) {
            // recherche des informations
            try {
                System.out.println("affiche formulaire");
                long commandeId = Long.parseLong(request.getParameter("commandeId"));

                Commande commande = commandeDAO.getByIdCommande(commandeId);

                request.setAttribute("clientId", request.getParameter("idClient"));
                request.setAttribute("commande", commande);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }
            RequestDispatcher dispath = request.getRequestDispatcher("supprimerCommande.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("idCom") != null) {
            System.out.println("suppr?");
            if (request.getParameter("group1").equals("Oui")) {
                System.out.println("on suppr");
                commandeDAO.delete(Long.parseLong(request.getParameter("idCom")));
            }

            request.setAttribute("clientId", request.getParameter("clientId"));

            RequestDispatcher dispath = request.getRequestDispatcher("ListeCommande");
            dispath.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
