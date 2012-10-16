package com.uniprix.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.uniprix.model.Login;
import com.uniprix.model.Role;

public interface RoleDAO {

    public List<Role> getAll();

    public Role getRoleById(long idrole);

    public void saveRole(Role role);
}
