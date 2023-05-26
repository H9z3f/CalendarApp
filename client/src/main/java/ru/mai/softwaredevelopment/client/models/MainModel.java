package ru.mai.softwaredevelopment.client.models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.mai.softwaredevelopment.client.ApplicationEntry;
import ru.mai.softwaredevelopment.client.controllers.AuthorizationController;
import ru.mai.softwaredevelopment.client.controllers.MainController;
import ru.mai.softwaredevelopment.client.modules.MonthlyCalendar;
import ru.mai.softwaredevelopment.client.views.MainView;
import ru.mai.softwaredevelopment.client.views.NoteView;
import ru.mai.softwaredevelopment.client.views.UserView;
import ru.mai.softwaredevelopment.client.views.animations.Animations;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainModel {
    private MonthlyCalendar monthlyCalendar;
    private MainController mainController;
    private AuthorizationController authorizationController;
    private UserModel userModel;
    private NoteModel noteModel;
    private Stage secondaryStage;
    private HashMap<Date, String> notes;

    public MainModel() {
        monthlyCalendar = new MonthlyCalendar();
        mainController = MainController.getMainController();
    }

    public void initializePrimaryStage() {
        initializeButtons();

        MainView.initializeCalendar(mainController.getGridPane(), monthlyCalendar);
        MainView.setCalendarDisabled(monthlyCalendar);
        MainView.setMonth(mainController.getMonthField(), monthlyCalendar.getMonth() + " - " + monthlyCalendar.getYear());
        MainView.setTodayButtonDisabled(mainController.getTodayButton());
        MainView.setLogOutButtonDisabled(mainController.getLogOutButton());
        NoteView.setNoteDisabled(mainController.getNoteArea());
        NoteView.setNote(mainController.getNoteDisplayField(), "Note-taking system is not available to unauthorized users...");
    }

    private void initializeButtons() {
        for (Button button : monthlyCalendar.getButtons()) {
            button.setOnMouseClicked(mouseEvent -> {
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("'Note -' d 'of' MMMM", Locale.ENGLISH);

                Date date;
                try {
                    date = inputDateFormat.parse(button.getText() + "." + (Arrays.asList(MonthlyCalendar.MONTHS).indexOf(monthlyCalendar.getMonth()) + 1) + "." + monthlyCalendar.getYear());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                addEvents(date);

                NoteView.setNoteEnabled(mainController.getNoteArea());
                NoteView.setNoteHeader(mainController.getNoteField(), outputDateFormat.format(date));
                NoteView.setNote(mainController.getNoteDisplayField(), notes.get(date));
            });
        }
    }

    private void addEvents(Date date) {
        mainController.getSaveButton().setOnMouseClicked(mouseEvent -> {
            String note = mainController.getNoteDisplayField().getText();

            noteModel = new NoteModel(userModel.getSessionKey(), userModel.getUsername(), date, note);
            noteModel.addNote();

            notes.put(date, note);
        });
        mainController.getDeleteButton().setOnMouseClicked(mouseEvent -> {
            noteModel = new NoteModel(userModel.getSessionKey(), userModel.getUsername(), date);
            noteModel.removeNote();

            notes.remove(date);

            NoteView.setNote(mainController.getNoteDisplayField(), "");
        });
    }

    public void previousMonth() {
        monthlyCalendar.prevMonth();

        MainView.setMonth(mainController.getMonthField(), monthlyCalendar.getMonth() + " - " + monthlyCalendar.getYear());
        NoteView.setNoteDisabled(mainController.getNoteArea());
        NoteView.setNoteHeader(mainController.getNoteField(), "Note");

        if (userModel != null) {
            NoteView.setNote(mainController.getNoteDisplayField(), "Select date...");
        }
    }

    public void nextMonth() {
        monthlyCalendar.nextMonth();

        MainView.setMonth(mainController.getMonthField(), monthlyCalendar.getMonth() + " - " + monthlyCalendar.getYear());
        NoteView.setNoteDisabled(mainController.getNoteArea());
        NoteView.setNoteHeader(mainController.getNoteField(), "Note");

        if (userModel != null) {
            NoteView.setNote(mainController.getNoteDisplayField(), "Select date...");
        }
    }

    public void initializeSecondaryStage() {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationEntry.class.getResource("authorization-window.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            Label label = new Label("Fatal error!");

            Button button = new Button("OK");
            button.setOnMouseClicked(mouseEvent -> {
                secondaryStage.close();
            });

            VBox vBox = new VBox(label, button);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.CENTER);

            scene = new Scene(vBox);
        }

        secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setResizable(false);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Authentication");
        secondaryStage.showAndWait();
    }

    private void destroySecondaryStage() {
        authorizationController = null;

        secondaryStage.close();
    }

    public void logIn() {
        authorizationController = AuthorizationController.getAuthorizationController();

        TextField usernameField = authorizationController.getUsernameLogInField();
        TextField passwordField = authorizationController.getPasswordLogInField();

        userModel = new UserModel(usernameField.getText(), passwordField.getText());
        if (userModel.logIn()) {
            doOnSuccess();
        } else {
            doOnFailure(usernameField, passwordField);
        }
    }

    public void signUp() {
        authorizationController = AuthorizationController.getAuthorizationController();

        TextField usernameField = authorizationController.getUsernameSignUpField();
        TextField passwordField = authorizationController.getPasswordSignUpField();

        userModel = new UserModel(usernameField.getText(), passwordField.getText());
        if (userModel.signUp()) {
            doOnSuccess();
        } else {
            doOnFailure(usernameField, passwordField);
        }
    }

    private void doOnSuccess() {
        notes = userModel.getNotes();

        destroySecondaryStage();
        returnToday();

        MainView.setCalendarEnabled(monthlyCalendar);
        MainView.setTodayButtonEnabled(mainController.getTodayButton());
        MainView.setLogInButtonDisabled(mainController.getLogInButton());
        MainView.setLogOutButtonEnabled(mainController.getLogOutButton());
        UserView.setUsername(mainController.getUsernameField(), "Hello, " + userModel.getUsername() + "!");
    }

    private void doOnFailure(TextField usernameField, TextField passwordField) {
        Animations.textFieldShaking(usernameField);
        Animations.textFieldShaking(passwordField);
        Animations.textFieldPainting(usernameField);
        Animations.textFieldPainting(passwordField);
    }

    public void logOut() {
        userModel = null;

        MainView.setCalendarDisabled(monthlyCalendar);
        MainView.setTodayButtonDisabled(mainController.getTodayButton());
        MainView.setLogInButtonEnabled(mainController.getLogInButton());
        MainView.setLogOutButtonDisabled(mainController.getLogOutButton());
        NoteView.setNoteDisabled(mainController.getNoteArea());
        NoteView.setNoteHeader(mainController.getNoteField(), "Note");
        NoteView.setNote(mainController.getNoteDisplayField(), "Note-taking system is not available to unauthorized users...");
        UserView.setUsername(mainController.getUsernameField(), "Calendar");
    }

    public void returnToday() {
        monthlyCalendar.returnToday();

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("'Note -' d 'of' MMMM", Locale.ENGLISH);

        Date date;
        try {
            date = inputDateFormat.parse(monthlyCalendar.getCurrentButton().getText() + "." + (Arrays.asList(MonthlyCalendar.MONTHS).indexOf(monthlyCalendar.getMonth()) + 1) + "." + monthlyCalendar.getYear());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        addEvents(date);

        MainView.setMonth(mainController.getMonthField(), monthlyCalendar.getMonth() + " - " + monthlyCalendar.getYear());
        NoteView.setNoteEnabled(mainController.getNoteArea());
        NoteView.setNoteHeader(mainController.getNoteField(), outputDateFormat.format(date));
        NoteView.setNote(mainController.getNoteDisplayField(), notes.get(date));
    }
}