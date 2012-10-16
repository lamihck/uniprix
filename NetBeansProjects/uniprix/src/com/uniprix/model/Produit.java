package com.uniprix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produit {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long produitId;
    private String nom;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Categorie categorie;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PROD_ARTI",
    joinColumns =
    @JoinColumn(name = "PROD_ID"),
    inverseJoinColumns =
    @JoinColumn(name = "ARTI_ID"))
    private List<Article> articles = new ArrayList<Article>(0);

    /**
     * @param nom
     */
    public Produit(String nom) {
        super();
        this.nom = nom;
    }

    /**
     * @param nom
     * @param articles
     */
    public Produit(String nom, List<Article> articles) {
        super();
        this.nom = nom;
        this.articles = articles;
    }

    public Produit() {
        super();
    }

    /**
     * @param nom
     * @param categorie
     */
    public Produit(String nom, Categorie categorie) {
        super();
        this.nom = nom;
        this.categorie = categorie;
    }

    /**
     * @param nom
     * @param categorie
     * @param articles
     */
    public Produit(String nom, Categorie categorie, List<Article> articles) {
        super();
        this.nom = nom;
        this.categorie = categorie;
        this.articles = articles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public long getProduitId() {
        return produitId;
    }

    public void setProduitId(long produitId) {
        this.produitId = produitId;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Produit [produitId=" + produitId + ", nom=" + nom + "]";
    }
}
