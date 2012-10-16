package com.uniprix.listener;

// Listener est applé quand le server demare. Contient la création de l'a
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextEntityManagerListener
 *
 */
public class ContextEntityManagerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextEntityManagerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent e) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasePU");
		e.getServletContext().setAttribute("emf", emf);

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent e) {

		EntityManagerFactory emf = (EntityManagerFactory) e.getServletContext().getAttribute("emf");
		emf.close();

    }
	
}
