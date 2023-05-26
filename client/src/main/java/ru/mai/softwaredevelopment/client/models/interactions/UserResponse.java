package ru.mai.softwaredevelopment.client.models.interactions;

import java.util.Date;
import java.util.HashMap;

public class UserResponse {
    private boolean success;
    private String description;
    private String sessionKey;
    private HashMap<Date, String> notes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public HashMap<Date, String> getNotes() {
        return notes;
    }

    public void setNotes(HashMap<Date, String> notes) {
        this.notes = notes;
    }
}