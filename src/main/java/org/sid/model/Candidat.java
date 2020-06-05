package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Candidat extends Utilisateur {
	
	@OneToMany(mappedBy = "pk.candidat")
	List<Demande> demandes =new ArrayList<>();
	
	@OneToMany(mappedBy = "pk.candidat")
	List<Entretien> entretiens = new ArrayList<>();
	
	
	public Candidat() {
		this.setRole("candidat");
	}
	
}
