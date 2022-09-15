package com.projects.oms.models;

public class JSONResponse {
    private String message;
    private String status;

    public JSONResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
