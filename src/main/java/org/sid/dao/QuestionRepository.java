package org.sid.dao;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
import org.sid.model.Annonce;
import org.sid.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query(value=
			"select * from question where code_categorie=:id",
			nativeQuery = true)
	Page<Question> findQuestionByCatgorie(@Param("id")long idCategory,Pageable pageable);

}
