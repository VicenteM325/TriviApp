package com.triviapp.validator.user;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ModifyUserRequestValidator extends Validator {
    
    @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean oldUser = false;
        boolean newPass = false;
        boolean newUser = false;
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "USUARIO_ANTIGUO" -> oldUser = true;
                case "USUARIO_NUEVO" -> newUser = true;
                case "NUEVO_PASSWORD" -> newPass = true;
            }
        }
        
        if (!oldUser | !newPass | !newUser) {
            error.append("Faltan parametros obligatorios en la solicitud, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
}
