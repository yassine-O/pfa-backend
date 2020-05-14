package org.sid.dao;

import org.sid.model.ResponsableRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ResponsableRHRepository extends JpaRepository<ResponsableRH, Long> {
		public ResponsableRH findByEmail(String email);
		
		
}
