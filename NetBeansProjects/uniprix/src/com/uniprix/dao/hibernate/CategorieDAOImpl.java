package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import com.uniprix.dao.CategorieDAO;
import com.uniprix.model.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

    /**
     *
     */
    public CategorieDAOImpl() {
        super();
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class);

    /**
     * @param emf
     */
    public CategorieDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Categorie> getAll() {
        List<Categorie> categorie = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            categorie = (List<Categorie>) em.createQuery("from Categorie").getResultList();
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method getAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return categorie;
    }

    @Override
    public Categorie getById(long idcategorie) {
        Categorie categorie = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            categorie = em.find(Categorie.class, idcategorie);
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method getById ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return categorie;
    }

    public void saveCategorie(Categorie categorie) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //logger.info("sdf");
            em.persist(categorie);
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method saveCategorie ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void delete(long idcategorie) {
        Categorie categorie = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            categorie = em.find(Categorie.class, idcategorie);
            em.remove(categorie);
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method delete ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void update(Categorie categorie) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(categorie);
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method update ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public int countAll() {
        List<Categorie> categorie = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            categorie = (List<Categorie>) em.createQuery("from Categorie").getResultList();
        } catch (Exception e) {
            logger.debug("class categorieDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return categorie.size();
    }
}
