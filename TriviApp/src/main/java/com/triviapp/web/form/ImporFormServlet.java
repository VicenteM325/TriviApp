package com.triviapp.web.form;

import com.triviapp.executor.form.ImportFormExecutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author vicente
 */
@WebServlet("/import")
public class ImporFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/plain");
        BufferedReader reader = request.getReader();
        String loggedUser = request.getHeader("loggedUser");
        String idForm = request.getHeader("idForm");
        
        ImportFormExecutor importFE = new ImportFormExecutor();
        String respuesta = importFE.execute(reader, loggedUser, idForm);
        
        try (PrintWriter out = response.getWriter()) {
            out.println(respuesta);
        } catch (Exception e) {
        }
    }
}
