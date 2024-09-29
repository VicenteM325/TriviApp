package com.triviapp.validator.component;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class DeleteComponentRequestValidator extends Validator{
    @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean id = false;
        boolean form = false;
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "ID" -> id = true;
                case "TRIVIA" -> form= true; //Formulario
            }
        }
        
        if (!id | !form) {
            error.append("El ID y TRIVIA son parametros obligatorios, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
}
