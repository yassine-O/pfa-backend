package org.sid.web;

import java.security.Principal;
import java.util.List;

import org.sid.model.Annonce;
import org.sid.model.Test;
import org.sid.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnonceRestController {
	
	@Autowired
	AnnonceService annonceService;
	
	@PostMapping("/annonces")
	public void addAnnonce(Principal user, @RequestBody Annonce annonce) {
		String email = user.getName();
		annonceService.addAnnonce(email, annonce);
	}
	
	@GetMapping("/annonces")
	public List<Annonce> getAnnonces(Principal user) {
		String email = user.getName();
		return annonceService.getAnnonces(email);
	}
	
	@PutMapping("/annonces/{idAnnonce}/test")
	public void addAnnonceTest(@PathVariable long idAnnonce, @RequestBody Test test) {
		annonceService.updateAnnonce(idAnnonce, test.getId());
	}

}
