package com.uniprix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
public class Categorie {

    @Id
    @GeneratedValue
    private long categorieId;
    private String nom;
    @Column(length = 400)
    private String image;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> categorieProduit = new ArrayList<Produit>();

    /**
     * @param nom
     * @param image
     */
    public Categorie(String nom, String image) {
        super();
        this.nom = nom;
        this.image = image;
    }

    /**
     * @param nom
     * @param image
     * @param categorieProduit
     */
    public Categorie(String nom, String image, List<Produit> categorieProduit) {
        super();
        this.nom = nom;
        this.image = image;
        this.categorieProduit = categorieProduit;
    }

    public Categorie() {
        categorieProduit = new ArrayList<Produit>();
    }

    /* GETTER SETTER */
    public long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(long categorieId) {
        this.categorieId = categorieId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Produit> getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(List<Produit> categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    @Override
    public String toString() {
        return "Categorie [categorieId=" + categorieId + ", nom=" + nom + "]";
    }
}
