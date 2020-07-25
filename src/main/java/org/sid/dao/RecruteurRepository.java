package org.sid.dao;

import org.sid.model.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {
	
	public Recruteur findByEmail(String email);

}
