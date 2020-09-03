package org.sid.service;

import java.util.List;

import org.sid.model.Annonce;
import org.sid.model.Test;

public interface AnnonceService {
	
	public Annonce getAnnonceById(long id);
	public Annonce addAnnonce(String email, Annonce annonce);
	public List<Annonce> getAnnonces(String email);
	public void updateAnnonce(long idAnnonce, long idTest);

}
