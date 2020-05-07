package org.sid.dao;

import org.sid.model.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
	Entretien findByIdEntretien(long id);

}
