package com.armando.springboot.jpa.springboot_jpa.DTO;

public class PersonalizedDTO {

    private String nombre;
    private String apellido;

    public PersonalizedDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
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

    @Override
    public String toString() {
        return "PersonalizedDTO [nombre=" + nombre + ", apellido=" + apellido + "]";
    }

}
