package com.triviapp.executor;

import com.triviapp.generator.response.ResponseStructureGenerator;
import com.triviapp.modelo.solicitudes.Solicitud;
import com.triviapp.modelo.response.Response;

/**
 *
 * @author vicente
 */
public abstract class Executor {
    protected StringBuilder response;
    
    public abstract String execute(Solicitud s);
    
    protected void addResponse(String message) {
        response.append(new ResponseStructureGenerator(new Response(message) {}).generate());
    }
}
