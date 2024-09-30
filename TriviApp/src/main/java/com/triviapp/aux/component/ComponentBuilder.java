package com.triviapp.aux.component;

import com.triviapp.modelo.Componente;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class ComponentBuilder {

    private final Solicitud solicitud;
    private Componente component;

    public ComponentBuilder(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    
    public Componente build() {
        component = new Componente();
        
        solicitud.getParametros().forEach(p -> {
            if (p.getName().replaceAll("\\s", "").replace("\"", "").equals("ID")) {
                component.setId(getValue(p));
            } else if (p.getName().contains("TRIVIA")) {
                component.setFormulario(getValue(p));
            } else if (p.getName().contains("CLASE")) {
                component.setClase(getValue(p));
            } else if (p.getName().contains("INDICE")) {
                component.setIndice(Integer.parseInt(getValue(p)));
            } else if (p.getName().contains("TEXTO_VISIBLE")) {
                component.setTextoVisible(p.getValue().replace("\"", ""));
            } else if (p.getName().contains("OPCIONES")) {
                component.setOpciones(getValue(p));
            } else if (p.getName().contains("RESPUESTA")) {
                component.setRespuestas(getValue(p));
            } else if (p.getName().contains("FILAS")) {
                component.setNoFilas(getValue(p));
            } else if (p.getName().contains("COLUMNAS")) {
                component.setNoColumnas(getValue(p));
            }

        });
        
        return component;
    }
    
    public Componente buildDelete() {
        component = new Componente();
        
        solicitud.getParametros().forEach(p -> {
            if (p.getName().contains("ID")) {
                component.setId(getValue(p));
            } else if (p.getName().contains("TRIVIA")) {
                component.setFormulario(getValue(p));
            }
        });
        
        return component;
    }
    
    private String getValue(Parametro p) {
        return p.getValue().replaceAll("\\s", "").replace("\"", "");
    }
}
