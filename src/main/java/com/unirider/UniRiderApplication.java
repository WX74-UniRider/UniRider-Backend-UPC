package com.unirider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class UniRiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniRiderApplication.class, args);
	}

}
