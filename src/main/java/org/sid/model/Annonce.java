package org.sid.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnnonce;
	@OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="code_annonce")
	private List<Demande> demandes =new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="code_annonce")
	private List<Entretien> entretien =new ArrayList<>();
	@OneToOne(mappedBy="annonce")
	@JoinColumn(name="code_annonce")
	private Test test;
	public void addDemande(Demande d) {
		this.getDemandes().add(d);
	}
	Date date;
    
    String title;
    @org.hibernate.annotations.Type( type = "text" )
    String description;
    String profileRecherche;
    String avantages;
    int salaireMin;
    int salaireMax;
    String typeDeContrat;
    String nomDeSociete;
    String niveauEtude;
    String ville;
}
