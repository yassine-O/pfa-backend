package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
public class Candidat extends Utilisateur {
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="code_candiddat")
	List<Entretien> entretiens =new ArrayList<>();
	@Embedded
	private Cv cv;
	@OneToMany(cascade = CascadeType.ALL ,orphanRemoval=true, fetch = FetchType.LAZY)
	@JoinColumn(name="code_candiddat")
	List<Demande> demandes =new ArrayList<>();
	public void addDemande(Demande demande) {
		this.getDemandes().add(demande);
	}
	public Candidat() {
		this.setRole("candidat");
	}
}
