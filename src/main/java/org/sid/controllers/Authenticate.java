package org.sid.controllers;

import org.sid.dao.UserDao;
import org.sid.model.Utilisateur;
import org.sid.security.AuthenticationRequest;
import org.sid.security.AuthenticationResponse;
import org.sid.security.JwtUtil;
import org.sid.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowedHeaders ="*",origins ="*")

public class Authenticate {
	@Autowired
	AuthenticationManager m;
	@Autowired
	MyUserDetailService userDetailService;
	@Autowired
	UserDao userDao;
	@Autowired
	JwtUtil jwtTokenUtil;
	@CrossOrigin(allowedHeaders ="*",origins ="*")
	/*@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		was a hello world for testing
		return ResponseEntity.ok(new AuthenticationResponse("hello"));
	}*/
	@PostMapping("/authenticate")
	public ResponseEntity<?>  authenticate(@RequestBody AuthenticationRequest au) throws Exception {
		try {
		System.out.println(au.getPassword()+au.getUsername());
		m.authenticate(new UsernamePasswordAuthenticationToken(au.getUsername(), au.getPassword()));
	}catch(AuthenticationException e) {
		System.out.println(e.getMessage()+"  authentication");
		return new ResponseEntity<>(
				"password or email incorrect", 
		          HttpStatus.UNAUTHORIZED);
	}
	catch(Exception z) {
		System.out.println(z);
		return new ResponseEntity<>(
				"an error occcured please try again", 
		          HttpStatus.UNAUTHORIZED);
	
	}
		final Utilisateur user=userDao.findByEmail(au.getUsername());
		final String jwt=jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponse(jwt,user.getRole()));
	}
	@PostMapping("/validate")
	public  ResponseEntity<?>  validate(@RequestBody String jwtToken){
		try {
			System.out.println(jwtToken);
			String username=  jwtTokenUtil.extractUsername(jwtToken);
			Utilisateur userDetails = userDao.findByEmail(username);
			System.out.println(userDetails.getEmail());
			return  ResponseEntity.ok(new AuthenticationResponse("notImportant",userDetails.getRole()));
			
		}catch(io.jsonwebtoken.MalformedJwtException ex) {
			

			return  ResponseEntity.ok(false);
		}
		
	}
	
}
 