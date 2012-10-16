package com.uniprix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Login;

public interface ClientDAO {

    public List<Client> getAll();

    public Client getById(long idclient);

    public void saveClient(Client client);

    public void delete(long idclient);

    public void update(Client client);

    public int countAll();

    public void saveClient(Client client, Login login);

    public Client getByLogin(String login);
}
