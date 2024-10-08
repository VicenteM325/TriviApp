package com.triviapp.modelo;

import java.util.List;

/**
 *
 * @author vicente
 */
public class Componente {

    private int indice;
    private String id;
    private String nombreCampo;
    private String formulario;
    private String textoVisible;
    private String respuesta;
    private String clase;
    private String opciones;
    private String noFilas;
    private String noColumnas;
    private List<String> options;

    public Componente() {
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    

    public String getTextoVisible() {
        return textoVisible;
    }

    public void setTextoVisible(String textoVisible) {
        this.textoVisible = textoVisible;
    }


    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public String getNoFilas() {
        return noFilas;
    }

    public void setNoFilas(String noFilas) {
        this.noFilas = noFilas;
    }

    public String getNoColumnas() {
        return noColumnas;
    }

    public void setNoColumnas(String noColumnas) {
        this.noColumnas = noColumnas;
    }


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Componente{" + "indice=" + indice + ", id=" + id + ", nombreCampo=" + nombreCampo + ", formulario=" + formulario + ", textoVisible=" + textoVisible + ", respuesta=" + respuesta +  ", clase=" + clase + ", opciones=" + opciones + ", noFilas=" + noFilas + ", noColumnas=" + noColumnas + ", options=" + options + '}';
    }
    
    
}
