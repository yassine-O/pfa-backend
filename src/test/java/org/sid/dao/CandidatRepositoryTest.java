package org.sid.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sid.model.Candidat;
import org.sid.model.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest(showSql= true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CandidatRepositoryTest {
	@Autowired
	private CandidatRepository candidatRepository;
	@Autowired
	private DemandeRepository demandeRepository;
	@Test
	public void testSave() {
		Candidat candidat=new Candidat() ;
		candidatRepository.save(candidat );
		Assert.assertNotNull(candidat.getId());
	}
	//testing find an existed candiat and a none existed candidat
	@Test
	public void testFindById() {
		Candidat candidat=new Candidat() ;
		candidatRepository.save(candidat );
		long id=candidat.getId();
		Assert.assertNotNull(candidatRepository.findById(id));
		Assert.assertNull(candidatRepository.findById(78L));
	}
	@Test
	public void testFindByEmail() {
		Candidat candidat=new Candidat() ;
		candidatRepository.save(candidat );
		candidat.setEmail("email");
		
		Assert.assertNotNull(candidatRepository.findByEmail("email"));
		Assert.assertNull(candidatRepository.findByEmail("none email"));
	}
	@Test
	public void testUpdate() {
		Candidat candidat=new Candidat() ;
		
		candidat.setEmail("email");
		candidatRepository.save(candidat );
		long id=candidat.getId();
		candidat.setNom("nom test");
		
		Assert.assertEquals("email",candidatRepository.findById(id).getEmail());
		Assert.assertEquals("nom test",candidatRepository.findById(id).getNom());
		Assert.assertNotEquals("wrong nom test",candidatRepository.findById(id).getNom());
	}
	
	@Test
	public void addDemande() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getId();
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findById(id).getDemandes().size());
		long idDemande=candidatRepository.findById(id).getDemandes().get(0).getIdDemande();
		Assert.assertNotNull(demandeRepository.findById(idDemande));
		}
	
	@Test
	public void deleteDemande() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getId();//saving id
		Assert.assertEquals(0,candidatRepository.findById(id).getDemandes().size());// checking if there is a demande
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findById(id).getDemandes().size());//there is a demeande
		long idDemande=candidatRepository.findById(id).getDemandes().get(0).getIdDemande();
		Assert.assertEquals(idDemande, demande.getIdDemande());
		Assert.assertNotNull(demande.getIdDemande());
		Assert.assertEquals(1,candidatRepository.findById(id).getDemandes().size());
		candidatRepository.findById(id).getDemandes().remove(0);
		Assert.assertEquals(0,candidatRepository.findById(id).getDemandes().size());
		Assert.assertNull(demandeRepository.findByIdDemande(idDemande));

		
	}
	@Test
	public void deleteCandidat____AND__DemandeCascadding() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getId();
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findById(id).getDemandes().size());
		long idDemande=candidatRepository.findById(id).getDemandes().get(0).getIdDemande();
		Assert.assertNotNull(demandeRepository.findById(idDemande));
		candidatRepository.delete(candidat);
		Assert.assertNull(candidatRepository.findById(id));
		Assert.assertNull(demandeRepository.findByIdDemande(idDemande));

		
	}
	
}