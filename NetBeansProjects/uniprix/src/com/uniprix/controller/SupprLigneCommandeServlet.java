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
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.dao.hibernate.LigneCommandeDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;

/**
 * Servlet implementation class SupprLigneCommandeServlet
 */
public class SupprLigneCommandeServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprLigneCommandeServlet() {
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
        LigneCommandeDAO ligneDAO = new LigneCommandeDAOImpl(emf);

        if (request.getParameter("lignecommandeId") != null
                && !"".equals(request.getParameter("lignecommandeId"))) {
            // recherche des informations
            try {
                System.out.println("affiche formulaire");
                long lignecommandeId = Long.parseLong(request.getParameter("lignecommandeId"));

                LigneCommande lignecom = ligneDAO.getByIdLigneCommande(lignecommandeId);

                request.setAttribute("clientId", request.getParameter("clientId"));
                request.setAttribute("lignecom", lignecom);
                request.setAttribute("idcommande", request.getParameter("commandeId"));
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }
            RequestDispatcher dispath = request.getRequestDispatcher("supprimerLigne.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("ligneId") != null) {
            System.out.println("suppr?");
            if (request.getParameter("group1").equals("Oui")) {
                System.out.println("on suppr" + request.getParameter("ligneId"));

                ligneDAO.deletelignecommande(Long.parseLong(request.getParameter("commandeId")), Long.parseLong(request.getParameter("ligneId")));
            }

            request.setAttribute("clientId", request.getParameter("clientId"));

            RequestDispatcher dispath = request.getRequestDispatcher("ListeLigneCommande");
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
