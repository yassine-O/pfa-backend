package org.sid.service.impl;

import org.sid.dao.UserRepository;
import org.sid.model.Utilisateur;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Utilisateur saveUser(Utilisateur user) {
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return userRepository.save(user);
	}

	@Override
	public Utilisateur findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
