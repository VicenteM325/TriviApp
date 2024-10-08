package com.triviapp.web.form;

import static com.triviapp.aux.FileController.readFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author vicente
 */
@WebServlet("/export")
public class FormExportServlet extends HttpServlet {

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
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + idForm + ".trivia\"");

        String nameUser = System.getProperty("user.name");
        String content = readFile("/home/" + nameUser + "/NetBeansProjects/TriviApp/data/trivias/" + idForm + "/estructura.db");
        

        File form = new File(idForm + ".trivia");
        FileWriter fileWriter = new FileWriter(form);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        try (FileInputStream inputStream = new FileInputStream(form);
             PrintWriter out = response.getWriter()) {
            int i;
            while ((i = inputStream.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

