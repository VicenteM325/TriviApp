package com.triviapp.datos;

import com.triviapp.analizadores.lexico.StorageLexer;
import com.triviapp.analizadores.sintactico.StorageParser;
import com.triviapp.modelo.DatoRecopilado;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.Usuario;
import java.io.Reader;
import java.util.List;

/**
 *
 * @author vicente
 */
public class StorageFileAnalyzer {
    private StorageLexer lex;
    private StorageParser parser;

    public StorageFileAnalyzer() {
    }
    
    public Usuario analyzeUser(Reader reader) {
        try {
            lex = new StorageLexer(reader);
            parser = new StorageParser(lex);
            
            parser.parse();
            return parser.getUsuario();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public Formulario analyzeForm(Reader reader){
        try {
            lex = new StorageLexer(reader);
            parser = new StorageParser(lex);
            
            parser.parse();
            return parser.getForm();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public List<DatoRecopilado> analyzeData(Reader reader) {
        try {
            lex = new StorageLexer(reader);
            parser = new StorageParser(lex);
            
            parser.parse();
            return parser.getDatos();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
}
