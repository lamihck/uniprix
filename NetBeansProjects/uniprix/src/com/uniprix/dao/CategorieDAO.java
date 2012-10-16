package com.uniprix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.uniprix.model.Categorie;

public interface CategorieDAO {

    public List<Categorie> getAll();

    public Categorie getById(long idcategorie);

    public void saveCategorie(Categorie categorie);

    public void delete(long idcategorie);

    public void update(Categorie categorie);

    public int countAll();
}
