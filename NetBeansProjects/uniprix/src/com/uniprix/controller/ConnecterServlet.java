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
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.model.Client;

/**
 * Servlet implementation class ConnecterServlet
 */
public class ConnecterServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);


        if (request.getUserPrincipal() == null) {
            request.setAttribute("client", null);
        } else {
            request.getUserPrincipal().getName();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasePU");
            ClientDAO clientDAO = new ClientDAOImpl(emf);
            Client client = clientDAO.getByLogin(request.getUserPrincipal().getName());

            System.out.println(client.getNom());

            if (client.getNom() == null || client.getPrenom() == null || client.getLogin() == null) {
                request.setAttribute("client", null);
            } else {
                request.setAttribute("client", client);
            }
        }

        System.out.println((Client) request.getAttribute("client"));

        RequestDispatcher dispath = request.getRequestDispatcher("CategoriesServlet");
        dispath.forward(request, response);
    }
}
