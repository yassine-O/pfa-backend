package org.sid.service;


import java.text.ParseException;
import java.util.List;

import org.sid.model.Annonce;
import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.ResponsableRH;
import org.sid.model.mapper.CategorieMapper;
import org.springframework.stereotype.Service;

public interface GrhService {
	public Annonce addAnnonce(Annonce annonce ,String emailGrh) ;

	public List fetchAnnonces(String email,int page);
	public int deleteAnnonce(long id);
	public Categorie addCategorie(Categorie categorie,String email);
	public long saveQuestion();
	public void deleteQuestion(long id);
	public List<CategorieMapper> fetchCatgories(String email);

	public Question updateQuestion(Question question, long idCategory);

	public Annonce getAnnoonceOfGrh(String email, long idAnnonce);

	public List getQuestionByCategory(long idCategory, int page,String email);
}
