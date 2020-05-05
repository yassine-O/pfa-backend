package org.sid.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idResponsableCandidat")
public class Candidat extends Utilisateur {

}
