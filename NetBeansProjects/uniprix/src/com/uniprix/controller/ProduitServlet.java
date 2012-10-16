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

import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class ProduitServlet
 */
public class ProduitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        ProduitDAO produitDAO = new ProduitDAOImpl(emf);

        System.out.println((String) request.getParameter("categorieId"));

        int idcategorie = Integer.parseInt((String) request.getParameter("categorieId"));

        List<Produit> produits = produitDAO.getProduitByIdCategorie(idcategorie);
        CategorieDAO categorieDAO = new CategorieDAOImpl(emf);
        List<Categorie> categories = categorieDAO.getAll();

        request.setAttribute("listCategorie", categories);

        request.setAttribute("listProduit", produits);

        RequestDispatcher dispath = request.getRequestDispatcher("produit.jsp");
        dispath.forward(request, response);
    }
}
