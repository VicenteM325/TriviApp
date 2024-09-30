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
public class CreateFormRequestExecutor extends Executor {

    private final CRUD<Formulario> formDAO;
    private FormBuilder formBuilder;

    public CreateFormRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    public String execute(Solicitud s, String loggedUser) {
        response = new StringBuilder();

        formBuilder = new FormBuilder(s, loggedUser);
        var form = formBuilder.build();

        if (!formDAO.exists(form.getId())) {
            if (form.getUsuarioCreacion().equals(loggedUser)) {
                formDAO.create(form);
                addResponse("Formulario " + form.getId() + " creado exitosamente");
            } else {
                addResponse("El usuario ingresado en el formulario " + form.getId() + " no es el que esta logueado actualmente");
            }
        } else {
            addResponse("No se puede crear, el formulario " + form.getId() + " ya existe");
        }

        return response.toString();
    }

    @Override
    public String execute(Solicitud s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}