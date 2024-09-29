package com.triviapp.modelo.errores;

/**
 *
 * @author vicente
 */
public class Error {
    private String descripcion;

    public Error() {
    }

    public Error(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
