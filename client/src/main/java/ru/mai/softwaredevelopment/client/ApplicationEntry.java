package ru.mai.softwaredevelopment.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationEntry extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationEntry.class.getResource("main-window.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            Label label = new Label("Fatal error!");

            Button button = new Button("OK");
            button.setOnMouseClicked(mouseEvent -> {
                stage.close();
            });

            VBox vBox = new VBox(label, button);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(20));
            vBox.setAlignment(Pos.CENTER);

            scene = new Scene(vBox);
        }

        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Application");
        stage.show();
    }
}