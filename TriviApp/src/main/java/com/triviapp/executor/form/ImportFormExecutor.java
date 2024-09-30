package com.triviapp.executor.form;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.StorageFileAnalyzer;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Solicitud;
import java.io.Reader;

/**
 *
 * @author vicente
 */
public class ImportFormExecutor extends Executor {

    private final StorageFileAnalyzer storageFileA;
    private final CRUD<Formulario> formDAO;

    public ImportFormExecutor() {
        storageFileA = new StorageFileAnalyzer();
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String execute(Reader reader, String loggedUser, String idForm) {
        response = new StringBuilder();

        var form = storageFileA.analyzeForm(reader);
        form.setId(idForm);
        form.setUsuarioCreacion(loggedUser);

        if (!formDAO.exists(form.getId())) {
            formDAO.create(form);
            addResponse("Formulario " + form.getId() + " importado exitosamente");
        } else {
            addResponse("No se puede importar, el formulario " + form.getId() + " ya existe");
        }

        return response.toString();
    }

}
