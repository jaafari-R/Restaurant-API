package com.att.tdp.bisbis10.controlleradvice;

import java.util.List;

public class ErrorResponse {
    private int status;
    private List<String> messages;

    public ErrorResponse(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
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

    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    
}
