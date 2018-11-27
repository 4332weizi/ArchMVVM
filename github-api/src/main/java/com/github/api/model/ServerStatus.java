package com.github.api.model;

public class ServerStatus {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAlive() {
        return message != null && message.contains("lives");
    }
}
