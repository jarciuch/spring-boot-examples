package com.shortmile.springexamples.restdb;

import com.shortmile.springexamples.restdb.entities.UserEntity;
import com.shortmile.springexamples.restdb.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.shortmile.springexamples.restdb.repositories")
public class SpringRestDbExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringRestDbExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			UserEntity jonDoe = new UserEntity(1L, "Jon Doe", "email@example.com");
			repository.save(jonDoe);
		};
	}
}
