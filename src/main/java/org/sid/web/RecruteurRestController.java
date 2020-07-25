package org.sid.web;

import java.security.Principal;
import java.util.List;

import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.service.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recruteur")
public class RecruteurRestController {
	
	@Autowired
	public RecruteurService recruteurService;
	
	@GetMapping("categories")
	public List<Categorie> getCategories(Principal user){
		String email = user.getName();
		return recruteurService.findAllCategories(email);
	}
	
	@GetMapping("categories/{id}/questions")
	public List<Question> getQuestions(Principal user, @PathVariable long id){
		String email = user.getName();
		return recruteurService.findQuestionsByCategorie(email, id);
	}

}
