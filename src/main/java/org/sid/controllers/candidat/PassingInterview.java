package org.sid.controllers.candidat;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.EntretienRepository;
import org.sid.dao.TestRepository;
import org.sid.model.Annonce;
import org.sid.model.Entretien;
import org.sid.model.Test;
import org.sid.service.CandidatService;
import org.sid.service.GrhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import videos.Blob;
import videos.VideoService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PassingInterview {
	VideoService  v=new VideoService();
	@Autowired 
	TestRepository testDao;
	@Autowired
	EntretienRepository entretienDao;
	@Autowired
	AnnonceRepository annonceDao;
	@Autowired
	CandidatService candidatService;
	@GetMapping("/test/{id}")
	public ResponseEntity<?> getTest(@PathVariable long id) { 
		Test test=testDao.findByIdTest(id);
		return ResponseEntity.status(200).body(test);
	}
	// id question answering now
	@PostMapping("/entretien/{idAnnonce}/{idQuestion}/{idEntretien}")
	public ResponseEntity passEntretien(
			@PathVariable long idAnnonce,
			@PathVariable long idQuestion,
			@PathVariable long idEntretien,
			@RequestBody Blob blob
			){
		
		if(idEntretien==-1) {
			Entretien entretien=new Entretien();
			entretienDao.save(entretien);
			Annonce annonce =annonceDao.findByIdAnnonce(idAnnonce);
			annonce.addEntretien(entretien);
			annonceDao.save(annonce);
			idEntretien=entretien.getIdEntretien();
			
		}
		this.v.upload(blob, "entretien","entretienNum"+idEntretien+"ResponseTo"+idQuestion);
		return ResponseEntity.
				status(200).
				body(idEntretien)
				;
		
	}
	@PostMapping("/entretien/{idEntretien}/{idTest}")
	public ResponseEntity createEntretien(@PathVariable long idEntretien,
			@PathVariable long idTest) {
		this.candidatService.createEntretien(idEntretien,idTest);
		return ResponseEntity.status(200).build();
		
	}
	
}
