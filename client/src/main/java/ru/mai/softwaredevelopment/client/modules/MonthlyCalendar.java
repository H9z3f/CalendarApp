package ru.mai.softwaredevelopment.client.modules;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class MonthlyCalendar extends GridPane {
    public static final String[] MONTHS = new String[]{"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
    public static final String[] WEEKDAYS = new String[]{"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
    public static final float SIZE_X = 350;
    public static final float SIZE_Y = 350;
    private ArrayList<Button> buttons;
    private Calendar calendar;
    private int year;
    private String month;
    private Button currentButton;
    private Paint currentButtonPaint;

    public MonthlyCalendar() {
        initialize();
    }

    private void initialize() {
        buttons = new ArrayList<>();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        for (int i = 0; i < WEEKDAYS.length; ++i) {
            Label label = new Label(WEEKDAYS[i]);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            if (i >= 5) label.setTextFill(Color.RED);
            label.setPrefSize(SIZE_X / 7, SIZE_Y / 7);
            label.setAlignment(Pos.CENTER);

            this.add(label, i, 0);
        }

        for (int j = 1; j <= 6; ++j) {
            for (int i = 0; i < WEEKDAYS.length; ++i) {
                Button button = new Button();
                button.setFont(Font.font("Arial", 14));
                if (i >= 5) button.setTextFill(Color.RED);
                button.setPrefSize(SIZE_X / 7, SIZE_Y / 7);
                button.setAlignment(Pos.CENTER);

                this.add(button, i, j);
                buttons.add(button);
            }
        }

        addContent();
    }

    private void addContent() {
        deleteContent();

        calendar = Calendar.getInstance();
        calendar.set(year, Arrays.asList(MONTHS).indexOf(month), 1);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == 1) {
            weekDay += 5;
        } else {
            weekDay -= 2;
        }
        int daysQuantity = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        int i = 0;
        for (Button button : buttons) {
            if (currentDay == i - weekDay + 1 && currentYear == year && currentMonth.equals(month)) {
                currentButton = button;
                currentButtonPaint = button.getTextFill();

                button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                button.setStyle("-fx-border-color: green");
                button.setTextFill(Color.GREEN);
            }

            if (i >= weekDay && daysQuantity + weekDay > 0) {
                button.setText(String.valueOf(i - weekDay + 1));
            } else {
                button.setDisable(true);
            }

            ++i;
            --daysQuantity;
        }
    }

    private void deleteContent() {
        for (Button button : buttons) {
            button.setDisable(false);
            button.setText("");
        }

        if (currentButton == null) return;

        currentButton.setFont(Font.font("Arial", 14));
        currentButton.setStyle("-fx-border-color: none");
        currentButton.setTextFill(currentButtonPaint);
    }

    public void prevMonth() {
        if (month.equals(MONTHS[0])) {
            --year;
            month = MONTHS[MONTHS.length - 1];
        } else {
            month = MONTHS[Arrays.asList(MONTHS).indexOf(month) - 1];
        }

        addContent();
    }

    public void nextMonth() {
        if (month.equals(MONTHS[MONTHS.length - 1])) {
            ++year;
            month = MONTHS[0];
        } else {
            month = MONTHS[Arrays.asList(MONTHS).indexOf(month) + 1];
        }

        addContent();
    }

    public void returnToday() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        addContent();
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public Button getCurrentButton() {
        return currentButton;
    }

    public Paint getCurrentButtonPaint() {
        return currentButtonPaint;
    }
}