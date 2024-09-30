package com.triviapp.web.form;

import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.DatoRecopiladoDAO;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.modelo.Componente;
import com.triviapp.modelo.DatoRecopilado;
import com.triviapp.modelo.Formulario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ReaDataServlet extends HttpServlet {
    private final CRUD<Formulario> formDAO = new FormularioDAO();
    private final DatoRecopiladoDAO dataDAO = new DatoRecopiladoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idForm = request.getParameter("idForm");
        var form = formDAO.getObject(idForm);

        Part part;
        String value;
        String values;
        List<DatoRecopilado> datosRecopilados;
        
        if (dataDAO.exists(idForm)) {
            datosRecopilados = dataDAO.getObject(idForm);
        } else {
            datosRecopilados = new ArrayList();
        }
        
        for (Componente c : form.getComponentes()) {
            values = null;
            value = null;
            switch (c.getClase()) {
                case "CHECKBOX" -> {
                    values = Arrays.toString(request.getParameterValues(c.getFormulario())).replace("[", "").replace("]", "").replaceAll("\\s", "");
                }
                case "FICHERO" -> {
                    try {
                        part = request.getPart(c.getFormulario());
                        value = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    } catch (IOException | ServletException e) {
                    }
                }
                case "CAMPO_TEXTO", "RADIO", "COMBO", "AREA_TEXTO" ->
                    value = request.getParameter(c.getFormulario());
            }
            if (values != null) {
                if (!values.equals("null")) {
                    datosRecopilados.add(new DatoRecopilado(c.getFormulario(), values));
                }
            }

            if (value != null) {
                if (!value.trim().isEmpty()) {
                    datosRecopilados.add(new DatoRecopilado(c.getFormulario(), value));
                }
            }
        }
        
        dataDAO.create(datosRecopilados, idForm);

        response.sendRedirect("success.jsp");
    }
}
