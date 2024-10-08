package com.triviapp.modelo;

/**
 *
 * @author vicente
 */
public class Usuario {
    
    private String nombre;
    private String password;
    private String name;
    private String institucion;
    private String fechaCreacion;
    private String fechaModificacion;

    public Usuario() {
    }

    public Usuario(String nombre, String password,String name, String institucion,String fechaCreacion) {
        this.nombre = nombre;
        this.password = password;
        this.name = name;
        this.institucion = institucion;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

