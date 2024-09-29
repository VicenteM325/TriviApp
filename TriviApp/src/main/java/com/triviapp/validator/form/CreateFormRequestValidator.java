package com.triviapp.validator.form;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class CreateFormRequestValidator extends Validator{
    
     @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean id = false;
        boolean titulo = false;
        boolean nombre = false;
        boolean tema = false;
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "ID" -> id = true;
                case "TITULO" -> titulo = true;
                case "NOMBRE" -> nombre = true;
                case "TEMA" -> tema = true;
            }
        }
        
        if (!id | !titulo | !nombre | !tema) {
            error.append("Faltan parametros obligatorios en la solicitud, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
    
}
