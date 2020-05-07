package org.sid.controllers;

import java.util.HashMap;
import java.util.Map;

import org.sid.dao.ResponsableRHRepository;
import org.sid.model.ResponsableRH;
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
	ResponsableRHRepository ghrDao;
	
	
	@Autowired
	JwtUtil jwtTokenUtil;
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody ResponsableRH ghr){
		System.out.println("saving ghr");
		//GRH is a candidat with entrprise name
		try {
		
			ghrDao.save(ghr);
		}
		
		catch(Exception e) {
			/*if(typeOf java.sql.SQLIntegrityConstraintViolationException ) {
				
			}*/
			return new ResponseEntity<String>("email already existes",HttpStatus.BAD_REQUEST );
		}
		 Map<String, Object> claims = new HashMap<>();
		final String jwt=jwtTokenUtil.createToken(claims, ghr.getEmail());
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
}
