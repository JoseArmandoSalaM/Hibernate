package com.armando.springboot.jpa.springboot_jpa;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.armando.springboot.jpa.springboot_jpa.DTO.PersonalizedDTO;
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
		// create();
		// update();
		// delete2();

		// personalizedQueries();
		// personalizedQueries2();

		// personalizedQueriesDistinct();

		// personalizedQueriesConcatUpperandLower();
		// personalizedQueriesBetween();
		// OrderBy();
		// queriesfuntionAggregation();
		// subQueries();

		whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		List<Autor> persons = repository.getPersonByIds();

		persons.forEach(p -> {
			System.out.println(p);
		});

		List<Autor> persons2 = repository
				.getPersonByIdsConsole(Arrays.asList("AUTOR-AR-004", "AUTOR-AR-005", "AUTOR-ARR-222"));
		persons2.forEach(p -> {
			System.out.println(p);
		});

		List<Autor> persons3 = repository
				.getNotPersonByIdsConsole(Arrays.asList("AUTOR-AR-004", "AUTOR-AR-005", "AUTOR-ARR-222"));
		persons3.forEach(p -> {
			System.out.println(p);
		});
	}

	@Transactional(readOnly = true)
	public void subQueries() {

		System.out.println("=================================");

		List<Object[]> name = repository.getShorterName();
		name.forEach(p -> {
			String nombre = (String) p[0];
			Integer length = (Integer) p[1];

			System.out.println("name: " + nombre + ", length: " + length);
		});

	}

	@Transactional(readOnly = true)
	public void queriesfuntionAggregation() {

		System.out.println("================================= consulta con el total de registros de la tabla");
		Long count = repository.totalPerson();
		System.out.println(count);
		// Long min = repository.minId();
		// System.out.println(min);
		// Long max = repository.maxId();
		// System.out.println(max);

		System.out.println("Consulta con su nombre y largo");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(p -> {
			String name = (String) p[0];
			Integer length = (Integer) p[1];

			System.out.println("name: " + name + ", length: " + length);
		});

		Integer min = repository.getMinLengthName();
		Integer max = repository.getMaxLengthName();

		System.out.println(min);
		System.out.println(max);

		System.out.println("===============================================");

		Object[] counts = (Object[]) repository.getResumeAggregationFuntion();
		System.out.println("min= " + counts[0] + " sum= " + counts[1]);

	}

	@Transactional(readOnly = true)
	public void OrderBy() {
		List<Autor> name = repository.getAllOrdered();
		name.forEach(p -> {
			System.out.println(p);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		List<Autor> name = repository.findByNombreBetweenOrderByNombreDescApellidoDesc("A", "Z");
		name.forEach(p -> {
			System.out.println(p);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperandLower() {

		System.out.println("============================================================");

		List<String> name = repository.getNameAndConcat();
		name.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("============================================================");

		List<String> name1 = repository.getNameAndConcat2();
		name1.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("============================================================");

		List<String> name2 = repository.getNameAndConcatlower();
		name2.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("============================================================");

		List<String> name3 = repository.getNameAndConcatupper();
		name3.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("============================================================");

		List<String> name4 = repository.getNameAndConcatupperandlower();
		name4.forEach(p -> {
			System.out.println(p);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("Consultas con nombres de personas");

		List<String> names = repository.findAllName();
		names.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("==============================");

		List<String> namess = repository.findAllNameDistinct();
		namess.forEach(p -> {
			System.out.println(p);
		});
		System.out.println("==============================");
		Long count = repository.findAllNameDistinctCount();
		System.out.println(count);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {

		List<Object[]> autor = repository.findAllMixPersonDataList();

		autor.forEach(reg -> {
			System.out.println("Nombre: " + reg[0] + ", Persona: " + reg[1]);
		});

		System.out.println("Consulta que pobla y devuelve el objeto entity de una clase personalizada: ");
		List<Autor> autores = repository.findAllPersonalizedObjectPerson();
		autores.forEach(p -> {
			System.out.println(p);
		});

		System.out.println("Consulta que pobla y devuelve el objeto dto");
		List<PersonalizedDTO> person = repository.findAllPersonalizedDTO();

		person.forEach(p -> {
			System.out.println(p);
		});

	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese el id para buscar a la persona");
		String id = scanner.next();
		scanner.close();

		String name = repository.getNameAndApellidoById(id);
		System.out.println(name);

		Optional<Object> data = repository.obtenerPersonDataById(id);
		if (data.isPresent()) {
			Object[] personaReg = (Object[]) data.get();
			System.out.println("id: " + personaReg[0] + ", nombre: " + personaReg[1] + ", apellido: " + personaReg[2]);
		}

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
