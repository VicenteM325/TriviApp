package com.triviapp.validator.component;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import com.triviapp.validator.Validator;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ClassParameterValidator extends Validator{
     @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        String msgError = "Hay parametros que no son aceptados por la clase indicada, linea: " + o.getLinea() + ", col: " + o.getColumna();

        params.forEach(p -> {
            switch (getClass(p)) {
                
                case "CAMPO_TEXTO", "FICHERO" -> {
                    params.forEach(p2 -> {
                        switch (getName(p2)) {
                            case "OPCIONES", "FILAS", "COLUMNAS" -> error.append(msgError);
                        }
                    });
                }
                
                case "CHECKBOX", "RADIO", "COMBO" -> {
                    params.forEach(p2 -> {
                        switch (getName(p2)) {
                            case "FILAS", "COLUMNAS"-> error.append(msgError);
                        }
                    });
                }
                
                case "AREA_TEXTO" -> {
                    params.forEach(p2 -> {
                        switch (getName(p2)) {
                            case "OPCIONES"-> error.append(msgError);
                        }
                    });
                }
                
                
            }
        });

        return error.toString();
    }

    protected String getClass(Parametro p) {
        return p.getValue().replace("\"", "").replaceAll("\\s", "");
    }
    
}
