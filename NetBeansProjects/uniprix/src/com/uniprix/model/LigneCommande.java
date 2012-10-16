package com.uniprix.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue
    private long ligneCommandeId;
    private int quantite;
    private double prix;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Commande commande;
    @OneToOne
    private Article article;

    /**
     * @param quantite
     * @param prix
     */
    public LigneCommande(int quantite, float prix) {
        super();
        this.quantite = quantite;
        this.prix = prix;
    }

    /**
     * @param quantite
     * @param prix
     * @param article
     */
    public LigneCommande(int quantite, float prix, Article article) {
        super();
        this.quantite = quantite;
        this.prix = prix;
        this.article = article;
    }

    public LigneCommande() {
        super();
    }

    /**
     * @param quantite
     * @param prix
     * @param commande
     * @param article
     */
    public LigneCommande(int quantite, float prix, Commande commande,
            Article article) {
        super();
        this.quantite = quantite;
        this.prix = prix;
        this.commande = commande;
        this.article = article;
    }

    public long getLigneCommandeId() {
        return ligneCommandeId;
    }

    public void setLigneCommandeId(long ligneCommandeId) {
        this.ligneCommandeId = ligneCommandeId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double d) {
        this.prix = d;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "LigneCommande [LigneCommandeId=" + ligneCommandeId
                + ", quantite=" + quantite + ", prix=" + prix + ", commande="
                + commande + ", article=" + article + "]";
    }
}
