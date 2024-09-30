package com.response.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vicente
 */
public class FileController {
    
    public static String readFile(String path) {
        String texto = "";
        ArrayList<String> textos;
        try {
            File archivo = new File(path);
            Scanner lectura = new Scanner(archivo);
            textos = new ArrayList<>();
            while (lectura.hasNext()) {
                textos.add(lectura.nextLine());
            }
            for (String t : textos) {
                texto += t + "\n";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo");
        }
        return texto;
    }

    public static void saveFile(String path, String texto) {
        try (PrintWriter escribir = new PrintWriter(path)) {
                escribir.print(texto);
        } catch (IOException ex) {
            System.out.println("No se encontró el archivo");
        }
    }

    public static boolean verifyFile(String archivo) {
        File file = new File(archivo);
        boolean exists = file.exists();
        return exists;
    }
}