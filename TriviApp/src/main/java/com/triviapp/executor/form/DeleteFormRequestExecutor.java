package com.triviapp.executor.form;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class DeleteFormRequestExecutor extends Executor {

    private final CRUD<Formulario> formDAO;

    public DeleteFormRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();

        String idForm = s.getParametros().get(0).getValue()
                .replace("\"", "")
                .replaceAll("\\s", "");

        if (formDAO.delete(idForm)) {
            addResponse("Formulario " + idForm + " eliminado");
        } else {
            addResponse("No se puede eliminar, el formulario " + idForm + " no existe");
        }

        return response.toString();
    }

}
