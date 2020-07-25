package org.sid.service.impl;

import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.dao.RecruteurRepository;
import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.Recruteur;
import org.sid.service.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruteurServiceImpl implements RecruteurService {
	
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public List<Categorie> findAllCategories(String email) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		return recruteur.getCategories();
	}

	@Override
	public List<Question> findQuestionsByCategorie(String email, long idCat) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		Categorie categorie = categorieRepository.findById(idCat).get();
		int index = recruteur.getCategories().indexOf(categorie);
		if(index!=-1)
			return categorie.getQuestions();
		return null;
	}

}
