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
 * Servlet implementation class SupprimerClientServlet
 */
public class SupprimerClientServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerClientServlet() {
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

        if (request.getParameter("clientId") != null
                && !"".equals(request.getParameter("clientId"))) {
            // recherche des informations
            try {
                long idclient = Long.parseLong(request.getParameter("clientId"));

                Client client = clientDAO.getById(idclient);

                request.setAttribute("client", client);
                request.setAttribute("idclient", request.getParameter("idclient"));
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }
            RequestDispatcher dispath = request.getRequestDispatcher("supprimerClient.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("idCli") != null) {
            System.out.println("phase de suppression" + request.getParameter("idCli"));
            if (request.getParameter("group1").equals("Oui")) {
                clientDAO.delete(Long.parseLong(request.getParameter("idCli")));
                System.out.println("suppression");
            }

            RequestDispatcher dispath = request.getRequestDispatcher("ListeClient");
            dispath.forward(request, response);
        }
    }
}
