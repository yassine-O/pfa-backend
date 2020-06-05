package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String mission;
	
	private String brefInformation;
	
	private String typeConrtat;
	
	private String avantage;
	
	@ManyToOne
	@JoinColumn(name="code_test")
	private Test test;
	
	@OneToMany(mappedBy = "pk.annonce")
	private List<Demande> demandes =new ArrayList<>();
	
	@OneToMany(mappedBy = "pk.annonce")
	private List<Entretien> entretiens =new ArrayList<>();
	
	
}
