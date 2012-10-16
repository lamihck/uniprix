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
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;

/**
 * Servlet implementation class ListeCommandeUserServlet
 */
public class ListeCommandeUserServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeCommandeUserServlet() {
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

        List<Commande> commandes = commandeDAO.getByIdClient(Long.parseLong(request.getParameter("clientId")));

        double tot = 0;
        //Hashtable<K, V>
        Hashtable<Commande, Double> dico = new Hashtable<Commande, Double>();
        for (Commande commande : commandes) {
            List<LigneCommande> lignecommandes = lignecommandeDAO.getByIdCommande(commande.getCommandeId());
            tot = commandeDAO.totalCommande(commande.getCommandeId(), lignecommandes);
            for (LigneCommande lignecommande : lignecommandes) {
                LigneCommande ligne = lignecommandeDAO.getByIdLigneCommande(lignecommande.getLigneCommandeId());
                //tot+=lignecommandeDAO.sousTotalLigne(lignecommande.getLigneCommandeId());
                System.out.println(ligne);
            }
            dico.put(commande, tot);
            //tot=0;
            System.out.println(tot);

        }
        Client client = clientDAO.getById(Long.parseLong(request.getParameter("clientId")));

        request.setAttribute("listCommandes", commandes);
        request.setAttribute("infoClient", client);
        request.setAttribute("infocommandetot", dico);

        RequestDispatcher dispath = request.getRequestDispatcher("listecommandeUser.jsp");
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
