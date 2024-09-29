package com.triviapp.validator.component;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class CreateComponentRequestValidator extends Validator {
     @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean id = false;
        boolean form = false;
        boolean clase = false;
        boolean textV = false;
        boolean answer = false;
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "ID" -> id = true;
                case "TRIVIA" -> form= true; //O formulario
                case "CLASE" -> clase = true;
                case "TEXTO_VISIBLE" -> textV = true;
                case "RESPUESTA" -> answer = true;
                //falta campo respuesta
            }
        }
        
        if (!id | !form | !clase | !textV | !answer) {
            error.append("Faltan parametros obligatorios en la solicitud, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
    
}
