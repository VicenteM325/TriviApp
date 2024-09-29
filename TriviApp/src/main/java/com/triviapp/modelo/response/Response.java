package com.triviapp.modelo.response;

/**
 *
 * @author vicente
 */
public class Response {
    private String message;
    private String loggedUser;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, String loggedUser) {
        this.message = message;
        this.loggedUser = loggedUser;
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
}
