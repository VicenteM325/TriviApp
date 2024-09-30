package com.triviapp.executor.user;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.datos.usuario.UsuarioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.Usuario;
import com.triviapp.modelo.solicitudes.Solicitud;
import java.util.List;

/**
 *
 * @author vicente
 */
public class DeleteUserRequestExecutor extends Executor {

    private final CRUD<Usuario> usuarioDAO;
    private final CRUD<Formulario> formDAO;

    public DeleteUserRequestExecutor() {
        usuarioDAO = new UsuarioDAO();
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();

        String nombre = s.getParametros().get(0).getValue()
                .replace("\"", "")
                .replaceAll("\\s", "");

        if (usuarioDAO.delete(nombre)) {
            List<Formulario> forms = formDAO.getList();
            forms.forEach(f -> {
                if (f.getUsuarioCreacion().equals(nombre)) {
                    formDAO.delete(f.getId());
                }
            });
            addResponse("Usuario " + nombre + " se elimino correctamente");
        } else {
            addResponse("Imposible eliminar, el usuario " + nombre + " no existe");
        }

        return response.toString();
    }

}