package com.triviapp.generator;

/**
 *
 * @author vicente
 */
public abstract class Generator {
    protected StringBuilder text;
    
    public abstract String generate();
    
    protected void addLine(String s, int tabulaciones) {

        for (int i = 0; i < tabulaciones; i++) {
            text.append("    ");
        }

        text.append(s).append("\n");
    }
}
