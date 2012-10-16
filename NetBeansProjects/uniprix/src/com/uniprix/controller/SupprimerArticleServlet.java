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
 * Servlet implementation class SupprimerArticleServlet
 */
public class SupprimerArticleServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerArticleServlet() {
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
        ArticleDAO articleDAO = new ArticleDAOImpl(emf);

        if (request.getParameter("articleId") != null
                && !"".equals(request.getParameter("articleId"))) {
            // recherche des informations
            try {
                long articleId = Long.parseLong(request.getParameter("articleId"));

                Article article = articleDAO.getByIdArticle(articleId);

                request.setAttribute("idProd", request.getParameter("idproduit"));
                request.setAttribute("article", article);
            } catch (Exception e) {
                System.out.println("!!! Ce n'est pas un id entier !");

            }
            System.out.println("idproduit" + request.getParameter("idproduit"));
            RequestDispatcher dispath = request.getRequestDispatcher("supprimerArticle.jsp");
            dispath.forward(request, response);
        } else if (request.getParameter("idArt") != null) {
            System.out.println("phase de suppression" + request.getParameter("idArt"));
            if (request.getParameter("group1").equals("Oui")) {
                articleDAO.delete(Long.parseLong(request.getParameter("idArt")));
                System.out.println("suppression");
            }

//			long idProd=Long.parseLong(request.getParameter("idProd"));
//			System.out.println("idprod"+idProd);
            request.setAttribute("idProd", request.getParameter("idProd"));

            RequestDispatcher dispath = request.getRequestDispatcher("ListeArticle");
            dispath.forward(request, response);
        }
    }
}
