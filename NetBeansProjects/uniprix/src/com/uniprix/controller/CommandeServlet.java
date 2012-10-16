package com.uniprix.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMultipart;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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
import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.CommandeDAO;
import com.uniprix.dao.LigneCommandeDAO;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.CommandeDAOImpl;
import com.uniprix.dao.hibernate.LigneCommandeDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.Commande;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.LignePanier;
import com.uniprix.model.Panier;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		Panier panier = null;

		// On récupère les données du client
			Client client = getClientConnected(request);
			request.setAttribute("client", client);


		// Si il a remplis les info de paiement
			if (request.getParameter("paiementNom") != null
					&& request.getParameter("paiementPrenom") != null
					&& request.getParameter("paiementAdresse") != null
					&& !"".equals(request.getParameter("paiementNom"))
					&& !"".equals(request.getParameter("paiementPrenom"))
					&& !"".equals(request.getParameter("paiementAdresse")))
			{
				System.out.println("Login : "+client.getLogin().getLogin());

				// si son panier est null, on en créer un fictif
				if (request.getSession().getAttribute("panier") == null){
					request.getSession().setAttribute("panier", new Panier());
					}//fin if panier

				// on stock le panier
				panier = (Panier) request.getSession().getAttribute("panier");

				// si le panier est vide, cette fois on traite l'erreur
				if (panier.getLignepanier().size() <= 0) {
					System.out.println("Le panier est vide, aucun paiement possible.");
				} else { // sinon on commence a le transformer en Commande
					EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
					CommandeDAO commandeDAO = new CommandeDAOImpl(emf);
					
					// on commence a remplire la commande
					Commande commande = new Commande();

					LigneCommandeDAO lignecommandeDAO = new LigneCommandeDAOImpl(emf);

					commande.setClient(client);
					commande.setAdresse((String) request.getParameter("paiementAdresse"));

					Date maDateAvecFormat = new Date();

					SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");

					commande.setDate(dateStandard.format(maDateAvecFormat));

					List<LignePanier> lignes = panier.getLignepanier();
					List<LigneCommande> lignescommande = null;

					commandeDAO.saveCommande(commande);

					// on brasse le panier pour remplire les lignes de commande
					for (LignePanier lignePanier : lignes) {
						LigneCommande ligneCommande = new LigneCommande();

						ligneCommande.setArticle(lignePanier.getArticle());

						ligneCommande.setQuantite(lignePanier.getQuantite());
						ligneCommande.setPrix(lignePanier.getArticle().getPrix());

						lignecommandeDAO.saveLigneCommande(ligneCommande);

						lignecommandeDAO.attacheArticleLigneCommande(
								lignePanier.getArticle().getArticleId(),
								ligneCommande.getLigneCommandeId());

						lignecommandeDAO.attacheCommandeLigneCommande(
								commande.getCommandeId(),
								ligneCommande.getLigneCommandeId());
						commandeDAO.attacheCommandeLigneCommande(
								commande.getCommandeId(),
								ligneCommande.getLigneCommandeId());
					}//fin for ligne panier

					// on stock les données de la commande pour l'affichage du récap
					request.setAttribute("commande", commande);
					
					System.out.println("Commande en cours :" + commande.getCommandeId() + " lignes");
					Commande commande2 = commandeDAO.getByIdCommande(commande.getCommandeId());
					System.out.println("Commande2 en cours :" + commande2.getCommandeLignecommande().size() + " lignes");

					
					/****************************************************************************/
					/*      PARTIE PDF        */
					
					response.setContentType("application/pdf");
					/*response.setHeader("Content-Disposition",
							 " attachment; filename=\"Facture.pdf\"");*/
					
					Document document = new Document();
					try{
						PdfWriter.getInstance(document, new FileOutputStream("C:/wamp/www/Facture.pdf"));
						//PdfWriter.getInstance(document,response.getOutputStream());
						document.open();

						/*Image img = Image.getInstance(fdf.getAttachedFile("image"));
		                img.scaleToFit(100, 100);
		                img.setAbsolutePosition(90, 590);
		                stamper.getOverContent(1).addImage(img);*/
						
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
						
						/*File delfile=new File("C:/wamp/www/HelloWorld.pdf");
						delfile.delete();*/
						
						document.close(); 
					}catch(DocumentException e){
						e.printStackTrace();
					}
					/******************************************************************/
					
					
					
					String username = "formation167@gmail.com";
					String password = "formation167";

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");

					Authenticator authenticator = new Authenticator(username,
							password);

					Session session = Session.getInstance(props, authenticator);

					try {

						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(
								"formation167@gmail.com"));
						message.setRecipients(Message.RecipientType.TO,
								InternetAddress.parse(client.getMail()));
						message.setSubject("Récapitulatif de votre commande");

						MimeBodyPart mbp1 = new MimeBodyPart();
						
						MimeBodyPart mbp2 = new MimeBodyPart();		
						String file = "C:/wamp/www/Facture.pdf";
						mbp2.attachFile(file);
						
						String messages = "<b>Bonjour " + client.getNom() + " "	+ client.getPrenom() + ",</b><br/><br/>";
						messages += "Nous confirmons que la commande numéro <i>" + commande2.getCommandeId() + "</i> est bien confirmée.<br/><br/>";

						List<LigneCommande> lignescommandes = commande2
								.getCommandeLignecommande();
						
						messages += "<table><tr><td>Nom du produit</td><td>Quantité</td><td>Prix unitaire<td/><td>Prix total<td/></tr>";
						int total = 0;
						for (LigneCommande ligneCommande2 : lignescommandes) {
							messages += "<tr>";
							messages += "<td>"+ligneCommande2.getArticle().getNom() +"</td>"
										+"<td>"+ligneCommande2.getQuantite()+"</td>"
										+"<td>"+ligneCommande2.getPrix()+"</td>"
										+"<td>"+ligneCommande2.getPrix()*ligneCommande2.getQuantite()+"</td>";
							messages += "</tr>";
							total += ligneCommande2.getPrix()*ligneCommande2.getQuantite();
						}
						
						messages += "<tr><td/><td/><td>Total</td><td><b>"+total+"</b></td></tr>";
						
						messages += "</table>";
						
						messages += "<br/><br/>Merci de votre confiance, à bientôt !";
						
						messages += "<br/><br/>L'équipe UniPrix.";

						mbp1.setContent(messages, "text/html");
						//mbp1.setText(messages);

						
						MimeMultipart mp = new MimeMultipart();
						mp.addBodyPart(mbp1);
						mp.addBodyPart(mbp2);
						message.setContent(mp, "text/html");
						
						Transport.send(message);

						System.out.println("Done");

					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}//fin try catch
					
					
					/****************************************************************************/
					/*      PARTIE PDF        */
//					
//					response.setContentType("application/pdf");
//					/*response.setHeader("Content-Disposition",
//							 " attachment; filename=\"Facture.pdf\"");*/
//					
//					Document document = new Document();
//					try{
//						PdfWriter.getInstance(document, new FileOutputStream("C:/wamp/www/HelloWorld.pdf"));
//						//PdfWriter.getInstance(document,response.getOutputStream());
//						document.open();
//
//						/*Image img = Image.getInstance(fdf.getAttachedFile("image"));
//		                img.scaleToFit(100, 100);
//		                img.setAbsolutePosition(90, 590);
//		                stamper.getOverContent(1).addImage(img);*/
//						
//						Paragraph para1 = new Paragraph("Récapitulatif de la facture",FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						para1.setAlignment(Element.ALIGN_CENTER);
//						Paragraph para2 = new Paragraph(".....................................................................................");
//						para2.setAlignment(Element.ALIGN_CENTER);
//						Paragraph para3 = new Paragraph("Entreprise Uniprix             Date: "+commande2.getDate(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						Paragraph para4 = new Paragraph("Facture Numéro : "+commande2.getCommandeId(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						Paragraph para5 = new Paragraph("Client : "+commande2.getClient().getNom()+" "+commande2.getClient().getPrenom(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						Paragraph para6 = new Paragraph("           "+commande2.getAdresse(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						Paragraph para7 = new Paragraph("Nombre d'article(s) : "+commande2.getCommandeLignecommande().size(),FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
//						Paragraph para8 = new Paragraph(" ");
//						
//						document.add(para1);
//						document.add(para2);
//						document.add(para3);
//						document.add(para4);
//						document.add(para5);
//						document.add(para6);
//						document.add(para7);
//						document.add(para8);
//						
//						PdfPTable table = new PdfPTable(4);
//						table.addCell("Nom Produit");
//						table.addCell("Quantité");
//						table.addCell("Prix Unitaire €");
//						table.addCell("Prix Total €");
//						
//						double TotCommande=0;
//						
//						List<LigneCommande> commandeTemp = commande2.getCommandeLignecommande();
//						for (LigneCommande ligneCommande : commandeTemp) {
//							table.addCell(ligneCommande.getArticle().getNom());
//							table.addCell(ligneCommande.getQuantite()+"");
//							table.addCell(ligneCommande.getArticle().getPrix()+" €");
//							double tot=ligneCommande.getArticle().getPrix()*ligneCommande.getQuantite();
//							table.addCell(tot+"");
//							TotCommande+=tot;
//						}
//						table.addCell("");
//						table.addCell("");
//						table.addCell("TOTAL");
//						table.addCell(TotCommande+" €");
//						// Code 4
//						document.add(table);
//						
//						/*File delfile=new File("C:/wamp/www/HelloWorld.pdf");
//						delfile.delete();*/
//						
//						document.close(); 
//					}catch(DocumentException e){
//						e.printStackTrace();
//					}
					File delfile=new File("C:/wamp/www/Facture.pdf");
					delfile.delete();
					
					/******************************************************************/
					/*        FIN PARTIE PDF      */

					
					System.out.println("Redirection");
					request.getSession().setAttribute("panier", null);
					request.setAttribute("idcom", commande2.getCommandeId());
					//RequestDispatcher dispath = request.getRequestDispatcher("home.jsp");
					RequestDispatcher dispath = request.getRequestDispatcher("CategoriesServlet");
					//System.out.println(dispath);
					dispath.forward(request, response);

				}//fin size >0

			} else {
				RequestDispatcher dispath = request.getRequestDispatcher("commande.jsp");
				dispath.forward(request, response);
			}
	}

	private static class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator(String username, String password) {
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
}
