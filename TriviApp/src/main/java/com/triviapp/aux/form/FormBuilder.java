package com.triviapp.aux.form;

import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.modelo.solicitudes.Solicitud;
import java.time.LocalDate;

/**
 *
 * @author vicente
 */
public class FormBuilder {

    private final Solicitud solicitud;
    private String loggedUser;
    private Formulario form;

    public FormBuilder(Solicitud solicitud, String loggedUser) {
        this.solicitud = solicitud;
        this.loggedUser = loggedUser;
    }

    public FormBuilder(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Formulario build() {
        form = new Formulario();

        solicitud.getParametros().forEach(p -> {
            if (p.getName().contains("ID_TRIVIA")) {
                form.setId(getValue(p));
            } else if (p.getName().contains("TIEMPO_PREGUNTA")) {
                form.setTiempo(p.getValue());
            } else if (p.getName().contains("NOMBRE")) {
                form.setNombre(getValue(p));
            } else if (p.getName().contains("TEMA")) {
                form.setTema(getValue(p));
            } else if (p.getName().contains("USUARIO_CREACION")) {
                form.setUsuarioCreacion(getValue(p));
            } else if (p.getName().contains("FECHA_CREACION")) {
                form.setFechaCreacion(getValue(p));
            }
        });
        if (form.getUsuarioCreacion() == null) {
            form.setUsuarioCreacion(loggedUser);
        }
        if (form.getFechaCreacion() == null) {
            form.setFechaCreacion(LocalDate.now().toString());
        }

        return form;
    }
    
    public Formulario buildModify() {
        form = new Formulario();
        
        solicitud.getParametros().forEach(p -> {
            if (p.getName().contains("ID_TRIVIA")) {
                form.setId(getValue(p));
            } else if (p.getName().contains("TIEMPO_PREGUNTA")) {
                form.setTiempo(p.getValue().replace("\"", ""));
            } else if (p.getName().contains("NOMBRE")) {
                form.setNombre(getValue(p));
            } else if (p.getName().contains("TEMA")) {
                form.setTema(getValue(p));
            }
        });
        
        return form;
    }

    private String getValue(Parametro p) {
        return p.getValue().replaceAll("\\s", "").replace("\"", "");
    }
}
