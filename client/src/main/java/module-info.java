module ru.mai.softwaredevelopment.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens ru.mai.softwaredevelopment.client to javafx.fxml;
    exports ru.mai.softwaredevelopment.client;
    opens ru.mai.softwaredevelopment.client.controllers to javafx.fxml;
    exports ru.mai.softwaredevelopment.client.controllers;
    opens ru.mai.softwaredevelopment.client.models.interactions to com.fasterxml.jackson.databind;
    exports ru.mai.softwaredevelopment.client.models.interactions;
}