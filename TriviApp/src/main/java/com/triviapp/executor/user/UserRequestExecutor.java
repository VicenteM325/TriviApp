package com.triviapp.executor.user;

import com.triviapp.executor.Executor;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class UserRequestExecutor {

    private final Executor createUserRE;
    private final Executor modifyUserRE;
    private final Executor deleteUserRE;
    private final Executor loginRE;

    public UserRequestExecutor() {
        createUserRE = new CreateUserRequestExecutor();
        modifyUserRE = new ModifyUserRequestExecutor();
        deleteUserRE = new DeleteUserRequestExecutor();
        loginRE = new LoginRequestExecutor();
    }

    public String executeCreateUser(Solicitud s) {
        return createUserRE.execute(s);
    }

    public String executeDeleteUser(Solicitud s) {
        return deleteUserRE.execute(s);
    }

    public String executeModifyUser(Solicitud s) {
        return modifyUserRE.execute(s);
    }
}
