package com.triviapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class Formulario {
    private String id;
    private String nombre;
    private String tiempo_pregunta;
    private String tema;
    private String usuarioCreacion;
    private String fechaCreacion;
    private List<Componente> componentes;

    public Formulario(String id, String nombre,String tiempo_pregunta, String tema, String usuarioCreacion, String fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo_pregunta = tiempo_pregunta;
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

    public String getTiempo_pregunta() {
        return tiempo_pregunta;
    }

    public void setTiempo_pregunta(String tiempo_pregunta) {
        this.tiempo_pregunta = tiempo_pregunta;
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
