package com.uniprix.dao;

import java.util.List;

import com.uniprix.model.Article;
import com.uniprix.model.Client;

public interface ArticleDAO {

    public List<Article> getAll();

    public Article getByIdArticle(long idarticle);

    public void saveArticle(Article article);

    public void delete(long idarticle);

    public void update(Article article);

    public void attacheProduitArticle(long idProd, long idArt);

    public int countAll();

    public List<Article> getArticleByIdProduit(long idproduit);

    public List<Article> findByLike(String chaineCaractere);
}
