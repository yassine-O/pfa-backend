package org.sid.service.Imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.QuestionRepository;
import org.sid.dao.ResponsableRHRepository;
import org.sid.model.Annonce;
import org.sid.model.Categorie;
import org.sid.model.Question;
import org.sid.model.ResponsableRH;
import org.sid.model.mapper.CategorieMapper;
import org.sid.service.GrhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import videos.VideoService;
@Service
public class GrhServiceImp implements GrhService {
	@Autowired
	QuestionRepository questionDao;
	@Autowired
	ResponsableRHRepository grhDao;
	@Autowired 
	AnnonceRepository annonceDao;
	@Autowired CategorieRepository categorieDao;
	@Override
	public Annonce addAnnonce(Annonce annonce, String emailGrh) {
			ResponsableRH grh=grhDao.findByEmail(emailGrh);
			SimpleDateFormat formatter = new SimpleDateFormat(
				      "dd-MM-yyyy");
			try {
				annonce.setDate(formatter.parse(formatter.format(new Date())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			annonceDao.save(annonce);
			grh.addAnnonce(annonce);
			grhDao.save(grh);
			return annonce;
		
	}
	@Override
	public List<Annonce> fetchAnnonces(String emailGrh,int page) {
		ResponsableRH grh=grhDao.findByEmail(emailGrh);
		Pageable p=PageRequest.of(page, 13);
		Page<Annonce> annonces=annonceDao.findGrhAnnonces(grh.getIdUser(),p);
		List pageAnnonce=new ArrayList();
		pageAnnonce.add(0,annonces.getContent());
		pageAnnonce.add(1,annonces.getTotalPages());
		return pageAnnonce;
	}
	@Override
	public int deleteAnnonce(long id) {
		Annonce a=new Annonce();
		a.setIdAnnonce(id);
		annonceDao.delete(a);
		return 1;
	}
	@Override
	public Categorie addCategorie(Categorie categorie,String email) {
		ResponsableRH grh=grhDao.findByEmail(email);
		categorieDao.save(categorie);
		grh.addCategorie(categorie);
		grhDao.save(grh);
		return categorie;
	}
	@Override
	public long saveQuestion() {
		Question question =new Question();
		questionDao.save(question);
		return question.getIdQuestion();
	}
	@Override
	public void deleteQuestion(long id) {
		Question question=new Question();
		question.setIdQuestion(id);
		questionDao.delete(question);
		
	}
	@Override
	public List<CategorieMapper> fetchCatgories(String email) {
		ResponsableRH grh=grhDao.findByEmail(email);
		
		List<Categorie> categories= grh.getCategories();
		
		List<CategorieMapper> categorieMappers=new ArrayList<>();
		
		categories.forEach(
		(categorie)->{
			    CategorieMapper categorieMapper=new CategorieMapper();
			    categorieMapper.setIdCategorie(categorie.getIdCategorie());
			    categorieMapper.setLibelle(categorie.getLibelle());
			    categorieMappers.add(categorieMapper);
		}
		);
		 
		return categorieMappers;

	}
	@Override
	public Question updateQuestion(Question question, long idCategory) {
		questionDao.save(question);
		Categorie categorie=categorieDao.findByIdCategorie(idCategory);
		categorie.addQuestion(question);
		categorieDao.save(categorie);
		VideoService v=new VideoService();
		ExecutorService threadpool = Executors.newCachedThreadPool();
		Future futureTask = threadpool.submit(() -> v.addMetadataDuration("question", question.getIdQuestion()+""));
		
		return question;
	}

}
