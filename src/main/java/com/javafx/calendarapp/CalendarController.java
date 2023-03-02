package com.javafx.calendarapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

public class CalendarController {
    @FXML
    private Button calendarClearBtn;

    @FXML
    private Button calendarEnterBtn;

    @FXML
    private Text calendarFld;

    @FXML
    private DatePicker dateFld;

    @FXML
    private Button holidaysClearBtn;

    @FXML
    private Button holidaysEnterBtn;

    @FXML
    private Text holidaysFld;

    @FXML
    private ChoiceBox<String> monthFld;

    @FXML
    private Spinner<Integer> yearFld;

    @FXML
    private void initialize() {
        CalendarView calendarView = new CalendarView(monthFld, yearFld, dateFld, calendarFld, holidaysFld);
        calendarView.init();
    }

    @FXML
    private void onCalendarEnterBtnAction() {
        CalendarView calendarView = new CalendarView(monthFld, yearFld, calendarFld);
        calendarView.displayCalendar();
    }

    @FXML
    private void onCalendarClearBtnAction() {
        CalendarView calendarView = new CalendarView(monthFld, yearFld, calendarFld);
        calendarView.clearCalendar();
    }

    @FXML
    private void onHolidaysEnterBtnAction() {
        CalendarView calendarView = new CalendarView(dateFld, holidaysFld);
        calendarView.displayHolidays();
    }

    @FXML
    private void onHolidaysClearBtnAction() {
        CalendarView calendarView = new CalendarView(dateFld, holidaysFld);
        calendarView.clearHolidays();
    }
}
