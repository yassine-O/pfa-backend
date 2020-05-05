package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor 
public class Candidat extends Utilisateur {
	@OneToMany
	@JoinColumn(name="code_candiddat")
	List<Entretien> entretiens =new ArrayList<>();
	@OneToOne
	@JoinColumn(name="code_candidat")
	private Cv cv;
	@OneToMany
	@JoinColumn(name="code_candiddat")
	List<Demande> demandes =new ArrayList<>();
	

}
