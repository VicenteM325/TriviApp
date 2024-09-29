package com.triviapp.web;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
    
    private CRUD<Formulario> formDAO = new FormularioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Formulario> forms = formDAO.getList();
        List<Formulario> formsUser = new ArrayList();
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        forms.forEach(f -> {
            if (f.getUsuarioCreacion().equals(user.getNombre())) {
                formsUser.add(f);
            }
        });
        request.setAttribute("formularios", formsUser);
        request.getRequestDispatcher("inicioUsuario.jsp").forward(request, response);
    }

    
}
