package org.sid.dao;

import org.sid.model.Candidat;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGh {
	@Autowired
	static
	CandidatRepository c;
	public static void main(String[] args) {
		Candidat c1=new Candidat();
		c.save(c1);

	}

}
