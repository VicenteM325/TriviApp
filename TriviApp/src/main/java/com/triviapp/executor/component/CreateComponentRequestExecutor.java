package com.triviapp.executor.component;

import com.triviapp.aux.component.ComponentBuilder;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Componente;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class CreateComponentRequestExecutor extends Executor {
    
    private final CRUD<Formulario> formDAO;
    private ComponentBuilder componentBuilder;

    public CreateComponentRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        componentBuilder = new ComponentBuilder(s);
        var component = componentBuilder.build();

        if (formDAO.exists(component.getFormulario())) {
            boolean exists = false;
            var form = formDAO.getObject(component.getFormulario());

            for (Componente c : form.getComponentes()) {
                if (c.getId().equals(component.getId())) {
                    exists = true;
                }
            }

            if (!exists) {
                form.getComponentes().add(component);
                formDAO.create(form);
                addResponse("Componente " + component.getId() + " agregado a la Trivia " + form.getId());
            } else {
                addResponse("Ya existe el componente " + component.getId() + " en la Trivia " + form.getId());
            }
        } else {
            addResponse("No existe la Trivia " + component.getFormulario());
        }
        
        return response.toString();
    }

}
