package com.triviapp.modelo.solicitudes;

import java.util.List;

/**
 *
 * @author vicente
 */
public class Solicitud {
    private TipoSolicitud tipo;
    private List<Parametro> parametros;

    public Solicitud() {
    }

    public Solicitud(TipoSolicitud tipo, List<Parametro> parametros) {
        this.tipo = tipo;
        this.parametros = parametros;
    }

    public TipoSolicitud getTipo() {
        return tipo;
    }

    public void setTipo(TipoSolicitud tipo) {
        this.tipo = tipo;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }
}
