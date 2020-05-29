package org.sid;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfaApplication implements CommandLineRunner {
	

	public static void main(String[] args) {
		SpringApplication.run(PfaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
