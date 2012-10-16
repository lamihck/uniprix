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
import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;

/**
 * Servlet implementation class ListeCategorieServlet
 */
public class ListeCategorieServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeCategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        Client client = getClientConnected(request);
        request.setAttribute("client", client);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);


        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        CategorieDAO categorieDAO = new CategorieDAOImpl(emf);

        List<Categorie> categories = categorieDAO.getAll();

        request.setAttribute("listCategorie", categories);

        RequestDispatcher dispath = request.getRequestDispatcher("listecategorieAdmin.jsp");
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
