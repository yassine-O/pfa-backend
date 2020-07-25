package org.sid.dao;

import org.sid.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
