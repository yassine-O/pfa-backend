package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idResponsableGRH")

public class ResponsableGRH extends Utilisateur {
	
	@OneToMany
	private List<Categorie> categories = new ArrayList<Categorie>();
	@OneToMany
	private List<Teste> testes = new ArrayList<Teste>();
	@OneToMany
	private List<Annonce> annonces = new ArrayList<Annonce>();

}
