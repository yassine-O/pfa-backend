package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
public class ResponsableRH extends Utilisateur {
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "code_RH")
	private List<Categorie> categories = new ArrayList<Categorie>();
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "code_RH")
	private List<Test> testes = new ArrayList<Test>();
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "code_RH")
	private List<Annonce> annonces = new ArrayList<Annonce>();
	public ResponsableRH() {
		this.setRole("grh");
	}
	public  void addAnnonce(Annonce annonce) {
		this.annonces.add(annonce);
	}
	public void addCategorie(Categorie categorie) {
		this.categories.add(categorie);
	}

}
