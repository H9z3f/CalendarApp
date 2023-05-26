package ru.mai.softwaredevelopment.client.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.mai.softwaredevelopment.client.models.interactions.ServerRequests;
import ru.mai.softwaredevelopment.client.models.interactions.UserResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

public class UserModel {
    private String username;
    private String password;
    private String sessionKey;
    private HashMap<Date, String> notes;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boolean logIn() {
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        UserResponse userResponse = new UserResponse();
        try {
            response = ServerRequests.loginRequest(hashToHex(username), hashToHex(password));
            userResponse = objectMapper.readValue(response, UserResponse.class);
        } catch (IOException e) {
            initializeAuthenticationErrorStage(userResponse.getDescription());
            return false;
        }

        sessionKey = userResponse.getSessionKey();
        notes = userResponse.getNotes();

        if (!userResponse.isSuccess()) {
            initializeAuthenticationErrorStage(userResponse.getDescription());
        }

        return userResponse.isSuccess();
    }

    public Boolean signUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        UserResponse userResponse = new UserResponse();
        try {
            response = ServerRequests.registrationRequest(hashToHex(username), hashToHex(password));
            userResponse = objectMapper.readValue(response, UserResponse.class);
        } catch (IOException e) {
            initializeAuthenticationErrorStage(userResponse.getDescription());
            return false;
        }

        sessionKey = userResponse.getSessionKey();
        notes = new HashMap<>();

        if (!userResponse.isSuccess()) {
            initializeAuthenticationErrorStage(userResponse.getDescription());
        }

        return userResponse.isSuccess();
    }

    private String hashToHex(String s) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashBytes = messageDigest.digest(s.getBytes());

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }

    private void initializeAuthenticationErrorStage(String s) {
        Stage authenticationErrorStage = new Stage();

        Label label = new Label(s);

        Button button = new Button("OK");
        button.setOnMouseClicked(mouseEvent -> {
            authenticationErrorStage.close();
        });

        VBox vBox = new VBox(label, button);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        authenticationErrorStage.initModality(Modality.APPLICATION_MODAL);
        authenticationErrorStage.setResizable(false);
        authenticationErrorStage.setScene(scene);
        authenticationErrorStage.setTitle("Failure");
        authenticationErrorStage.showAndWait();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public HashMap<Date, String> getNotes() {
        return notes;
    }
}