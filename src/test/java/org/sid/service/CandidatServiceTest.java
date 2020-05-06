package org.sid.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sid.dao.AnnonceRepository;
import org.sid.dao.CandidatRepository;
import org.sid.dao.DemandeRepository;
import org.sid.model.Annonce;
import org.sid.model.Candidat;
import org.sid.model.Demande;
import org.sid.service.Imp.CandidatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class CandidatServiceTest {
	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	DemandeRepository demandeRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	@Autowired
	CandidatService candidatService;
	@Test
	public void addDemande() throws Exception {
		Annonce annonce=new Annonce();
		annonceRepository.save(annonce);
		Demande demande =new  Demande();
		Candidat candidat=new Candidat();
		candidatRepository.save(candidat);
		candidatService.addDemande(annonce.getIdAnnonce(),demande,candidat);
		Assert.assertEquals(1,candidatRepository.findByIdUser(candidat.getIdUser()).getDemandes().size());
		Assert.assertEquals(1,annonceRepository.findByIdAnnonce(annonce.getIdAnnonce()).getDemandes().size());
		Assert.assertEquals(1,demandeRepository.findAll().size());

		Assert.assertEquals(
				candidatRepository.findByIdUser(candidat.getIdUser()).getDemandes().get(0).getIdDemande(),
				annonceRepository.findByIdAnnonce(annonce.getIdAnnonce()).getDemandes().get(0).getIdDemande());
	}
	

}
