package org.sid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class PfaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PfaApplication.class, args);
		
	}
	
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public void run(String... args) throws Exception {
		
	}

}
