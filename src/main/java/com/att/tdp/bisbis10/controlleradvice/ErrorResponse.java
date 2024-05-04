package com.att.tdp.bisbis10.controlleradvice;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
