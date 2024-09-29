package com.triviapp.modelo;

/**
 *
 * @author vicente
 */
public class Token {
    private int linea;
    private int columna;
    private final String lexema;

    public Token(int linea, int columna, String lexema) {
        this.linea = linea + 1;
        this.columna = columna + 1;
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getLexema() {
        return lexema;
    }
}
