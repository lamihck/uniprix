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
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class ModifieProduitServlet
 */
public class ModifieProduitServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifieProduitServlet() {
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

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ProduitDAO produitDAO = new ProduitDAOImpl(emf);

        if (request.getParameter("produitId") != null
                && !"".equals(request.getParameter("produitId"))) {
            // recherche des informations
            try {
                int produitId = Integer.parseInt(request.getParameter("produitId"));

                Produit produit = produitDAO.getByIdProduit(produitId);

                request.setAttribute("produit", produit);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }

            RequestDispatcher dispath = request.getRequestDispatcher("modifierProduit.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("nomProd") != null) {
            Produit produit = produitDAO.getByIdProduit(Integer.parseInt(request.getParameter("idProd")));
            produit.setNom(request.getParameter("nomProd"));
            produitDAO.update(produit);

            System.out.println("id categorie" + request.getParameter("idCat"));
            int idCat = Integer.parseInt(request.getParameter("idCat"));
            request.setAttribute("idCat", idCat);

            RequestDispatcher dispath = request.getRequestDispatcher("ListeProduitId");
            dispath.forward(request, response);
        }
    }
}
