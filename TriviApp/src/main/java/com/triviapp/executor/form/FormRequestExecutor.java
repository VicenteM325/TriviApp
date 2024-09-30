package com.triviapp.executor.form;

import com.triviapp.executor.Executor;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class FormRequestExecutor {
    
    private final CreateFormRequestExecutor createFormRE;
    private final Executor modifyFormRE;
    private final Executor deleteFormRE;
    
    public FormRequestExecutor() {
        createFormRE = new CreateFormRequestExecutor();
        modifyFormRE = new ModifyFormRequestExecutor();
        deleteFormRE = new DeleteFormRequestExecutor();
    }

    public String executeCreateForm(Solicitud s, String loggedUser) {
        return createFormRE.execute(s, loggedUser);
    }

    public String executeDeleteForm(Solicitud s) {
        return deleteFormRE.execute(s);
    }

    public String executeModifyForm(Solicitud s) {
        return modifyFormRE.execute(s);
    }
}