package com.triviapp.generator.response;

import com.triviapp.generator.Generator;
import com.triviapp.modelo.response.Response;

/**
 *
 * @author vicente
 */
public class ResponseStructureGenerator extends Generator {
    
    private final Response r;

    public ResponseStructureGenerator(Response r) {
        this.r = r;
    }

    @Override
    public String generate() {
        text = new StringBuilder();

        addLine("<!envio_respuesta: \"RESPUESTA_SERVIDOR\">", 0);
        addLine("{\"PARAMETROS_RESPUESTA\" : [", 1);
        addLine("{", 3);
        addLine("\"MESSAGE\" : \""+r.getMessage()+"\",", 4);
        if (r.getLoggedUser() != null) addLine("\"LOGGED_USER\" : \""+r.getLoggedUser()+"\",", 0);
        addLine("}", 3);
        addLine("]", 2);
        addLine("}", 1);
        addLine("<!fin_envio_respuesta>", 0);
        text.deleteCharAt(text.lastIndexOf(","));

        return text.toString();
    }

}