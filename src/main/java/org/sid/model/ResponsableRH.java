package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data 
public class ResponsableRH extends Utilisateur {
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Test> testes = new ArrayList<Test>();
	
	@OneToMany
	@JoinColumn(name = "code_RH")
	private List<Annonce> annonces = new ArrayList<Annonce>();
	
	
	public ResponsableRH() {
		this.setRole("grh");
	}

}
