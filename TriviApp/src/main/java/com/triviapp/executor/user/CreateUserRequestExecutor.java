package com.triviapp.executor.user;

import com.triviapp.aux.user.UserBuilder;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Usuario;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.usuario.UsuarioDAO;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class CreateUserRequestExecutor extends Executor{
    private final CRUD<Usuario> usuarioDAO;
    private UserBuilder userBuilder;

    public CreateUserRequestExecutor() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        userBuilder = new UserBuilder(s);

        var usuario = userBuilder.build();

        if (!usuarioDAO.exists(usuario.getNombre())) {
            usuarioDAO.create(usuario);
            //Generar respuesta
            addResponse("Usuario " + usuario.getNombre() + " creado");
        } else {
            addResponse("El usuario " + usuario.getNombre() + " ya existe en el sistema");
        }
        
        return response.toString();
    }
}
