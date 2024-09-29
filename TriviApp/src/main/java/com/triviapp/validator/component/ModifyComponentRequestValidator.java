package com.triviapp.validator.component;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ModifyComponentRequestValidator extends Validator {
    
     @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        boolean id = false;
        boolean form = false;
        boolean[] prms = {false, false, false, false, false, false, false, false, false, false};
        
        for (Parametro p : params) {
            switch (getName(p)) {
                case "ID" -> id = true;
                case "TRIVIA" -> form = true;
                case "CLASE" -> prms[0] = true;
                case "INDICE" -> prms[1] = true;
                case "TEXTO_VISIBLE" -> prms[2] = true; //falta respuesta
                case "OPCIONES" -> prms[3] = true;
                case "RESPUESTA" -> prms[4]= true;
                case "FILAS" -> prms[5] = true;
                case "COLUMNAS" -> prms[6] = true;
            }
        }
        
        boolean faltan = !prms[0] & !prms[1] & !prms[2] & !prms[3] & !prms[4] &
                !prms[5] & !prms[6];
        if (!id | !form) {
            error.append("El ID y TRIVIA son parametros obligatorios, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        } else if (faltan) {
            error.append("Debe ingresarse al menos un parametro a modificar, linea: ")
                    .append(o.getLinea())
                    .append(", col: ")
                    .append(o.getColumna());
        }
        
        return error.toString();
    }
}
