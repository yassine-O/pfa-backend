package org.sid.service.impl;

import java.util.List;

import org.sid.dao.RecruteurRepository;
import org.sid.dao.TestRepository;
import org.sid.model.Recruteur;
import org.sid.model.Test;
import org.sid.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	TestRepository testRepository;

	@Override
	public List<Test> getTests(String email) {
		Recruteur recruteur = recruteurRepository.findByEmail(email);
		return recruteur.getTestes();
	}

}
