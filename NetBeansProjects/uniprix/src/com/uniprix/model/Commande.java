package com.uniprix.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private long commandeId;
    private String date;
    @Column(length = 400)
    private String adresse;
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LigneCommande> commandeLignecommande = new ArrayList<LigneCommande>();
    @ManyToOne(cascade = CascadeType.MERGE)
    private Client client;

    /**
     * @param date
     * @param adresse
     * @param client
     */
    public Commande(String date, String adresse, Client client) {
        super();
        this.date = date;
        this.adresse = adresse;
        this.client = client;
    }

    /**
     * @param date
     * @param adresse
     * @param commandeLignecommande
     * @param client
     */
    public Commande(String date, String adresse,
            List<LigneCommande> commandeLignecommande, Client client) {
        super();
        this.date = date;
        this.adresse = adresse;
        this.commandeLignecommande = commandeLignecommande;
        this.client = client;
    }

    /**
     * @param date
     * @param commandeLignecommande
     * @param client
     */
    public Commande(String date, List<LigneCommande> commandeLignecommande,
            Client client) {
        super();
        this.date = date;
        this.commandeLignecommande = commandeLignecommande;
        this.client = client;
    }

    /**
     * @param date
     * @param adresse
     * @param commandeLignecommande
     */
    public Commande(String date, String adresse,
            List<LigneCommande> commandeLignecommande) {
        super();
        this.date = date;
        this.adresse = adresse;
        this.commandeLignecommande = commandeLignecommande;
    }

    /**
     * @param date
     * @param idClient
     * @param nomClient
     * @param telephoneClient
     * @param adresse
     */
    public Commande(String date, String adresse) {
        super();
        this.date = date;
        this.adresse = adresse;
    }

    public Commande() {
        commandeLignecommande = new ArrayList<LigneCommande>();
    }

    public long getCommandeId() {
        return commandeId;
    }

    /* GETTER SETTER */
    public void setCommandeId(long commandeId) {
        this.commandeId = commandeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<LigneCommande> getCommandeLignecommande() {
        return commandeLignecommande;
    }

    public void setCommandeLignecommande(List<LigneCommande> commandeLignecommande) {
        this.commandeLignecommande = commandeLignecommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
