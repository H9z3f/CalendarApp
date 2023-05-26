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
import ru.mai.softwaredevelopment.client.models.interactions.NoteResponse;
import ru.mai.softwaredevelopment.client.models.interactions.ServerRequests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteModel {
    private String sessionKey;
    private String username;
    private Date date;
    private String note;

    public NoteModel(String sessionKey, String username, Date date) {
        this.sessionKey = sessionKey;
        this.username = username;
        this.date = date;
    }

    public NoteModel(String sessionKey, String username, Date date, String note) {
        this.sessionKey = sessionKey;
        this.username = username;
        this.date = date;
        this.note = note;
    }

    public void addNote() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        NoteResponse noteResponse;
        try {
            response = ServerRequests.addNoteRequest(sessionKey, username, simpleDateFormat.format(date), note);
            noteResponse = objectMapper.readValue(response, NoteResponse.class);
        } catch (IOException e) {
            initializeConnectionErrorStage();
        }
    }

    public void removeNote() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);

        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        NoteResponse noteResponse;
        try {
            response = ServerRequests.removeNoteRequest(sessionKey, username, simpleDateFormat.format(date));
            noteResponse = objectMapper.readValue(response, NoteResponse.class);
        } catch (IOException e) {
            initializeConnectionErrorStage();
        }
    }

    public void initializeConnectionErrorStage() {
        Stage connectionErrorStage = new Stage();

        Label label = new Label("This action cannot be performed...\nPlease reconnect to the internet or report a possible server error to us!");

        Button button = new Button("OK");
        button.setOnMouseClicked(mouseEvent -> {
            connectionErrorStage.close();
        });

        VBox vBox = new VBox(label, button);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        connectionErrorStage.initModality(Modality.APPLICATION_MODAL);
        connectionErrorStage.setResizable(false);
        connectionErrorStage.setScene(scene);
        connectionErrorStage.setTitle("Failure");
        connectionErrorStage.showAndWait();
    }
}