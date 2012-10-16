package com.uniprix.controller;

import java.io.IOException;
import java.util.Hashtable;
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
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.dao.hibernate.LigneCommandeDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.LigneCommande;

/**
 * Servlet implementation class LstLigneCommandeServlet
 */
public class LstLigneCommandeServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LstLigneCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client clientt = getClientConnected(request);
        request.setAttribute("client", clientt);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        CommandeDAO commandeDAO = new CommandeDAOImpl(emf);
        ClientDAO clientDAO = new ClientDAOImpl(emf);
        LigneCommandeDAO lignecommandeDAO = new LigneCommandeDAOImpl(emf);

        Hashtable<LigneCommande, Double> dico = new Hashtable<LigneCommande, Double>();
        double tot = 0;
        List<LigneCommande> ligneCommandes = lignecommandeDAO.
                getByIdCommande(Long.parseLong(request.getParameter("commandeId")));
        for (LigneCommande lignecommande : ligneCommandes) {
            LigneCommande ligne = lignecommandeDAO.getByIdLigneCommande(lignecommande.getLigneCommandeId());
            tot = lignecommandeDAO.sousTotalLigne(lignecommande.getLigneCommandeId());
            dico.put(ligne, tot);
            System.out.println(ligne);
        }

        Client client = clientDAO.getById(Long.parseLong(request.getParameter("idClient")));

        request.setAttribute("infolignecom", dico);
        request.setAttribute("client", client);
        request.setAttribute("idcom", request.getParameter("commandeId"));


        RequestDispatcher dispath = request.getRequestDispatcher("lignecommandeUser.jsp");
        dispath.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
