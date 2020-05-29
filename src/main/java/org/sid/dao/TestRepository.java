package org.sid.dao;

import org.sid.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TestRepository extends JpaRepository<Test, Long> {
	public Test findByIdTest(long idTest);
}
