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
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.LigneCommandeDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.LigneCommande;

/**
 * Servlet implementation class ModifieLigneCommandeServlet
 */
public class ModifieLigneCommandeServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifieLigneCommandeServlet() {
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
        ClientDAO clientDAO = new ClientDAOImpl(emf);
        ArticleDAO articleDAO = new ArticleDAOImpl(emf);
        LigneCommandeDAO lignecommandeDAO = new LigneCommandeDAOImpl(emf);

        if (request.getParameter("lignecommandeId") != null
                && !"".equals(request.getParameter("lignecommandeId"))) {
            // recherche des informations
            try {
                long lignecommandeId = Long.parseLong(request.getParameter("lignecommandeId"));

                LigneCommande lignecommande = lignecommandeDAO.getByIdLigneCommande(lignecommandeId);

                request.setAttribute("idcommande", request.getParameter("commandeId"));
                request.setAttribute("lignecommande", lignecommande);
                System.out.println("id client" + request.getParameter("idClient"));
                request.setAttribute("idClient", request.getParameter("clientId"));

                //request.setAttribute("client", client);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }

            RequestDispatcher dispath = request.getRequestDispatcher("modifierLigne.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("prixU") != null && request.getParameter("qtt") != null) {
            LigneCommande lignecom = lignecommandeDAO.
                    getByIdLigneCommande(Long.parseLong(request.getParameter("idligne")));
            lignecom.setPrix(Double.parseDouble(request.getParameter("prixU")));
            lignecom.setQuantite(Integer.parseInt(request.getParameter("qtt")));

            lignecommandeDAO.update(lignecom);



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
