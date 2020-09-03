package org.sid.web;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.Test;
import org.sid.service.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import constantes.Chemain;

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
	
	@PostMapping("categories")
	public Categorie addCategory(Principal user, @RequestBody String libelle) {
		String email = user.getName();
		return recruteurService.addCategorie(email, libelle);
	}
	
	@PostMapping("categories/{id}/questions")
	public void addQuestion(Principal user, @PathVariable long id, @RequestParam String titre, @RequestParam MultipartFile video) throws Exception {
		String email = user.getName();
		Question question = recruteurService.addQuestion(id, titre);
		Path path = Chemain.pathQuestion(question.getId());
		if(!Files.exists(path.getParent())) {
			Files.createDirectories(path.getParent());
		}
		Files.write(path, video.getBytes());
	}
	
	@PostMapping("tests")
	public void addTest(Principal user, @RequestBody Test test) {
		String email = user.getName();
		recruteurService.addTest(email, test);
	}

}
