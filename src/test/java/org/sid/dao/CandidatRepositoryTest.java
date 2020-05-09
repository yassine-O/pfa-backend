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
		Assert.assertNotNull(candidat.getIdUser());
	}
	//testing find an existed candiat and a none existed candidat
	@Test
	public void testFindById() {
		Candidat candidat=new Candidat() ;
		candidatRepository.save(candidat );
		long id=candidat.getIdUser();
		Assert.assertNotNull(candidatRepository.findByIdUser(id));
		Assert.assertNull(candidatRepository.findByIdUser(78L));
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
		long id=candidat.getIdUser();
		candidat.setNom("nom test");
		
		Assert.assertEquals("email",candidatRepository.findByIdUser(id).getEmail());
		Assert.assertEquals("nom test",candidatRepository.findByIdUser(id).getNom());
		Assert.assertNotEquals("wrong nom test",candidatRepository.findByIdUser(id).getNom());
	}
	
	@Test
	public void addDemande() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getIdUser();
		Assert.assertEquals(0,candidatRepository.findByIdUser(id).getDemandes().size());
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findByIdUser(id).getDemandes().size());
		long idDemande=candidatRepository.findByIdUser(id).getDemandes().get(0).getIdDemande();
		System.out.print("hhhhhhhhhhhhhh"+idDemande);
		Assert.assertEquals(demande.getIdDemande(), idDemande);
		Demande d=demandeRepository.findByIdDemande(idDemande);
		Assert.assertNotNull(d);
		}
	
	@Test
	public void deleteDemande() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getIdUser();//saving id
		Assert.assertEquals(0,candidatRepository.findByIdUser(id).getDemandes().size());// checking if there is a demande
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findByIdUser(id).getDemandes().size());//there is a demeande
		long idDemande=candidatRepository.findByIdUser(id).getDemandes().get(0).getIdDemande();
		candidatRepository.findByIdUser(id).getDemandes().remove(0);
		Assert.assertEquals(0,candidatRepository.findByIdUser(id).getDemandes().size());
		

		
	}
	public void deleteCandidat____AND__DemandeCascadding() {
		Candidat candidat=new Candidat();
		Demande demande=new Demande();
		candidatRepository.save(candidat);
		long id=candidat.getIdUser();
		candidat.addDemande(demande);
		Assert.assertEquals(1,candidatRepository.findByIdUser(id).getDemandes().size());
		long idDemande=candidatRepository.findByIdUser(id).getDemandes().get(0).getIdDemande();
		Assert.assertNotNull(demandeRepository.findByIdDemande(idDemande));
		candidatRepository.delete(candidat);
		Assert.assertNull(candidatRepository.findByIdUser(id));
		Assert.assertNull(demandeRepository.findByIdDemande(idDemande));

	}
	
}