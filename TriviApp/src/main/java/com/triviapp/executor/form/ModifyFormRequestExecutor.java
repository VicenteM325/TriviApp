package com.triviapp.executor.form;

import com.triviapp.aux.form.FormBuilder;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class ModifyFormRequestExecutor extends Executor {
    
    private final CRUD<Formulario> formDAO;
    private FormBuilder formBuilder;

    public ModifyFormRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        formBuilder = new FormBuilder(s);

        var modifyForm = formBuilder.buildModify();

        if (formDAO.exists(modifyForm.getId())) {
            var currentForm = formDAO.getObject(modifyForm.getId());

            if (modifyForm.getTiempo_pregunta() != null) currentForm.setTiempo_pregunta(modifyForm.getTiempo_pregunta());
            if (modifyForm.getNombre() != null) currentForm.setNombre(modifyForm.getNombre());
            if (modifyForm.getTema() != null) currentForm.setTema(modifyForm.getTema());
            
            formDAO.create(currentForm);
            addResponse("El formulario " + currentForm.getId() + " fue modificado");
            //generar respuesta
        } else {
            addResponse("No se puede modificar el formulario " + modifyForm.getId() + " porque no existe");
        }
        
        return response.toString();
    }

}