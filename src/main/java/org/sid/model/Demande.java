package org.sid.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Demande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDemande;
	private String pathToPresentation;
}
