package org.sid.dao;

import org.sid.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur,Long> {
	
	public Utilisateur findByEmail(String email);

}
