package com.triviapp.validator;

import java.util.List;
import com.triviapp.modelo.Token;
import com.triviapp.modelo.solicitudes.Parametro;

/**
 *
 * @author vicente
 */
public abstract class Validator {
    
    protected StringBuilder error;

    public abstract String validate(Token o, List<Parametro> params);
    
    protected String getName(Parametro p) {
        return p.getName().replace("\"", "").replaceAll("\\s", "");
    }
}
