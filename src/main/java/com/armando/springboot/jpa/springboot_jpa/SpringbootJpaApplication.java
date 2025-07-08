package com.armando.springboot.jpa.springboot_jpa;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.armando.springboot.jpa.springboot_jpa.entities.Autor;
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

		// findOne();
		// list();
		create();
		// update();
		// delete2();
	}

	@Transactional
	public void delete() {
		repository.findAll().forEach(p -> {
			System.out.println(p);
		});

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el id de la persona a eliminar: ");
		String id = scanner.next();

		repository.deleteById(id);
		repository.findAll().forEach(p -> {
			System.out.println(p);
		});

		scanner.close();
	}

	@Transactional
	public void delete2() {
		repository.findAll().forEach(p -> {
			System.out.println(p);
		});

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el id de la persona a eliminar: ");
		String id = scanner.next();

		Optional<Autor> autor = repository.findById(id);

		autor.ifPresentOrElse(
				a -> {
					repository.delete(a);
					System.out.println("Autor eliminado con exito");
				},
				() -> System.out.println("El id no existe"));

		repository.findAll().forEach(p -> {
			System.out.println(p);
		});

		// if (autor.isPresent()) {
		// repository.delete(autor.get());
		// repository.findAll().forEach(p -> {
		// System.out.println(p);
		// });

		// } else {
		// System.out.println("El id que pasaste no coincide con ninguna persona");
		// }

		scanner.close();
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el id de la persona a editar: ");
		String id = scanner.next();

		Optional<Autor> optionalAutor = repository.findById(id);

		// optionalAutor.ifPresent(autor -> {

		if (optionalAutor.isPresent()) {

			Autor autorDB = optionalAutor.orElseThrow();

			System.out.println("Ingrese el nuevo nombre: ");
			String nombre = scanner.next();
			System.out.println(autorDB);
			autorDB.setNombre(nombre);
			Autor personUpdate = repository.save(autorDB);
			System.out.println(personUpdate);

		} else {
			System.out.println("El id que proporciono no es correcto");
		}
		// });

		scanner.close();
	}

	@Transactional
	public void create() {

		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2002);
		// cal.set(Calendar.MONTH, Calendar.JANUARY);
		// cal.set(Calendar.DAY_OF_MONTH, 21);
		// Date dateRepresentation = cal.getTime();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id: ");
		String id = scanner.next();
		System.out.println("Ingrese el nombre: ");
		String nombre = scanner.next();
		System.out.println("Ingrese el apellido: ");
		String apellido = scanner.next();

		LocalDate fecha2 = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		while (fecha2 == null) {
			System.out.println("Ingrese la fecha en formato yyyy-MM-dd");
			String fechanacimiento = scanner.next();
			try {
				fecha2 = LocalDate.parse(fechanacimiento, formatter);

			} catch (Exception e) {
				System.out.println("Formato de fecha incorrento intenta de nuevo, EJEMPLO: 2000-06-26");
			}
		}

		System.out.println("Ingrese el pais de origen: ");
		String paisorigen = scanner.next();

		scanner.close();

		// LocalDate fecha = LocalDate.of(2002, 6, 21);
		Date sqldate = java.sql.Date.valueOf(fecha2);

		Autor autor = new Autor(id, nombre, apellido, sqldate, paisorigen);

		Autor autornew = repository.save(autor);
		System.out.println(autornew);

		repository.findById(autornew.getId()).ifPresent(p -> {
			System.out.println(p);
		});

	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Autor autor = null;
		// Optional<Autor> autoroptional = repository.findById("AUTOR-GGM-002");
		// if (autoroptional.isPresent()) {
		// autor = autoroptional.get();
		// System.out.println(autor);
		// }

		// System.out.println("No hay autores con ese id");

		repository.findFirstByIdContaining("004").ifPresent(person -> {

			System.out.println(person);
		});
	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Autor> persons = (List<Autor>) repository.findAll();
		// List<Autor> persons = (List<Autor>)
		// repository.findByNombreAndApellido("George", "Orwell");

		List<Object[]> persons = repository.obtenerNombreUser("Gabriel");

		persons.stream().forEach(person -> {
			System.out.println(person[0] + " " + person[1]);
		});

		List<Autor> personss = repository.buscarByNombre("Gabriel");

		personss.stream().forEach(person -> {
			System.out.println(person);
		});
	}

}
