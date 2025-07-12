package com.armando.springboot.jpa.springboot_jpa.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @Column(name = "autor_id")
    private String id;

    private String nombre;
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Date fechanacimiento;

    @Column(name = "pais_origen")
    private String paisorigen;

    @Embedded
    private Audit audit = new Audit();

    public Autor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Autor(String id, String nombre, String apellido, Date fechanacimiento, String paisorigen) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.paisorigen = paisorigen;
    }

    public Autor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getPaisorigen() {
        return paisorigen;
    }

    public void setPaisorigen(String paisorigen) {
        this.paisorigen = paisorigen;
    }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechanacimiento="
                + fechanacimiento + ", paisorigen=" + paisorigen + ", created_at= " + audit.getCreatedAt()
                + ", updatedAt= "
                + audit.getUpdatedAt() + "]";
    }

}
