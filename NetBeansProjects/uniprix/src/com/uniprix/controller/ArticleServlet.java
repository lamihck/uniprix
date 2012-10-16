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
import com.uniprix.dao.ArticleDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.dao.hibernate.ArticleDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Article;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
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
        int idproduit = 0;

        try {
            idproduit = Integer.parseInt((String) request.getParameter("articles"));
        } catch (NumberFormatException e) {
            idproduit = 0;
        }

        request.getSession().setAttribute("IdProduitSelectionner", idproduit);

        ArticleDAO articleDAO = new ArticleDAOImpl(emf);


        List<Article> articles = articleDAO.getArticleByIdProduit(idproduit);

        request.setAttribute("listArticle", articles);

        List<CategorieProduit> listCategorieProduit = getCategorieProduit();

        request.setAttribute("listCategorieProduit", listCategorieProduit);

        RequestDispatcher dispath = request.getRequestDispatcher("articles.jsp");
//		RequestDispatcher dispath = request.getRequestDispatcher("CategorieServlet?chemin=product");
        dispath.forward(request, response);


    }
}
