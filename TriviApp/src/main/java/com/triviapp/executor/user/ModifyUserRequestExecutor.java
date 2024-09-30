package com.triviapp.executor.user;

import com.triviapp.aux.user.UserBuilder;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.datos.usuario.UsuarioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.generator.response.ResponseStructureGenerator;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.Usuario;
import com.triviapp.modelo.response.Response;
import com.triviapp.modelo.solicitudes.Solicitud;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ModifyUserRequestExecutor extends Executor {
    
    private final CRUD<Usuario> usuarioDAO;
    private final CRUD<Formulario> formDAO;
    private UserBuilder userBuilder;
    
    public ModifyUserRequestExecutor() {
        usuarioDAO = new UsuarioDAO();
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();
        
        userBuilder = new UserBuilder(s);

        var u = userBuilder.buildOld();
        if (usuarioDAO.exists(u.getUsuario())) {
            var oldUser = usuarioDAO.getObject(u.getUsuario());
            var newUser = userBuilder.buildNew();
            newUser.setFechaCreacion(oldUser.getFechaCreacion());
            
            List<Formulario> forms = formDAO.getList();
            forms.forEach(f -> {
                if (f.getUsuarioCreacion().equals(oldUser.getUsuario())) {
                    f.setUsuarioCreacion(newUser.getUsuario());
                    formDAO.create(f);
                }
            });
            usuarioDAO.delete(oldUser.getUsuario());
            usuarioDAO.create(newUser);

            response.append(new ResponseStructureGenerator(new Response("Se modifico el usuario " + oldUser.getUsuario(), newUser.getUsuario())).generate());
        } else {
            addResponse("No se puede modificar, el usuario " + u.getUsuario() + " no existe");
        }
        
        return response.toString();
    }
    
}
