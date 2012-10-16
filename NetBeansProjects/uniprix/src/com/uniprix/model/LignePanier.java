package com.uniprix.model;

public class LignePanier {

    private int quantite;
    private Article article;

    public LignePanier(Article article, int quantite) {
        // TODO Auto-generated constructor stub
        this.article = article;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "LignePanier [quantite=" + quantite + ", article=" + article
                + "]";
    }
}
