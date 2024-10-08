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
public class DeleteComponentRequestExecutor extends Executor{
    
    private final CRUD<Formulario> formDAO;
    private ComponentBuilder componentBuilder;

    public DeleteComponentRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        componentBuilder = new ComponentBuilder(s);
        var component = componentBuilder.buildDelete();

        if (formDAO.exists(component.getFormulario())) {
            var form = formDAO.getObject(component.getFormulario());
            int index = indexOfComponent(form, component.getId());
            
            if (index != -1) {
                form.getComponentes().remove(index);
                formDAO.create(form);
                addResponse("Se elimino el componente " + component.getId() + " de la Trivia " + form.getId());
            } else {
                addResponse("No existe el componente " + component.getId() + " en la Trivia " + form.getId());
            }
        } else {
            addResponse("La trivia " + component.getFormulario() + " no existe");
        }

        return response.toString();
    }
    
    private int indexOfComponent(Formulario form, String idComponent) {
        Componente c;
        for (int i = 0; i < form.getComponentes().size(); i++) {
            c = form.getComponentes().get(i);
            if (c.getId().equals(idComponent)) {
                return i;
            }
        }
        return -1;
    }

}
