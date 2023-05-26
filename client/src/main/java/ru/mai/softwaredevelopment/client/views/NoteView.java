package ru.mai.softwaredevelopment.client.views;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class NoteView {
    public static void setNoteDisabled(VBox vBox) {
        vBox.setDisable(true);
    }

    public static void setNoteEnabled(VBox vBox) {
        vBox.setDisable(false);
    }

    public static void setNoteHeader(Label label, String string) {
        label.setText(string);
    }

    public static void setNote(TextArea textArea, String string) {
        textArea.setText(string);
    }
}