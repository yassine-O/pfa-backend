package org.sid.service;

import java.util.List;

import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.Test;

public interface RecruteurService {
	
	public List<Categorie> findAllCategories(String email);
	public List<Question> findQuestionsByCategorie(String email, long idCat);
	public Categorie addCategorie(String email, String libelle);
	public Question addQuestion(long idCategorie, String titre);
	
	public void addTest(String email, Test test);

}
