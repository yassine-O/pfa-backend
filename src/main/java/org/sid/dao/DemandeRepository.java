package org.sid.dao;

import org.sid.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface DemandeRepository extends JpaRepository<Demande,Long>{
	Demande findByIdDemande(long id);
}
