package com.triviapp.aux.solicitud;

import com.triviapp.analizadores.lexico.RequestsLexer;
import com.triviapp.analizadores.sintactico.parser;
import com.triviapp.modelo.solicitudes.Solicitud;
import com.triviapp.modelo.errores.Error;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class RequestAnalyzer {

    private RequestsLexer lex;
    private parser parser;
    private List<Error> errores = new ArrayList<>();
    private List<Solicitud> solicitudes = new ArrayList<>();

    public RequestAnalyzer() {
    }

    public void analyze(Reader reader) {
        try {
            lex = new RequestsLexer(reader);
            parser = new parser(lex);
            parser.parse();
            setListados();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public List<Error> getErrores() {
        return errores;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    private void setListados() {
        errores.addAll(lex.getErrores());
        errores.addAll(parser.getErrores());
        
        solicitudes = parser.getSolicitudes();

    }
}
