package com.uniprix.dao;

import java.util.List;

import com.uniprix.model.LigneCommande;

public interface LigneCommandeDAO {

    public List<LigneCommande> getAll();

    public LigneCommande getByIdLigneCommande(long idlignecommande);

    public List<LigneCommande> getByIdCommande(long idcommande);

    public List<LigneCommande> getByIdArticle(long idarticle);

    public void saveLigneCommande(LigneCommande lignecommande);

    public void delete(long idlignecommande);

    public void update(LigneCommande lignecommande);

    public void attacheCommandeLigneCommande(long idcom, long idlignecom);

    public void attacheArticleLigneCommande(long idarticle, long idlignecom);

    public int countAll();

    public void deletelignecommande(long parseLong, long parseLong2);

    public double sousTotalLigne(long idlignecommande);
}
