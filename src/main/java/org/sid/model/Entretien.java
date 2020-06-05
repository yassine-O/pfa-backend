package org.sid.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Entretien {
	
	@Id
	private Entretien_PK pk;

}
