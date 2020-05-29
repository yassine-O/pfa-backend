package org.sid.dao;

import org.sid.model.Annonce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

	Annonce findByIdAnnonce(long idAnnonce);
	@Query(
			  value = "SELECT * FROM annonce where code_rh=:id", 
			  nativeQuery = true)
	public Page<Annonce> findGrhAnnonces(@Param("id") long id,Pageable pageable);
	@Query(
			  value = "SELECT * FROM annonce where code_rh=:idGrh and id_annonce=:idAnnonce", 
			  nativeQuery = true)
	Annonce getAnnonceByIdGrh__andIdAnnonce(@Param("idGrh") long idUser,@Param("idAnnonce") long idAnnonce);
	

}
