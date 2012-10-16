package com.uniprix.ClassUtilitaire;

import java.util.List;

import javax.persistence.EntityManagerFactory;


import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ProduitDAOImpl;
import com.uniprix.model.Article;
import com.uniprix.model.Categorie;
import com.uniprix.model.Produit;

public class CategorieProduit {

    private ProduitDAOImpl produitDao;
    private CategorieDAOImpl categorieDAOImpl;
    private List<Produit> listProduit;
    private long IdCategorie;
    private EntityManagerFactory emf;
    private Categorie categorie;

    // Constructeur
    // Constructeur prenant en param�tre un EntityManagerFactory,et l'Id du produit duquel
    public CategorieProduit(EntityManagerFactory emf, long l) {
        super();
        this.IdCategorie = l;
        this.emf = emf;
        getCategorieProduits();
    }

    // Fin Constructeur
    public void setProduitDao(ProduitDAOImpl produitDao) {
        this.produitDao = produitDao;
    }

    public List<Produit> getListProduit() {
        return listProduit;
    }

    public void setListProduit(List<Produit> listProduit) {
        this.listProduit = listProduit;
    }

    public long getIdCategorie() {
        return IdCategorie;
    }

    public void setIdProduitTraitement(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    private void getCategorieProduits() {

        // Obtenir le nom de la categorie 
        getNomCategorie();

        // obtenir les produit de cette categorie
        produitDao = new ProduitDAOImpl(emf);
        listProduit = produitDao.getProduitByIdCategorie(IdCategorie);

    }

    private void getNomCategorie() {
        categorieDAOImpl = new CategorieDAOImpl(emf);
        categorie = new Categorie();
        categorie = categorieDAOImpl.getById(IdCategorie);
        System.out.println(categorie.getNom());
    }
}
