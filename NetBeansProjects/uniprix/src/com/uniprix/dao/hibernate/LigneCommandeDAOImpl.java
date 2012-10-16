package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.model.Article;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.Produit;

public class LigneCommandeDAOImpl implements LigneCommandeDAO {

    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(LigneCommandeDAOImpl.class);

    /**
     *
     */
    public LigneCommandeDAOImpl() {
        super();
    }

    /**
     * @param emf
     */
    public LigneCommandeDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @Override
    public List<LigneCommande> getAll() {
        List<LigneCommande> ligneCommande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            ligneCommande = (List<LigneCommande>) em.createQuery("from LigneCommande").getResultList();
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method getAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return ligneCommande;
    }

    @Override
    public LigneCommande getByIdLigneCommande(long idlignecommande) {
        LigneCommande ligne = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            ligne = em.find(LigneCommande.class, idlignecommande);
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method getByIdLigneCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return ligne;
    }

    @Override
    public List<LigneCommande> getByIdCommande(long idcommande) {
        List<LigneCommande> ligneCommande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            String SQL_QUERY = "from LigneCommande l where l.commande.commandeId=:id";
            Query query3 = em.createQuery(SQL_QUERY);
            query3.setParameter("id", idcommande);
            ligneCommande = query3.getResultList();
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method getByIdCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return ligneCommande;
    }

    @Override
    public List<LigneCommande> getByIdArticle(long idarticle) {
        List<LigneCommande> ligneCommande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            String SQL_QUERY = "from LigneCommande l where l.article.articleId=:id";
            Query query3 = em.createQuery(SQL_QUERY);
            query3.setParameter("id", idarticle);
            ligneCommande = query3.getResultList();
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method getByIdArticle ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return ligneCommande;
    }

    @Override
    public void saveLigneCommande(LigneCommande lignecommande) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(lignecommande);
        } catch (Exception e) {
            logger.debug("class lignecommandeeDaoimpl ... method saveLigneCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void delete(long idlignecommande) {
        LigneCommande ligneCommande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            ligneCommande = em.find(LigneCommande.class, idlignecommande);
            em.remove(ligneCommande);
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method delete ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void update(LigneCommande lignecommande) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(lignecommande);
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method update ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void attacheCommandeLigneCommande(long idcom, long idlignecom) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Commande commande = em.find(Commande.class, idcom);
            LigneCommande ligneCommande = em.find(LigneCommande.class, idlignecom);
            commande.getCommandeLignecommande().add(ligneCommande);
            ligneCommande.setCommande(commande);
        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method attacheCommandeLigneCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void attacheArticleLigneCommande(long idarticle, long idlignecom) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Article article = em.find(Article.class, idarticle);
            LigneCommande ligneCommande = em.find(LigneCommande.class, idlignecom);
            ligneCommande.setArticle(article);
            //article.setLigneCommande(ligneCommande);

        } catch (Exception e) {
            logger.debug("class ligneCommandeDaoimpl ... method attacheArticleLigneCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public int countAll() {
        List<LigneCommande> ligneCommande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            ligneCommande = (List<LigneCommande>) em.createQuery("from LigneCommande").getResultList();
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return ligneCommande.size();
    }

    @Override
    public void deletelignecommande(long idcommande, long idlignecommande) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Commande commande = em.find(Commande.class, idcommande);
            LigneCommande lignecom = em.find(LigneCommande.class, idlignecommande);
            commande.getCommandeLignecommande().remove(lignecom);
            em.remove(lignecom);
            //em.merge(commande);

        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public double sousTotalLigne(long idlignecommande) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        double total = 0;
        try {

            LigneCommande lignecom = em.find(LigneCommande.class, idlignecommande);
            total = lignecom.getQuantite() * lignecom.getPrix();

        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return total;
    }
}
