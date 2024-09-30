package com.response.controller.textEditor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author vicente
 */
public class SendDataToServer {
    
    private static final String URL = "http://localhost:8080/TriviApp/requestReader";
    private static final String URL2 = "http://localhost:8080/TriviApp/import";
    private static final HttpClient client = HttpClient.newHttpClient();
    
    public static String send(String text, String loggedUser) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(text))
                .setHeader("loggedUser", loggedUser)
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(System.out);
            return e.getMessage();
        }
    }
    
    public static String send(String text, String loggedUser, String idForm) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(text))
                .setHeader("loggedUser", loggedUser)
                .setHeader("idForm", idForm)
                .uri(URI.create(URL2))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(System.out);
            return e.getMessage();
        }
    }
}
