package org.sid.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sid.model.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest(showSql= true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemandeRepositoryTest  {
	@Autowired
	private DemandeRepository demandeRepository;
	@Test
	public void testSave() {
		Demande demande=new Demande() ;
		demandeRepository.save(demande );
		Assert.assertNotNull(demande.getIdDemande());
	}
	//testing find an existed candiat and a none existed demande
	public void testFindById() {
		Demande demande=new Demande() ;
		demandeRepository.save(demande );
		long id=demande.getIdDemande();
		Assert.assertNotNull(demandeRepository.findByIdDemande(id));
		Assert.assertNull(demandeRepository.findByIdDemande(78L));
	}
	public void deleteDemande() {
		Demande demande=new Demande() ;
		demandeRepository.save(demande );
		long id=demande.getIdDemande();
		Assert.assertNotNull(demandeRepository.findByIdDemande(id));
		demande=null;
		Assert.assertNotNull(demandeRepository.findByIdDemande(id));
	}
	
	
	
	

}
