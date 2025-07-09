package com.armando.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.armando.springboot.jpa.springboot_jpa.DTO.PersonalizedDTO;
import com.armando.springboot.jpa.springboot_jpa.entities.Autor;

// JpaReposiroty = es igual que crudreposiroty y aparte sirve para paginar
// CrudReposiroty = es para tener las funciones de un crud

public interface PersonRepository extends CrudRepository<Autor, String> {

    @Query("select min(length(p.nombre)) from Autor p")
    public Integer getMinLengthName();

    @Query("select max(length(p.nombre)) from Autor p")
    public Integer getMaxLengthName();

    @Query("select p.nombre, length(p.nombre) from Autor p")
    List<Object[]> getPersonNameLength();

    @Query("select count(p) from Autor p")
    Long totalPerson();

    // @Query("select min(p.id) from Autor p")
    // Long minId();

    // @Query("select max(p.id) from Autor p")
    // Long maxId();

    List<Autor> findAllByOrderByNombreDesc();

    @Query("select p from Autor p order by p.nombre desc")
    List<Autor> getAllOrdered();

    List<Autor> findByNombreBetweenOrderByNombreDescApellidoDesc(String c1, String c2);

    // @Query("select p from Autor p where p.nombre between ?1 and ?2 order by
    // p.nombre")
    @Query("select p from Autor p where p.nombre between ?1 and ?2  order by p.nombre desc , p.apellido desc")
    List<Autor> findAllBetweenName(String c1, String c2);

    // @Query("select p from Autor p where p.id between 2 and 5")
    // List<Autor> findAllBetweenId();

    @Query("select concat(p.nombre, ' ', p.apellido) from Autor p")
    List<String> getNameAndConcat();

    @Query("select p.nombre || ' ' || p.apellido from Autor p")
    List<String> getNameAndConcat2();

    @Query("select lower(concat(p.nombre, ' ', p.apellido)) from Autor p")
    List<String> getNameAndConcatlower();

    @Query("select upper(concat(p.nombre, ' ', p.apellido)) from Autor p")
    List<String> getNameAndConcatupper();

    @Query("select p.id, upper(p.nombre), lower(p.apellido) from Autor p")
    List<String> getNameAndConcatupperandlower();

    @Query("select p.nombre from Autor p")
    List<String> findAllName();

    @Query("select count(distinct(p.nombre)) from Autor p")
    Long findAllNameDistinctCount();

    @Query("select distinct(p.nombre) from Autor p")
    List<String> findAllNameDistinct();

    @Query("select new com.armando.springboot.jpa.springboot_jpa.DTO.PersonalizedDTO(p.nombre, p.apellido) from Autor p")
    List<PersonalizedDTO> findAllPersonalizedDTO();

    @Query("select new Autor(p.nombre, p.apellido) from Autor p")
    List<Autor> findAllPersonalizedObjectPerson();

    @Query("select a.nombre , a from Autor a")
    List<Object[]> findAllMixPersonDataList();

    @Query("select p.id, p.nombre,p.apellido from Autor p where p.id=?1")
    Optional<Object> obtenerPersonDataById(String id);

    @Query("select p.nombre from Autor p where p.id=?1")
    String getNameById(String id);

    @Query("select concat(p.nombre, ' ', p.apellido) from Autor p where p.id=?1")
    String getNameAndApellidoById(String id);

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
