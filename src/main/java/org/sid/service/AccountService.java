package org.sid.service;

import org.sid.model.Utilisateur;

public interface AccountService {
	
	public Utilisateur saveUser(Utilisateur user);
	public Utilisateur findUserByEmail(String email);

}
