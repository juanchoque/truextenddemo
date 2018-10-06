package com.truextend.dev.recipes.model;

public class MessageError {
    private String message;
    private Integer status;

    public MessageError() {
    }

    public MessageError(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
