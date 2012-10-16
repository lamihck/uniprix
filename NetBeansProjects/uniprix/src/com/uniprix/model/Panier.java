package com.uniprix.model;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.uniprix.dao.hibernate.ArticleDAOImpl;

public class Panier {

	private List<LignePanier> lignepanier;

	public Panier() {
		super();
		lignepanier = new ArrayList<LignePanier>();
	}

	public boolean exist(int articleId) {
		boolean flag = false;
		for (LignePanier ligne : lignepanier)
			if (ligne.getArticle().getArticleId() == articleId)
				flag = true;
		return flag;
	}

	public void edit(int articleId, int quantite) {

		if (articleId != 0) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("BasePU");
			ArticleDAOImpl articledao = new ArticleDAOImpl(emf);

			// System.out.println("On cherche l'article dans la BDD");
			Article article = articledao.getByIdArticle(articleId);
			// System.out.println("Article trouvé !");
			if (article != null) {

				if (quantite <= 0) {

					LignePanier temp = null;
					// System.out.println("Quantité <= 0, on supprime l'article");
					for (LignePanier ligne : lignepanier)
						if (ligne.getArticle().getArticleId() == article
								.getArticleId())
							temp = ligne;
					lignepanier.remove(temp);
				} else {

					// System.out.println("Quantité valide : " + quantite);

					boolean flag = false;

					if (lignepanier != null) {
						// System.out.println("Le panier contient des lignes !");
						for (LignePanier ligne : lignepanier)
							if (ligne.getArticle().getArticleId() == article
									.getArticleId()) {
								ligne.setQuantite(quantite);
								flag = true;
							}
					}

					if (!flag) {
						System.out
								.println("L'article n'existe pas encore, on l'ajoute !");
						// System.out.println("Nom de l'article : " +
						// article.getNom());
						// System.out.println("Quantité         : " + quantite);
						LignePanier lignetemp = new LignePanier(article,
								quantite);
						lignepanier.add(lignetemp);
						// System.out.println("Article ajouté !");
					}
				}

			}

		}

	}

	public List<LignePanier> getLignepanier() {
		return lignepanier;
	}

	public void setLignepanier(List<LignePanier> lignepanier) {
		this.lignepanier = lignepanier;
	}

}
