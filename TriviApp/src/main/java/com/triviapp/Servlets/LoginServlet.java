package com.triviapp.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.triviapp.analizadores.sintactico.parser;

/**
 *
 * @author vicente
 */
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String inputField = request.getParameter("inputField");

        // Inicializa el parser con los datos de entrada
        parser parser = new parser();
        
        // Supongamos que tu parser devuelve errores o un resultado exitoso
        if (parser.getErrores().isEmpty()) {
            // Maneja el inicio de sesión exitoso (puedes redirigir o mostrar un mensaje de éxito)
            response.sendRedirect("success.html"); // O muestra un mensaje de éxito
        } else {
            // Maneja los errores
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Errores:</h3><ul>");
            for (com.triviapp.modelo.errores.Error error : parser.getErrores()) {
                out.println("<li>" + error.getDescripcion() + "</li>"); // Personaliza esto para mostrar información del error
            }
            out.println("</ul></body></html>");
        }
    }
    
}
