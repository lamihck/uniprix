package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import com.uniprix.dao.LoginDAO;
import com.uniprix.model.Login;

public class LoginDAOImpl implements LoginDAO {

    /**
     *
     */
    public LoginDAOImpl() {
        super();
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class);

    /**
     * @param emf
     */
    public LoginDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @Override
    public List<Login> getAll() {
        List<Login> Login = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Login = (List<Login>) em.createQuery("from Login").getResultList();
        tx.commit();
        em.close();
        return Login;
    }

    @Override
    public Login getById(long idLogin) {
        Login Login = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Login = em.find(Login.class, idLogin);

        tx.commit();
        em.close();
        return Login;
    }

    @Override
    public void saveLogin(Login Login) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(Login);
        } catch (Exception e) {
            logger.debug("class LoginDaoimpl ... method saveLogin ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }

    @Override
    public void delete(long idLogin) {
        Login Login = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Login = em.find(Login.class, idLogin);
        em.remove(Login);
        tx.commit();
        em.close();

    }

    @Override
    public void update(Login Login) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(Login);
        tx.commit();
        em.close();
    }

    @Override
    public int countAll() {
        List<Login> Login = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Login = (List<Login>) em.createQuery("from Login").getResultList();
        tx.commit();
        em.close();
        return Login.size();
    }
    /*
     @Override
     public boolean verifyLogin(String logintxt, String password) {
     Login login=null;
     EntityManager em=emf.createEntityManager();
     EntityTransaction tx=em.getTransaction();
     tx.begin();
     login= (Login)em.createQuery("from Login s where s.login = :login").setParameter("login", logintxt).getSingleResult();
     tx.commit();
     em.close();
		
     if(login != null)
     if(login.getLogin() == logintxt && login.getPassword() == password)
     return true;
		
     return false;
     }*/
}
