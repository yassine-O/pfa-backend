package org.sid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titrePoste;
	
	private String descriptionPoste;
	
	private String typeConrtat;
		
	private String niveauEtude;
	
	private String experience;
	
	private String lieuPoste;
	
	private LocalDate datePublication;
	
	@ManyToMany
	private List<AnnonceCategorie> listCategories = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "code_entreprise")
	private Entreprise entreprise;
	
	@ManyToOne
	@JoinColumn(name="code_test")
	private Test test;
	
	@OneToMany(mappedBy = "pk.annonce")
	private List<Demande> demandes =new ArrayList<>();
	
	@OneToMany(mappedBy = "pk.annonce")
	private List<Entretien> entretiens =new ArrayList<>();
	
	
}
