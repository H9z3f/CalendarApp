package ru.mai.softwaredevelopment.client.views.animations;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Animations {
    public static void textFieldShaking(TextField textField) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(75), textField);
        translateTransition.setFromX(-5);
        translateTransition.setToX(5);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(true);
        translateTransition.setOnFinished(actionEvent -> {
            textField.setTranslateX(0);
        });

        translateTransition.playFromStart();
    }

    public static void textFieldPainting(TextField textField) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(375));
        pauseTransition.setOnFinished(actionEvent -> {
            textField.setStyle("-fx-border-color: none");
        });

        textField.setStyle("-fx-border-color: red");

        pauseTransition.playFromStart();
    }
}