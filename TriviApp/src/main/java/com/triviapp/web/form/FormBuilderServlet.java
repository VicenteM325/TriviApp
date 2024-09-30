package com.triviapp.web.form;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.modelo.Formulario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vicente
 */
@WebServlet("/builder")
public class FormBuilderServlet extends HttpServlet {

    private CRUD<Formulario> formDAO = new FormularioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idForm = request.getParameter("id");
        Formulario form = formDAO.getObject(idForm);

        form.getComponentes().forEach(c -> {
            switch (c.getClase()) {
                case "CHECKBOX", "RADIO", "COMBO" -> {
                    if (c.getOpciones().contains("|")) {
                        c.setOptions(Arrays.asList(c.getOpciones().split("\\|")));
                    } else {
                        List<String> options = new ArrayList();
                        options.add(c.getOpciones());
                        c.setOptions(options);
                    }
                }
            }
        });

        request.setAttribute("form", form);
        request.getRequestDispatcher("formulario.jsp").forward(request, response);
    }

}
