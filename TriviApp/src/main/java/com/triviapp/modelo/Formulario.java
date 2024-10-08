package com.triviapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class Formulario {

    private String id;
    private String tiempo;
    private String nombre;
    private String tema;
    private String usuarioCreacion;
    private String fechaCreacion;
    private List<Componente> componentes;

    public Formulario(String id, String tiempo, String nombre, String tema, String usuarioCreacion, String fechaCreacion) {
        this.id = id;
        this.tiempo = tiempo;
        this.nombre = nombre;
        this.tema = tema;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.componentes = new ArrayList();
    }

    public Formulario() {
        this.componentes = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema.toUpperCase();
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
}
