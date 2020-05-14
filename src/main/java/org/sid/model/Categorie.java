package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategorie;
	private String libelle;
	@OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "code_categorie")
	private List<Question> questions = new ArrayList<Question>();
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

}
