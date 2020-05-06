package org.sid.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTest;
	private String description;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "code_test")
	private List<Question> questions = new ArrayList<Question>();
	@OneToOne
	@JoinColumn(name="code_annonce")
	Annonce annonce;
}
