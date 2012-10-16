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
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Article;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class ListeArticleServlet
 */
public class ListeArticleServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Client client = getClientConnected(request);
        request.setAttribute("client", client);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);


        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

        long produitId = 0;

        ArticleDAO articleDAO = new ArticleDAOImpl(emf);

        if (request.getAttribute("idProd") != null) {
//			System.out.println("dans idProd");
            produitId = Long.parseLong((String) request.getAttribute("idProd"));
//			System.out.println(produitId);
        } else if (request.getParameter("produitId") != null) {
//			System.out.println("idProduit est null");
//			System.out.println("id produit"+request.getParameter("produitId"));
            produitId = Long.parseLong(request.getParameter("produitId"));
        }

        List<Article> articles = articleDAO.getArticleByIdProduit(produitId);

        request.setAttribute("listArticle", articles);
        request.setAttribute("idprod", produitId);
        System.out.println("idcat attribute" + request.getAttribute("idCat"));
        System.out.println("idcat param" + request.getParameter("idCat"));
        request.setAttribute("idCat", request.getParameter("idCat"));

        RequestDispatcher dispath = request.getRequestDispatcher("listearticleAdmin.jsp");
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
