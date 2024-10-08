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
        if (usuarioDAO.exists(u.getNombre())) {
            var oldUser = usuarioDAO.getObject(u.getNombre());
            var newUser = userBuilder.buildNew();
            
            if(newUser.getInstitucion()==null){
               newUser.setInstitucion(oldUser.getInstitucion());
            }
            newUser.setName(oldUser.getName());
            newUser.setFechaCreacion(oldUser.getFechaCreacion());

            List<Formulario> forms = formDAO.getList();
            forms.forEach(f -> {
                if (f.getUsuarioCreacion().equals(oldUser.getNombre())) {
                    f.setUsuarioCreacion(newUser.getNombre());
                    formDAO.create(f);
                }
            });
            usuarioDAO.delete(oldUser.getNombre());
            usuarioDAO.create(newUser);

            response.append(new ResponseStructureGenerator(new Response("Se modifico el usuario " + oldUser.getNombre(), newUser.getNombre())).generate());
        } else {
            addResponse("No se puede modificar, el usuario " + u.getNombre() + " no existe");
        }
        
        return response.toString();
    }
    
}
