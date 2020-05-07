package org.sid.controllers;

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
	JwtUtil jwtTokenUtil;
	@CrossOrigin(allowedHeaders ="*",origins ="*")
	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		
		return ResponseEntity.ok(new AuthenticationResponse("hello"));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<?>  authenticate(@RequestBody AuthenticationRequest au) throws Exception {
		try {
		
		m.authenticate(new UsernamePasswordAuthenticationToken(au.getUsername(), au.getPassword()));
	}catch(Exception z) {
		return new ResponseEntity<>(
				"error", 
		          HttpStatus.UNAUTHORIZED);
	
	}
		final UserDetails userDetail=userDetailService.loadUserByUsername(au.getUsername());
		final String jwt=jwtTokenUtil.generateToken(userDetail);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
 