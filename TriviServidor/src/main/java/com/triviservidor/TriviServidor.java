
package com.triviservidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author vicente
 */
public class TriviServidor {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String solicitud = in.readLine();
                    if (solicitud != null && solicitud.contains("LOGIN_USUARIO")) {
                        String usuario = in.readLine();
                        String password = in.readLine();

                        if (verificarCredenciales(usuario, password)) {
                            out.println("Inicio de sesión exitoso");
                        } else {
                            out.println("Error: Credenciales incorrectas");
                        }
                    }

                } catch (IOException e) {
                    System.err.println("Error con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static boolean verificarCredenciales(String usuario, String password) {
        File archivoUsuarios = new File("Usuarios.xson");

        try (Scanner scanner = new Scanner(archivoUsuarios)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                if (linea.contains("\"USUARIO\": \"" + usuario + "\"") &&
                    scanner.nextLine().contains("\"PASSWORD\": \"" + password + "\"")) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo de usuarios no encontrado: " + e.getMessage());
        }

        return false;
    }
}
