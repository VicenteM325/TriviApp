package com.triviapp.aux.solicitud;

import com.triviapp.executor.component.ComponentRequestExecutor;
import com.triviapp.executor.form.FormRequestExecutor;
import com.triviapp.executor.user.LoginRequestExecutor;
import com.triviapp.executor.user.UserRequestExecutor;
import com.triviapp.generator.response.ResponseStructureGenerator;
import com.triviapp.modelo.response.Response;
import java.io.Reader;

/**
 *
 * @author vicente
 */
public class RequestExecutor {

    private StringBuilder answer = new StringBuilder();

    public RequestExecutor() {
    }

    public String run(Reader reader, String loggedUser) {

        var analyzer = new RequestAnalyzer();
        analyzer.analyze(reader);

        var errores = analyzer.getErrores();
        var solicitudes = analyzer.getSolicitudes();

        if (!loggedUser.trim().isEmpty()) {
            if (errores.isEmpty()) {

                var userRE = new UserRequestExecutor();
                var formRE = new FormRequestExecutor();
                var componentRE = new ComponentRequestExecutor();

                solicitudes.forEach(s -> {
                    switch (s.getTipo()) {
                        case CREATE_USER        -> addLinea(userRE.executeCreateUser(s));
                        case MODIFY_USER        -> addLinea(userRE.executeModifyUser(s));
                        case DELETE_USER        -> addLinea(userRE.executeDeleteUser(s));
                        case NEW_FORM           -> addLinea(formRE.executeCreateForm(s, loggedUser));
                        case EDIT_FORM          -> addLinea(formRE.executeModifyForm(s));
                        case DELETE_FORM        -> addLinea(formRE.executeDeleteForm(s));
                        case NEW_COMPONENT      -> addLinea(componentRE.executeAddComponent(s));
                        case DELETE_COMPONENT   -> addLinea(componentRE.executeDeleteComponent(s));
                        case EDIT_COMPONENT     -> addLinea(componentRE.executeModifyComponent(s));
                        case LOGIN              -> addResponse("Ya hay un usuario logueado, cierre sesion primero");
                    }
                });
                
                if (solicitudes.size() > 1) {
                    StringBuilder temp = answer;
                    answer = new StringBuilder("<!envio_respuestas>\n");
                    answer.append(temp.toString());
                    addLinea("<!fin_envio_respuestas>");
                }
            } else {
                
                if (errores.size() > 1) {
                    addLinea("<!envio_respuestas>");
                    errores.forEach(e -> addResponse(e.getDescripcion().replace("\"", "")));
                    addLinea("<!fin_envio_respuesta>");
                } else {
                    errores.forEach(e -> addResponse(e.getDescripcion().replace("\"", "")));
                }
            }
        } else {
            solicitudes.forEach(s -> {
                var loginRE = new LoginRequestExecutor();
                if ("LOGIN".equals(s.getTipo().name())) {
                    answer = new StringBuilder();
                    addLinea(loginRE.execute(s));
                }
            });
        }

        return answer.toString();
    }

    private void addLinea(String s) {
        answer.append(s).append("\n");
    }
    
    protected void addResponse(String message) {
        addLinea(new ResponseStructureGenerator(new Response(message)).generate());
    }
}