package org.sid.service;

import java.util.List;

import org.sid.model.Categorie;
import org.sid.model.Question;

public interface RecruteurService {
	
	public List<Categorie> findAllCategories(String email);
	public List<Question> findQuestionsByCategorie(String email, long idCat);

}
