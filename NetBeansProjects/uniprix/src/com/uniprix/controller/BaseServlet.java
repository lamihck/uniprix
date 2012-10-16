package com.uniprix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.uniprix.ClassUtilitaire.CategorieProduit;
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.hibernate.CategorieDAOImpl;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.Produit;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	
	
	public List<CategorieProduit> getCategorieProduit(){
		
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
	
		CategorieDAOImpl categorieDAO = new CategorieDAOImpl(emf);
		List<CategorieProduit> listCategorieProduit = new ArrayList<CategorieProduit>();
	
		List<Categorie> listCategorie = categorieDAO.getAll();
		
		for (Categorie categorie : listCategorie) {
			CategorieProduit categorieProduit = new CategorieProduit(emf,
					categorie.getCategorieId());
			listCategorieProduit.add(categorieProduit);
		}
	
		return listCategorieProduit;
	}
	
	public Client getClientConnected(HttpServletRequest request){
		if (request.getUserPrincipal() == null) {
			return null;
		} else {
			request.getUserPrincipal().getName();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasePU");
			ClientDAO clientDAO = new ClientDAOImpl(emf);
			Client client = clientDAO.getByLogin(request.getUserPrincipal().getName());
			
			System.out.println(client.getNom());
			
			if(client.getNom() == null || client.getPrenom() == null || client.getLogin() == null)
				return null;
			else
				return client;
		}
		
	}
	
	public Document createPdf(long idcommande,HttpServletResponse response){		
		response.setContentType("application/pdf");
		/*response.setHeader("Content-Disposition",
				 " attachment; filename=\"Facture.pdf\"");*/
		Document document = new Document();
		try{
			PdfWriter.getInstance(document,response.getOutputStream());
			document.open();

//			Image img = Image.getInstance(fdf.getAttachedFile("image"));
//            img.scaleToFit(100, 100);
//            img.setAbsolutePosition(90, 590);
//            stamper.getOverContent(1).addImage(img);
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			CommandeDAO commandeDAO = new CommandeDAOImpl(emf);
			Commande commande2 = commandeDAO.getByIdCommande(idcommande);
			
			Paragraph para1 = new Paragraph("Récapitulatif de la facture",FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			para1.setAlignment(Element.ALIGN_CENTER);
			Paragraph para2 = new Paragraph(".....................................................................................");
			para2.setAlignment(Element.ALIGN_CENTER);
			Paragraph para3 = new Paragraph("Entreprise Uniprix             Date: "+commande2.getDate(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			Paragraph para4 = new Paragraph("Facture Numéro : "+commande2.getCommandeId(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			Paragraph para5 = new Paragraph("Client : "+commande2.getClient().getNom()+" "+commande2.getClient().getPrenom(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			Paragraph para6 = new Paragraph("           "+commande2.getAdresse(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			Paragraph para7 = new Paragraph("Nombre d'article(s) : "+commande2.getCommandeLignecommande().size(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
			Paragraph para8 = new Paragraph(" ");
			
			document.add(para1);
			document.add(para2);
			document.add(para3);
			document.add(para4);
			document.add(para5);
			document.add(para6);
			document.add(para7);
			document.add(para8);
			
			PdfPTable table = new PdfPTable(4);
			table.addCell("Nom Produit");
			table.addCell("Quantité");
			table.addCell("Prix Unitaire €");
			table.addCell("Prix Total €");
			
			double TotCommande=0;
			
			List<LigneCommande> commandeTemp = commande2.getCommandeLignecommande();
			for (LigneCommande ligneCommande : commandeTemp) {
				table.addCell(ligneCommande.getArticle().getNom());
				table.addCell(ligneCommande.getQuantite()+"");
				table.addCell(ligneCommande.getArticle().getPrix()+" €");
				double tot=ligneCommande.getArticle().getPrix()*ligneCommande.getQuantite();
				table.addCell(tot+"");
				TotCommande+=tot;
			}
			table.addCell("");
			table.addCell("");
			table.addCell("TOTAL");
			table.addCell(TotCommande+" €");
			// Code 4
			document.add(table);		
			document.close(); 
		}catch(DocumentException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	
	
	
	}

}
