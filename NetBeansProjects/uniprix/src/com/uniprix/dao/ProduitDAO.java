package com.uniprix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.uniprix.model.Categorie;
import com.uniprix.model.Produit;

public interface ProduitDAO {

    public List<Produit> getAll();

    public Produit getByIdProduit(long idproduit);

    public void saveProduit(Produit produit);

    public void delete(long idproduit);

    public void update(Produit produit);

    public void attacheProdCategorie(long idcat, long idprod);

    public int countAll();

    public List<Produit> getProduitByIdCategorie(long idcategorie);
    //private EntityManagerFactory emf;
    /**
     * @param emf
     */
    /*public ProduitDAO(EntityManagerFactory emf) {
     super();
     this.emf = emf;
     }
	
     public void saveProduit(Produit produit){
     EntityManager em=emf.createEntityManager();
     EntityTransaction tx=em.getTransaction();
     tx.begin();
     em.persist(produit);
     tx.commit();
     em.close();
     }
	
     public Produit getProduit(int id){
     Produit produit=null;
     EntityManager em=emf.createEntityManager();
     EntityTransaction tx=em.getTransaction();
     tx.begin();
     produit=em.find(Produit.class, id);
     tx.commit();
     em.close();
     return produit;
     }
	
     public List<Produit> getAllProduit(){
     List<Produit> produit=null;
     EntityManager em=emf.createEntityManager();
     EntityTransaction tx=em.getTransaction();
     tx.begin();
     produit=(List<Produit>)em.createQuery("from Produit").getResultList();
     tx.commit();
     em.close();
     return produit;
     }*/
}
