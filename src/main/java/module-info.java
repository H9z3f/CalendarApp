module com.javafx.calendarapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafx.calendarapp to javafx.fxml;
    exports com.javafx.calendarapp;
}