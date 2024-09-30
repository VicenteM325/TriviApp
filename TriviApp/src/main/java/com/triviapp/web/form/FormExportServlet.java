package com.triviapp.web.form;

import static com.triviapp.aux.FileController.readFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
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
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + idForm + ".form");

        String nameUser = System.getProperty("user.name");
        String content = readFile("/home/" + nameUser + "/NetBeansProjects/triviapp/data/forms/" + idForm + "/estructura.db");
        File form = new File(idForm + ".form");
        FileWriter fileWriter = new FileWriter(form);
        try ( BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        FileInputStream inputStream = new FileInputStream(form);
        try ( PrintWriter out = response.getWriter()) {
            int i;
            while ((i = inputStream.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
