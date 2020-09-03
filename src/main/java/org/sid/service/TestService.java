package org.sid.service;

import java.util.List;

import org.sid.model.Test;

public interface TestService {
	
	public List<Test> getTests(String email);

}
