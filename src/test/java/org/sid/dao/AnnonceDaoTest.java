package org.sid.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.sid.model.Annonce;
import org.sid.model.Demande;
import org.sid.dao.AnnonceRepository;
@RunWith(SpringRunner.class)
@DataJpaTest(showSql= true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnnonceDaoTest {
	 	@Autowired
	 	private AnnonceRepository annonceDao;
	    
	     
	 	
	    @Test
	    public void testSave() 
	    {
	        Annonce annonce =new Annonce();
	        annonceDao.save(annonce);
	        Assert.assertNotNull(annonceDao);
	    }
	    @Test
	    public void testDelete() {
	    	Annonce annonce=new Annonce();
	    	annonceDao.save(annonce);
	    	long id=annonce.getIdAnnonce();
	    	annonceDao.deleteById(id);
	    	Assert.assertNull(annonceDao.findByIdAnnonce(id));
	    }
	    @Test
	    public void testUpdate() {
	    	Annonce annonce=new Annonce();
	    	annonceDao.save(annonce);
	    	long id=annonce.getIdAnnonce();
	    	annonce.setMission("mission test");
	    	Assert.assertEquals("mission test",annonceDao.findByIdAnnonce(id).getMission());
	    }
	    @Test
	    public void addDemande() {
	    	 Annonce annonce =new Annonce();
		     annonceDao.save(annonce);
		     long idAnnonce =annonce.getIdAnnonce();
		     Demande demande=new Demande();
		     Assert.assertEquals(0,annonceDao.findByIdAnnonce(idAnnonce).getDemandes().size());
		     annonce.addDemande(demande);
		     Assert.assertEquals(1,annonceDao.findByIdAnnonce(idAnnonce).getDemandes().size());
		     
	    }
	    @Test 
	    public void delete_demande() {
	    	Annonce annonce=new Annonce();
	    	annonceDao.save(annonce);
	    	long idAnnonce=annonce.getIdAnnonce();
		     Demande demande=new Demande();
		     long idDemande=demande.getIdDemande();
	    	Assert.assertEquals(0,annonceDao.findByIdAnnonce(idAnnonce).getDemandes().size());
		     annonce.addDemande(demande);
		     Assert.assertEquals(1,annonceDao.findByIdAnnonce(idAnnonce).getDemandes().size());
		    
	    	
	    }
	   
}
