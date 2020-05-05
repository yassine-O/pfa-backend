package org.sid.dao;

import org.sid.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
