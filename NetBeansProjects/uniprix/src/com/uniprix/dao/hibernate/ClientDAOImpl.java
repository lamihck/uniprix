package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import com.uniprix.dao.ClientDAO;
import com.uniprix.model.Client;
import com.uniprix.model.Login;

public class ClientDAOImpl implements ClientDAO {

    /**
     *
     */
    public ClientDAOImpl() {
        super();
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class);

    /**
     * @param emf
     */
    public ClientDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @Override
    public List<Client> getAll() {
        List<Client> client = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        client = (List<Client>) em.createQuery("from Client").getResultList();
        tx.commit();
        em.close();
        return client;
    }

    @Override
    public Client getByLogin(String login) {
        Client client = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        client = (Client) em.createQuery("from Client s where s.login.login = :login")
                .setParameter("login", login)
                .getSingleResult();

        if (client == null) {
            System.out.println("Pas de client pour se login");
        }

        tx.commit();
        em.close();
        return client;
    }

    @Override
    public Client getById(long idclient) {
        Client client = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        client = em.find(Client.class, idclient);

        tx.commit();
        em.close();
        return client;
    }

    @Override
    public void saveClient(Client client) { // ajouter le login ici, commit ensemble
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(client);
            em.persist(client.getLogin());

            client.setLogin(client.getLogin());
            client.getLogin().setLoginClient(client);
        } catch (Exception e) {
            logger.debug("class clientDaoimpl ... method saveClient ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public void saveClient(Client client, Login login) { // ajouter le login ici, commit ensemble
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(client);
            em.persist(login);
            client.setLogin(login);
            login.setLoginClient(client);
        } catch (Exception e) {
            logger.debug("class clientDaoimpl ... method saveClient ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public void delete(long idclient) {
        Client client = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        client = em.find(Client.class, idclient);
        em.remove(client);
        tx.commit();
        em.close();

    }

    @Override
    public void update(Client Client) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(Client);
        tx.commit();
        em.close();
    }

    @Override
    public int countAll() {
        List<Client> Client = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Client = (List<Client>) em.createQuery("from Client").getResultList();
        tx.commit();
        em.close();
        return Client.size();
    }
}
