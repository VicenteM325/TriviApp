package com.triviapp.validator.form;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ModifyFormRequestValidator extends Validator {
    @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean titulo = false;
        boolean nombre = false;
        boolean tema = false;
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "TITULO" -> titulo = true; //falta tiempo_pregunta y no va titulo
                case "NOMBRE" -> nombre = true;
                case "TEMA" -> tema = true;
            }
        }
        
        if (!titulo & !nombre & !tema) {
            error.append("Debe ingresarse al menos un parametro a modificar, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
    
}
