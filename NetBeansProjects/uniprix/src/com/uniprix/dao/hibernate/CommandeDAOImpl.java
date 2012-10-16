package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.uniprix.dao.CategorieDAO;
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.Produit;

public class CommandeDAOImpl implements CommandeDAO {

    /**
     *
     */
    public CommandeDAOImpl() {
        super();
    }

    /**
     * @param emf
     */
    public CommandeDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(CommandeDAOImpl.class);

    @Override
    public List<Commande> getAll() {
        List<Commande> commande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            commande = (List<Commande>) em.createQuery("from Commande").getResultList();
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method getAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return commande;
    }

    @Override
    public Commande getByIdCommande(long idcommande) {
        Commande commande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            commande = em.find(Commande.class, idcommande);
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method getById ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return commande;
    }

    @Override
    public void saveCommande(Commande commande) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(commande);
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method saveCommande ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void delete(long idcommande) {
        Commande commande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            commande = em.find(Commande.class, idcommande);
            em.remove(commande);
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method delete ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void update(Commande commande) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(commande);
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method update ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
    }

    @Override
    public void attacheCommandeLigneCommande(long idcom, long idlignecom) {
        /*EntityManager em=emf.createEntityManager();
         EntityTransaction tx=em.getTransaction();
         tx.begin();
         try {
         LigneCommande lignecommande=em.find(LigneCommande.class, idlignecom);
         Commande commande=em.find(Commande.class, idcom);
         categorie.getCategorieProduit().add(produit);
         produit.setCategorie(categorie);
         } catch (Exception e) {
         logger.debug("class produitDaoimpl ... method attacheProdCategorie ... exception: "+e);
         }finally{
         tx.commit();
         em.close();
         }	*/
    }

    @Override
    public void attacheCommandeClient(long idcom, long idclient) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Client client = em.find(Client.class, idclient);
            Commande commande = em.find(Commande.class, idcom);
            client.getClientCommande().add(commande);
            commande.setClient(client);
        } catch (Exception e) {
            logger.debug("class produitDaoimpl ... method attacheProdCategorie ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public int countAll() {
        List<Commande> commande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            commande = (List<Commande>) em.createQuery("from Commande").getResultList();
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method countAll ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return commande.size();
    }

    @Override
    public List<Commande> getByIdClient(long idclient) {
        List<Commande> commande = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            String SQL_QUERY = "from Commande c where c.client.clientId=:id";
            Query query3 = em.createQuery(SQL_QUERY);
            query3.setParameter("id", idclient);
            commande = query3.getResultList();
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method getByIdClient ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return commande;
    }

    @Override
    public double totalCommande(long commandeId,
            List<LigneCommande> lignecommandes) {
        double total = 0;
        LigneCommandeDAO lignecommandeDAO = new LigneCommandeDAOImpl(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            for (LigneCommande lignecom : lignecommandes) {
                //em.find(LigneCommande.class, lignecom.getLigneCommandeId());
                total += lignecommandeDAO.sousTotalLigne(lignecom.getLigneCommandeId());
            }
        } catch (Exception e) {
            logger.debug("class commandeDaoimpl ... method getByIdClient ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }
        return total;
    }
}
