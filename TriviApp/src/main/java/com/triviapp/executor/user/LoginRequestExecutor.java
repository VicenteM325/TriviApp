package com.triviapp.executor.user;

import com.triviapp.aux.user.UserBuilder;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.usuario.UsuarioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.generator.response.ResponseStructureGenerator;
import com.triviapp.modelo.Usuario;
import com.triviapp.modelo.solicitudes.Solicitud;
import com.triviapp.modelo.response.Response;


/**
 *
 * @author vicente
 */
public class LoginRequestExecutor extends Executor{
    private final CRUD<Usuario> userDAO;
    private UserBuilder userBuilder;

    public LoginRequestExecutor() {
        userDAO = new UsuarioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        userBuilder = new UserBuilder(s);
        
        var user = userBuilder.build();
        
        if (userDAO.exists(user.getNombre())) {
            var userSys = userDAO.getObject(user.getNombre());
            
            if (userSys.getPassword().equals(user.getPassword())) {
                response.append(new ResponseStructureGenerator(new Response("El usuario logueado ahora es " + user.getNombre(), user.getNombre())).generate());
            } else {
                addResponse("Las contraseñas no coinciden");
            }
        } else {
            addResponse("El usuario no existe, imposible logearse");
        }
        
        return response.toString();
    }
}
