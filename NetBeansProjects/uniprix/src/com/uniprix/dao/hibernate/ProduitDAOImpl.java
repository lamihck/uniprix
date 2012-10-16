package com.uniprix.dao.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.uniprix.dao.ProduitDAO;
import com.uniprix.model.Article;
import com.uniprix.model.Categorie;
import com.uniprix.model.Produit;

public class ProduitDAOImpl implements ProduitDAO {

    /**
     *
     */
    public ProduitDAOImpl() {
        super();
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(ProduitDAOImpl.class);

    /**
     * @param emf
     */
    public ProduitDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produit = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            produit = (List<Produit>) em.createQuery("from Produit").getResultList();
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method getAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return produit;
    }

    @Override
    public Produit getByIdProduit(long idproduit) {
        Produit produit = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            produit = em.find(Produit.class, idproduit);
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method getByIdProduit ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return produit;
    }

    @Override
    public void saveProduit(Produit produit) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            //Article article=em.find(Article.class, produit.getArticles().get(0).getArticleId());
            //article.getProduits().add(produit);
            em.persist(produit);
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method saveProduit ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void delete(long idproduit) {
        Produit produit = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            produit = em.find(Produit.class, idproduit);
            em.remove(produit);
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method delete ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void update(Produit produit) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(produit);

        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method update ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public int countAll() {
        List<Produit> produit = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            produit = (List<Produit>) em.createQuery("from Produit").getResultList();
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return produit.size();
    }

    @Override
    public List<Produit> getProduitByIdCategorie(long idcategorie) {
        List<Produit> produit = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            String SQL_QUERY = "from Produit p where p.categorie.categorieId=:id";
            Query query3 = em.createQuery(SQL_QUERY);
            query3.setParameter("id", idcategorie);
            produit = query3.getResultList();
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method getProduitByIdCategorie ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return produit;
    }

    @Override
    public void attacheProdCategorie(long idcat, long idprod) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Categorie categorie = em.find(Categorie.class, idcat);
            Produit produit = em.find(Produit.class, idprod);
            categorie.getCategorieProduit().add(produit);
            produit.setCategorie(categorie);
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method attacheProdCategorie ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }
}
