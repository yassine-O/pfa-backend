package org.sid.service.Imp;

import java.lang.annotation.Target;
import java.util.List;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CandidatRepository;
import org.sid.dao.DemandeRepository;
import org.sid.model.Annonce;
import org.sid.model.Candidat;
import org.sid.model.Demande;
import org.sid.model.Entretien;
import org.sid.model.Test;
import org.sid.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;
@Service
public class CandidatServiceImp implements CandidatService{
	@Autowired
	CandidatRepository candidatRrepository;
	@Autowired
	DemandeRepository demandeRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	public void addDemande(long annonceId,Demande demande,Candidat candidat)throws Exception {
		Annonce annonce=annonceRepository.findByIdAnnonce(annonceId);
		if(annonce==null) {
			throw new Exception("there is no annonce");
		}
		System.out.println("dddddddd"+demande.getIdDemande());
		demandeRepository.save(demande);
		annonce.addDemande(demande);
		annonceRepository.save(annonce);
			System.out.println("dddddddd"+demande.getIdDemande());
			candidat.addDemande(demande);
			candidatRrepository.save(candidat);
	}
	public void deleteDemande(long idDemande) {
		Demande demande =new Demande();
		demande.setIdDemande(idDemande);
		demandeRepository.delete(demande);
	}
}
