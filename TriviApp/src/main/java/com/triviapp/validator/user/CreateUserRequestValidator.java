package com.triviapp.validator.user;

import java.util.List;
import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;

/**
 *
 * @author vicente
 */
public class CreateUserRequestValidator extends  Validator {

    @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean user = false;
        boolean pass = false;
        for (Parametro p : params) {
            switch (getName(p)) {
                case "USUARIO" -> user = true;
                case "PASSWORD" -> pass = true;
            }
        }
        
        if (!user | !pass  ) {
            error.append("El USUARIO y PASSWORD son parametros obligatorios, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }

}