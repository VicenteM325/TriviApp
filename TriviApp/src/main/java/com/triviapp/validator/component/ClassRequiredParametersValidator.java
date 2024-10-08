package com.triviapp.validator.component;

import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ClassRequiredParametersValidator extends ClassParameterValidator {

    @Override
    public String validate(Token o, List<Parametro> params) {
        error = new StringBuilder("");
        String clase = "";
        boolean nombreC = false;
        boolean filas = false;
        boolean cols = false;
        boolean options = false;
        boolean url = false;
        boolean textV = false;
        
        for (Parametro p : params) {
            switch (getClass(p)) {
                
                case "CAMPO_TEXTO", "FICHERO" -> {
                    clase = getClass(p);
                    for (Parametro pr : params) {
                        switch (getName(pr)) {
                          //  case "NOMBRE_CAMPO" -> nombreC = true;
                            case "TEXTO_VISIBLE" -> textV = true;
                        }
                    }
                }
                
                case "CHECKBOX", "RADIO", "COMBO" -> {
                    clase = getClass(p);
                    for (Parametro pr : params) {
                        switch (getName(pr)) {
                         //   case "NOMBRE_CAMPO" -> nombreC = true;
                            case "OPCIONES" -> options = true; 
                            case "TEXTO_VISIBLE" -> textV = true;
                        }
                    }
                }
                
                case "AREA_TEXTO" -> {
                    clase = getClass(p);
                    for (Parametro pr : params) {
                        switch (getName(pr)) {
                        //    case "NOMBRE_CAMPO" -> nombreC = true;
                            case "FILAS" -> filas = true;
                            case "COLUMNAS" -> cols= true;
                            case "TEXTO_VISIBLE" -> textV = true;
                        }
                    }
                }

                
            }
        }
        
        switch (clase) {
          /*  case "CAMPO_TEXTO", "FICHERO" -> {
                if (!nombreC) {
                    setMSG("El parametro TextoVisible es obligatorio, ", o);
                }
            }
           */ 
            case "CHECKBOX", "RADIO", "COMBO" -> {
                if (!nombreC | !options) {
                    setMSG("Los parametros  OPCIONES son obligatorios, ", o);
                }
            }
            
            case "AREA_TEXTO" -> {
                if (!nombreC | !filas | !cols) {
                    setMSG("Faltan parametros obligatorios para la clase AREA_TEXTO, ", o);
                }
            }
            
        }
        
        switch (clase) {
            case "CAMPO_TEXTO", "FICHERO", "CHECKBOX", "RADIO", "COMBO", "AREA_TEXTO" -> {
                if (!textV) {
                    setMSG("el parametro TEXTO_VISIBLE es obligatorio cuando se modifica la clase del componente", o);
                }
            }
        }
        
        return error.toString();
    }
    
    private void setMSG(String s, Token o) {
        error.append(s)
                .append(", linea: ")
                .append(o.getLinea())
                .append(", col: ")
                .append(o.getColumna());
    }

}