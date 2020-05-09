package org.sid.dao;

import org.sid.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	Candidat findByEmail(String string);
	void delete(Candidat candidat);
	Candidat findByIdUser(long id);
}
