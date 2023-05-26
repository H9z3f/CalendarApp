package ru.mai.softwaredevelopment.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.mai.softwaredevelopment.client.models.MainModel;

public class AuthorizationController {
    private static AuthorizationController authorizationController;
    private MainModel mainModel;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passwordLogInField;

    @FXML
    private TextField passwordSignUpField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameLogInField;

    @FXML
    private TextField usernameSignUpField;

    @FXML
    private void initialize() {
        authorizationController = this;
        mainModel = MainController.getMainModel();
    }

    @FXML
    private void onLogInButtonAction(ActionEvent event) {
        mainModel.logIn();
    }

    @FXML
    private void onSignUpButtonAction(ActionEvent event) {
        mainModel.signUp();
    }

    public static AuthorizationController getAuthorizationController() {
        return authorizationController;
    }

    public Button getLogInButton() {
        return logInButton;
    }

    public TextField getPasswordLogInField() {
        return passwordLogInField;
    }

    public TextField getPasswordSignUpField() {
        return passwordSignUpField;
    }

    public Button getSignUpButton() {
        return signUpButton;
    }

    public TextField getUsernameLogInField() {
        return usernameLogInField;
    }

    public TextField getUsernameSignUpField() {
        return usernameSignUpField;
    }
}