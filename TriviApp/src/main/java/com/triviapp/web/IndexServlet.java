package com.triviapp.web;

import static com.triviapp.aux.FileController.createDirectory;
import static com.triviapp.aux.FileController.saveFile;
import static com.triviapp.aux.FileController.verifyFile;
import com.triviapp.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vicente
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("user");
        crearUsuarioInicio();
        if (user != null) {
            response.sendRedirect("inicioUsuario.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void crearUsuarioInicio() throws IOException {
        String NAME_USER_LINUX = System.getProperty("user.name");
        String ruta = "/home/" + NAME_USER_LINUX + "/WebFormBuilder/data/users/";
        String archivoDestino = ruta + "admin.db";
        String texto = """
                       db.user(
                           {
                               "USUARIO" : "admin",
                               "PASSWORD" : "admin",
                               "FECHA_CREACION" : "2024-09-16"
                           }
                       )""";

        if (!verifyFile(archivoDestino)) {
            createDirectory(ruta);
            saveFile(archivoDestino, texto);
        }
    }
}
