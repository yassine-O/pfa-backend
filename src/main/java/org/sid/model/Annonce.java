package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
	private Long id;
	@OneToMany
	@JoinColumn(name="code_annonce")
	private List<Demande> demandes =new ArrayList<>();
	@OneToMany
	@JoinColumn(name="code_annonce")
	private List<Entretien> entretien =new ArrayList<>();
	@OneToOne
	@JoinColumn(name="code_annonce")
	private Test test;

}
