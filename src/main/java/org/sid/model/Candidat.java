package org.sid.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("C")
@Data @NoArgsConstructor 
public class Candidat extends Utilisateur {

}
