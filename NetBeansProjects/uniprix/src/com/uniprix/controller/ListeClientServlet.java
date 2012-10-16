package com.uniprix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.LoginDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.dao.hibernate.LigneCommandeDAOImpl;
import com.uniprix.dao.hibernate.LoginDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.Login;

/**
 * Servlet implementation class ListeClientServlet
 */
public class ListeClientServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        Client clientt = getClientConnected(request);
        request.setAttribute("client", clientt);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ClientDAO clientDAO = new ClientDAOImpl(emf);
        CommandeDAO commandeDAO = new CommandeDAOImpl(emf);
        LigneCommandeDAO lignecommandeDAO = new LigneCommandeDAOImpl(emf);

        List<Client> clients = clientDAO.getAll();
        //List<Commande> commandes = null;
        Hashtable<Client, Integer> dico = new Hashtable<Client, Integer>();
        //List<Integer> nb=new ArrayList<Integer>();
        for (Client client : clients) {
            dico.put(client, commandeDAO.getByIdClient(client.getClientId()).size());
            //nb.add(commandeDAO.getByIdClient(client.getClientId()).size());
            System.out.println(commandeDAO.getByIdClient(client.getClientId()).size());
            System.out.println(clients);
        }

        request.setAttribute("listCommandes", dico);
        request.setAttribute("listClient", clients);

        System.out.println(clients);

        RequestDispatcher dispath = request.getRequestDispatcher("listeclientAdmin.jsp");
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
