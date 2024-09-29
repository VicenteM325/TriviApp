package com.triviapp.modelo.errores;

/**
 *
 * @author vicente
 */
public class ErrorAnalisis extends Error {
    private String lexema;
    private int linea;
    private int columna;
    private TipoError tipoError;

    public ErrorAnalisis(String lexema, int linea, int columna, TipoError tipoError, String descripcion) {
        super(descripcion);
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipoError = tipoError;
    }

    public ErrorAnalisis() {
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
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

    public TipoError getTipoError() {
        return tipoError;
    }

    public void setTipoError(TipoError tipoError) {
        this.tipoError = tipoError;
    }

    @Override
    public String getDescripcion() {
        StringBuilder desc = new StringBuilder();
        desc.append("Error en lexema: ").append(getLexema())
                .append(" -  linea: ").append(getLinea())
                .append(" y columna: ").append(getColumna())
                .append(" - ").append(super.getDescripcion());
        return desc.toString();
    }
}
