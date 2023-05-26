package ru.mai.softwaredevelopment.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.mai.softwaredevelopment.client.models.MainModel;

public class MainController {
    private static MainController mainController;
    private static MainModel mainModel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logInButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label monthField;

    @FXML
    private Button nextMonthButton;

    @FXML
    private TextArea noteDisplayField;

    @FXML
    private Label noteField;

    @FXML
    private VBox noteArea;

    @FXML
    private Button previousMonthButton;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button todayButton;

    @FXML
    private Label usernameField;

    @FXML
    private void initialize() {
        mainController = this;
        mainModel = new MainModel();

        mainModel.initializePrimaryStage();
    }

    @FXML
    private void onLogInButtonAction(ActionEvent event) {
        mainModel.initializeSecondaryStage();
    }

    @FXML
    private void onLogOutButtonAction(ActionEvent event) {
        mainModel.logOut();
    }

    @FXML
    private void onNextMonthButtonAction(ActionEvent event) {
        mainModel.nextMonth();
    }

    @FXML
    private void onPreviousMonthButtonAction(ActionEvent event) {
        mainModel.previousMonth();
    }

    @FXML
    private void onTodayButtonAction(ActionEvent event) {
        mainModel.returnToday();
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static MainModel getMainModel() {
        return mainModel;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getLogInButton() {
        return logInButton;
    }

    public Button getLogOutButton() {
        return logOutButton;
    }

    public Label getMonthField() {
        return monthField;
    }

    public Button getNextMonthButton() {
        return nextMonthButton;
    }

    public TextArea getNoteDisplayField() {
        return noteDisplayField;
    }

    public Label getNoteField() {
        return noteField;
    }

    public VBox getNoteArea() {
        return noteArea;
    }

    public Button getPreviousMonthButton() {
        return previousMonthButton;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getTodayButton() {
        return todayButton;
    }

    public Label getUsernameField() {
        return usernameField;
    }
}