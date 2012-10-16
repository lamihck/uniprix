package com.uniprix.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.uniprix.dao.RoleDAO;
import com.uniprix.model.Login;
import com.uniprix.model.Role;

public class RoleDAOImpl implements RoleDAO {

    /**
     *
     */
    public RoleDAOImpl() {
        super();
    }
    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class);

    /**
     * @param emf
     */
    public RoleDAOImpl(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }

    @Override
    public List<Role> getAll() {
        List<Role> Role = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Role = (List<Role>) em.createQuery("from Role").getResultList();
        tx.commit();
        em.close();
        return Role;
    }

    @Override
    public Role getRoleById(long idrole) {
        Role role = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        role = em.find(Role.class, idrole);

        tx.commit();
        em.close();
        return role;
    }

    @Override
    public void saveRole(Role role) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(role);
        } catch (Exception e) {
            logger.debug("class RoleDaoimpl ... method saveRole ... exception: " + e);
        } finally {
            tx.commit();
            em.close();
        }

    }
}
