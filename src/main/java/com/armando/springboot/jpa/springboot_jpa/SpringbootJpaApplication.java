package com.armando.springboot.jpa.springboot_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.armando.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// List<Autor> persons = (List<Autor>) repository.findAll();
		// List<Autor> persons = (List<Autor>)
		// repository.findByNombreAndApellido("George", "Orwell");

		List<Object[]> persons = repository.obtenerNombreUser("George");

		persons.stream().forEach(person -> {
			System.out.println(person[0] + " " + person[1]);
		});

	}

}
