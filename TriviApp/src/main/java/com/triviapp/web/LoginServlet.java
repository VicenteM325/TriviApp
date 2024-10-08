package com.triviapp.web;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.usuario.UsuarioDAO;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    CRUD<Usuario> userDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "log" -> {

                String user = request.getParameter("usuario");
                String pass = request.getParameter("password");

                if (userDAO.exists(user)) {
                    Usuario usuario = userDAO.getObject(user);

                    if (usuario.getPassword().equals(pass)) {
                        HttpSession sesion = request.getSession();
                        sesion.setMaxInactiveInterval(3600);
                        sesion.setAttribute("user", usuario);

                        response.sendRedirect("inicio");

                    } else {
                        request.setAttribute("errorLogin", "Las credenciales ingresadas no son validas");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorLogin", "Las credenciales ingresadas no son validas");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            case "logout" -> {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            }
        }

    }

}
