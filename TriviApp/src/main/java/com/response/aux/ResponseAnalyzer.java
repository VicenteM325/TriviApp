package com.response.aux;

import com.response.analizador.lexico.ResponseLexer;
import com.response.analizador.sintactico.ResponseParser;
import com.response.modelo.Respuesta;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class ResponseAnalyzer {

    private ResponseLexer lexer;
    private ResponseParser parser;
    private List<Respuesta> respuestas;
    private String loggedUser;
    private StringBuilder messages;

    public ResponseAnalyzer() {
    }

    public void analyze(String s) {
        respuestas = new ArrayList();
        loggedUser = null;
        StringReader reader = new StringReader(s);
        try {
            lexer = new ResponseLexer(reader);
            parser = new ResponseParser(lexer);
            parser.parse();
            respuestas = parser.getRespuestas();
        } catch (Exception e) {
            System.out.println("Entrada vacia");
        }
    }

    public String getMessages() {
        messages = new StringBuilder();

        if (respuestas != null) {
            respuestas.forEach(r -> {
                if (r.getLoggedUser() != null) {
                    loggedUser = r.getLoggedUser();
                }
                addLinea("  " + r.getMessage());
            });
        }

        return messages.toString();
    }

    public String getLoggedUser() {
        return (loggedUser != null) ? loggedUser.replace("\"", "") : "";
    }

    private void addLinea(String s) {
        messages.append(s.replace("\"", "")).append("\n");
    }
}
