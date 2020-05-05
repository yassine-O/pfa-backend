package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("RH")
@Data @NoArgsConstructor
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

}
