package com.triviapp.executor.component;

import com.triviapp.executor.Executor;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class ComponentRequestExecutor {
    
    private final Executor createComponentRE;
    private final Executor modifyComponentRE;
    private final Executor deleteComponentRE;

    public ComponentRequestExecutor() {
        createComponentRE = new CreateComponentRequestExecutor();
        modifyComponentRE = new ModifyComponentRequestExecutor();
        deleteComponentRE = new DeleteComponentRequestExecutor();
    }

    public String executeAddComponent(Solicitud s) {
        return createComponentRE.execute(s);
    }

    public String executeDeleteComponent(Solicitud s) {
        return deleteComponentRE.execute(s);
    }
    
    public String executeModifyComponent(Solicitud s) {
        return modifyComponentRE.execute(s);
    }

    
}