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
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.model.Article;
import com.uniprix.model.Client;

/**
 * Servlet implementation class ModifierClientServlet
 */
public class ModifierClientServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        Client clientt = getClientConnected(request);
        request.setAttribute("client", clientt);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ClientDAO clientDAO = new ClientDAOImpl(emf);
        ArticleDAO articleDAO = new ArticleDAOImpl(emf);

        if (request.getParameter("clientId") != null
                && !"".equals(request.getParameter("clientId"))) {
            // recherche des informations
            try {
                System.out.println("1ere fois");
                long idclient = Long.parseLong(request.getParameter("clientId"));

                Client client = clientDAO.getById(idclient);

                request.setAttribute("montant", request.getParameter("montant"));
                request.setAttribute("client", client);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }

            RequestDispatcher dispath = request.getRequestDispatcher("modifierClient.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("nomCli") != null && request.getParameter("prenomCli") != null
                && request.getParameter("adr1") != null && request.getParameter("adr2") != null
                && request.getParameter("cpCli") != null && request.getParameter("villeCli") != null
                && request.getParameter("paysCli") != null && request.getParameter("telCli") != null
                && request.getParameter("mailCli") != null) {
            System.out.println("partie modification");
            System.out.println("idclient" + request.getParameter("idCli"));
            Client client = clientDAO.getById(Long.parseLong(request.getParameter("idCli")));
            client.setNom(request.getParameter("nomCli"));
            client.setPrenom(request.getParameter("prenomCli"));
            client.setAdresse1(request.getParameter("adr1"));
            client.setAdresse2(request.getParameter("adr2"));
            client.setCp(request.getParameter("cpCli"));
            client.setVille(request.getParameter("villeCli"));
            client.setPays(request.getParameter("paysCli"));
            client.setTelephone(request.getParameter("telCli"));
            client.setMail(request.getParameter("mailCli"));

            clientDAO.update(client);

            System.out.println("valeur id client modifie" + request.getParameter("idCli"));

            RequestDispatcher dispath = request.getRequestDispatcher("ListeClient");
            dispath.forward(request, response);
        }
    }
}
