package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data 
public class Recruteur extends Utilisateur {
	
	@OneToOne
	@JoinColumn(name = "code_entreprise")
	private Entreprise entreprise;
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Test> testes = new ArrayList<Test>();
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Annonce> annonces = new ArrayList<Annonce>();
	
	
	public Recruteur() {
		this.setRole("RECRUTEUR");
	}

}
