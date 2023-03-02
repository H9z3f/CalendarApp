package com.javafx.calendarapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarView {
    private ChoiceBox<String> monthFld;
    private Spinner<Integer> yearFld;
    private DatePicker dateFld;
    private Text calendarFld;
    private Text holidaysFld;

    public CalendarView(ChoiceBox<String> monthFld, Spinner<Integer> yearFld, DatePicker dateFld, Text calendarFld, Text holidaysFld) {
        this.monthFld = monthFld;
        this.yearFld = yearFld;
        this.dateFld = dateFld;
        this.calendarFld = calendarFld;
        this.holidaysFld = holidaysFld;
    }
    public CalendarView(ChoiceBox<String> monthFld, Spinner<Integer> yearFld, Text calendarFld) {
        this.monthFld = monthFld;
        this.yearFld = yearFld;
        this.calendarFld = calendarFld;
    }

    public CalendarView(DatePicker dateFld, Text holidaysFld) {
        this.dateFld = dateFld;
        this.holidaysFld = holidaysFld;
    }

    public void init() {
        Calendar calendar = Calendar.getInstance();
        LocalDate currentDate = LocalDate.now();
        ObservableList<String> monthList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        SpinnerValueFactory<Integer> yearRange = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999, currentYear);
        monthFld.setValue(monthList.get(currentMonth));
        monthFld.setItems(monthList);
        yearFld.setValueFactory(yearRange);
        dateFld.setValue(currentDate);
        displayCalendar();
        displayHolidays();
    }

    public void displayCalendar() {
        String month = monthFld.getValue();
        int year = yearFld.getValue();
        CalendarModel calendarModel = new CalendarModel(month, year);
        String calendar = calendarModel.returnCalendar();
        calendarFld.setText(calendar);
    }

    public void clearCalendar() {
        calendarFld.setText("");
    }

    public void displayHolidays() {
        LocalDate date = dateFld.getValue();
        CalendarModel calendarModel = new CalendarModel(date);
        String holidays = calendarModel.returnHolidays();
        holidaysFld.setText(holidays);
    }

    public void clearHolidays() {
        holidaysFld.setText("");
    }
}
