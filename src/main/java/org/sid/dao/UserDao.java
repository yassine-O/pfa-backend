package org.sid.dao;

import org.sid.model.ResponsableRH;
import org.sid.model.Test;
import org.sid.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource

public interface UserDao extends JpaRepository<Utilisateur, Long> {
		//because what we care about is id and role for authentification
		public Utilisateur findByEmail(String email);
		
}
