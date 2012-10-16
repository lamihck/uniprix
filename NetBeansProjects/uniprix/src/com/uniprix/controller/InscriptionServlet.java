package com.uniprix.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.uniprix.dao.ClientDAO;
import com.uniprix.dao.LoginDAO;
import com.uniprix.dao.RoleDAO;
import com.uniprix.dao.hibernate.ClientDAOImpl;
import com.uniprix.dao.hibernate.LoginDAOImpl;
import com.uniprix.dao.hibernate.RoleDAOImpl;
import com.uniprix.model.Client;
import com.uniprix.model.LigneCommande;
import com.uniprix.model.Login;
import com.uniprix.model.Role;

/**
 * Servlet implementation class InscriptionServlet
 */
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String mail=request.getParameter("mail");
		String adresse1=request.getParameter("adresse1");
		String adresse2=request.getParameter("adresse2");
		String ville=request.getParameter("ville");
		String pays=request.getParameter("pays");
		String cp=request.getParameter("cp");
		String tel=request.getParameter("tel");
		String login=request.getParameter("login");
		String pass1=request.getParameter("pass1");
		String pass2=request.getParameter("pass2");
		int validator=0;
		
		Login l=new Login(login, pass1);
		Client c =new Client(prenom, nom, tel, mail, adresse1, adresse2, pays, ville, cp);
		request.setAttribute("clients", c);
		request.setAttribute("login", l);
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		/***
		 * 
		 * 				TEST SUR FORMULAIRE
		 * 
		 * ****/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasePU");
		
		if(		pass1 !=null && !"".equals(pass1)
				&& pass2 !=null && !"".equals(pass2)
				&& nom !=null && !"".equals(nom)
				&& prenom !=null && !"".equals(prenom)
				&& mail !=null && !"".equals(mail)
				&& adresse1 !=null && !"".equals(adresse1)
				&& ville !=null && !"".equals(ville)
				&& pays !=null && !"".equals(pays)
				&& cp !=null && !"".equals(cp)
				&& login !=null && !"".equals(login)
				){
			validator=0;
			System.out.println("champs pas null");
			//RequestDispatcher dispatch=request.getRequestDispatcher("index.jsp");
			//dispatch.forward(request, response);
		}
		else{
			System.out.println("Champs null");
			validator=1;
			request.setAttribute("errorNull", "Champs null");
			//RequestDispatcher dispatch=request.getRequestDispatcher("inscription.jsp");
			//dispatch.forward(request, response);
		}
		
		
		
		if(pass1.equals(pass2) && pass1 !=null && !"".equals(pass1)
				&& pass2 !=null && !"".equals(pass2)
				){
			validator=0;
			System.out.println("pass sont égaux");
			//RequestDispatcher dispatch=request.getRequestDispatcher("index.jsp");
			//dispatch.forward(request, response);
		}
		else{
			System.out.println("pas égaux , pas de validator");
			validator=1;
			request.setAttribute("errorPass", "les mots de passe sont différents");
			//RequestDispatcher dispatch=request.getRequestDispatcher("inscription.jsp");
			//dispatch.forward(request, response);
		}
		
		
		ClientDAO clientDAO = new ClientDAOImpl(emf);
		List<Client> clientlist=clientDAO.getAll();
		for(Client client:clientlist){
			if(nom.equals(client.getNom()) && prenom.equals(client.getPrenom()) && adresse1.equals(client.getAdresse1())
					&& mail.equals(client.getMail())){
				System.out.println("tout est pareil client");
				validator=1;
				request.setAttribute("errorClient", "le client est déjà inscrit");
				break;
			}
			else{
				System.out.println("client pas connu");
				System.out.println(nom);
				System.out.println(prenom);
				System.out.println(adresse1);
				System.out.println(mail);
				//validator=0;
				//RequestDispatcher dispatch=request.getRequestDispatcher("index.jsp");
				//dispatch.forward(request, response);
			}
		}
		
		LoginDAO loginDAO = new LoginDAOImpl(emf);
		List<Login> loginlist=loginDAO.getAll();
		for(Login loginExistant:loginlist){
			if(login.equals(loginExistant.getLogin())){
				System.out.println("tout est pareil login");
				validator=1;
				request.setAttribute("errorLogin", "le login est déjà existant");
				break;
				//RequestDispatcher dispatch=request.getRequestDispatcher("inscription.jsp");
				//dispatch.forward(request, response);
			}
			else{
				System.out.println("login pas connu");
				//validator=0;
				//RequestDispatcher dispatch=request.getRequestDispatcher("index.jsp");
				//dispatch.forward(request, response);
			}
		}
			System.out.println(validator);
		if(validator==0)
		{
			System.out.println("vers index");
			RoleDAO roleDAO;
			roleDAO = new RoleDAOImpl(emf);
			
			Role role = roleDAO.getRoleById(1);
			Login login2 = new Login(login, pass1,role);
			Client client2 = new Client(login2, prenom, nom, tel, mail, adresse1, adresse2, pays, ville, cp);
			clientDAO.saveClient(client2);
			
			/*******************************************************************************************/
			/*							PARTIE MAIL AVEC ENVOIE D'INFORMATIONS  	   				   */
			/*******************************************************************************************/
			
			
			String username = "formation167@gmail.com";
			String password = "formation167";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Authenticator authenticator = new Authenticator(username,password);

			Session session = Session.getInstance(props, authenticator);

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(
						"formation167@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(client2.getMail()));
				message.setSubject("Inscription réussit");

				MimeBodyPart mbp1 = new MimeBodyPart();
				
				String messages = "<b>Bonjour " + client2.getNom() + " "	+ client2.getPrenom() + ",</b><br/><br/>";
				messages += "<p>Nous avons le plaisir de vous acceuillir sur notre site." +
						" Vous y trouverez une multitude d'articles avec des prix <b style='color:red;'>IMBATTABLE</b>." +
						"<br/> N'oubliez pas chez nous le client est roi !!!</p>";

				messages +="<p>Informations personnelles</p>";
				messages +="<p> Login : "+login2.getLogin()+"<br/></p>";
				
				messages += "<br/><br/>Merci de votre confiance, à très bientôt !";
				
				messages += "<br/><br/>L'équipe UniPrix.";

				mbp1.setContent(messages, "text/html");
				//mbp1.setText(messages);

				
				MimeMultipart mp = new MimeMultipart();
				mp.addBodyPart(mbp1);
				message.setContent(mp, "text/html");
				
				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}//fin try catch
			
			/*******************************************************************************************/
			
			RequestDispatcher dispatch=request.getRequestDispatcher("CategoriesServlet");
			dispatch.forward(request, response);
		}
		else
		{
			System.out.println("vers inscription");
			RequestDispatcher dispatch=request.getRequestDispatcher("inscription.jsp");
			dispatch.forward(request, response);
		}
		//dispatch.forward(request, response);
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
