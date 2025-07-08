package com.armando.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.armando.springboot.jpa.springboot_jpa.entities.Autor;

// JpaReposiroty = es igual que crudreposiroty y aparte sirve para paginar
// CrudReposiroty = es para tener las funciones de un crud

public interface PersonRepository extends CrudRepository<Autor, String> {

    List<Autor> findByNombre(String nombre);

    @Query("select p from Autor p where p.id=?1")
    Optional<Autor> findOne(String id);

    // @Query("select p from Autor p where p.nombre like :nombre ORDER BY p.id ASC")
    Optional<Autor> findFirstByNombreContainingOrderByIdAsc(String nombre);

    Optional<Autor> findFirstByIdContaining(String id);

    @Query("select a from Autor a where a.nombre=?1")
    List<Autor> buscarByNombre(String nombre);

    List<Autor> findByNombreAndApellido(String nombre, String apellido);

    @Query("select a.nombre, a.apellido from Autor a where a.nombre=?1")
    List<Object[]> obtenerNombreUser(String nombre);

    // Para obtener todos los datos pero solo con los campos nombre y apellido
    @Query("select a.nombre, a.apellido from Autor a")
    List<Object[]> obtenerNombreUser();

}
