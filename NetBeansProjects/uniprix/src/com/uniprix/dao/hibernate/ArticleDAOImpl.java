package com.uniprix.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.uniprix.dao.ArticleDAO;
import com.uniprix.dao.ProduitDAO;
import com.uniprix.model.Article;
import com.uniprix.model.Categorie;
import com.uniprix.model.Client;
import com.uniprix.model.Produit;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ArticleDAOImpl implements ArticleDAO{

	/**
	 * 
	 */
	public ArticleDAOImpl() {
		super();
	}

	private EntityManagerFactory emf;
	private static final Logger logger=Logger.getLogger(ArticleDAOImpl.class);	

	/**
	 * @param emf
	 */
	public ArticleDAOImpl(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	@Override
	public List<Article> getAll() {
            
                HibernateTemplate template = new HibernateTemplate();
                return (List<Article>)template.find("FROM Article");
		/*List<Article> article=null;
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			article=(List<Article>)em.createQuery("from Article").getResultList();
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method getAll ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}
		return article;*/
	}

	@Override
	public Article getByIdArticle(long idarticle) {
		Article article=null;
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			article=em.find(Article.class, idarticle);
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method getByIdArticle ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}
		return article;
	}

	@Override
	public void saveArticle(Article article) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			//Produit produit=em.find(Produit.class, article.getProduits().get(0).getProduitId());
			//produit.getArticles().add(article);
			em.persist(article);
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method saveArticle ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}	
	}

	@Override
	public void delete(long idarticle) {
		Article article=null;
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			article=em.find(Article.class, idarticle);
			List<Produit> produits= article.getProduits();
			for(Produit p:produits){
				p.getArticles().remove(article);
			}
			em.remove(article);
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method delete ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}
	}

	@Override
	public void update(Article article) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			em.merge(article);	
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method update ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}
	}

	@Override
	public int countAll() {
		List<Article> article=null;
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			article=(List<Article>)em.createQuery("from Article").getResultList();
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method countAll ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}
		return article.size();
	}

	@Override
	public List<Article> getArticleByIdProduit(long idproduit) {
		List<Article> articles=null;
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			String SQL_QUERY="Select distinct a from Article a LEFT JOIN FETCH" +
					" a.produits pr where pr.produitId=:id";
			Query query3=em.createQuery(SQL_QUERY);
			query3.setParameter("id",idproduit);
			articles=query3.getResultList();
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method getArticleByIdProduit ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}		
		return articles;
	}

	@Override
	public void attacheProduitArticle(long idProd, long idArt) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			Produit produit=em.find(Produit.class, idProd);
			Article article=em.find(Article.class, idArt);
			produit.getArticles().add(article);
			article.getProduits().add(produit);
		} catch (Exception e) {
			logger.debug("class articleDaoimpl ... method attacheProduitArticle ... exception: "+e);
		}finally{
			tx.commit();
			em.close();
		}	
	}
	
	@Override
	public  List<Article> findByLike(String chaineCaractere) {
		List<Article> list  = null;
		 
		// on r�cup�re l'emf
		EntityManagerFactory emf = this.emf;
		
		// on trouve la DAO de produit
		//ArticleDAO articleDAO = new ArticleDAOImpl(emf);
		
		EntityManager em=emf.createEntityManager();
		
		
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		// on pr�pare la requ�te avec Like %%
		list = (List<Article>)em.createQuery("from Article s where s.description like :motclef OR s.nom like :motclef ")
				// on introduit la variable chaineCaract�re
				.setParameter("motclef", "%"+chaineCaractere+"%")
				.getResultList();
		
		tx.commit();
		em.close();
		// on regarde la size de notre resultat
		if(list.size() <= 0)
		{
			System.out.println("Pas de r�sultat");
			System.out.println("Pas de r�sultat");
			// si c'est <=0 on renvoit null
			return null;
		}
		
		// sinon on renvois la liste
		System.out.println("nombre d'article trouv� " + list.size());
		return list;
	}
		
	}



