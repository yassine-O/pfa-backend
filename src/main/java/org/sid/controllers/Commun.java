package org.sid.controllers;

import java.util.HashMap;
import java.util.Map;

import org.sid.dao.CandidatRepository;
import org.sid.dao.ResponsableRHRepository;
import org.sid.model.Candidat;
import org.sid.model.ResponsableRH;
import org.sid.model.Utilisateur;
import org.sid.security.AuthenticationResponse;
import org.sid.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLIntegrityConstraintViolationException;



@RestController
@CrossOrigin(origins="*",allowedHeaders ="*")
public class Commun {
	@Autowired
	ResponsableRHRepository grhDao;
	@Autowired
	CandidatRepository candidatDao;
	
	
	@Autowired
	JwtUtil jwtTokenUtil;
	@PostMapping("/grh")
	public ResponseEntity<?> saveUser(@RequestBody ResponsableRH grh){
		System.out.println("saving grh");
		//GRH is a candidat with entrprise name
		try {
		
			grhDao.save(grh);
		}
		
		catch(Exception e) {
			/*if(typeOf java.sql.SQLIntegrityConstraintViolationException ) {
				
			}*/
			return new ResponseEntity<String>("email already existes",HttpStatus.BAD_REQUEST );
		}
		 Map<String, Object> claims = new HashMap<>();
		final String jwt=jwtTokenUtil.createToken(claims, grh.getEmail());
		return ResponseEntity.ok(new AuthenticationResponse(jwt,grh.getRole()));
		
	}
	@PostMapping("/candidat")
	public ResponseEntity<?> saveCandidat(@RequestBody Candidat candidat){
		System.out.println("saving grh");
		//GRH is a candidat with entrprise name
		try {
		
			candidatDao.save(candidat);
		}
		
		catch(Exception e) {
			/*if(typeOf java.sql.SQLIntegrityConstraintViolationException ) {
				
			}*/
			return new ResponseEntity<String>("email already existes",HttpStatus.BAD_REQUEST );
		}
		 Map<String, Object> claims = new HashMap<>();
		final String jwt=jwtTokenUtil.createToken(claims, candidat.getEmail());
		return ResponseEntity.ok(new AuthenticationResponse(jwt,candidat.getRole()));
		
	}
}
