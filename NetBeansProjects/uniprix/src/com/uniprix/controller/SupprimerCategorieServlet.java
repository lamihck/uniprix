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
import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;

/**
 * Servlet implementation class SupprimerCategorie
 */
public class SupprimerCategorieServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCategorieServlet() {
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



        Client client = getClientConnected(request);
        request.setAttribute("client", client);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasePU");
        CategorieDAO categorieDAO = new CategorieDAOImpl(emf);

        if (request.getParameter("categorieId") != null
                && !"".equals(request.getParameter("categorieId"))) {
            // recherche des informations
            try {
                int categorieId = Integer.parseInt(request.getParameter("categorieId"));


                Categorie categorie = categorieDAO.getById(categorieId);

                request.setAttribute("categorie", categorie);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }

            RequestDispatcher dispath = request.getRequestDispatcher("supprimerCategorie.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("idCat") != null) {

            if (request.getParameter("group1").equals("Oui")) {
                categorieDAO.delete(Integer.parseInt(request.getParameter("idCat")));
            }

            RequestDispatcher dispath = request.getRequestDispatcher("ListeCategorie");
            dispath.forward(request, response);
        }
    }
}
