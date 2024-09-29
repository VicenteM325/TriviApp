package com.triviapp.web;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.usuario.UsuarioDAO;
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
                System.out.println("Usuario: " + user);
System.out.println("Password: " + pass);
System.out.println("Acción: " + accion);


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
