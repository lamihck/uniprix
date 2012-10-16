package com.uniprix.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client {
	
	
	@Id
	@GeneratedValue
	private long clientId;	
	
	private String prenom;
	private String nom;
	private String telephone;
	private String mail;
	private String adresse1;
	private String adresse2;
	private String pays;
	private String ville;
	private String cp;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Login_Id",referencedColumnName="LoginId")
	private Login login;
	
	@OneToMany(mappedBy="client",cascade=CascadeType.MERGE)
	private List<Commande> clientCommande=new ArrayList<Commande>();
	

	/**
	 * @param prenom
	 * @param nom
	 * @param telephone
	 * @param mail
	 * @param adresse1
	 * @param adresse2
	 * @param pays
	 * @param ville
	 * @param cp
	 * @param login
	 * @param commandeClient
	 */
	public Client(String prenom, String nom, String telephone, String mail,
			String adresse1, String adresse2, String pays, String ville,
			String cp, Login login) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.pays = pays;
		this.ville = ville;
		this.cp = cp;
		this.login = login;
	}

	
	public Client(Login login, String prenom,
			String nom, String telephone, String mail, String adresse1,
			String adresse2, String pays, String ville, String cp) {
		super();
		this.login = login;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.pays = pays;
		this.ville = ville;
		this.cp = cp;
	}
	
	public Client(String prenom,
			String nom, String telephone, String mail, String adresse1,
			String adresse2, String pays, String ville, String cp) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.pays = pays;
		this.ville = ville;
		this.cp = cp;
	}
	


	public Login getLogin() {
		return login;
	}




	public void setLogin(Login login) {
		this.login = login;
	}




	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getClientId() {
		return clientId;
	}


	public void setClientId(long clientId) {
		this.clientId = clientId;
	}




	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getAdresse1() {
		return adresse1;
	}


	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}


	public String getAdresse2() {
		return adresse2;
	}


	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", prenom=" + prenom + ", nom="
				+ nom + ", telephone=" + telephone + ", mail=" + mail
				+ ", adresse1=" + adresse1 + ", adresse2=" + adresse2
				+ ", pays=" + pays + ", ville=" + ville + ", cp=" + cp
				+ ", login=" + login + "]";
	}


	public List<Commande> getClientCommande() {
		return clientCommande;
	}


	public void setClientCommande(List<Commande> clientCommande) {
		this.clientCommande = clientCommande;
	}
	
	//Gérer les roles des clients onetoOne avec role
	
	
}
