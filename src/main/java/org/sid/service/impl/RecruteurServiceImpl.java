package org.sid.service.impl;

import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.dao.QuestionRepository;
import org.sid.dao.RecruteurRepository;
import org.sid.dao.TestRepository;
import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.Recruteur;
import org.sid.model.Test;
import org.sid.service.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecruteurServiceImpl implements RecruteurService {
	
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private TestRepository testRepository;
	

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

	@Override
	public Categorie addCategorie(String email, String libelle) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		Categorie categorie = new Categorie();
		categorie.setLibelle(libelle);
		categorieRepository.save(categorie);
		recruteur.addCategorie(categorie);
		return categorie;
	}

	@Override
	public Question addQuestion(long idCategorie, String titre) {
		Categorie categorie = categorieRepository.getOne(idCategorie);
		Question question = new  Question();
		question.setTitre(titre);
		questionRepository.save(question);
		categorie.addQuestion(question);
		return question;
	}

	@Override
	public void addTest(String email, Test test) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		testRepository.save(test);
		recruteur.getTestes().add(test);
	}

}
