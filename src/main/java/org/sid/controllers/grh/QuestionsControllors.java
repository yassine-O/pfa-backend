package org.sid.controllers.grh;

import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.dao.QuestionRepository;
import org.sid.dao.ResponsableRHRepository;
import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.mapper.CategorieMapper;
import org.sid.service.GrhService;
import org.sid.service.Imp.AuthorizationServiceImp;
import org.sid.service.Imp.GrhServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import videos.Blob;
import videos.VideoService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class QuestionsControllors {
	VideoService  v=new VideoService();
	@Autowired
	ResponsableRHRepository grhdao;
	@Autowired
	GrhService grhService;
	@Autowired
	CategorieRepository categoryDao;
	@Autowired
	AuthorizationServiceImp authorizationService;
	@PostMapping("/question/{idQuestion}")
	public ResponseEntity<Long> addQuestion(
			@RequestBody Blob blob,
			@PathVariable(required = false) long idQuestion){
		
		
		// the first chunk of video i  give -1 as an id
		if(idQuestion==-1) {
			idQuestion=this.grhService.saveQuestion();
		}	
		v.upload(blob, "question",idQuestion+"");
		return ResponseEntity.
				status(200)
				.body(idQuestion)
				;
		
	}
	@DeleteMapping("/question/{idQuestion}")
	public ResponseEntity<String> deleteQUestion(
			@PathVariable long idQuestion
			){
		grhService.deleteQuestion(idQuestion);
		boolean deleted=v.delete("question", idQuestion+"");
		return ResponseEntity.
				status(200)
				.body(deleted+"")
				;
		
	}
	@PostMapping("/categorie")
	public ResponseEntity<Categorie> addCategory(@RequestBody Categorie categorie,
			@RequestHeader(value="Authorization") String authorizationHeader){
		String email=this.authorizationService.getConnectedUserName(authorizationHeader);
		categorie=this.grhService.addCategorie(categorie,email);
		return ResponseEntity
				.status(200)
				.body(categorie);
		
	}
	@GetMapping("/Categories")
	public ResponseEntity<List<CategorieMapper>> fetchCategories(
			@RequestHeader(value="Authorization") String authorization){
		String email=this.authorizationService.getConnectedUserName(authorization);
		List<CategorieMapper> categories=this.grhService.fetchCatgories(email);
		System.out.print("i am here");
		return ResponseEntity
				.status(200)
				.body(categories);
	}
	@PutMapping("/question")
	public ResponseEntity<Question> updataQuestion(
			@RequestParam(value="idCategory") long idCategory,
			@RequestBody Question question,
			@RequestHeader(value="Authorization") String authorization){
		System.out.println(question.getTitre()+question.getDurationResponse());
		String email=this.authorizationService.getConnectedUserName(authorization);
		Question questionResponse=this.grhService.updateQuestion(question ,idCategory);
		System.out.println("response");
		return ResponseEntity.
				status(200).
				body(questionResponse);
		
	}
	
}
