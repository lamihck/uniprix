package com.uniprix.dao;

import java.util.List;

import com.uniprix.model.Article;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;

public interface CommandeDAO {

    public List<Commande> getAll();

    public Commande getByIdCommande(long idcommande);

    public List<Commande> getByIdClient(long idclient);

    public void saveCommande(Commande commande);

    public void delete(long idcommande);

    public void update(Commande commande);

    public void attacheCommandeLigneCommande(long idcom, long idlignecom);

    public void attacheCommandeClient(long idcom, long idclient);

    public int countAll();

    public double totalCommande(long commandeId, List<LigneCommande> lignecommandes);
}
