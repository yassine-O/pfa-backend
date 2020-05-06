package org.sid.service;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CandidatRepository;
import org.sid.dao.DemandeRepository;
import org.sid.model.Annonce;
import org.sid.model.Candidat;
import org.sid.model.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CandidatService {
	public void addDemande(long annonceId,Demande demande,Candidat candidat) throws Exception;
	
}
