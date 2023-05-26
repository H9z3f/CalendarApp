package ru.mai.softwaredevelopment.client.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.mai.softwaredevelopment.client.modules.MonthlyCalendar;

public class MainView {
    public static void initializeCalendar(GridPane gridPane, MonthlyCalendar monthlyCalendar) {
        gridPane.add(monthlyCalendar, 0, 1);
    }

    public static void setCalendarDisabled(MonthlyCalendar monthlyCalendar) {
        monthlyCalendar.setDisable(true);
    }

    public static void setCalendarEnabled(MonthlyCalendar monthlyCalendar) {
        monthlyCalendar.setDisable(false);
    }

    public static void setMonth(Label label, String string) {
        label.setText(string);
    }

    public static void setTodayButtonDisabled(Button button) {
        button.setDisable(true);
    }

    public static void setTodayButtonEnabled(Button button) {
        button.setDisable(false);
    }

    public static void setLogInButtonDisabled(Button button) {
        button.setDisable(true);
    }

    public static void setLogInButtonEnabled(Button button) {
        button.setDisable(false);
    }

    public static void setLogOutButtonDisabled(Button button) {
        button.setDisable(true);
    }

    public static void setLogOutButtonEnabled(Button button) {
        button.setDisable(false);
    }
}