package org.sid.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sid.model.Annonce;
import org.sid.model.Candidat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor 
public class Demande_PK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "code_candidat")
	private Candidat candidat;
	
	@ManyToOne
	@JoinColumn(name = "code_annonce")
	private Annonce annonce;

}
