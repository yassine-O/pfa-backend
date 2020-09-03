package org.sid.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.RecruteurRepository;
import org.sid.dao.TestRepository;
import org.sid.model.Annonce;
import org.sid.model.Recruteur;
import org.sid.model.Test;
import org.sid.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnonceServiceImpl implements AnnonceService {
	
	@Autowired
	RecruteurRepository recruteurRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	@Autowired
	TestRepository testRepository;

	@Override
	public Annonce addAnnonce(String email, Annonce annonce) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		annonce.setDatePublication(LocalDate.now());
		annonce.setEntreprise(recruteur.getEntreprise());
		annonce.setStatut("Nouvelle");
		annonceRepository.save(annonce);
		recruteur.addAnnonce(annonce);
		return annonce;
	}

	@Override
	public List<Annonce> getAnnonces(String email) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		return recruteur.getAnnonces();
	}

	@Override
	public Annonce getAnnonceById(long id) {
		return annonceRepository.getOne(id);
	}

	@Override
	public void updateAnnonce(long idAnnonce, long idTest) {
		Annonce annonce = getAnnonceById(idAnnonce);
		Test test = testRepository.getOne(idTest);
		annonce.setStatut("Publiée");
		annonce.setTest(test);
	}

}
