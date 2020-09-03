package org.sid.web;

import java.security.Principal;
import java.util.List;

import org.sid.model.Test;
import org.sid.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/tests")
	public List<Test> getTests(Principal user) {
		String email = user.getName();
		return testService.getTests(email);
	}

}
