package com.triviapp.aux.user;

import com.triviapp.modelo.Usuario;
import com.triviapp.modelo.solicitudes.Solicitud;
import java.time.LocalDate;

/**
 *
 * @author vicente
 */
public class UserBuilder {

    private final Solicitud solicitud;
    private Usuario user;

    public UserBuilder(Solicitud s) {
        this.solicitud = s;
    }

    public Usuario build() {
        user = new Usuario();

        solicitud.getParametros().forEach(p -> {
            
            String name = p.getName();
        String value = getValue(p.getValue());
        
            if (name.contains("\"USUARIO\"")) {
                user.setNombre(getValue(p.getValue()));
            } else if (name.contains("PASSWORD")) {
                user.setPassword(getValue(p.getValue()));
            } else if (name.contains("NOMBRE")) {
                user.setName(getValue(p.getValue()));
            } else if (name.contains("INSTITUCION")) {
                user.setInstitucion(getValue(p.getValue()));
            } else if (name.contains("FECHA_CREACION")) {
                user.setFechaCreacion(getValue(p.getValue()));
            }
        });
        
        if (user.getFechaCreacion() == null) {
            user.setFechaCreacion(LocalDate.now().toString());
        }

        return user;
    }
    
    public Usuario buildOld() {
        user = new Usuario();
        
        solicitud.getParametros().forEach(p -> {
            if (p.getName().contains("USUARIO_ANTIGUO")) {
                user.setNombre(getValue(p.getValue()));
            }
        });
        
        return user;
    }
    
    public Usuario buildNew() {
        user = new Usuario();
        
        solicitud.getParametros().forEach(p -> {
            if (p.getName().contains("USUARIO_NUEVO")) {
                user.setNombre(getValue(p.getValue()));
            } else if (p.getName().contains("NUEVO_PASSWORD")) {
                user.setPassword(getValue(p.getValue()));
            } else if (p.getName().contains("INSTITUCION")) {
                user.setInstitucion(getValue(p.getValue()));
            } else if (p.getName().contains("MODIFICACION")) {
                user.setFechaModificacion(getValue(p.getValue()));
            }
        });
        if (user.getFechaModificacion() == null) {
            user.setFechaModificacion(LocalDate.now().toString());
        }
        if (user.getNombre() == null) {
            user.setNombre(user.getNombre());
        }
        if (user.getInstitucion() == null) {
            user.setInstitucion(user.getInstitucion());
        }

        return user;
    }
    
    private String getValue(String s) {
        return s.replaceAll("\\s", "").replace("\"", "");
    }

}