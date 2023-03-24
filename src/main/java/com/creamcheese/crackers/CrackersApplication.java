package com.creamcheese.crackers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrackersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrackersApplication.class, args);
	}

}
