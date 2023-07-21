package com.mslearning.attendancesystem.error;

import java.sql.Timestamp;

public class ErrorResponse {

    String message;

    Timestamp timestamp;

    String description;

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
