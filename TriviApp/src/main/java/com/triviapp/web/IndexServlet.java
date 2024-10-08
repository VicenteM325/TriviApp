package com.triviapp.web;

import static com.triviapp.aux.FileController.createDirectory;
import static com.triviapp.aux.FileController.saveFile;
import static com.triviapp.aux.FileController.verifyFile;
import com.triviapp.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


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
        String ruta = "/home/" + NAME_USER_LINUX + "/NetBeansProjects/TriviApp/data/users/";
        String archivoDestino = ruta + "admin.db";
        String texto = """
                       db.user(
                           {
                               "USUARIO" : "admin",
                               "PASSWORD" : "admin",
                               "FECHA_CREACION" : "2024-31-09"
                           }
                       )""";

        if (!verifyFile(archivoDestino)) {
            createDirectory(ruta);
            saveFile(archivoDestino, texto);
        }
    }
}