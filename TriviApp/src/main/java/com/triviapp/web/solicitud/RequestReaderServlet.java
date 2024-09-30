package com.triviapp.web.solicitud;

import com.triviapp.aux.solicitud.RequestExecutor;
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
@WebServlet("/requestReader")
public class RequestReaderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        BufferedReader reader = request.getReader();
        String loggedUser = request.getHeader("loggedUser");

        RequestExecutor executor = new RequestExecutor();
        String respuesta = executor.run(reader, loggedUser);
        
        try (PrintWriter out = response.getWriter()) {
            out.println(respuesta);
        } catch (Exception e) {
        }

    }

}