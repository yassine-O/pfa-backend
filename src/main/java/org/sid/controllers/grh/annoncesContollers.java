package org.sid.controllers.grh;

import java.util.List;

import org.sid.model.Annonce;
import org.sid.model.ResponsableRH;
import org.sid.security.JwtUtil;
import org.sid.service.GrhService;
import org.sid.service.Imp.AuthorizationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class annoncesContollers {
	@Autowired
	GrhService grhService;
	@Autowired
	AuthorizationServiceImp authorizationService;
	@PostMapping("/saveAnnonce")
	public ResponseEntity<Annonce>  addAnnonce(@RequestBody Annonce a,
			@RequestHeader(value="Authorization") String authorizationHeader){
		
        String email = this.authorizationService.getConnectedUserName(authorizationHeader);
		Annonce annonceResponse=grhService.addAnnonce(a,email);// email is like an id
		return new ResponseEntity(annonceResponse,HttpStatus.ACCEPTED);
		
	}
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@GetMapping("/Annonces")
	public ResponseEntity<List<Annonce>> fetchAnnonces(
			@RequestHeader(value="Authorization") String authorizationHeader,
			@RequestParam(value="page") int page ){
		System.out.println("annonces");
		
		String jwt = authorizationHeader.substring(7);
        String email = authorizationService.getConnectedUserName(authorizationHeader);
        List annonces_pages=grhService.fetchAnnonces(email,page-1);
		List<Annonce> annonceResponse=(List<Annonce>)annonces_pages.get(0);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("page",annonces_pages.get(1).toString());
		responseHeaders.add("Access-Control-Expose-Headers","page");
		return  ResponseEntity.ok()
				.headers(responseHeaders)
				.body(annonceResponse)
				;
				
		
	}
	// 1 
	@DeleteMapping("/deleteAnnonce/{id}")
	public ResponseEntity deleteAnnonce(@PathVariable long id){
		
		grhService.deleteAnnonce(id);
		return ResponseEntity
				.status(200)
				.build();
	}
	@GetMapping("/annonce/{idAnnonce}")
	public ResponseEntity getAnnonce(@PathVariable long idAnnonce,
			@RequestHeader(value="Authorization") String authorizationHeader){
		 String email = authorizationService.getConnectedUserName(authorizationHeader);
		 Annonce annonce=grhService.getAnnoonceOfGrh(email,idAnnonce);
		 if(annonce==null) {
			 return ResponseEntity.
					 status(404)
					 .build();
		 }
		 return ResponseEntity.ok()
					.body(annonce);
			
		}
}
