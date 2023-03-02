package com.javafx.calendarapp;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarModel {
    private String month;
    private int year;
    private LocalDate date;

    public CalendarModel(String month, int year) {
        this.month = month;
        this.year = year;
    }

    public CalendarModel(LocalDate date) {
        this.date = date;
    }

    public String returnCalendar() {
        String stringedCalendar = "Su Mo Tu We Th Fr Sa";
        Calendar calendar = Calendar.getInstance();
        switch (month) {
            case "January" -> calendar.set(year, Calendar.JANUARY, 1);
            case "February" -> calendar.set(year, Calendar.FEBRUARY, 1);
            case "March" -> calendar.set(year, Calendar.MARCH, 1);
            case "April" -> calendar.set(year, Calendar.APRIL, 1);
            case "May" -> calendar.set(year, Calendar.MAY, 1);
            case "June" -> calendar.set(year, Calendar.JUNE, 1);
            case "July" -> calendar.set(year, Calendar.JULY, 1);
            case "August" -> calendar.set(year, Calendar.AUGUST, 1);
            case "September" -> calendar.set(year, Calendar.SEPTEMBER, 1);
            case "October" -> calendar.set(year, Calendar.OCTOBER, 1);
            case "November" -> calendar.set(year, Calendar.NOVEMBER, 1);
            case "December" -> calendar.set(year, Calendar.DECEMBER, 1);
        }
        int daysAmount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < daysAmount + weekDay; ++i) {
            if (i % 7 == 0) {
                stringedCalendar += "\n";
            }
            if (i + 1 < weekDay) {
                stringedCalendar += "   ";
            } else if (i + 1 < daysAmount + weekDay) {
                if (i - weekDay + 2 < 10) {
                    stringedCalendar += " " + (i - weekDay + 2) + " ";
                } else {
                    stringedCalendar += (i - weekDay + 2) + " ";
                }
            }
        }
        return stringedCalendar;
    }

    public String returnHolidays() { //Реализовать!
        /*
         * 1) Распарсить файл с праздниками в HashMap /
         * подключить БД (заполненную).
         * 2) Переменной stringedHolidays присвоить значение из HashMap по ключу this.date (после приведения к типу String) /
         * Переменной stringedHolidays присвоить значение, полученное из БД по запросу this.date (после приведения к типу String).
         * */
        String stringedHolidays = "Holidays";
        return stringedHolidays;
    }
}
