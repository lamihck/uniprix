package com.uniprix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private long articleId;
    private String nom;
    private double prix;
    @Column(length = 1500)
    private String description;
    @Column(length = 400)
    private String image;
    @ManyToMany(mappedBy = "articles")
    private List<Produit> produits = new ArrayList<Produit>(0);

    //manytoone
//	@OneToOne(mappedBy="article")
//	private LigneCommande ligneCommande;
    public Article() {
        super();
    }

    /**
     * @param nom
     * @param prix
     * @param description
     * @param image
     * @param students
     */
    public Article(String nom, double prix, String description, String image,
            List<Produit> produits) {
        super();
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.produits = produits;
    }

    /**
     * @param nom
     * @param prix
     * @param description
     * @param image
     */
    public Article(String nom, double prix, String description, String image) {
        super();
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    /*
     public LigneCommande getLigneCommande() {
     return ligneCommande;
     }

     public void setLigneCommande(LigneCommande ligneCommande) {
     this.ligneCommande = ligneCommande;
     }*/
}
